package cd.booker.rpc.exception;

/**
 * 序列化异常
 */
public class SerializeException extends RuntimeException {
    public SerializeException(String msg) {
        super(msg);
    }
}