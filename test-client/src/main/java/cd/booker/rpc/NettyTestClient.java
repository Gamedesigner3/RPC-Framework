package cd.booker.rpc;


import cd.booker.rpc.serializer.ProtobufSerializer;
import cd.booker.rpc.transport.RpcClient;
import cd.booker.rpc.transport.RpcClientProxy;
import cd.booker.rpc.transport.netty.client.NettyClient;

/**
 * 测试用Netty消费者
 * 客户端反射，动态代理传输到服务端，获得请求类，对象，返回结果。
 * @author booker guan
 */

public class NettyTestClient {

    public static void main(String[] args) {
        RpcClient client = new NettyClient();
        client.setSerializer(new ProtobufSerializer());
        RpcClientProxy rpcClientProxy = new RpcClientProxy(client);
        HelloService helloService = rpcClientProxy.getProxy(HelloService.class);
        HelloObject object = new HelloObject(12, "This is a message");
        String res = helloService.hello(object);
        System.out.println(res);
    }
}
