package authention;

import java.util.List;
import java.util.Optional;
import models.APIKey;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;

/**
 * 客户端权限校验器。
 * <p>
 * 当请求头的权限字段中，包含有效APIKey，并与预定义的APIKey一致，则通过校验。
 *
 * @author mrZQ
 */
public class ClientAuthenticator extends Security.Authenticator {
  private static final String BASIC = "Basic ";



  @Override public String getUsername(Http.Context ctx) {
    Optional<String> authOptional = ctx.request().header(Http.HeaderNames.AUTHORIZATION);
    if (authOptional.isPresent()) {
      String authorization = authOptional.get();
      List<APIKey> apiKeyList = APIKey.find.all();
      // TODO
    }
    return null;
  }

  @Override public Result onUnauthorized(Http.Context ctx) {
    return unauthorized();
  }
}
