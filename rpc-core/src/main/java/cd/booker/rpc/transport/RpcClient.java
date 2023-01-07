package cd.booker.rpc.transport;

import cd.booker.rpc.entity.RpcRequest;
import cd.booker.rpc.serializer.CommonSerializer;

/**
 * 服务调用端接口
 * @author booker guan
 */
public interface RpcClient {

    Object sendRequest(RpcRequest rpcRequest);

    void setSerializer(CommonSerializer serializer);

}
