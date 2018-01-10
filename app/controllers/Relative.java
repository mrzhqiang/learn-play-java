package controllers;

import play.mvc.Controller;
import play.mvc.Result;

public class Relative extends Controller {

  public Result helloview() {
    return ok(views.html.hello.render("Bob", request()));
  }

  public Result hello(String name) {
    return ok("Hello " + name + "!");
  }
}
