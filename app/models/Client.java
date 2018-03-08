package models;

import io.ebean.Finder;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "client")
public class Client extends BaseModel {
  public static final Finder<Long, Client> find = new Finder<>(Client.class);

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

  public static boolean verify(@Nonnull String username, @Nonnull String password) {
    Optional<Client> clientOptional = find.query().where()
        .eq("username", username)
        .eq("password", password)
        .findOneOrEmpty();
    return clientOptional.isPresent();
  }

  @NotNull
  @Column(unique = true)
  public String username;
  @NotNull
  @Column(unique = true)
  public UUID password;
  public String description;

  @Override public String toString() {
    return baseStringHelper()
        .add("name", username)
        .add("password", password)
        .add("description", description)
        .toString();
  }

  @Override public int hashCode() {
    return Objects.hash(super.hashCode(), username, password, description);
  }

  @Override public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (!(obj instanceof Client)) {
      return false;
    }

    Client other = (Client) obj;
    return super.equals(obj)
        && Objects.equals(username, other.username)
        && Objects.equals(password, other.password)
        && Objects.equals(description, other.description);
  }
}
