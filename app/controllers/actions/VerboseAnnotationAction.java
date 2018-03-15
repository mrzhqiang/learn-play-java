package controllers.actions;

import controllers.annotations.VerboseAnnotation;
import java.util.concurrent.CompletionStage;
import play.Logger;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;

public class VerboseAnnotationAction extends Action<VerboseAnnotation> {
  @Override public CompletionStage<Result> call(Http.Context ctx) {
    if (configuration.value()) {
      Logger.info("Calling action for {}", ctx);
    }
    return delegate.call(ctx);
  }
}
