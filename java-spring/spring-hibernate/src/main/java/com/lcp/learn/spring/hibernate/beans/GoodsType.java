package com.lcp.learn.spring.hibernate.beans;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/3/17-17:58
 */
public enum GoodsType {

  BOOK(1),
  FOOD(2);

  private int code;

  GoodsType(int code) {
    this.code = code;
  }

  public static GoodsType getTypeByCode(int code) {
    GoodsType[] goodsTypeArray = values();
    for (GoodsType goodsType : goodsTypeArray) {
      if (code == goodsType.code) {
        return goodsType;
      }
    }
    return null;
  }

  public int getCode() {
    return code;
  }
}
