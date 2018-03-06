package util;

import io.ebean.Finder;
import models.User;

public class Users {
  private Users() {
    // no instance
  }

  public static final Finder<Long, User> find = new Finder<>(User.class);

  // TODO builder
}
