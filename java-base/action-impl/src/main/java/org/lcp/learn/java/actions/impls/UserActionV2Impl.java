package org.lcp.learn.java.actions.impls;

import org.lcp.learn.java.api.UserAction;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/12/8-17:53
 */
public class UserActionV2Impl extends AbstractAction implements UserAction {

  @Override
  public String doHaha(String name) {
    return name + ",haha~~";
  }

  @Override
  public int getVersion() {
    return 203;
  }

}
