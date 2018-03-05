package com.sun.cms.web.dto.indexpic;

import com.sun.cms.model.BaseDto;

public class IndexNewsPic extends BaseDto{
	private String id;
	
	private int isIndexPic;
	
	private String title;
	
	private String newName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getIsIndexPic() {
		return isIndexPic;
	}

	public void setIsIndexPic(int isIndexPic) {
		this.isIndexPic = isIndexPic;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNewName() {
		return newName;
	}

	public void setNewName(String newName) {
		this.newName = newName;
	}

}
