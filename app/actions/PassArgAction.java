package actions;

import java.util.concurrent.CompletionStage;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;

public class PassArgAction extends Action.Simple {

  @Override public CompletionStage<Result> call(Http.Context ctx) {
    //ctx.args.put("user", Contact.findById(1));
    return delegate.call(ctx);
  }
}
