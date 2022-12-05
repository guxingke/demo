package example.armeria.grpc;

import com.linecorp.armeria.common.HttpRequest;
import com.linecorp.armeria.common.HttpResponse;
import com.linecorp.armeria.server.HttpService;
import com.linecorp.armeria.server.ServiceRequestContext;
import com.linecorp.armeria.server.SimpleDecoratingHttpService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PeekHeaderDecorator extends SimpleDecoratingHttpService {

  /**
   * Creates a new instance that decorates the specified {@link HttpService}.
   */
  public PeekHeaderDecorator(HttpService delegate) {
    super(delegate);
  }

  @Override
  public HttpResponse serve(
      ServiceRequestContext ctx,
      HttpRequest req
  ) throws Exception {  // AggregatingDecodedHttpRequest
    req.headers().stream().forEach(it -> {
      log.info("header {}", it.getKey().toString() + " : " + it.getValue());
    });
    return unwrap().serve(ctx, req);
  }
}
