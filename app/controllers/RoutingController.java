package controllers;

import models.Page;
import play.mvc.Controller;
import play.mvc.Result;

public class RoutingController extends Controller {

  private static final String IMAGES = "/assets/images/%s";
  private static final String JAVA_SCRIPTS = "/assets/javascripts/%s";
  private static final String STYLE_SHEETS = "/assets/stylesheets/%s";

  public Result index() {
    return redirect(controllers.routes.ActionController.req());
  }

  public Result list(String version) {
    return ok(version);
  }

  public Result clients(int page) {
    return ok(String.valueOf(page));
  }

  public Result show(String page) {
    String content = Page.getContentOf(page);
    return ok(content).as("text/html");
  }

  public Result items(Long id) {
    if (id <= 0) {
      id = 1L;
    }
    long result = (long) (1 + Math.random() * id);
    return ok("Random: " + result);
  }

  public Result download(String name) {
    if (name == null || name.length() == 0) {
      return badRequest("Name invalid");
    }

    String[] names = name.split("\\.");
    if (names.length <= 1) {
      return notFound("Name is " + name);
    }

    String suffix = names[1];
    switch (suffix) {
      case "png":
      case "jpg":
        return redirect(String.format(IMAGES, name));
      case "js":
        return redirect(String.format(JAVA_SCRIPTS, name));
      case "css":
        return redirect(String.format(STYLE_SHEETS, name));
      default:
        break;
    }
    return play.mvc.Results.TODO;
  }
}
