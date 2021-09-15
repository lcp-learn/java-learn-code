package proxy.config;

import org.springframework.context.annotation.ComponentScan;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2021/2/25-14:43
 */
@ComponentScan({
        "com.lcp.learn.dubbo.provider.proxy.services.impls",
        "com.lcp.learn.dubbo.provider.proxy.handlers"
})
public class BeanConfig {


}
