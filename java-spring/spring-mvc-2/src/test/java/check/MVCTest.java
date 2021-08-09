package check;

import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;

import check.beans.MvcInfo;
import com.alibaba.fastjson.JSON;
import com.lcp.learn.spring.mvc.controller.a.WorldAController;
import com.lcp.learn.spring.mvc.controller.request.UserRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019/9/6-15:59
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {
    "classpath:applicationContext.xml",
    "classpath:springmvc-servlet-a.xml"
})
@WebAppConfiguration
public class MVCTest {

  protected final Logger logger = LoggerFactory.getLogger(getClass());

  @Autowired
  private WorldAController worldAController;

  @Autowired
  private Environment environment;

  @Autowired
  protected WebApplicationContext webApplicationContext;

  // private MockHttpServletRequestBuilder requestBuilder;
  private MockMvc mockMvc;

  private List<MvcInfo> mvcInfoList;

  @BeforeEach
  public void init() {

    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

    Map<String, Object> beanMap = webApplicationContext.getBeansWithAnnotation(Controller.class);

    mvcInfoList = beanMap.values().stream()
        .map(instance -> instance.getClass().getMethods())//得到方法
        .flatMap((Function<Method[], Stream<Method>>) Arrays::stream)//打散打平
        .filter(method -> method.getAnnotation(RequestMapping.class) != null)//过滤，有RequestMapping注解的method
        .map(method -> { //转换
          MvcInfo mvcInfo = new MvcInfo();
          try {
            RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
            String path = requestMapping.value()[INTEGER_ZERO];

            Arrays.stream(method.getParameters()).forEach(parameter ->
                logger.info("name:{},type:{}", parameter.getName(), parameter.getType().getName()));

            Map<String, String> param = Arrays.stream(method.getParameters())
                .collect(Collectors.toMap(Parameter::getName, parameter -> parameter.getType().getSimpleName()));

            mvcInfo.setUrl(path);
            mvcInfo.setDescription(path);
            mvcInfo.setParam(param);
          } catch (Exception exception) {
            exception.printStackTrace();
          }

          return mvcInfo;

        }).collect(Collectors.toList());

  }

  @Test
  public void testMock() {

    mvcInfoList.forEach(mvcInfo -> { //跑测试

      logger.info("mvcInfo:{}", JSON.toJSONString(mvcInfo));
      try {

        //拼装request
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(mvcInfo.getUrl());
        Optional.ofNullable(mvcInfo.getParam()).ifPresent(stringObjectMap ->
            stringObjectMap.forEach(requestBuilder::param));

        //执行
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        //得到ModelAndView
        Optional.ofNullable(mvcResult.getModelAndView()).ifPresent(modelAndView1 ->
            logger.info("{},viewName:{}", mvcInfo.getDescription(), modelAndView1.getViewName()));

        //得到结果
        String context = mvcResult.getResponse().getContentAsString();

        if (StringUtils.isBlank(context)) {
          String errorMessage = "\"" + mvcInfo.getDescription() + "\"输出为空";
          logger.error("{},url:{},param:{}", errorMessage, mvcInfo.getUrl(), JSON.toJSONString(mvcInfo.getParam()));
          handleErrorMessage(mvcInfo);
          throw new Exception(errorMessage);
        }
        //查看结果
        logger.info("{} = {}", mvcInfo.getDescription(), context);

      } catch (Exception e) {
        // e.printStackTrace();
      }

    });

    // try {
    //   MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
    //   MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
    //   logger.info("ContentAsString = {}", mockHttpServletResponse.getContentAsString());
    //
    // } catch (Exception e) {
    //   e.printStackTrace();
    // }

  }

  @Test
  public void testMockSimple() {

    try {

      UserRequest userRequest = new UserRequest();
      userRequest.setAddress("北京");

      //拼装request
      MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
          .post("/world/second")
          .contentType(MediaType.APPLICATION_JSON)
          .content(JSON.toJSONString(userRequest));

      //执行
      MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

      //得到结果
      String context = mvcResult.getResponse().getContentAsString();

      //查看结果
      logger.info("context:{}", context);

    } catch (Exception e) {
      // e.printStackTrace();
    }

  }

  /**
   * 处理错误信息
   *
   * @param mvcInfo
   */
  private void handleErrorMessage(MvcInfo mvcInfo) {
    // TODO-lichunpeng 处理错误信息 2021/7/22-17:21

  }
  //
  // @Test
  // public void testSpring() {
  //
  //   // Assertions.assertNotNull(worldAController);
  //   // logger.info("worldController = {}", worldAController);
  //   //
  //   // Assertions.assertNotNull(environment);
  //   // logger.info("environment = {}", environment);
  //   //
  //   // MockHttpServletRequest mockHttpServletRequest =
  //   //     requestBuilder.buildRequest(Objects.requireNonNull(webApplicationContext.getServletContext()));
  //
  //   MockHttpServletResponse mockHttpServletResponse = new MockHttpServletResponse();
  //   // mockHttpServletResponse.setCharacterEncoding(UTF_8);
  //   // mockHttpServletResponse.setContentType("text/html; charset=UTF-8");
  //
  //   UserRequest userRequest=new UserRequest();
  //  
  //   User user = worldAController.second(userRequest);
  //   logger.info("user = {}", JSON.toJSONString(user));
  //
  //   try {
  //     logger.info("ContentAsString = {}", mockHttpServletResponse.getContentAsString());
  //   } catch (Exception exception) {
  //     exception.printStackTrace();
  //   }
  //
  // }

}
