package controllers;

import authentions.UserAuthenticator;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import models.User;
import models.form.AccountData;
import models.form.UserData;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import utils.Users;

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
      return badRequest(views.html.login.render(this.accountForm, error));
    }

    // TODO 聊天界面或账户中心
    return ok("登陆成功！");
  }

  public Result register() {
    return ok(views.html.register.render(userForm, Optional.empty()));
  }

  public Result registerAccount() {
    final Form<UserData> userForm = this.userForm.bindFromRequest();

    UserData userData = userForm.get();

    Optional<String> optionalError = userData.checkError();
    if (optionalError.isPresent()) {
      return badRequest(views.html.register.render(userForm, optionalError));
    }

    // TODO 通过用户资料，创建账户，并登陆
    return ok("注册成功！");
  }

  @Security.Authenticated(UserAuthenticator.class)
  public Result accountList() {
    List<User> result = Users.find.all();
    return ok("Add successful: " + result);
  }
}
