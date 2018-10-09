package com.szcti.lcloud.exchange.idc.service.impl;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.szcti.lcloud.exchange.idc.utils.MqttClientUtil;

public class PushCallback implements MqttCallback {
	private static final Logger log = LoggerFactory.getLogger(PushCallback.class);
	
	private ScheduledExecutorService scheduler;

	@Override
	public void connectionLost(Throwable cause) {
		MqttClient client = MqttClientUtil.getInstance();
		//尝试重连
		log.debug("connection lost.........");
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(new Runnable() {
            public void run() {
                if (!client.isConnected()) {
                    try {
                    	log.debug("connecting.........");
                        client.connect(MqttClientUtil.getOptions());
                    } catch (MqttSecurityException e) {
                        e.printStackTrace();
                    } catch (MqttException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, 0 * 1000, 10 * 1000, TimeUnit.MILLISECONDS);
	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		log.debug("接收消息主题:"+topic);
        log.debug("接收消息Qos:"+message.getQos());
        log.debug("接收消息内容:"+new String(message.getPayload()));
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
		// publish后会执行到这里
		log.debug("deliveryComplete---------"+ token.isComplete());
	}

}
