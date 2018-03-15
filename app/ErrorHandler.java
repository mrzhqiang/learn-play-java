import com.typesafe.config.Config;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import models.utils.ErrorResponses;
import play.Environment;
import play.api.OptionalSourceMapper;
import play.api.routing.Router;
import play.http.DefaultHttpErrorHandler;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;

@Singleton
public class ErrorHandler extends DefaultHttpErrorHandler {

  @Inject
  public ErrorHandler(Config config, Environment environment,
      OptionalSourceMapper sourceMapper,
      Provider<Router> routes) {
    super(config, environment, sourceMapper, routes);
  }

  @Override
  public CompletionStage<Result> onServerError(Http.RequestHeader request, Throwable exception) {
    return CompletableFuture.completedFuture(
        Results.internalServerError(ErrorResponses.of(exception).toJsonNode()));
  }
}
