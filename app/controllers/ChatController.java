package controllers;

import akka.actor.ActorSystem;
import akka.stream.Materializer;
import akka.stream.javadsl.Flow;
import javax.inject.Inject;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.WebSocket;

public class ChatController extends Controller {
  private final ActorSystem actorSystem;
  private final Materializer materializer;

  @Inject
  public ChatController(ActorSystem actorSystem, Materializer materializer) {
    this.actorSystem = actorSystem;
    this.materializer = materializer;
  }

  public Result room() {
    Http.Request request = request();
    String url = routes.ChatController.chat().webSocketURL(request);
    return ok(views.html.chat.render(url));
  }

  public WebSocket chat() {
    return WebSocket.Text.accept(request ->
        Flow.<String>create().map(msg -> {
          System.out.println(msg);
          return "收到消息：" + msg;
        }));
  }
}
