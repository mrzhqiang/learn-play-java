package controllers;

import play.mvc.Controller;
import play.mvc.Result;

/** Session and Flash scopes */
public class SessionController extends Controller {

  public Result flashScala() {
    return ok(views.html.flash.render(flash()));
  }

  public Result flashIndex() {
    String message = flash("success");
    if (message == null) {
      return ok("Welcome!");
    }
    return created(message);
  }

  public Result flashScope() {
    flash("success", "The item has been created");
    if (Math.random() > 0.5) {
      return redirect("/session/flashScala");
    }
    return redirect("/session/flashIndex");
  }

  public Result discardSession() {
    session().clear();
    return ok("Session is clear.");
  }

  public Result readSession() {
    String user = session("connected");
    if (user != null) {
      return ok("Hello " + user);
    }
    return unauthorized("Oops, you are not connected!");
  }

  public Result logout() {
    String email = session().remove("connected");
    return ok("Bye:" + email);
  }

  public Result login() {
    session("connected", "user@gmail.com");
    return ok("Welcome!");
  }
}
