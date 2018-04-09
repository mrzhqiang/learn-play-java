package db;

import javax.annotation.Nonnull;
import redis.clients.jedis.Jedis;

/** 用来提供给 JedisManager 省却模板方法的接口 */
public interface JedisAction {
  void run(@Nonnull Jedis jedis);
}
