package controllers;

import play.mvc.*;

/**
 * 这里是主页控制器，通常是程序的入口，提供一个页面分发功能。
 *
 * @author mrZQ
 */
public class HomeController extends Controller {

  /**
   * 主页，通常是以 index 命名，这样通过浏览器访问域名时，会自动加载这个页面。
   */
  public Result index() {
    // 在 Play 的views包下，有一堆html文件，可以通过下面这样的方式来引用。
    return ok(views.html.index.render());
  }
}
