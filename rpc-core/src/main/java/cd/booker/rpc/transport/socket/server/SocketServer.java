package cd.booker.rpc.transport.socket.server;

import cd.booker.rpc.enumeration.RpcError;
import cd.booker.rpc.provider.ServiceProviderImpl;
import cd.booker.rpc.registry.NacosServiceRegistry;
import cd.booker.rpc.registry.ServiceRegistry;
import cd.booker.rpc.transport.RpcServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cd.booker.rpc.handler.RequestHandler;
import cd.booker.rpc.exception.RpcException;
import cd.booker.rpc.provider.ServiceProvider;
import cd.booker.rpc.serializer.CommonSerializer;
import cd.booker.rpc.util.ThreadPoolFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

/**
 * 远程方法调用的服务端
 * @author booker guan
 */
public class SocketServer implements RpcServer {

    private static final Logger logger = LoggerFactory.getLogger(SocketServer.class);

    private final ExecutorService threadPool;;
    private final String host;
    private final int port;
    private CommonSerializer serializer;
    private RequestHandler requestHandler = new RequestHandler();

    private final ServiceRegistry serviceRegistry;
    private final ServiceProvider serviceProvider;

    public SocketServer(String host, int port) {
        this.host = host;
        this.port = port;
        threadPool = ThreadPoolFactory.createDefaultThreadPool("socket-rpc-server");
        this.serviceRegistry = new NacosServiceRegistry();
        this.serviceProvider = new ServiceProviderImpl();
    }

    @Override
    public <T> void publishService(Object service, Class<T> serviceClass) {
        if(serializer == null) {
            logger.error("未设置序列化器");
            throw new RpcException(RpcError.SERIALIZER_NOT_FOUND);
        }
        serviceProvider.addServiceProvider(service);
        serviceRegistry.register(serviceClass.getCanonicalName(), new InetSocketAddress(host, port));
        start();
    }

    @Override
    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            logger.info("服务器启动……");
            Socket socket;
            while ((socket = serverSocket.accept()) != null) {
                logger.info("消费者连接: {}:{}", socket.getInetAddress(), socket.getPort());
                threadPool.execute(new RequestHandlerThread(socket, requestHandler, serviceRegistry, serializer));
            }
            threadPool.shutdown();
        } catch (IOException e) {
            logger.error("服务器启动时有错误发生:", e);
        }
    }

    @Override
    public void setSerializer(CommonSerializer serializer) {
        this.serializer = serializer;
    }

}
