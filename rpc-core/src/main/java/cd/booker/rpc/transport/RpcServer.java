package cd.booker.rpc.transport;

import cd.booker.rpc.serializer.CommonSerializer;

/**
 * 服务提供端接口
 * @author booker guan
 */
public interface RpcServer {


    void start();

    void setSerializer(CommonSerializer serializer);

    <T> void publishService(Object service,Class<T> serviceClass);
}
