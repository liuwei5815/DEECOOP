package com.yvan.cache;

import com.yvan.YvanUtil;
import org.apache.commons.lang.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by 46368 on 2018/7/18.
 */
public class RedisClient {
    private JedisPool jedisPool;

    public void set(String key, String value) throws Exception {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set(key, value);
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception(e);
        }finally {
            jedis.close();
        }
    }

    /**
     * 设置过期时间
     * @param key
     * @param value
     * @param exptime
     * @throws Exception
     */
    public void setWithExpireTime(String key, String value, int exptime) throws Exception {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
//            jedis.set(key, value, "NX", "EX", exptime);
            jedis.set(key, value);
            jedis.pexpire(key, exptime);
        } catch (Exception e){
            e.printStackTrace();
            throw new Exception(e);
        }finally {
            jedis.close();
        }
    }

    public String get(String key) throws Exception {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.get(key);
        } catch (Exception e){
            e.printStackTrace();
            throw new Exception(e);
        }finally {
            if (jedis != null)
                jedis.close();
        }
    }


    public JedisPool getJedisPool() {
        return jedisPool;
    }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }
}
