package com.yvan;


import com.yvan.cache.RedisClient;
import redis.clients.jedis.Jedis;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by 46368 on 2018/7/31.
 */
public class RedisUtils {

    /**
     * 根据userID-获取Redis缓存中的会话ID(Uuid)
     * (手机端)Redis缓存Key:   (uuid:用户ID:企业ID:deecoop:userLoginMap:app)
     * (web端)Redis缓存Key:   (uuid:用户ID:企业ID:deecoop:userLoginMap:web)
     *
     * @param userID  系统用户ID
     * @return
     */
    public static String findRedisUuidByUserID(RedisClient redisClient, String userID, String loginType) {
        if (userID == null || userID.trim().length() == 0) {return null;}
        if (loginType == null) {loginType = new String();}

        Jedis jedis = null;
        try {
            String strTemp = new String("*:" + userID + ":*");
            if (loginType.trim().length() > 0) {
                strTemp = strTemp + ":" + loginType;
            }
            //strTemp := "*:userID:*:loginType"

            jedis = redisClient.getJedisPool().getResource();
            Set<String> keySet = jedis.keys(strTemp);
            if (keySet == null || keySet.size() == 0) {return null;}

            for (Iterator iterator = keySet.iterator(); iterator.hasNext();) {
                String key = (String) iterator.next();
                String[] strArry = key.split(":");
                if (strArry.length > 0) {return strArry[0];}
            }
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            if (jedis != null) jedis.close();
        }

        return null;
    }

    /**
     * 根据userID-移除Redis缓存数据
     * (手机端)Redis缓存Key:   (uuid:用户ID:企业ID:deecoop:userLoginMap:mobile)
     * (web端)Redis缓存Key:   (uuid:用户ID:企业ID:deecoop:userLoginMap:web)
     *
     * @param userID
     */
    public static void removeByUserID(RedisClient redisClient, String userID, String loginType) {
        if (userID == null || userID.trim().length() == 0) {return;}

        Jedis jedis = null;
        try {
            String strTemp = new String("*:" + userID + ":*");
            if (loginType.trim().length() > 0) {
                strTemp = strTemp + ":" + loginType;
            }
            //strTemp(*:userID:*:loginType)

            jedis = redisClient.getJedisPool().getResource();
            Set<String> keySet = jedis.keys(strTemp);
            for (Iterator iterator = keySet.iterator(); iterator.hasNext();) {
                String key = (String) iterator.next();
                if(jedis.exists(key)){
                    jedis.del(key);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 根据uuid-移除Redis缓存数据
     * @param uuid
     */
    public static void removeByUuid(RedisClient redisClient, String uuid) {
        if (uuid == null || uuid.trim().length() == 0) {return;}

        Jedis jedis = null;
        try {
            jedis = redisClient.getJedisPool().getResource();
            Set<String> keySet = jedis.keys(uuid + "*");
            for (Iterator iterator = keySet.iterator(); iterator.hasNext();) {
                String key = (String) iterator.next();
                if(jedis.exists(key)){
                    jedis.del(key);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public static void removeByCode(RedisClient redisClient, String code) {
        if (code == null || code.trim().length() == 0) {return;}

        Jedis jedis = null;
        try {
            jedis = redisClient.getJedisPool().getResource();
            Set<String> keySet = jedis.keys("*" + code + "*");
            for (Iterator iterator = keySet.iterator(); iterator.hasNext();) {
                String key = (String) iterator.next();
                if(jedis.exists(key)){
                    jedis.del(key);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public static String getRedisJsonStringBySessionID(RedisClient redisClient, String sessionID) {
        Jedis jedis = null;
        String sessionJson = "";
        try {
            jedis = redisClient.getJedisPool().getResource();
            sessionJson = jedis.get(sessionID);
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            if (jedis != null)
                jedis.close();
        }
        return sessionJson;
    }
}
