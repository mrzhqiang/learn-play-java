package controllers;

import akka.stream.javadsl.Flow;
import akka.stream.javadsl.Framing;
import akka.stream.javadsl.FramingTruncation;
import akka.stream.javadsl.Keep;
import akka.stream.javadsl.Sink;
import akka.util.ByteString;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executor;
import javax.inject.Inject;
import models.User;
import parsers.UserBodyParser;
import play.http.HttpErrorHandler;
import play.libs.F;
import play.libs.streams.Accumulator;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

/** Body parsers */
public class BodyController extends Controller {

  /** 这部分仅仅是根据文档写的，并没有做验证 */
  public static class CsvBodyParser implements BodyParser<List<List<String>>> {

    private Executor executor;

    @Inject
    public CsvBodyParser(Executor executor) {
      this.executor = executor;
    }

    @Override public Accumulator<ByteString, F.Either<Result, List<List<String>>>> apply(
        Http.RequestHeader request) {
      Sink<ByteString, CompletionStage<List<List<String>>>> sink =
          Flow.<ByteString>create().via(
              Framing.delimiter(ByteString.fromString("\n"), 1000, FramingTruncation.ALLOW))
              .map(bytes -> {
                String[] values = bytes.utf8String().trim().split(",");
                return Arrays.asList(values);
              })
              .toMat(
                  Sink.<List<List<String>>, List<String>>fold(new ArrayList<>(), (list, values) -> {
                    list.add(values);
                    return list;
                  }), Keep.right());
      return Accumulator.fromSink(sink).map(F.Either::Right, executor);
    }
  }

  @BodyParser.Of(Text10Kb.class)
  public Result index() {
    return ok("Got body: " + request().body().asText());
  }

  public static class Text10Kb extends BodyParser.Text {
    @Inject
    public Text10Kb(HttpErrorHandler errorHandler) {
      super(10 * 1024, errorHandler);
    }
  }

  @BodyParser.Of(UserBodyParser.class)
  public Result save() {
    Http.RequestBody body = request().body();
    User user = body.as(User.class);
    return ok("Got name: " + user.getName());
  }

  @BodyParser.Of(BodyParser.Text.class)
  public Result checkParser() {
    Http.RequestBody body = request().body();
    return ok("Got text:" + body.asText());
  }

  /**
   * Http.MimeTypes.TEXT ==> asText
   * Http.MimeTypes.JSON ==> asJson
   * Http.MimeTypes.XML or Http.MimeTypes.HTML or Http.MimeTypes.XHTML ==> asXml
   * Http.MimeTypes.FORM ==> asFormUrlEncoded
   * "multipart/form-data" ==> asMultipartFormData
   * Any other content type:RawBuffer ==> asRaw
   */
  public Result checkRequest() {
    JsonNode json = request().body().asJson();
    return ok("Got name: " + json.get("name").asText());
  }
}
