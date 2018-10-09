package com.szcti.lcloud.user.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.szcti.lcloud.common.utils.R;
import com.szcti.lcloud.common.utils.RedisUtil;

import redis.clients.jedis.Jedis;

@Component
@Path("test")
public class TestRedisApi {


	@Autowired
	private RedisUtil redisUtil;

	

	/**redis缓存
	 * 添加数据到缓存中
	 * 
	 * @param data
	 * @return
	 */
	@Path("/redis")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public R redis(String data) {
		try {
			String t = System.currentTimeMillis() + "";

			//添加有过期时间的记录
			redisUtil.strings().setEx(t, 600, t);
			
			//直接保存对象记录
			redisUtil.objects().setObject("sobj", t);
			Object sobj = redisUtil.objects().getObject("sobj");

			
			String s = redisUtil.strings().get(t);

			Map<String, String> map = new HashMap<>();
			map.put("1", "1");
			map.put("2", "sfasdfa");
			map.put("3", "第三条数据");
			
			//保存hash记录
			redisUtil.hash().hmset("map", map);
			List<String> ls = redisUtil.hash().hmget("map", "3");
			
			redisUtil.objects().setObject("obj", map);
			Object ob = redisUtil.objects().getObject("obj");

			return R.ok().put("s", s).put("sobj", sobj).put("ob", ob).put("ls", ls);

		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}
	
	/**
	 * 清空表缓存 
	 * @return
	 */
	@Path("/flushCache")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public R flushCache() {
		Jedis jedis = redisUtil.getJedis();
		jedis.select(1);
		String s = jedis.flushDB();
		return R.ok().put("msg", s);
	}
}
