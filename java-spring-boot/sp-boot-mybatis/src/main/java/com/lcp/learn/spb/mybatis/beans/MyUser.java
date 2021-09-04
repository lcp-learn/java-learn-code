package com.lcp.learn.spb.mybatis.beans;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

/**
 * desc:    <br/>
 * @since 2020/10/13-13:57 
 * @author lichunpeng
 */
public class MyUser implements Serializable {

    private static final long serialVersionUID = -2449881603682848529L;
    private Long id;
    private String name;
    private Integer age;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof MyUser)) return false;

        MyUser myUser = (MyUser) o;

        return new EqualsBuilder()
                .append(id, myUser.id)
                .append(name, myUser.name)
                .append(age, myUser.age)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(name)
                .append(age)
                .toHashCode();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
