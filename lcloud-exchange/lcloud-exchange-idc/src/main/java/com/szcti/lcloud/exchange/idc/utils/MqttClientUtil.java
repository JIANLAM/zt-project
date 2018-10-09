package com.szcti.lcloud.exchange.idc.utils;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MqttClientUtil {
	
	private static MqttClient instance = null;
	private static MqttConnectOptions options;

	private MqttClientUtil() {
	}

	private static String broker;
	
    private static String clientId;
	
    private static String username;
	
	
    private static String password;
    
	/**
	 * 返回MqttClient的单例
	 * 
	 * @return
	 */
	public static MqttClient getInstance() {
		if (instance == null) {
//			synchronized (MqttClientUtil.class) {
				try {
//					if (instance == null) {
						instance = new MqttClient(broker, clientId,new MemoryPersistence());
//					}
					options = new MqttConnectOptions();
					// 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录(支持离线消息)
					//这里设置为true表示每次连接到服务器都以新的身份连接
					options.setCleanSession(false);
					options.setUserName(username);
					options.setPassword(password.toCharArray());
					// 设置超时时间 单位为秒
					options.setConnectionTimeout(10);
					// 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
					options.setKeepAliveInterval(20);
				} catch (MqttException e) {
					e.printStackTrace();
				}
//			}
		}
		return instance;
	}

	/**
	 * 发布消息
	 * @param topic 发送的主题
	 * @param content 消息的文本
	 */
	public static void publish(String topic, String content) {
		MqttTopic mqttTopic = getInstance().getTopic(topic);
		MqttMessage message = new MqttMessage();
		message.setQos(2);
		message.setRetained(false);
		message.setPayload(content.getBytes());
		try {
			// 发布MqttMessege消息
			mqttTopic.publish(message);
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}

	public static MqttConnectOptions getOptions() {
		return options;
	}
	
	@Value("${emq.clientId}")
	public void setClientId(String clientId) {
		MqttClientUtil.clientId = clientId;
	}
	
    @Value("${emq.broker}")
	public void setBroker(String broker) {
		MqttClientUtil.broker = broker;
	}

	@Value("${emq.username}")
	public void setUsername(String username) {
		MqttClientUtil.username = username;
	}
	
	@Value("${emq.password}")
	public void setPassword(String password) {
		MqttClientUtil.password = password;
	}
}
