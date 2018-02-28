package controllers;

import authention.ClientAuthenticator;
import play.mvc.*;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
@Security.Authenticated(ClientAuthenticator.class)
public class HomeController extends Controller {

  /**
   * An action that renders an HTML page with a welcome message.
   * The configuration in the <code>routes</code> file means that
   * this method will be called when the application receives a
   * <code>GET</code> request with a path of <code>/</code>.
   */
  public Result index() {
    // TODO 一个华丽的主页
    return ok("欢迎您");
  }
}
