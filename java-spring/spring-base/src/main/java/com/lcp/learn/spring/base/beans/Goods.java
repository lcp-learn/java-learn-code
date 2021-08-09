package com.lcp.learn.spring.base.beans;

import java.io.Serializable;

public class Goods implements Serializable {

  private static final long serialVersionUID = -8516212546951331731L;

  private String goodaName;
  private String sku;
  private int price;

  public String getGoodaName() {
    return goodaName;
  }

  public final void setGoodaName(String goodaName) {
    this.goodaName = goodaName;
  }

  public String getSku() {
    return sku;
  }

  public final void setSku(String sku) {
    this.sku = sku;
  }

  public int getPrice() {
    return price;
  }

  public final void setPrice(int price) {
    this.price = price;
  }
}
