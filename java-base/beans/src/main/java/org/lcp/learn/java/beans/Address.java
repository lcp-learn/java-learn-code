package org.lcp.learn.java.beans;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;

import java.io.Serializable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/7/7-16:30
 */
public class Address implements Serializable {

  private static final long serialVersionUID = -7604961607884245578L;
  private String email = EMPTY;
  private String httpUrl = EMPTY;
  private int houseNumber = INTEGER_ZERO;

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getHttpUrl() {
    return httpUrl;
  }

  public void setHttpUrl(String httpUrl) {
    this.httpUrl = httpUrl;
  }

  public int getHouseNumber() {
    return houseNumber;
  }

  public void setHouseNumber(int houseNumber) {
    this.houseNumber = houseNumber;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (obj == this) {
      return true;
    }
    if (obj.getClass() != getClass()) {
      return false;
    }
    Address rhs = (Address) obj;
    return new EqualsBuilder().append(this.email, rhs.email).append(this.httpUrl, rhs.httpUrl)
        .append(this.houseNumber, rhs.houseNumber).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder().append(email).append(httpUrl).append(houseNumber).toHashCode();
  }
}
