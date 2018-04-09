package db.util;

import org.junit.Test;

public class CustomConfigurationTest {

  @Test
  public void testReadProperties() {
    System.out.println(CustomConfiguration.readProperties());
  }

  @Test
  public void testReadRedis() {
    System.out.println(CustomConfiguration.readOf("redis.url", "redis.port"));
  }
}
