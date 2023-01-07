package cd.booker.rpc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 接口实现
 * @author booker guan
 */
public class HelloServiceImpl implements HelloService{

    private static final Logger logger = LoggerFactory.getLogger(HelloServiceImpl.class);

    @Override
    public String hello(HelloObject object){
        logger.info("接受到：{}",object.getMessage());
        return "调用返回值，id = " + object.getId();
    }

}
