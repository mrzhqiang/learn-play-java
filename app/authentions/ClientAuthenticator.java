package authentions;

import java.util.Base64;
import java.util.Optional;
import play.mvc.Http;
import play.mvc.Result;
import utils.Clients;

/**
 * 客户端权限校验器。
 * <p>
 * 当请求头的权限字段中，包含有效APIKey，并与预定义的APIKey一致，则通过校验。
 *
 * @author mrZQ
 */
public class ClientAuthenticator extends UserAuthenticator {
  private static final String BASIC = "Basic ";

  @Override public String getUsername(Http.Context ctx) {
    // 首先尝试一下用户权限，如果通过，那么直接通过
    String userAuth = super.getUsername(ctx);
    if (userAuth != null) {
      return userAuth;
    }
    try {
      Optional<String> optionalAuth = ctx.request().header(Http.HeaderNames.AUTHORIZATION);
      if (optionalAuth.isPresent()) {
        String auth = optionalAuth.get();
        if (auth.startsWith(BASIC)) {
          // 使用Java8新增的Base64解码器解码
          byte[] decodeAuth = Base64.getDecoder().decode(auth.replaceFirst(BASIC, ""));
          // UTF-8格式，事实上也不太需要注意
          String authorization = new String(decodeAuth, "UTF-8");
          // Basic是通过 username:password 的方式做权限认证
          int index = authorization.indexOf(":");
          if (index != -1) {
            String username = authorization.substring(0, index);
            String password = authorization.substring(index + 1);
            if (Clients.verify(username, password)) {
              // 这里返回任何不为null的字符串都行，返回username主要是为了方便查询
              return username;
            }
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override public Result onUnauthorized(Http.Context ctx) {
    return unauthorized("关于权限访问的问题，请联系QQ：287431404。");
  }
}
