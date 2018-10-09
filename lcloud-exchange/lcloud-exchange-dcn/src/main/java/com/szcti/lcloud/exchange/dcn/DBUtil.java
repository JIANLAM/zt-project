package com.szcti.lcloud.exchange.dcn;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import oracle.jdbc.driver.OracleConnection;
import oracle.jdbc.pool.OracleDataSource;

@Component
public class DBUtil {

	@Value("${spring.datasource.oracle.username}")
	String user;
	@Value("${spring.datasource.oracle.password}")
	String password;
	@Value("${spring.datasource.oracle.url}")
	String url;

	static HikariDataSource ds = null;
	static JdbcTemplate jt = null;

	public HikariDataSource DS() {
		if (ds == null) {

			synchronized (this) {
				HikariConfig config = new HikariConfig();
				config.setJdbcUrl(url);
				config.setUsername(user);
				config.setPassword(password);
				config.addDataSourceProperty("cachePrepStmts", "true");
				config.addDataSourceProperty("prepStmtCacheSize", "250");
				config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
				ds = new HikariDataSource(config);
			}
		}
		return ds;
	}

	public OracleDataSource getDataSource() {
		OracleDataSource dataSource = null;
		try {
			dataSource = new OracleDataSource();
			dataSource.setUser(user);
			dataSource.setPassword(password);
			dataSource.setURL(url);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dataSource;
	}

	public OracleConnection getOracleConnection() {
		OracleConnection conn = null;
		try {
			conn = (OracleConnection) getDataSource().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public JdbcTemplate getJdbcTemplate() {
		if (jt == null) {
			synchronized (this) {
				jt = new JdbcTemplate(DS());
			}
		}
		return jt;
	}
}
