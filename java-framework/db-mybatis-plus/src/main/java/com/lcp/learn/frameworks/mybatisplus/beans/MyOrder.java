package com.lcp.learn.frameworks.mybatisplus.beans;

import static com.baomidou.mybatisplus.annotation.IdType.AUTO;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2021/2/7-17:34
 */
@TableName("my_order")
public class MyOrder implements Serializable {

  private static final long serialVersionUID = -2001244223184819615L;

  @TableId(value = "id", type = AUTO)
  private Long id;
  private Long orderId;
  private Integer userId;
  private Date createTime;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }
}
