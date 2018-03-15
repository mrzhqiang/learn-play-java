package controllers.authentions;

import java.util.Optional;
import models.utils.ErrorResponses;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;
import models.utils.Tokens;

/**
 * Token认证器。
 * <p>
 * 与浏览器不同的是，APP接口有很多零碎的请求，这将无法保证会话的有效期，也无从鉴定同一个用户。
 * 因此，利用请求头做每次请求的权限认证，将会有效地改善这一点。
 *
 * @author mrZQ
 * @see Security.Authenticator 默认的权限认证器，只提取会话中 username 字段的值
 */
public class TokenAuthenticator extends Security.Authenticator {

  @Override public String getUsername(Http.Context ctx) {
    Optional<String> optionalAuth = ctx.request().header(Http.HeaderNames.AUTHORIZATION);
    if (optionalAuth.isPresent()) {
      String auth = optionalAuth.get();
      return Tokens.authenticate(auth).orElse(null);
    }
    return null;
  }

  @Override public Result onUnauthorized(Http.Context ctx) {
    return unauthorized(
        ErrorResponses.of(ErrorResponses.AUTHENTICATOR_ERROR,
            "Token过期，请刷新或重新登陆。", "正常错误。").toJsonNode());
  }
}
