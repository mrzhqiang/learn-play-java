package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.List;
import models.Account;
import models.User;
import play.mvc.Controller;
import play.mvc.Result;
import util.Accounts;

public class AccountController extends Controller {

  public Result register() {
    JsonNode jsonNode = request().body().asJson();
    return play.mvc.Results.TODO;
  }

  public Result accountList() {
    List<Account> result = Account.find.all();
    return ok("Add successful: " + result);
  }

  public Result addAccount(long userId) {
    User user = User.find.byId(userId);
    if (user == null) {
      return notFound();
    }
    Account account = Accounts.randomOf(user);
    account.insert();
    return ok(account.toString());
  }
}
