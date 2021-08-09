package com.lcp.learn.spring.mvc.controller.request;

import jakarta.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2021/7/23-13:31
 */
public class UserRequest implements Serializable {

  private static final long serialVersionUID = 6326650811747564988L;

  @NotNull(message = "id不能为空!")
  private String address;

  public String getAddress() {
    return address;
  }

  public final void setAddress(String address) {
    this.address = address;
  }
}
