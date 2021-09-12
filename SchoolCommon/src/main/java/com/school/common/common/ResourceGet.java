package com.school.common.common;

public class ResourceGet {

	public String getUserImagePathWithId(Integer id, String photoName) {
		StringBuilder pathImg = new StringBuilder();
		pathImg.append(SystemConstant.FORWARD_SLASH);
		pathImg.append(SystemConstant.PHOTOS_OF_USERS_FOLDER);
		pathImg.append(SystemConstant.FORWARD_SLASH);
		pathImg.append(id.toString());
		pathImg.append(SystemConstant.FORWARD_SLASH);
		pathImg.append(photoName);
		return pathImg.toString();
	}
}
