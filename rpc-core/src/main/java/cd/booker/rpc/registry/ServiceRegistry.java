package cd.booker.rpc.registry;

import java.net.InetSocketAddress;

/**
 * Nacos 远程服务注册接口
 * @author booker guan
 */
public interface ServiceRegistry {

    void register(String serviceName, InetSocketAddress inetSocketAddress);

    InetSocketAddress lookupService(String serviceName);
}
