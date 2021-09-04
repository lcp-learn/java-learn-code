package com.lcp.learn.spring.spb.simple.controller.advice;

import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/9/16-14:39
 */
@RestControllerAdvice
@Deprecated
public class MyControllerAdvice {//implements ResponseBodyAdvice<ResponseInfo> {
  //
  // private final Logger logger = LoggerFactory.getLogger(MyControllerAdvice.class);
  //
  // /**
  //  * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
  //  * @param webDataBinder
  //  */
  // @InitBinder
  // public void initBinder(WebDataBinder webDataBinder) {
  //     // logger.info("webDataBinder:{}", JSON.toJSONString(webDataBinder));
  // }
  //
  // /**
  //  * 把值绑定到Model中，使全局@RequestMapping可以获取到该值
  //  * @param model
  //  */
  // @ModelAttribute
  // public void addAttributes(Model model) {
  //     // model.addAttribute("author", "james bond");
  //     // logger.info("model:{}", JSON.toJSONString(model));
  // }
  //
  // /**
  //  * 全局异常捕捉处理
  //  * @param exception
  //  * @return
  //  */
  // @ExceptionHandler(value = {ParamException.class, HttpMessageNotReadableException.class})
  // public ResponseInfo<String> errorHandler(Exception exception,
  //                                          HttpServletRequest httpServletRequest) {
  //
  //     // TODO-lichunpeng https://blog.csdn.net/yb223731/article/details/104977453/ 2020/10/19-16:28
  //     // https://www.cnblogs.com/lvbinbin2yujie/p/10574812.html
  //
  //     var response = new ResponseInfo<String>();
  //     response.setData(EMPTY);
  //
  //     if (exception instanceof ParamException) {
  //
  //         var paramException = (ParamException) exception;
  //         CheckError checkError = paramException.getCheckError();
  //         logger.info("ex:{}.ex.message:{}", exception.getClass().getName(), exception.getMessage());
  //
  //         response.setCode(checkError.getCode());
  //         response.setDesc(exception.getMessage());
  //
  //     } else if (exception instanceof HttpMessageNotReadableException) {
  //         var httpMessageNotReadableException = (HttpMessageNotReadableException) exception;
  //         httpMessageNotReadableException.printStackTrace();
  //
  //     } else {
  //         exception.printStackTrace();
  //
  //     }
  //
  //     return response;
  //
  //
  // }
  //
  // @Override
  // public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
  //     return false;
  // }
  //
  // @Override
  // public ResponseInfo beforeBodyWrite(ResponseInfo body, MethodParameter returnType, MediaType
  // selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
  // ServerHttpResponse response) {
  //     return null;
  // }
}
