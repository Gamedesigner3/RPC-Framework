package cd.booker.rpc.provider;

/**
 * 本地服务接口
 *
 * @author booker guan
 */
public interface ServiceProvider {

    <T> void addServiceProvider(T service);

    Object getServiceProvider(String serviceName);
}
