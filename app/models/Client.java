package models;

import io.ebean.Finder;
import io.ebean.annotation.NotNull;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "client")
public class Client extends BaseModel {
  @NotNull
  @Size(max = 32)
  public String name;
  @NotNull
  public UUID apikey = UUID.randomUUID();
  public String description;

  public static final Finder<Long, Client> find = new Finder<>(Client.class);

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
