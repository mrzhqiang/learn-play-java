package controllers.authentions;

import java.util.Optional;
import models.utils.Clients;
import models.utils.ErrorResponses;
import models.utils.Tokens;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;

/**
 * Client认证器。
 * <p>
 * 与浏览器不同的是，APP接口有很多零碎的请求，这将无法保证会话的有效期，也无法防止接口被恶意攻击。
 * 因此，利用请求头做每次请求的权限认证，将会有效地改善这一点。
 *
 * @author mrZQ
 * @see Security.Authenticator 默认的权限认证器，只提取会话中 username 字段的值
 */
public class ClientAuthenticator extends Security.Authenticator {

  @Override public String getUsername(Http.Context ctx) {
    Optional<String> optionalAuth = ctx.request().header(Http.HeaderNames.AUTHORIZATION);
    if (optionalAuth.isPresent()) {
      String basicAuth = optionalAuth.get();
      // 如果是Token权限，也可以通过认证，这是因为Token的安全级别更高。
      return Clients.authenticate(basicAuth).orElse(
          Tokens.authenticate(basicAuth).orElse(null));
    }
    return null;
  }

  @Override public Result onUnauthorized(Http.Context ctx) {
    return unauthorized(
        ErrorResponses.of(ErrorResponses.AUTHENTICATOR_ERROR,
            "关于权限访问，请联系QQ：287431404。", "正常错误。").toJsonNode());
  }
}
