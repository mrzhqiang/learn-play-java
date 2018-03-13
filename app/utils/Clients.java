package utils;

import io.ebean.Finder;
import java.io.UnsupportedEncodingException;
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

  public static final String BASIC = "Basic ";
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

  @Nonnull
  public static String authOf(@Nonnull String name, @Nonnull String apikey) {
    String auth = name + ":" + apikey;
    try {
      auth = new String(Base64.getEncoder().encode(auth.getBytes()), "UTF-8");
    } catch (UnsupportedEncodingException e) {
      auth = Base64.getEncoder().encodeToString(auth.getBytes());
    }
    return BASIC + auth;
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
