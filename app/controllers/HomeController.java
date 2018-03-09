package controllers;

import authentions.ClientAuthenticator;
import java.io.File;
import play.mvc.*;

/**
 * 这里是主页控制器，通常是程序的入口，提供一个页面分发功能。
 *
 * @author mrZQ
 * @see Security.Authenticator 默认的权限认证器，用来保持登陆状态
 * @see ClientAuthenticator 客户端级别的权限认证器，用来简单地防止接口盗用
 */
public class HomeController extends Controller {

  /*这里只是测试用的方法，未来将删除它*/
  public Result favicon() {
    // 可以通过这种方式，返回一个服务器既定资源
    File file = new File("./public/images/favicon.png");
    if (!file.exists() || !file.isFile()) {
      return notFound("未找到该文件！");
    }
    return ok(file);
  }

  /**
   * 主页，通常是以 index 命名，这样通过浏览器访问域名时，会自动加载这个页面。
   */
  public Result index() {
    // 在 Play 的views包下，有一堆html文件，可以通过下面这样的方式来引用。
    return ok(views.html.index.render());
  }
}
