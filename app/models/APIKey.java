package models;

import io.ebean.Finder;
import io.ebean.Model;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import play.data.format.Formats;
import play.data.validation.Constraints;

@Entity
@Table(name = "api_key")
public class APIKey extends Model {
  private static final long serialVersionUID = 1L;

  @Id
  public long id;
  @Constraints.Required
  public String name;
  @Formats.DateTime(pattern = "yyyy-MM-dd HH:mm:ss")
  public Date created = new Date();
  @Formats.DateTime(pattern = "yyyy-MM-dd HH:mm:ss")
  public Date updated = new Date();

  public static final Finder<Long, APIKey> find = new Finder<>(APIKey.class);

  @Override public String toString() {
    return "("
        + this.getClass().getSimpleName()
        + ") [id="
        + id
        + ", name="
        + name
        + ", created="
        + created
        + ", updated="
        + updated
        + "]";
  }
}
