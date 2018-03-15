package controllers;

import controllers.actions.PassArgAction;
import controllers.annotations.VerboseAnnotation;
import java.io.File;
import java.security.SecureRandom;
import java.util.Random;
import play.Logger;
import play.libs.Json;
import play.mvc.Call;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;
import play.mvc.With;
import play.twirl.api.Content;

public class ActionController extends Controller {

  @With(PassArgAction.class)
  public Result passArgIndex() {
    Object user = ctx().args.get("user");
    return ok(Json.toJson(user));
  }

  @VerboseAnnotation()
  public Result verboseAnnotationIndex() {
    return ok("It's works!");
  }

  @Security.Authenticated
  //@play.cache.Cached(key = "index.result")
  public Result authenticatedCachedIndex() {
    return ok("It works!");
  }

  @With(VerboseAction.class)
  public Result verboseIndex() {
    return play.mvc.Results.TODO;
  }

  public Result req() {
    return ok("Got request " + request() + "!");
  }

  /** 这个方法，是用随机数生成一个20以内的数字，用来随机返回各种状态 */
  public Result other() {
    Random random = new SecureRandom();
    int r = random.nextInt(21);
    return otherByCode(r);
  }

  /** 路由相关的方法不允许重载 */
  public Result otherByCode(int r) {
    switch (r) {
      case 0:
        // 400
        return badRequest(views.html.index.render());
      case 1:
        return badRequest(new File("./public/images/favicon.png"));
      case 2:
        // 201
        return created();
      case 3:
        return created("创建成功！");
      case 4:
        // 403
        return forbidden();
      case 5:
        return forbidden(new Content() {
          @Override public String body() {
            return "权限验证失败";
          }

          @Override public String contentType() {
            return Http.MimeTypes.TEXT;
          }
        });
      case 6:
        // 302
        return found("https://www.baidu.com/");
      case 7:
        return found(new Call() {
          @Override public String url() {
            return "https://github.com/";
          }

          @Override public String method() {
            return Http.HttpVerbs.GET;
          }

          @Override public String fragment() {
            return null;
          }
        });
      case 8:
        // 500
        return internalServerError();
      case 9:
        return internalServerError("Oops");
      case 10:
        // 301
        return movedPermanently("http://haowanba.com/");
      case 11:
        // 204
        return noContent();
      case 12:
        // 406
        return notAcceptable();
      case 13:
        // 404
        return notFound("<h1>Page not found</h1>").as(Http.MimeTypes.HTML);
      case 14:
        // 402
        return paymentRequired();
      case 15:
        // 303
        return redirect("http://106.14.6.174/");
      case 16:
        // simple result
        return status(ACCEPTED);
      case 17:
        // 307
        return temporaryRedirect("/");
      case 18:
        // 401
        return unauthorized();
      case 19:
        // 415
        return unsupportedMediaType();
      case 20:
        // 200
        return ok("I am sorry!");
      default:
        Logger.debug("TODO");
        break;
    }
    return TODO;
  }
}
