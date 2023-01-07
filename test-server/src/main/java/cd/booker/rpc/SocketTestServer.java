package cd.booker.rpc;


import cd.booker.rpc.serializer.HessianSerializer;
import cd.booker.rpc.transport.socket.server.SocketServer;

/**
 * 测试服务提供端
 * @author booker guan
 */

public class SocketTestServer {

    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        SocketServer socketServer = new SocketServer("127.0.0.1", 9998);
        socketServer.setSerializer(new HessianSerializer());
        socketServer.publishService(helloService, HelloService.class);
    }
}