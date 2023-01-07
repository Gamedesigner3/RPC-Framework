package cd.booker.rpc;

import cd.booker.rpc.serializer.ProtobufSerializer;
import cd.booker.rpc.transport.netty.server.NettyServer;
/**
 * 服务提供端
 * @author booker guan
 */
public class NettyTestServer {

    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        NettyServer server = new NettyServer("127.0.0.1", 9999);
        server.setSerializer(new ProtobufSerializer());
        server.publishService(helloService, HelloService.class);
    }
}
