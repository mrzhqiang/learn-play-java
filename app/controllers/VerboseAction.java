package controllers;

import java.util.concurrent.CompletionStage;
import play.Logger;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;

public class VerboseAction extends Action.Simple {
  @Override public CompletionStage<Result> call(Http.Context ctx) {
    Logger.info("Calling action for {}", ctx);
    return delegate.call(ctx);
  }
}
