package com.lcp.learn.spring.hibernate.beans;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/3/17-14:16
 */
@Entity
public class UOrder implements Serializable {

  private static final long serialVersionUID = 2193119939202719035L;
  // @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column(name = "id")
  private Integer id;

  @Column(name = "user_id")
  private Integer userId;

  @Column(name = "goods_name")
  private String goodsName;

  public Integer getId() {
    return id;
  }

  public final void setId(Integer id) {
    this.id = id;
  }

  public Integer getUserId() {
    return userId;
  }

  public final void setUserId(Integer userId) {
    this.userId = userId;
  }

  public String getGoodsName() {
    return goodsName;
  }

  public final void setGoodsName(String goodsName) {
    this.goodsName = goodsName;
  }
}
