package models;

import java.util.HashMap;
import java.util.Map;

public class User {
  private Long id;
  private String name;

  private static final Map<Long, User> USER_POOL = new HashMap<>();

  static {
    for (int i = 0; i < 100; i++) {
      long id = i + 1;
      USER_POOL.put(id, new User(id, "Index:" + i));
    }
  }

  public User() {
  }

  public User(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  public static User findById(long id) {
    if (id < 0) {
      return new User(id + 1, "Index: " + id);
    }
    return USER_POOL.get(id);
  }

  public String getName() {
    return name;
  }
}
