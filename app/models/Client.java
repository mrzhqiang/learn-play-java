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

/** 客户端，表示访问入口api接口的授权，后台有收回访问权限的可能 */
@Entity
@Table(name = "client")
public class Client extends BaseModel {

  @Column(unique = true, nullable = false)
  public String name;
  @Column(unique = true, nullable = false)
  public UUID apikey;
  public String description;

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

  public static boolean verify(@Nonnull String username, @Nonnull String password) {
    try {
      Optional<Client> clientOptional = find.query().where()
          .eq("name", username)
          .eq("apikey", password)
          .findOneOrEmpty();
      return clientOptional.isPresent();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  private static UUID generateApikey() {
    UUID randomUUID = UUID.randomUUID();
    Optional<Client> clientOptional =
        find.query().where().eq("apikey", randomUUID.toString()).findOneOrEmpty();
    if (!clientOptional.isPresent()) {
      return randomUUID;
    }
    // 必须找到一个不重复的UUID，以保证apikey的唯一性
    return generateApikey();
  }

  @Override public String toString() {
    return baseStringHelper()
        .add("name", name)
        .add("apikey", apikey)
        .add("description", description)
        .toString();
  }

  @Override public int hashCode() {
    return Objects.hash(super.hashCode(), name, apikey, description);
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
        && Objects.equals(name, other.name)
        && Objects.equals(apikey, other.apikey)
        && Objects.equals(description, other.description);
  }
}
