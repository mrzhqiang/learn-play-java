package authentions;

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
          // 使用Java8新增的Base64解码器解码，注意UTF-8格式
          String auth = new String(Base64.getDecoder().decode(authEncode), "UTF-8");
          // Basic是通过 username:password 的方式做权限认证
          String[] authSplit = auth.split(":", 2);
          Optional<Client> clientOptional = Clients.verify(authSplit[0], authSplit[1]);
          if (clientOptional.isPresent()) {
            // 这里返回任何不为null的字符串都行，返回username主要是为了方便查询
            return clientOptional.get().username;
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override public Result onUnauthorized(Http.Context ctx) {
    return unauthorized(views.html.transfer.render());
  }
}
