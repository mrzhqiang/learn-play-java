package util;

import io.ebean.Finder;
import java.util.Optional;
import java.util.UUID;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import models.Client;

public class Clients {

  public static final Finder<Long, Client> find = new Finder<>(Client.class);

  private Clients() {
    // no instance
  }

  @Nonnull
  public static Client of(@Nonnull String name) {
    return of(name, null);
  }

  @Nonnull
  public static Client of(@Nonnull String name, @Nullable String description) {
    Client client = new Client();
    client.username = name;
    client.password = UUID.randomUUID();
    client.description = description;
    return client;
  }

  public static Optional<Client> find(@Nonnull String username, @Nonnull String password) {
    return find.query().where()
        .eq("username", username)
        .eq("password", password)
        .findOneOrEmpty();
  }
}
