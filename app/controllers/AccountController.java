package controllers;

import controllers.authentions.TokenAuthenticator;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import models.Account;
import models.Token;
import models.User;
import models.forms.AccountData;
import models.forms.UserData;
import models.utils.Accounts;
import models.utils.Tokens;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import models.utils.Users;

public class AccountController extends Controller {

  private final Form<AccountData> accountForm;
  private final Form<UserData> userForm;

  @Inject
  public AccountController(FormFactory formFactory) {
    this.accountForm = formFactory.form(AccountData.class);
    this.userForm = formFactory.form(UserData.class);
  }

  public Result login() {
    return ok(views.html.login.render(accountForm, Optional.empty()));
  }

  public Result loginAccount() {
    final Form<AccountData> accountForm = this.accountForm.bindFromRequest();

    AccountData accountData = accountForm.get();

    Optional<String> error = accountData.checkError();
    if (error.isPresent()) {
      return badRequest(views.html.login.render(accountForm, error));
    }

    Account account = Accounts.of(accountData.getNumber(), accountData.getPassword());
    if (account == null) {
      return badRequest(views.html.login.render(accountForm, Optional.of("登陆失败，账号或密码错误！")));
    }

    Token token = Tokens.of(account);
    session().put("token", token.accessToken);

    // TODO 聊天界面或账户中心
    return ok("登陆成功！");
  }

  public Result register() {
    return ok(views.html.register.render(userForm, Optional.empty()));
  }

  public Result registerAccount() {
    final Form<UserData> userForm = this.userForm.bindFromRequest();

    UserData userData = userForm.get();

    Optional<String> error = userData.checkError();
    if (error.isPresent()) {
      return badRequest(views.html.register.render(userForm, error));
    }

    User user = userData.toUser();
    user.save();

    Account account = Accounts.of(user);

    Token token = Tokens.of(account);
    token.save();
    session().put("token", token.accessToken);

    // TODO 聊天界面或账户中心
    return ok("注册成功！QQ号：" + account.number + ", 密码：" + account.password);
  }

  @Security.Authenticated(TokenAuthenticator.class)
  public Result accountList() {
    List<User> result = Users.find.all();
    return ok("Add successful: " + result);
  }
}
