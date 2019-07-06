package com.netease.ssm.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * Created by bjzhangxicheng on 2017/3/10.
 */
public class RedisUtil {
	protected static Logger logger = Logger.getLogger(RedisUtil.class);

	//Redis??????IP
	//private static String host = "10.112.157.244";
	//private static String host = "127.0.0.1";
	private static String host = "10.130.65.112";
	
	//Redis?????
	//private static int port = 6379;
	private static int port = 19701;
	//????????
	private static String AUTH = "test";

	//??????????????????????????8??
	//???????-1????????????????pool?????????maxActive??jedis?????????pool?????exhausted(???)??
	private static int MAX_ACTIVE = 1024;

	//???????pool????§Ø???????idle(???§Ö?)??jedis????????????8??
	private static int MAX_IDLE = 200;

	//????????????????????¦Ë?????????-1???????????????????????????????????JedisConnectionException??
	private static int MAX_WAIT = 10000;

	private static int TIMEOUT = 10000;

	//??borrow???jedis????????????????validate??????????true????????jedis????????????
	private static boolean TEST_ON_BORROW = true;

	private static JedisPool jedisPool = null;

	/**
	 * ?????????
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
	 * ???Jedis???
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
	 * ???jedis???
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
	 * ??list?????????
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
			//???redis????
			jedisPool.returnBrokenResource(jedis);
			logger.error(String.format("redis setList error ! key:%s,value:%s",key,value),e);
		} finally {
			//???????????
			close(jedis);
		}
		return i;
	}

	/**
	 * ????key??§¹???
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
			//???redis????
			jedisPool.returnBrokenResource(jedis);
			logger.error(String.format("redis setList error ! key:%s,second:%s",key,second),e);
		} finally {
			//???????????
			close(jedis);
		}
		return i;
	}

	/**
	 * ????list?????????
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
			//???redis????
			jedisPool.returnBrokenResource(jedis);
			logger.error(String.format("redis rpopList error ! key:%s",key),e);
		} finally {
			//???????????
			close(jedis);
		}
		return  value;
	}

	/**
	 * ???????
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
			//???redis????
			jedisPool.returnBrokenResource(jedis);
			logger.error(String.format("redis get key error ! key:%s",key),e);
		} finally {
			//???????????
			close(jedis);
		}
		return value;
	}

	/**
	 * ??????
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
			//???redis????
			jedisPool.returnBrokenResource(jedis);
			logger.error(String.format("redis get key error ! key:%s",key),e);
		} finally {
			//???????????
			close(jedis);
		}
		return i;
	}

	/**
	 * ???list?§Ö?????value
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
			//???redis????
			jedisPool.returnBrokenResource(jedis);
			logger.error(String.format("redis getListAll error ! key:%s",key),e);
		} finally {
			//???????????
			close(jedis);
		}
		return list;
	}

	/**
	 * ??????????????key
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
			//???redis????
			jedisPool.returnBrokenResource(jedis);
			logger.error(String.format("redis getPatternKeys error ! pattern:%s",pattern),e);
		} finally {
			//???????????
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
				logger.info(THREAD_LOCK+"  ?§Õ??timeStr??:"+timeStr);
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
			//???????????
			close(jedis);
		}
	}

	public static String getMd5(String plainText, int digit) {
		String result = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			if (digit == 32)
				result = buf.toString();
			else
				result = buf.toString().substring(8, 24);
		} catch (NoSuchAlgorithmException e) {

		}
		return result;
	}


	public static void main(String[] args) {

		Jedis jedis = getJedis();
		/*Set<String> set = new HashSet<>();
		String[] setString = new String[]{"uuuu","oooo"};
		long r = jedis.sadd("hobbys",setString);*/
		System.out.println(jedis.scard("hobbys"));
		System.out.println(jedis.smembers("hobbys"));
		System.out.println(jedis.sismember("hobbys","11"));

		System.out.println(getMd5("zxc",32));
		System.out.println(DigestUtils.md5Hex("zxc"));
		System.out.println(getMd5("zxc",16));


		IntStream.range(1, 320000).forEach(i -> {
			jedis.sadd("binbin",RandomStringUtils.randomAlphabetic(8));
			System.out.println(jedis.scard("binbin"));
		});
	}
}
