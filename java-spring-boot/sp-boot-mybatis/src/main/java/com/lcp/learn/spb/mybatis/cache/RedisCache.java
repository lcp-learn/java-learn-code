package com.lcp.learn.spb.mybatis.cache;

import com.lcp.learn.spb.mybatis.components.SpringContextHolder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.cache.CacheException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * desc:    <br/>
 * @since 2020/10/13-18:08
 * @author lichunpeng
 */
public class RedisCache implements Cache {

    private static final Logger logger = LoggerFactory.getLogger(RedisCache.class);
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final String id; // cache instance id
    private static final int EXPIRE_TIME_IN_MINUTES = 30; // redis过期时间

    private HashOperations<String, String, byte[]> hashOperations;

    public RedisCache(String id) {
        if (id == null) {
            throw new IllegalArgumentException("Cache instances require an ID");
        }
        this.id = id;

        try {
            RedisTemplate<String, Object> redisTemplate = SpringContextHolder.getBean("redisTemplate");
            hashOperations = redisTemplate.opsForHash();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public String getId() {
        return id;
    }

    /**
     * Put query result to redis
     *
     * @param key
     * @param value
     */
    @Override
    @SuppressWarnings("unchecked")
    public void putObject(Object key, Object value) {

        hashOperations.put(id, key.toString(), serialize(value));
      // hashOperations.put(id, key.toString(), JSON.toJSONString(value));
        logger.debug("Put query result to redis");
    }

    /**
     * Get cached query result from redis
     *
     * @param key
     * @return
     */
    @Override
    public Object getObject(Object key) {
        logger.debug("Get cached query result from redis");
        byte[] result = hashOperations.get(id, key.toString());
        return unserialize(result);
        // return JSONObject.parse(result);
    }

    /**
     * Remove cached query result from redis
     *
     * @param key
     * @return
     */
    @Override
    @SuppressWarnings("unchecked")
    public Object removeObject(Object key) {
        logger.debug("Remove cached query result from redis");
        return hashOperations.delete(id, key.toString());
    }

    /**
     * Clears this cache instance
     */
    @Override
    public void clear() {
        logger.debug("Clear all the cached query result from redis");
        hashOperations.delete(id);

    }

    @Override
    public int getSize() {
        return hashOperations.size(id).intValue();

    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return readWriteLock;
    }

    public byte[] serialize(Object object) {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (Exception e) {
            throw new CacheException(e);
        }
    }

    public Object unserialize(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        ByteArrayInputStream bais = null;
        try {
            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
            throw new CacheException(e);
        }
    }

}
