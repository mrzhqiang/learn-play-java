package models;

import com.google.common.base.MoreObjects;
import io.ebean.Finder;
import io.ebean.Model;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import play.data.format.Formats;

/**
 * 客户端表，实际上是为了持久化APIKey
 *
 * @author mrZQ
 */
@Entity
@Table(name = "client")
public class Client extends Model {
  @Id
  public Long id;
  @NotNull @Size(max = 32)
  public String name;
  @NotNull
  public UUID apikey;
  public String description;
  @OrderBy("DESC")
  @Formats.DateTime(pattern = "yyyy-MM-dd HH:mm:ss")
  public Date created;
  @OrderBy("DESC")
  @Formats.DateTime(pattern = "yyyy-MM-dd HH:mm:ss")
  public Date updated;

  public static Finder<Long, Client> find = new Finder<>(Client.class);

  @Override public int hashCode() {
    return Objects.hash(id, name, apikey, description);
  }

  @Override public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Client)) {
      return false;
    }
    Client other = (Client) obj;
    return Objects.equals(id, other.id)
        && Objects.equals(name, other.name)
        && Objects.equals(apikey, other.apikey)
        && Objects.equals(description, other.description);
  }

  @Override public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("id", id)
        .add("name", name)
        .add("apikey", apikey)
        .add("desccription", description)
        .add("created", created)
        .add("updated", updated)
        .toString();
  }
}
