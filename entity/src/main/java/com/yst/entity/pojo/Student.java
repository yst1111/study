package com.yst.entity.pojo;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@TableName("student")
public class Student implements Serializable {

  //private static final

  private String id;
  private String name;
  private Integer age;
  private String country;
  @TableField(exist = false)
  private String like;
  private String likes;


  public String getId() {
    return id;
  }

  public void setId(String id) {
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

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }


  public String getLikes() {
    return likes;
  }

  public void setLikes(String like) {
    this.likes = like;
  }

  public String getLike() {
    return like;
  }

  public void setLike(String like) {
    this.like = like;
  }
}
