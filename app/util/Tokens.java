package util;

import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;
import models.Client;
import models.Token;
import models.User;

public class Tokens {
  private Tokens() {
    // no instance
  }

  private static final long TOKEN_EXPIRES_IN = TimeUnit.DAYS.toSeconds(30);

  @NotNull
  public static Token of(@Nonnull User user) {
    Token token = new Token();
    token.user = user;
    token.accessToken = accessTokenOf(user.client);
    token.refreshToken = refreshTokenOf(user.client);
    token.expiresIn = TOKEN_EXPIRES_IN;
    return token;
  }

  private static String accessTokenOf(Client client) {

    return null;
  }

  private static String refreshTokenOf(Client client) {
    return null;
  }
}
