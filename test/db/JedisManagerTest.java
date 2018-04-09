package db;

import db.JedisManager;
import org.junit.Test;

public class JedisManagerTest {

  @Test
  public void testGetJedis() {
    JedisManager manager = new JedisManager();
    manager.execute(jedis -> {
      jedis.set("foo", "bar");
      System.out.println(jedis.get("foo"));
    });
    manager.finish();
  }
}
