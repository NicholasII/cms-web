package com.sun.cms.web.dto.topic;

import com.sun.cms.model.BaseDto;

public class KeywordDto extends BaseDto{

	private static final long serialVersionUID = 1L;

	private Integer id;

    private String name;

    private Integer times;

    private String namefullpy;

    private String nameshortpy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    public String getNamefullpy() {
        return namefullpy;
    }

    public void setNamefullpy(String namefullpy) {
        this.namefullpy = namefullpy == null ? null : namefullpy.trim();
    }

    public String getNameshortpy() {
        return nameshortpy;
    }

    public void setNameshortpy(String nameshortpy) {
        this.nameshortpy = nameshortpy == null ? null : nameshortpy.trim();
    }
}