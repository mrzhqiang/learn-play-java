package models.utils;

import models.Client;
import org.junit.Assert;
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
    Client browserClient = Clients.of("Browser", "浏览器通用的客户端API Key。");
    Client androidClient = Clients.of("Android", "安卓手机通用的客户端API Key。");
    System.out.println(browserClient);
    System.out.println(androidClient);
    Assert.assertNotEquals(browserClient, androidClient);
  }
}
