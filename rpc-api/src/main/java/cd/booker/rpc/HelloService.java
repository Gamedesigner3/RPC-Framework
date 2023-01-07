package cd.booker.rpc;

/**
 * 传输接口
 * @author booker guan
 */
public interface HelloService {

    /**
     * 传输的函数
     * @param object
     * @return
     */
    String hello(HelloObject object);
}
