package com.yst.entity.pojo;


import lombok.Data;

public class Sorces {

  private String id;
  private String chinese;
  private String math;
  private String english;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getChinese() {
    return chinese;
  }

  public void setChinese(String chinese) {
    this.chinese = chinese;
  }


  public String getMath() {
    return math;
  }

  public void setMath(String math) {
    this.math = math;
  }


  public String getEnglish() {
    return english;
  }

  public void setEnglish(String english) {
    this.english = english;
  }

}
