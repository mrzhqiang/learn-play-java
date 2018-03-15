package models.utils;

import io.ebean.Finder;
import java.util.Base64;
import java.util.Optional;
import java.util.UUID;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import models.Client;

public class Clients {
  private Clients() {
    // no instance
  }

  private static final String BASIC = "Basic ";

  public static final Finder<Long, Client> find = new Finder<>(Client.class);

  @Nonnull
  public static Client of(@Nonnull String name) {
    return of(name, null);
  }

  @Nonnull
  public static Client of(@Nonnull String name, @Nullable String description) {
    Client client = new Client();
    client.name = name;
    client.apikey = generateApikey();
    client.description = description;
    client.save();
    return client;
  }

  @CheckReturnValue
  public static Optional<String> authenticate(@Nonnull String basicAuth) {
    if (basicAuth.startsWith(BASIC)) {
      String authorization =
          new String(Base64.getDecoder().decode(basicAuth.replaceFirst(BASIC, "")));
      int index = authorization.indexOf(":");
      if (index != -1) {
        String username = authorization.substring(0, index);
        String password = authorization.substring(index + 1);
        if (verify(username, password)) {
          return Optional.of(username);
        }
      }
    }
    return Optional.empty();
  }

  @CheckReturnValue
  private static boolean verify(@Nonnull String name, @Nonnull String apikey) {
    if (name.isEmpty() || apikey.isEmpty()) {
      return false;
    }

    Optional<Client> optional = find.query().where()
        .eq("name", name)
        .eq("apikey", apikey)
        .findOneOrEmpty();
    return optional.isPresent();
  }

  @Nonnull
  private static UUID generateApikey() {
    UUID randomUUID = UUID.randomUUID();
    Optional<Client> optionalClient =
        find.query().where().eq("apikey", randomUUID.toString()).findOneOrEmpty();
    if (!optionalClient.isPresent()) {
      return randomUUID;
    }
    // 必须找到一个不重复的UUID，以保证apikey的唯一性
    return generateApikey();
  }
}
