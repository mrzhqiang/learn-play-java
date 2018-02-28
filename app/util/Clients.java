package util;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import models.Client;

public class Clients {
  private Clients() {
    // no instance
  }

  @Nonnull
  public static Client of(@Nonnull String name, @Nullable String description) {
    Client client = new Client();
    client.name = name;
    client.description = description;
    return client;
  }
}
