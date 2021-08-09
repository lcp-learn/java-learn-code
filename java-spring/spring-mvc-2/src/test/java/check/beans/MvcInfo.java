package check.beans;

import java.util.Map;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2021/7/22-16:24
 */
public class MvcInfo {

  private String url;
  private Map<String, String> param;
  private String description;

  public MvcInfo() {
  }

  public MvcInfo(String description, String url, Map<String, String> param) {
    this.url = url;
    this.param = param;
    this.description = description;
  }

  public String getDescription() {
    return description;
  }

  public final void setDescription(String description) {
    this.description = description;
  }

  public String getUrl() {
    return url;
  }

  public final void setUrl(String url) {
    this.url = url;
  }

  public Map<String, String> getParam() {
    return param;
  }

  public final void setParam(Map<String, String> param) {
    this.param = param;
  }
}
