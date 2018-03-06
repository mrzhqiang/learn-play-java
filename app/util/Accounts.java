package util;

import io.ebean.Finder;
import models.Account;
import models.User;

public final class Accounts {
  private Accounts() {
    // no instance
  }

  public static final Finder<Long, Account> find = new Finder<>(Account.class);

  public static Account of(User user) {
    Account account = new Account();
    int minLength = Math.max(6, user.hashCode() & 0xF);
    account.number = RandomUtil.numberOf(minLength, 10);
    account.password = RandomUtil.stringOf(minLength, 10);
    account.token = Tokens.of(user);
    return account;
  }

}
