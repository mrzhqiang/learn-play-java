package models.utils;

import io.ebean.Finder;
import java.util.Optional;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import models.Account;
import models.User;

public class Accounts {
  private Accounts() {
    // no instance
  }

  private static final int MIN_LENGTH = 6;
  private static final int MAX_LENGTH = 16;

  public static final Finder<Long, Account> find = new Finder<>(Account.class);

  @Nonnull
  @CheckReturnValue
  public static Account of(@Nonnull User user) {
    Account account = new Account();
    int minLength = Math.max(MIN_LENGTH, user.hashCode() & 0xF);
    account.number = numberOf(minLength);
    account.password = RandomUtil.stringOf(minLength, MAX_LENGTH);
    account.level = 1;
    account.user = user;
    account.save();
    return account;
  }

  @Nullable
  @CheckReturnValue
  public static Account of(@Nonnull String number, @Nonnull String password) {
    Optional<Account> accountOptional = find.query().where()
        .eq("number", number)
        .eq("password", password)
        .findOneOrEmpty();
    return accountOptional.orElse(null);
  }

  private static String numberOf(int minLength) {
    String number = RandomUtil.numberOf(minLength, MAX_LENGTH);
    Optional<Account> accountOptional =
        find.query().where().eq("number", number).findOneOrEmpty();
    if (accountOptional.isPresent()) {
      return numberOf(minLength);
    }
    return number;
  }
}
