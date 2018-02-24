package com.sun.cms.web.dto.topic;

import com.sun.cms.model.BaseDto;

public class AttachmentDto extends BaseDto{
	
	private static final long serialVersionUID = 1L;

	private String id;

    private String topicid;

    private String newname;

    private String oldname;

    private String type;

    private String suffix;

    private Long size;

    private Integer isindexpic;

    private Integer isimg;

    private Integer isattach;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTopicid() {
        return topicid;
    }

    public void setTopicid(String topicid) {
        this.topicid = topicid == null ? null : topicid.trim();
    }

    public String getNewname() {
        return newname;
    }

    public void setNewname(String newname) {
        this.newname = newname == null ? null : newname.trim();
    }

    public String getOldname() {
        return oldname;
    }

    public void setOldname(String oldname) {
        this.oldname = oldname == null ? null : oldname.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix == null ? null : suffix.trim();
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Integer getIsindexpic() {
        return isindexpic;
    }

    public void setIsindexpic(Integer isindexpic) {
        this.isindexpic = isindexpic;
    }

    public Integer getIsimg() {
        return isimg;
    }

    public void setIsimg(Integer isimg) {
        this.isimg = isimg;
    }

    public Integer getIsattach() {
        return isattach;
    }

    public void setIsattach(Integer isattach) {
        this.isattach = isattach;
    }
}