package controllers;

import authentions.ClientAuthenticator;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.List;
import models.Account;
import models.User;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import utils.Accounts;
import utils.Users;

@Security.Authenticated(ClientAuthenticator.class)
public class AccountController extends Controller {

  public Result register() {
    JsonNode jsonNode = request().body().asJson();
    User user = Json.fromJson(jsonNode, User.class);
    Account account = Accounts.of(user);
    user.save();
    account.save();
    return created(account.toString());
  }

  public Result accountList() {
    List<User> result = Users.find.all();
    return ok("Add successful: " + result);
  }
}
