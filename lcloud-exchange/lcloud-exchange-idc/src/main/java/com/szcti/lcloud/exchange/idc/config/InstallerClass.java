package com.szcti.lcloud.exchange.idc.config;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.szcti.lcloud.exchange.idc.service.impl.PushCallback;
import com.szcti.lcloud.exchange.idc.utils.MqttClientUtil;

@Component
public class InstallerClass implements CommandLineRunner{

	@Override
	public void run(String... args) throws Exception {
		MqttClient client = MqttClientUtil.getInstance();
		client.setCallback(new PushCallback());
		client.connect(MqttClientUtil.getOptions());
		//订阅消息  
//        int[] Qos  = {2};
//        String[] topic = {"sap-new-book","order"};
		String[] topic = {"order"};
		client.subscribe(topic);
	}

}
