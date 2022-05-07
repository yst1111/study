package com.yst.entity.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

/**
* 
* @TableName family
*/
public class FamilyDto implements Serializable {

    /**
    * 
    */
    @ApiModelProperty(value = "id")
    @Length(max= 36,message="编码长度不能超过36")
    private String id;
    /**
    * 
    */
    @ApiModelProperty(value = "birthday")
    @Length(max= 36,message="编码长度不能超过36")
    private String birthday;
    /**
    * 
    */
    @ApiModelProperty(value = "father")
    @Length(max= 36,message="编码长度不能超过36")
    private String father;
    /**
    * 
    */
    @ApiModelProperty(value = "mother")
    @Length(max= 36,message="编码长度不能超过36")
    private String mother;

    /**
    * 
    */
    private void setId(String id){
    this.id = id;
    }

    /**
    * 
    */
    private void setBirthday(String birthday){
    this.birthday = birthday;
    }

    /**
    * 
    */
    private void setFather(String father){
    this.father = father;
    }

    /**
    * 
    */
    private void setMother(String mother){
    this.mother = mother;
    }


    /**
    * 
    */
    private String getId(){
    return this.id;
    }

    /**
    * 
    */
    private String getBirthday(){
    return this.birthday;
    }

    /**
    * 
    */
    private String getFather(){
    return this.father;
    }

    /**
    * 
    */
    private String getMother(){
    return this.mother;
    }

}
