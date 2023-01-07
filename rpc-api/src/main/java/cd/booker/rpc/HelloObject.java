package cd.booker.rpc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *  传输主体类
 *  @author booker guan
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HelloObject implements Serializable {

    /**
     * 传输的数据
     */
    private Integer id;

    private String message;
}
