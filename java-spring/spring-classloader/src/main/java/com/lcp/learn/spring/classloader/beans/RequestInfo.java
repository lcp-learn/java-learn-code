package com.lcp.learn.spring.classloader.beans;

import java.util.Arrays;
import java.util.Objects;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2019/9/20-13:12
 */
public class RequestInfo {

  private String path;
  private String method;
  private Object[] param;

  private Class apiClazz;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RequestInfo that = (RequestInfo) o;
    return Objects.equals(path, that.path) && Objects.equals(method, that.method) && Arrays.equals(param, that.param) && Objects
        .equals(apiClazz, that.apiClazz);
  }

  @Override
  public int hashCode() {
    int result = Objects.hash(path, method, apiClazz);
    result = 31 * result + Arrays.hashCode(param);
    return result;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public String getMethod() {
    return method;
  }

  public void setMethod(String method) {
    this.method = method;
  }

  public Object[] getParam() {
    return param;
  }

  public void setParam(Object[] param) {
    this.param = param;
  }

  public Class getApiClazz() {
    return apiClazz;
  }

  public void setApiClazz(Class apiClazz) {
    this.apiClazz = apiClazz;
  }
}
