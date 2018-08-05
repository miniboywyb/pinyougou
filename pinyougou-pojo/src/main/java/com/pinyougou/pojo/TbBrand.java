package com.pinyougou.pojo;

import java.io.Serializable;

public class TbBrand implements Serializable {
    private Long id;
    private String name;
    private String firstChar;

    @Override
    public String toString() {
        return "TbBrand{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", firstChar='" + firstChar + '\'' +
                '}';
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

    public String getFirstChar() {
        return firstChar;
    }

    public void setFirstChar(String firstChar) {
        this.firstChar = firstChar;
    }

    public TbBrand(Long id, String name, String firstChar) {
        this.id = id;
        this.name = name;
        this.firstChar = firstChar;
    }

    public TbBrand() {
    }
}
