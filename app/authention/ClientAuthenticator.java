package authention;

import java.util.Base64;
import java.util.Optional;
import models.Client;
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
    try {
      Optional<String> authorization = ctx.request().header(Http.HeaderNames.AUTHORIZATION);
      if (authorization.isPresent()) {
        String basicAuth = authorization.get();
        if (basicAuth.startsWith(BASIC)) {
          String authEncode = basicAuth.replaceFirst(BASIC, "");
          String auth = new String(Base64.getDecoder().decode(authEncode), "UTF-8");
          String[] authSplit = auth.split(":", 2);
          String name = authSplit[0];
          String apikey = authSplit[1];
          Optional<Client> clientOptional =
              Client.find.query()
                  .where()
                  .eq("name", name)
                  .eq("apikey", apikey)
                  .findOneOrEmpty();
          if (clientOptional.isPresent()) {
            return name;
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override public Result onUnauthorized(Http.Context ctx) {
    return unauthorized("客户端认证失败");
  }
}
