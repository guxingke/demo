package example.armeria.grpc;

import com.linecorp.armeria.client.grpc.GrpcClients;
import com.linecorp.armeria.client.logging.LoggingClient;
import com.linecorp.armeria.common.grpc.GrpcSerializationFormats;
import example.armeria.grpc.Hello.HelloRequest;
import example.armeria.grpc.HelloServiceGrpc.HelloServiceBlockingStub;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ClientMain {

  private static final Logger logger = LoggerFactory.getLogger(ClientMain.class);

  public static void main(String[] args) throws Exception {
    var cb = GrpcClients.builder("http://127.0.0.1:8080/").serializationFormat(GrpcSerializationFormats.PROTO)
        .responseTimeoutMillis(10000).decorator(AddHeaderClientDecorator::new).decorator(LoggingClient.newDecorator());

    var hello = cb.build(HelloServiceBlockingStub.class);

    var req = HelloRequest.newBuilder().setName("Hello").build();

    var scanner = new Scanner(System.in);
    while (scanner.hasNextLine()) {
      var line = scanner.nextLine();
      if (line.startsWith("quit")) {
        break;
      }
      logger.info("req {}", req);
      var resp = hello.hello(req);
      logger.info("resp {}", resp);
    }
  }

  private ClientMain() {
  }
}
