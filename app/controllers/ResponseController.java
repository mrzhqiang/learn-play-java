package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import java.time.Duration;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

/** Manipulating the response */
public class ResponseController extends Controller {

  public Result characterOf() {
    return ok("<h1>Hello, Character!</h1>", "GBK").as(Http.MimeTypes.HTML + "; charset=GBK");
  }

  public Result cookieOf() {
    //response().setCookie(Http.Cookie.builder("theme", "blue").build());
    return ok("aaaaa").withCookies(Http.Cookie.builder("theme", "blue")
        .withDomain("/index")
        .withHttpOnly(true)
        .withMaxAge(Duration.ofHours(1))
        .withSameSite(Http.Cookie.SameSite.STRICT)
        //.withDomain("")
        .build());
  }

  public Result settingHeader() {
    // 1.
    //return ok().withHeader(Http.HeaderNames.CACHE_CONTROL, "max-age=30");
    // 2.
    //return ok().withHeaders(Http.HeaderNames.WARNING, "Just a error");
    // 3.
    response().setHeader(Http.HeaderNames.LOCATION, "SuZhou");
    response().setHeader(Http.HeaderNames.ORIGIN, "CYCYY");
    return ok();
  }

  public Result contentOf() {
    return ok("<h1>Hello Fun!</h1>").as(Http.MimeTypes.HTML);
  }

  public Result contentJson() {
    // default content-type is application/json
    return ok();
  }

  public Result contentText() {
    // default content-type is text/plain
    return ok("Content Type");
  }
}
