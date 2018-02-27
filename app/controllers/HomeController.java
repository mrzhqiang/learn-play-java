package controllers;

import models.Client;
import play.mvc.*;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

  /**
   * An action that renders an HTML page with a welcome message.
   * The configuration in the <code>routes</code> file means that
   * this method will be called when the application receives a
   * <code>GET</code> request with a path of <code>/</code>.
   */
  public Result index() {
    Client client = new Client();
    client.name = "Android";
    client.description = "这是安卓客户端的通用APIKey";
    client.save();
    return ok(client.toString());
  }
}
