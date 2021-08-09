package org.lcp.learn.java.beans;

import java.io.Serializable;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/11/18-20:08
 */
public class Order implements Serializable {

  private static final long serialVersionUID = -6358320479613388407L;
  private Long orderId;
  private Long skuId;
  private int money;

  public Order() {
  }

  public Order(Long orderId, Long skuId, int money) {
    this.orderId = orderId;
    this.skuId = skuId;
    this.money = money;
  }

  public Long getOrderId() {
    return orderId;
  }

  public final void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  public Long getSkuId() {
    return skuId;
  }

  public final void setSkuId(Long skuId) {
    this.skuId = skuId;
  }

  public int getMoney() {
    return money;
  }

  public final void setMoney(int money) {
    this.money = money;
  }
}
