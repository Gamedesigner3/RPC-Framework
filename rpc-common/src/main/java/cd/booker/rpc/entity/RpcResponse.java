package cd.booker.rpc.entity;

import cd.booker.rpc.enumeration.ResponseCode;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 服务提供端的返回执行成功或失败的结果
 * @author booker guan
 */
@Data
@NoArgsConstructor
public class RpcResponse<T> implements Serializable {

    private String requestId;
    /**
     * 状态响应码
     */
    private Integer statusCode;

    /**
     * 状态相应信息
     */
    private String message;

    /**
     * 状态响应数据
     */
    private T data;

    public static <T> RpcResponse<T> success(T data, String requestId) {
        RpcResponse<T> response = new RpcResponse<>();
        response.setRequestId(requestId);
        response.setStatusCode(ResponseCode.SUCCESS.getCode());
        response.setData(data);
        return response;
    }

    public static <T> RpcResponse<T> fail(ResponseCode code, String requestId) {
        RpcResponse<T> response = new RpcResponse<>();
        response.setRequestId(requestId);
        response.setStatusCode(code.getCode());
        response.setMessage(code.getMessage());
        return response;
    }

}
