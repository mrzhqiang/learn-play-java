package utils;

import io.ebean.Finder;
import java.sql.Timestamp;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import models.Account;
import models.Token;

import static models.Token.ACCESS_TOKEN;
import static models.Token.REFRESH_TOKEN;

public class Tokens {
  private Tokens() {
    // no instance
  }

  // 这里由于Token只给APP使用，所以默认的过期时间是30天
  private static final long TOKEN_EXPIRES_IN = TimeUnit.DAYS.toSeconds(30);
  // 这部分参考的是 https://www.oauth.com/oauth2-servers/access-tokens/access-token-response/
  private static final int O_AUTH_ACCESS_TOKEN_LENGTH = 32;
  private static final int O_AUTH_REFRESH_TOKEN_LENGTH = 34;

  public static final Finder<Long, Token> find = new Finder<>(Token.class);

  @Nonnull
  public static Token of(@Nonnull Account account) {
    Token token = new Token();
    token.accessToken = generateAccessToken();
    token.refreshToken = generateRefreshToken();
    token.expiresIn = TOKEN_EXPIRES_IN;
    token.account = account;
    token.save();
    return token;
  }

  @Nullable
  public static Optional<Token> of(@Nonnull String refreshToken) {
    Optional<Token> optionalToken =
        find.query().where().eq(REFRESH_TOKEN, refreshToken).findOneOrEmpty();
    if (optionalToken.isPresent()) {
      Token token = optionalToken.get();
      token.accessToken = generateAccessToken();
      token.modified = new Timestamp(System.currentTimeMillis());
      token.save();
      return Optional.of(token);
    }
    return Optional.empty();
  }

  @CheckReturnValue
  public static boolean verify(@Nonnull String accessToken) {
    Optional<Token> optionalToken =
        find.query().where().eq(ACCESS_TOKEN, accessToken).findOneOrEmpty();
    return optionalToken.isPresent();
  }

  private static String generateAccessToken() {
    String accessToken = RandomUtil.stringOf(O_AUTH_ACCESS_TOKEN_LENGTH);
    Optional<Token> optionalToken =
        find.query().where().eq(ACCESS_TOKEN, accessToken).findOneOrEmpty();
    if (optionalToken.isPresent()) {
      return generateAccessToken();
    }
    return accessToken;
  }

  private static String generateRefreshToken() {
    String refreshToken = RandomUtil.stringOf(O_AUTH_REFRESH_TOKEN_LENGTH);
    Optional<Token> optionalToken =
        find.query().where().eq(REFRESH_TOKEN, refreshToken).findOneOrEmpty();
    if (optionalToken.isPresent()) {
      return generateAccessToken();
    }
    return refreshToken;
  }
}
