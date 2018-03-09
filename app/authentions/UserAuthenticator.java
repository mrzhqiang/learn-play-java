package authentions;

import java.util.Optional;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;
import utils.Tokens;

/**
 * 用户权限认证器。
 * <p>
 * 通常来说，用户权限会包含了客户端权限，这可以视为，客户端权限实际上是特殊的用户权限——即游客。
 *
 * @author mrZQ
 * @see Security.Authenticator 默认的权限认证器，只提取会话中 username 字段的值
 */
public class UserAuthenticator extends Security.Authenticator {
  private static final String BEARER = "Bearer ";

  @Override public String getUsername(Http.Context ctx) {
    try {
      Optional<String> optionalAuth = ctx.request().header(Http.HeaderNames.AUTHORIZATION);
      if (optionalAuth.isPresent()) {
        String auth = optionalAuth.get();
        if (auth.startsWith(BEARER)) {
          String token = auth.replaceFirst(BEARER, "");
          if (!token.isEmpty() && Tokens.verify(token)) {
            // 返回token字符串只是为了确保权限认证通过
            return token;
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override public Result onUnauthorized(Http.Context ctx) {
    return unauthorized("用户权限过期，请重新获取AccessToken！");
  }
}
