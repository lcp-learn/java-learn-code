package com.lcp.learn.spring.mvc.validator;

import com.lcp.learn.spring.mvc.controller.request.UserRequest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2021/7/23-13:55
 */
@ComponentScan
public class UserValidator implements Validator {

  @Override
  public boolean supports(Class<?> clazz) {
    //要验证的model，返回值为false则不验证
    return UserRequest.class.isAssignableFrom(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    UserRequest userRequest = (UserRequest) target;//object要验证的对象

    if (userRequest.getAddress() == null) {
      errors.rejectValue("address", null, "address is null");
    }

    //goods.gname.required 是错误消息属性文件中的编码（国际化后对应的是国际化的信息
    // ValidationUtils.rejectIfEmpty(errors, "gname", "goods.gname.required");
    // ValidationUtils.rejectIfEmpty(errors, "gdescription", "goods.gdescription.required");
    // if (userRequest.getGprice() > 100 || userRequest.getGprice() < 0) {
    //   errors.rejectValue("gprice", "gprice.invalid");
    // }
    // Date goodsDate = userRequest.getGdate();
    // //在系统时间之后
    // if (goodsDate != null && goodsDate.after(new Date())) {
    //   errors.rejectValue("gdate", "gdate.invalid");
    // }
  }
}
