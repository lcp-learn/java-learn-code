package com.lcp.learn.spring.hibernate.beans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/3/17-13:31
 */
@Table(name = "goods")
@Entity
public class Goods implements Serializable {

  private static final long serialVersionUID = 7699748525929164046L;

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column(name = "id")
  private Integer id;

  @Column(name = "goods_name")
  private String goodsName;

  @Column(name = "goods_type")
  private GoodsType goodsType;

  @Column(name = "create_time")
  private Date createTime;

  public Goods() {
  }

  public Goods(Integer id, String goodsName) {
    this.id = id;
    this.goodsName = goodsName;
  }

  public Goods(Integer id, String goodsName, GoodsType goodsType, Date createTime) {
    this.id = id;
    this.goodsName = goodsName;
    this.goodsType = goodsType;
    this.createTime = createTime;
  }

  public Goods(String goodsName) {
    this.goodsName = goodsName;
  }

  public Integer getId() {
    return id;
  }

  public final void setId(Integer id) {
    this.id = id;
  }

  public String getGoodsName() {
    return goodsName;
  }

  public final void setGoodsName(String goodsName) {
    this.goodsName = goodsName;
  }

  public GoodsType getGoodsType() {
    return goodsType;
  }

  public final void setGoodsType(GoodsType goodsType) {
    this.goodsType = goodsType;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public final void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }
}
