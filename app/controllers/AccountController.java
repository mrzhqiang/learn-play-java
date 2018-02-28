package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.List;
import models.Account;
import models.User;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import util.Accounts;

public class AccountController extends Controller {

  public Result register() {
    JsonNode jsonNode = request().body().asJson();
    User user = Json.fromJson(jsonNode, User.class);
    user.save();
    Account account = Accounts.randomOf(user);
    account.save();
    return created(account.toString());
  }

  public Result accountList() {
    List<User> result = User.find.all();
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
