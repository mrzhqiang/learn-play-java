package utils;

import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.test.WithApplication;

public class ClientsTest extends WithApplication {

  @Override protected Application provideApplication() {
    return new GuiceApplicationBuilder().build();
  }

  @Test
  public void testClient() {
    System.out.println(Clients.of("Browser", "这是浏览器通用的客户端API Key。"));
  }
}
