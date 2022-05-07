package com.yst.entity.dto;


import io.swagger.annotations.ApiModelProperty;

public class StudentDto {
  @ApiModelProperty(value="学生id",name="id",example="",hidden = false)
  private String id;

  @ApiModelProperty(value="学生姓名",name="name",example="")
  private String name;

  @ApiModelProperty(value="学生年龄",name="age",example="")
  private long age;

  @ApiModelProperty(value = "国家",name = "country",example = "")
  private String country;

  @ApiModelProperty(value = "爱好",name = "like",example = "")
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


  public long getAge() {
    return age;
  }

  public void setAge(long age) {
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
    this.likes = likes;
  }

}
