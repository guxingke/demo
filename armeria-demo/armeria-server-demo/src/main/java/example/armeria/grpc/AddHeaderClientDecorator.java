package example.armeria.grpc;

import com.linecorp.armeria.client.ClientRequestContext;
import com.linecorp.armeria.client.HttpClient;
import com.linecorp.armeria.client.SimpleDecoratingHttpClient;
import com.linecorp.armeria.common.HttpRequest;
import com.linecorp.armeria.common.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddHeaderClientDecorator extends SimpleDecoratingHttpClient {

  private static final Logger log = LoggerFactory.getLogger(AddHeaderClientDecorator.class);

  /**
   * Creates a new instance that decorates the specified {@link HttpClient}.
   */
  public AddHeaderClientDecorator(HttpClient delegate) {
    super(delegate);
  }

  @Override
  public HttpResponse execute(
      ClientRequestContext ctx,
      HttpRequest req
  ) throws Exception {
    log.info("before decorator ...");

    var newHeaders = req.headers().withMutations(it -> it.add("X-HELLO", "hello"));
    var newReq = req.withHeaders(newHeaders);
    ctx.updateRequest(newReq);


    newReq.headers().forEach(it -> {
      log.info("req header {} : {}", it.getKey(), it.getValue());
    });
    try {
      return unwrap().execute(ctx, newReq);
    } finally {
      log.info("end decorator ...");
    }
  }
}
