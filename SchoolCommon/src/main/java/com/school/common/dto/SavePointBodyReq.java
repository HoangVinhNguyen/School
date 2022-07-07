package com.school.common.dto;

import java.util.ArrayList;
import java.util.List;

public class SavePointBodyReq {

	private List<UserSavePointDto> listStudents = new ArrayList<>();

	public SavePointBodyReq() {
	}

	public SavePointBodyReq(List<UserSavePointDto> listStudents) {
		this.setListStudents(listStudents);
	}

	public List<UserSavePointDto> getListStudents() {
		return listStudents;
	}

	public void setListStudents(List<UserSavePointDto> listStudents) {
		this.listStudents = listStudents;
	}

}
