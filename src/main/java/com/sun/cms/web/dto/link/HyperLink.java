package com.sun.cms.web.dto.link;

import com.sun.cms.model.BaseDto;

public class HyperLink extends BaseDto{
    private Integer id;

    private String title;

    private String url;

    private String type;

    private int newwin;

    private String urlid;

    private String urlclass;

    private Integer pos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public int getNewwin() {
        return newwin;
    }

    public void setNewwin(int newwin) {
        this.newwin = newwin;
    }

    public String getUrlid() {
        return urlid;
    }

    public void setUrlid(String urlid) {
        this.urlid = urlid == null ? null : urlid.trim();
    }

    public String getUrlclass() {
        return urlclass;
    }

    public void setUrlclass(String urlclass) {
        this.urlclass = urlclass == null ? null : urlclass.trim();
    }

    public Integer getPos() {
        return pos;
    }

    public void setPos(Integer pos) {
        this.pos = pos;
    }
}