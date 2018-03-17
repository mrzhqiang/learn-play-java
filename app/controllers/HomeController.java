package controllers;

import java.util.Optional;
import models.Account;
import models.Token;
import models.utils.Tokens;
import play.mvc.*;

/**
 * 这里是主页控制器，通常是程序的入口，提供一个页面分发功能。
 *
 * @author mrZQ
 */
public class HomeController extends Controller {

  public Result index() {
    String accessToken = session("token");
    if (accessToken != null && !accessToken.isEmpty()) {
      Optional<Token> verify = Tokens.verify(accessToken);
      if (verify.isPresent()) {
        Account account = verify.get().account;
        return ok(views.html.user.render(account.user));
      }
    }
    return ok(views.html.index.render());
  }
}
