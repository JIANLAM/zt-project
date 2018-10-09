package com.szcti.lcloud.exchange.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.szcti.lcloud.exchange.dcn.SyncData;

@RestController
@RequestMapping("sync")
public class SyncController {

	public static int isSync1 = 0;
	public static int isSync2 = 0;
	public static int isSync3 = 0;
	@Autowired
	SyncData syncData;

	@GetMapping("/user")
	public String user(@RequestParam(defaultValue = "1", required = false) int limit) {

		if (isSync1 == 0) {

			isSync1 = 1;

			new Thread(new Runnable() {

				@Override
				public void run() {

					syncData.syncREADER(limit);

				}
			}).start();
			;

			return "已启动同步，请等待同步完成。";

		} else {
			return "同步中，请等待同步完成。";
		}

	}

	@GetMapping("/book")
	public String book(@RequestParam(defaultValue = "1", required = false) int limit) {

		if (isSync2 == 0) {

			isSync2 = 1;

			new Thread(new Runnable() {

				@Override
				public void run() {
					syncData.syncBIBLIOS(limit);
				}
			}).start();
			;

			return "已启动同步，请等待同步完成。";

		} else {
			return "同步中，请等待同步完成。";
		}

	}

	@GetMapping("/hold")
	public String hold(@RequestParam(defaultValue = "1", required = false) int limit) {

		if (isSync3 == 0) {

			isSync3 = 1;
			new Thread(new Runnable() {

				@Override
				public void run() {

					syncData.syncHOLDING(limit);

				}
			}).start();
			;

			return "已启动同步，请等待同步完成。";

		} else {
			return "同步中，请等待同步完成。";
		}

	}

}
