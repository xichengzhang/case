package com.netease.ssm.util;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by bjzhangxicheng on 2017/3/10.
 */
public class RedisUtil {
	protected static Logger logger = Logger.getLogger(RedisUtil.class);

	//Redis服务器IP
	//private static String host = "10.112.157.244";
	//private static String host = "127.0.0.1";
	private static String host = "220.181.29.165";
	
	//Redis的端口号
	//private static int port = 6379;
	private static int port = 8888;
	//访问密码
	private static String AUTH = null;

	//可用连接实例的最大数目，默认值为8；
	//如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
	private static int MAX_ACTIVE = 1024;

	//控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
	private static int MAX_IDLE = 200;

	//等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
	private static int MAX_WAIT = 10000;

	private static int TIMEOUT = 10000;

	//在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
	private static boolean TEST_ON_BORROW = true;

	private static JedisPool jedisPool = null;

	/**
	 * 建立连接池
	 *
	 */
	private static void createJedisPool() {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxActive(MAX_ACTIVE);
		config.setMaxIdle(MAX_IDLE);
		config.setMaxWait(MAX_WAIT);
		config.setTestOnBorrow(TEST_ON_BORROW);
		if(StringUtils.isNotBlank(AUTH)) {
			jedisPool = new JedisPool(config, host, port, TIMEOUT,AUTH);
		}else{
			jedisPool = new JedisPool(config, host, port, TIMEOUT);
		}

	}

	/**
	 * 获取Jedis实例
	 *
	 * @return
	 */
	public synchronized static Jedis getJedis() {
		if (jedisPool == null) {
			createJedisPool();
		}
		Jedis jedis = jedisPool.getResource();
		return jedis;

	}

	/**
	 * 释放jedis资源
	 *
	 * @param jedis
	 */
	public static void close(final Jedis jedis) {
		if (jedis != null) {
			try {
				jedisPool.returnResource(jedis);
			} catch (Exception e) {
				if (jedis.isConnected()) {
					jedis.quit();
					jedis.disconnect();
				}
				logger.error("close jedis error",e);
			}
		}
	}

	/**
	 * 往list里面放数据
	 * @param key
	 * @param value
	 * @return
	 */
	public static long setList (String key,String value){

		Jedis jedis = null;
		long i = 0;
		try {
			jedis = getJedis();
			i = jedis.lpush(key,value);
		} catch (Exception e) {
			//释放redis对象
			jedisPool.returnBrokenResource(jedis);
			logger.error(String.format("redis setList error ! key:%s,value:%s",key,value),e);
		} finally {
			//返还到连接池
			close(jedis);
		}
		return i;
	}

	/**
	 * 设置key值失效时间
	 * @param key
	 * @param second
	 * @return
	 */
	public static long expireKeyTime (String key,int second){
		Jedis jedis = null;
		long i = 0;
		try {
			jedis = getJedis();
			i = jedis.expire(key,second);
		} catch (Exception e) {
			//释放redis对象
			jedisPool.returnBrokenResource(jedis);
			logger.error(String.format("redis setList error ! key:%s,second:%s",key,second),e);
		} finally {
			//返还到连接池
			close(jedis);
		}
		return i;
	}

	/**
	 * 弹出list最后一个元素
	 * @param key
	 * @return
	 */
	public static String rpopList (String key){
		Jedis jedis = null;
		String value = null;
		try {
			jedis = getJedis();
			value = jedis.rpop(key);
		} catch (Exception e) {
			//释放redis对象
			jedisPool.returnBrokenResource(jedis);
			logger.error(String.format("redis rpopList error ! key:%s",key),e);
		} finally {
			//返还到连接池
			close(jedis);
		}
		return  value;
	}

	/**
	 * 获取数据
	 * @param key
	 * @return
	 */
	public static String getValue(String key) {
		String value = null;
		Jedis jedis = null;
		try {
			jedis = getJedis();
			value = jedis.get(key);
		} catch (Exception e) {
			//释放redis对象
			jedisPool.returnBrokenResource(jedis);
			logger.error(String.format("redis get key error ! key:%s",key),e);
		} finally {
			//返还到连接池
			close(jedis);
		}
		return value;
	}

	/**
	 * 存数据
	 * @param key
	 * @param value
	 * @return
	 */
	public static long setKey(String key,String value) {
		Jedis jedis = null;
		long i = 0;
		try {
			jedis = getJedis();
			value = jedis.set(key, value);
		} catch (Exception e) {
			//释放redis对象
			jedisPool.returnBrokenResource(jedis);
			logger.error(String.format("redis get key error ! key:%s",key),e);
		} finally {
			//返还到连接池
			close(jedis);
		}
		return i;
	}

	/**
	 * 获取list中的所有value
	 * @param key
	 * @return
	 */
	public static List<String> getListAll (String key){
		Jedis jedis = null;
		List<String> list = null;
		try {
			jedis = getJedis();
			list = jedis.lrange(key,0,-1);
		} catch (Exception e) {
			//释放redis对象
			jedisPool.returnBrokenResource(jedis);
			logger.error(String.format("redis getListAll error ! key:%s",key),e);
		} finally {
			//返还到连接池
			close(jedis);
		}
		return list;
	}

	/**
	 * 根据匹配获取响应的key
	 * @param pattern
	 * @return
	 */
	public static Set<String> getPatternKeys(String pattern) {
		Jedis jedis = null;
		Set<String> s  = null;
		try {
			jedis = getJedis();
			s = jedis.keys(pattern);
		} catch (Exception e) {
			//释放redis对象
			jedisPool.returnBrokenResource(jedis);
			logger.error(String.format("redis getPatternKeys error ! pattern:%s",pattern),e);
		} finally {
			//返还到连接池
			close(jedis);
		}
		return s;
	}

	public static  boolean checkWhetherCanGetLock(String THREAD_LOCK,Integer expireSeconds){
		Jedis jedis = null;
		try{
			jedis = getJedis();
			expireSeconds =expireSeconds==null  ? 60*3 : expireSeconds;
			long result = jedis.setnx(THREAD_LOCK, String.valueOf(new Date().getTime()));
			if(result==1){  //get the lock
				return true;
			}else{
				String timeStr = jedis.get(THREAD_LOCK);
				if(StringUtils.isBlank(timeStr)){
					//synchronized (JobUtil.class) {
					long r2 = jedis.setnx(THREAD_LOCK, String.valueOf(new Date().getTime()));
					if(r2==1) {
						return true;
					}
					//}
					timeStr = jedis.get(THREAD_LOCK);
				}
				logger.info(THREAD_LOCK+"  中存的timeStr数:"+timeStr);
				long originTime = Long.parseLong(timeStr);
				long nowTime = new Date().getTime();
				if(Math.abs(nowTime-originTime)>expireSeconds*1000){//has already expire
					String oldTimeStr = jedis.getSet(THREAD_LOCK, String.valueOf(new Date().getTime()));
					logger.error("oldTimeStr"+oldTimeStr);
					if(StringUtils.isNotBlank(oldTimeStr)){ //check whether it is expire or not
						long oldTime = Long.parseLong(oldTimeStr);
						if(originTime==oldTime){
							return true;
						}else{
							return false;
						} 
					}else{//execute the job
						return true;
					}
				}else{
					return false;
				}
			}



		}catch(Exception e){
			logger.error("checkWhetherCanGetLock error",e);
			return true;
		} finally {
			//返还到连接池
			close(jedis);
		}
	}


	public static void main(String[] args) {

	}
}
