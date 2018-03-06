package authentions;

import java.util.Optional;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;

/**
 * 用户权限认证器。
 * <p>
 * 一般来说，用户权限是比较严格的，因为涉及的功能要比客户端权限多得多，但又不能泄露隐私。
 *
 * @author mrZQ
 * @see Security.Authenticator 默认的权限认证器，只提取会话中 username 字段的值
 */
public class UserAuthenticator extends Security.Authenticator {
  private static final String BEARER = "Bearer ";

  @Override public String getUsername(Http.Context ctx) {
    try {
      Optional<String> authorization = ctx.request().header(Http.HeaderNames.AUTHORIZATION);
      if (authorization.isPresent()) {
        String bearerAuth = authorization.get();
        if (bearerAuth.startsWith(BEARER)) {
          String token = bearerAuth.replaceFirst(BEARER, "");
          if (!token.isEmpty()) {
            // TODO token校验
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override public Result onUnauthorized(Http.Context ctx) {
    return unauthorized(views.html.login.render());
  }
}
