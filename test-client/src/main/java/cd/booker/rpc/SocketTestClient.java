package cd.booker.rpc;

import cd.booker.rpc.serializer.HessianSerializer;
import cd.booker.rpc.transport.RpcClientProxy;
import cd.booker.rpc.transport.socket.client.SocketClient;


/**
 *  测试服务调用端
 * @author booker guan
 */
public class SocketTestClient {

    public static void main(String[] args) {

        SocketClient client = new SocketClient();
        client.setSerializer(new HessianSerializer());
        RpcClientProxy proxy = new RpcClientProxy(client);
        HelloService helloService = proxy.getProxy(HelloService.class);
        HelloObject object = new HelloObject(12, "This is a message");
        String res = helloService.hello(object);
        System.out.println(res);
    }
}
