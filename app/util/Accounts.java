package util;

import io.ebean.Finder;
import models.Account;
import models.User;

public final class Accounts {
  private Accounts() {
    // no instance
  }

  public static final Finder<Long, Account> find = new Finder<>(Account.class);

  public static Account randomOf(User user) {
    Account account = new Account();
    account.number = RandomUtil.numberOf(user.hashCode() & 0xF, 10);
    account.password = RandomUtil.stringOf(user.hashCode() & 0xF, 10);
    account.token = Tokens.of(user);
    return account;
  }

}
