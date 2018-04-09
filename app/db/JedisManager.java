package db;

import db.util.CustomConfiguration;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.inject.Singleton;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Singleton
public final class JedisManager {

  private static final String REDIS_URL = "redis.url";
  private static final String REDIS_PORT = "redis.port";

  /** 这个连接池实例作为静态字段也是线程安全的 */
  private final JedisPool jedisPool;

  public JedisManager() {
    Map<String, String> redisConfig = CustomConfiguration.readOf(REDIS_URL, REDIS_PORT);
    JedisPoolConfig poolConfig = new JedisPoolConfig();
    jedisPool = new JedisPool(poolConfig, redisConfig.get(REDIS_URL),
        Integer.valueOf(redisConfig.get(REDIS_PORT)));
  }

  public void execute(@Nonnull JedisAction action) {
    try (Jedis resource = jedisPool.getResource()) {
      action.run(resource);
    }
  }

  /**
   * 直接获取实例不是一个好的做法，所以必须通过以下方式来调用：
   * <pre>
   * {@code Jedis jedis = null;
   *   try {
   *     jedis = jedisManager.getJedis();
   *     // do something..
   *   } finally {
   *     if (jedis !=null) {
   *       jedis.close();
   *     }
   *   }
   * }
   * </pre>
   */
  @Nonnull
  public Jedis getJedis() {
    return jedisPool.getResource();
  }

  public void finish() {
    jedisPool.close();
  }
}
