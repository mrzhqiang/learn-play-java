package controllers;

import java.util.List;
import models.Account;
import play.mvc.Controller;
import play.mvc.Result;

public class AccountController extends Controller {

  public Result accountList() {
    List<Account> result = Account.find.all();
    return ok("Add successful: " + result);
  }

  public Result addAccount(int userId) {
    Account account = Account.randomCreate(userId);
    account.insert();
    return ok(account.toString());
  }
}
