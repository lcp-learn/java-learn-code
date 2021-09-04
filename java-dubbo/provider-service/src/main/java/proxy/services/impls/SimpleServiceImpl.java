package proxy.services.impls;

import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import proxy.services.SimpleService;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2021/2/25-14:30
 */
@DubboService
public class SimpleServiceImpl implements SimpleService {

  private final Logger logger = LoggerFactory.getLogger(SimpleServiceImpl.class);

  @Override
  public String check(String name) {

    String result = "simple service check " + name;

    logger.info("result:{}", result);

    return result;
  }
}
