package com.lcp.learn.spring.spb.simple.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import com.alibaba.fastjson.JSON;
import com.lcp.learn.spring.spb.simple.beans.User;
import com.lcp.learn.spring.spb.simple.beans.request.RequestUser;
import com.lcp.learn.spring.spb.simple.beans.response.ResponseInfo;
import com.lcp.learn.spring.spb.simple.exceptions.CheckError;
import com.lcp.learn.spring.spb.simple.exceptions.ParamException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/ex/check")
public class ExceptionController extends AbstractController {

  private final Logger logger = LoggerFactory.getLogger(ExceptionController.class);

  @RequestMapping(value = "/throw1", method = {POST})
  public ResponseInfo<User> throw1(
      @Validated @RequestBody RequestUser requestUser,
      BindingResult bindingResult) throws Exception {

    logger.info("bindingResult.errorCount:{}", bindingResult.getErrorCount());

    if (bindingResult.hasErrors()) {
      FieldError fieldError = bindingResult.getFieldError();
      String message = fieldError.getDefaultMessage();
      throw new ParamException(CheckError.PARAM_EMPTY, message);
    }

    User user = new User(requestUser.getName(), requestUser.getAge());
    logger.info("create user:{}", JSON.toJSONString(user));
    return getSuccessResponse(user);
  }

}
