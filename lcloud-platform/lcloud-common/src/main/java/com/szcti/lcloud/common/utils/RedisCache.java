package com.szcti.lcloud.common.utils;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.jedis.exceptions.JedisDataException;
import redis.clients.jedis.exceptions.JedisException;

/*
 * 使用第三方缓存服务器，处理二级缓存
 */

public class RedisCache implements Cache {
	
	private static final Logger log = LoggerFactory.getLogger(RedisCache.class);

		
	/** The ReadWriteLock. */
	private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	private String id;
	private static final int DB_INDEX = 1;
	private final String COMMON_CACHE_KEY = "LCLOUD:";
	private static final String UTF8 = "utf-8";

	private static int MAX_ACTIVE = 1024;

	// 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
	private static int MAX_IDLE = 200;

	// 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
	private static int MAX_WAIT = 10000;

	private static int TIMEOUT = 10000;

	// 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
	private static boolean TEST_ON_BORROW = true;

	private static JedisPool jedisPool = null;

	
	/**
	 * 获取Jedis实例
	 * 
	 * @return
	 */
	public Jedis getJedis() {
		try {
			if (jedisPool == null) {

				synchronized (this) {

					JedisPoolConfig config = new JedisPoolConfig();
					config.setMaxTotal(MAX_ACTIVE);
					config.setMaxIdle(MAX_IDLE);
					config.setMaxWaitMillis(MAX_WAIT);
					config.setTestOnBorrow(TEST_ON_BORROW);
					jedisPool = new JedisPool(config, new URI(SpringBootUtil.getEnvironment().getProperty("redis.url")), TIMEOUT);

				}
			}
			Jedis resource = jedisPool.getResource();
			return resource;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public RedisCache() {
		
	}
	/**
	 * 按照一定规则标识key
	 */
	private String getKey(Object key) {
		StringBuilder accum = new StringBuilder();
		accum.append(COMMON_CACHE_KEY);
		accum.append(this.id).append(":");
		accum.append(DigestUtils.md5Hex(String.valueOf(key)));
		return accum.toString();
	}

	/**
	 * redis key规则前缀
	 */
	private String getKeys() {
		return COMMON_CACHE_KEY + this.id + ":*";
	}

	
	public RedisCache(final String id) {
		if (id == null) {
			throw new IllegalArgumentException("必须传入ID");
		}
		log.debug(">>>>>>>>>>>>>>>>>>>>>MybatisRedisCache:id=" + id);
		this.id = id;
	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public int getSize() {
		Jedis jedis = null;
		int result = 0;
		boolean broken = false;
		try {
			jedis = getJedis();
			jedis.select(DB_INDEX);
			Set<byte[]> keys = jedis.keys(getKeys().getBytes(UTF8));
			if (null != keys && !keys.isEmpty()) {
				result = keys.size();
			}
			log.debug(this.id + "---->>>>总缓存数:" + result);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (JedisException e) {
			broken = handleJedisException(e);
			throw e;
		} finally {
			closeResource(jedis, broken);
		}
		return result;
	}

	@Override
	public void putObject(Object key, Object value) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			jedis.select(DB_INDEX);

			byte[] keys = getKey(key).getBytes(UTF8);
			jedis.set(keys, SerializeUtil.serialize(value));
			log.debug(">>>>>>>>>>>>>>>>>>>>>添加缓存--------" + this.id);
			// getSize();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (JedisException e) {
			broken = handleJedisException(e);
			throw e;
		} finally {
			closeResource(jedis, broken);
		}
	}

	@Override
	public Object getObject(Object key) {
		Jedis jedis = null;
		Object value = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			jedis.select(DB_INDEX);
			value = SerializeUtil.unserialize(jedis.get(getKey(key).getBytes(UTF8)));
			log.debug(">>>>>>>>>>>>>>>>>>>>>从缓存中获取-----" + this.id);
			// getSize();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (JedisException e) {
			broken = handleJedisException(e);
			throw e;
		} finally {
			closeResource(jedis, broken);
		}
		return value;
	}

	@Override
	public Object removeObject(Object key) {
		Jedis jedis = null;
		Object value = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			jedis.select(DB_INDEX);
			value = jedis.del(getKey(key).getBytes(UTF8));
			log.debug(">>>>>>>>>>>>>>>>>>>>>LRU算法从缓存中移除-----" + this.id);
			// getSize();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (JedisException e) {
			broken = handleJedisException(e);
			throw e;
		} finally {
			closeResource(jedis, broken);
		}
		return value;
	}

	@Override
	public void clear() {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			jedis.select(DB_INDEX);
			// 如果有删除操作，会影响到整个表中的数据，因此要清空一个mapper的缓存（一个mapper的不同数据操作对应不同的key）
			Set<byte[]> keys = jedis.keys(getKeys().getBytes(UTF8));
			log.debug(">>>>>>>>>>>>>>>>>>>>>出现CUD操作，清空对应Mapper缓存(" + this.id + ")======>" + keys.size());
			for (byte[] key : keys) {
				jedis.del(key);
			}
			// 下面是网上流传的方法，极大的降低系统性能，没起到加入缓存应有的作用，这是不可取的。
			// jedis.flushDB();
			// jedis.flushAll();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (JedisException e) {
			broken = handleJedisException(e);
			throw e;
		} finally {
			closeResource(jedis, broken);
		}
	}

	@Override
	public ReadWriteLock getReadWriteLock() {
		return readWriteLock;
	}

	public static void closeResource(Jedis jedis, boolean conectionBroken) {
		try {
			jedis.close();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public static boolean handleJedisException(JedisException jedisException) {
		if (jedisException instanceof JedisConnectionException) {
			System.out.println("Redis connection lost." + jedisException);
		} else if (jedisException instanceof JedisDataException) {
			if ((jedisException.getMessage() != null) && (jedisException.getMessage().indexOf("READONLY") != -1)) {
				System.out.println("Redis connection are read-only slave." + jedisException);
			} else {
				// dataException, isBroken=false
				return false;
			}
		} else {
			System.out.println("Jedis exception happen." + jedisException);
		}
		return true;
	}
}