package com.cn.util.redis;

import redis.clients.jedis.ShardedJedis;

/**
 * Created by home on 2017/10/25.
 */
public interface RedisDataSource {

    public abstract ShardedJedis getRedisClient();
    public void returnResource(ShardedJedis shardedJedis);
    public void returnResource(ShardedJedis shardedJedis, boolean broken);
}

