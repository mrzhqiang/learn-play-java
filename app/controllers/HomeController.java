package controllers;

import models.APIKey;
import play.mvc.*;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
@Security.Authenticated
public class HomeController extends Controller {

  /**
   * An action that renders an HTML page with a welcome message.
   * The configuration in the <code>routes</code> file means that
   * this method will be called when the application receives a
   * <code>GET</code> request with a path of <code>/</code>.
   */
  public Result index() {
    APIKey apiKey = new APIKey();
    apiKey.id = (long) (Math.random() * 10000);
    apiKey.name = String.valueOf(Math.random() * 1000);
    apiKey.save();
    return ok(apiKey.toString());
  }
}
