package com.yst.entity.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

/**
* 
* @TableName sorcesNO
*/
public class SorcesnoDto implements Serializable {

    /**
    * 
    */
    @Size(max= 36,message="编码长度不能超过36")
    @ApiModelProperty(value = "id")
    @Length(max= 36,message="编码长度不能超过36")
    private String id;
    /**
    * 
    */
    @Size(max= 36,message="编码长度不能超过36")
    @ApiModelProperty(value = "no")
    @Length(max= 36,message="编码长度不能超过36")
    private String sorcesno;
    /**
    * 
    */
    @Size(max= 255,message="编码长度不能超过255")
    @ApiModelProperty(value = "msg")
    @Length(max= 255,message="编码长度不能超过255")
    private String sorcemsg;

    public SorcesnoDto() {
    }

    public SorcesnoDto( String id, String sorcesno, String sorcemsg) {
        this.id = id;
        this.sorcesno = sorcesno;
        this.sorcemsg = sorcemsg;
    }

    /**
    * 
    */
    private void setId(String id){
    this.id = id;
    }

    /**
    * 
    */
    private void setSorcesno(String sorcesno){
    this.sorcesno = sorcesno;
    }

    /**
    * 
    */
    private void setSorcemsg(String sorcemsg){
    this.sorcemsg = sorcemsg;
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
    private String getSorcesno(){
    return this.sorcesno;
    }

    /**
    * 
    */
    private String getSorcemsg(){
    return this.sorcemsg;
    }

}
