package com.yst.entity.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @creator: ly-yangst
 * @date: 2022/6/6
 */
public class TPojo<T> {
    private String name;
    private String age;
    private T likes;

    public static <T> TPojo<T> build(T t){
        TPojo<T> ttPojo = new TPojo<>();
        ttPojo.setLikes(t);
        return ttPojo;
    }

    public TPojo() {
    }

    public TPojo(String name, String age, T likes) {
        this.name = name;
        this.age = age;
        this.likes = likes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public T getLikes() {
        return likes;
    }

    public void setLikes(T likes) {
        this.likes = likes;
    }
}
