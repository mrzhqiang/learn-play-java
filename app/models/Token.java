package models;

import java.util.Objects;
import java.util.concurrent.TimeUnit;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "token")
public class Token extends BaseModel {
  public static final String ACCESS_TOKEN = "access_token";
  public static final String REFRESH_TOKEN = "refresh_token";

  @Column(name = ACCESS_TOKEN, unique = true, nullable = false)
  public String accessToken;
  @Column(name = REFRESH_TOKEN, unique = true, nullable = false)
  public String refreshToken;
  @Column(name = "expires_in", nullable = false)
  public long expiresIn;
  @ManyToOne(optional = false)
  public Account account;

  /** 是否已经过期。如果过期，那么就会返回401，客户端必须在有Token的情况下，调用刷新接口。 */
  public boolean isExpires() {
    long lastTime = modified.after(created) ? modified.getTime() : created.getTime();
    long nowTime = System.currentTimeMillis();
    return TimeUnit.MILLISECONDS.toSeconds(nowTime - lastTime) >= expiresIn;
  }

  @Override public int hashCode() {
    return Objects.hash(super.hashCode(), accessToken, refreshToken, expiresIn, account);
  }

  @Override public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (!(obj instanceof Token)) {
      return false;
    }

    Token other = (Token) obj;
    return super.equals(obj)
        && Objects.equals(accessToken, other.accessToken)
        && Objects.equals(refreshToken, other.refreshToken)
        && Objects.equals(expiresIn, other.expiresIn)
        && Objects.equals(account, other.account);
  }

  @Override public String toString() {
    return baseStringHelper()
        .add("accessToken", accessToken)
        .add("refreshToken", refreshToken)
        .add("expiresIn", expiresIn)
        .add("account", account)
        .toString();
  }
}
