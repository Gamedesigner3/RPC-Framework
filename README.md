# My-Rpc-FrameWork

#### 介绍
My-RPC-Framework 是一款基于 Nacos 实现的 RPC 框架。网络传输实现了基于 Java 原生 Socket 与 Netty 版本，并且实现了多种序列化。

#### 软件架构
根据实际调用需求，可选择基于 Java 原生 Socket，该步骤基于 BIO 实现。选择基于 Netty 的传输方法，则基于 NIO 实现。Nacos 实现服务的注册和提供服务地址的功能。不同序列化满足不同层面的需求：JSON，Hessian，Kryo 等框架。

#### 项目模块
  .rpc-api —— 调用接口  
  .rpc-common —— 工具类  
  .rpc-core —— 传输类  
  .rpc-client —— 消费端测试  
  .rpc-server —— 服务端测试

#### 项目启动

通用接口
```
public interface HelloService {
    String hello(String name);
}
```

接口实现
```
public class HelloServiceImpl implements HelloService{
    @Override
    public String hello(HelloObject object){
        logger.info("接受到：{}",object.getMessage());
        return "调用返回值，id = " + object.getId();
    }
}
```
Socket 服务端(先开)
```
public class SocketTestServer {
    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        SocketServer socketServer = new SocketServer("127.0.0.1", 9998);
        socketServer.setSerializer(new HessianSerializer());
        socketServer.publishService(helloService, HelloService.class);
    }
}
```
Socket 消费端（后开）
```
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
```
Netty 服务端
```
public class NettyTestServer {
    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        NettyServer server = new NettyServer("127.0.0.1", 9999);
        server.setSerializer(new ProtobufSerializer());
        server.publishService(helloService, HelloService.class);
    }
}
```
Netty 消费端
```
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

```

#### 参考文章
https://blog.csdn.net/qq_40856284/category_10138756.html
https://github.com/CN-GuoZiyang/My-RPC-Framework


