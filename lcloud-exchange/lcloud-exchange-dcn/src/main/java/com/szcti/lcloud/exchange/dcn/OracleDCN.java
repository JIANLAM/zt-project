package com.szcti.lcloud.exchange.dcn;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.szcti.lcloud.exchange.service.DcnSycnService;

import oracle.jdbc.OracleStatement;
import oracle.jdbc.dcn.DatabaseChangeEvent;
import oracle.jdbc.dcn.DatabaseChangeListener;
import oracle.jdbc.dcn.DatabaseChangeRegistration;
import oracle.jdbc.dcn.RowChangeDescription;
import oracle.jdbc.dcn.RowChangeDescription.RowOperation;
import oracle.jdbc.dcn.TableChangeDescription;
import oracle.jdbc.driver.OracleConnection;
import oracle.sql.ROWID;

@Component
@Order(1)
public class OracleDCN implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(OracleDCN.class);

	@Autowired
	DBUtil dbUtil;

	@Autowired
	DcnSycnService dcnSycnService;

	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	DatabaseChangeListener dcl = new DatabaseChangeListener() {

		@Override
		public void onDatabaseChangeNotification(DatabaseChangeEvent databaseChangeEvent) {
			TableChangeDescription[] tds = databaseChangeEvent.getTableChangeDescription();
			log.info("=============================" + df.format(new Date()) + "=============================");
			// log.info("TableChangeDescription(数据表的变化次数):" + tds.length);
			JdbcTemplate oracleJt = dbUtil.getJdbcTemplate();

			for (TableChangeDescription td : tds) {
				log.info("数据库表id：" + td.getObjectNumber());
				log.info("数据表名称：" + td.getTableName());
				String tableName = td.getTableName();

				// 获得返回的行级变化描述通知 行id、影响这一行的DML操作(行是插入、更新或删除的一种)
				RowChangeDescription[] rds = td.getRowChangeDescription();
				for (RowChangeDescription rd : rds) {
					try {

						RowOperation rowOperation = rd.getRowOperation();
						log.info("操作：" + rowOperation.toString());
						String oper = rowOperation.toString();
						ROWID rowid = rd.getRowid();
						log.info("ROWID： " + rowid.stringValue());

						String topic = "dcn/" + tableName + "/" + oper;

						if ("UPDATE".equalsIgnoreCase(oper) || "INSERT".equalsIgnoreCase(oper)) {
							Map<String, Object> map = oracleJt.queryForMap(
									"select * from " + tableName + " where rowid='" + rowid.stringValue() + "'");
							map.put("ROWID", rowid.stringValue());

							String content = JsonUtil.toJSONString(map);
							// mqttClientUtil.publish(topic, content);
							log.info("publish msg::  topic: {}, content: {}", topic, content);
							dcnSycnService.handle(topic, content,0);

						} else if ("DELETE".equalsIgnoreCase(oper)) {
							Map<String, Object> map = new HashMap<>();
							map.put("ROWID", rowid.stringValue());

							String content = JsonUtil.toJSONString(map);
							// mqttClientUtil.publish(topic, content);
							log.info("publish msg::  topic: {}, content: {}", topic, content);
							dcnSycnService.handle(topic, content, 0);

						} else {
							log.info("unknow Operation" + oper);
						}

					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			}
		}
	};

	@Override
	public void run(String... args) throws Exception {

		unregisterDatabaseChangeNotification();
		registerDatabaseChangeNotification();
	}

	void unregisterDatabaseChangeNotification() {
		try {
			OracleConnection conn = dbUtil.getOracleConnection();
			OracleStatement stat = (OracleStatement) conn.createStatement();
			// stat.execute("select REGID from DBA_CHANGE_NOTIFICATION_REGS");
			ResultSet rs = stat.executeQuery("select REGID from DBA_CHANGE_NOTIFICATION_REGS");
			while (rs.next()) {
				int regid = rs.getInt("REGID");
				log.debug("取消注册id：" + regid);
				conn.unregisterDatabaseChangeNotification(regid);
			}

			rs.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	void registerDatabaseChangeNotification() {
		Properties prop = new Properties();
		prop.setProperty(OracleConnection.DCN_NOTIFY_ROWIDS, "true");// 要取得更改记录的rowid
		// 设置超时，这里是1个小时，届时数据库和驱动器的资源自动释放。
		// 如果为0或不设置，则用不过期，直到程序停止监听，当数据库发送更新通知时，因为没有监听端口，数据库随后释放资源
		prop.setProperty(OracleConnection.NTF_TIMEOUT, "0");
		DatabaseChangeRegistration databaseChangeRegistration;
		try {

			OracleConnection conn = dbUtil.getOracleConnection();
			databaseChangeRegistration = conn.registerDatabaseChangeNotification(prop);

			databaseChangeRegistration.addListener(dcl);
			OracleStatement statement = (OracleStatement) conn.createStatement();
			statement.setDatabaseChangeRegistration(databaseChangeRegistration);
			statement.executeQuery("select * from HOLDING t where 1=2");
			statement.executeQuery("select * from READER t where 1=2");
			statement.executeQuery("select * from BIBLIOS t where 1=2");
			statement.close();
			conn.close();
			log.info("数据库更改通知开启...");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
