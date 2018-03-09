package utils;

import io.ebean.Finder;
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
    return client;
  }

  @CheckReturnValue
  public static boolean verify(@Nonnull String username, @Nonnull String password) {
    Optional<Client> optionalClient = find.query().where()
        .eq("name", username)
        .eq("apikey", password)
        .findOneOrEmpty();
    return optionalClient.isPresent();
  }

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
