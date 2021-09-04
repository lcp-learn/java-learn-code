package com.lcp.learn.spring.spb.check.other.services.impls;

import com.lcp.learn.spring.spb.check.other.services.LockService;
import javax.annotation.Resource;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * desc:    <br/>
 * @since 2020/10/15-16:16
 * @author lichunpeng
 */
@Service
public class LockServiceImpl implements LockService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final String keyPrefix = "lock-";

    @Resource
    private RedissonClient redissonClient;


    @Override
    public int updateData(int sourceData, int target) {

        RLock rLock = redissonClient.getLock(keyPrefix + sourceData);
        rLock.lock();
        String threadName = Thread.currentThread().getName();
        logger.info("thread:{},update {} to {}", threadName, sourceData, target);
        rLock.unlock();

        return target;
    }
}
