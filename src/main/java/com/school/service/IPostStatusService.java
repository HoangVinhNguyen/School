package com.school.service;

import java.util.List;

import com.school.model.PostStatusModel;
import com.school.paging.Pageble;

public interface IPostStatusService {

	public List<PostStatusModel> findAll(Pageble pageble);
	public List<PostStatusModel> findAll();
	public PostStatusModel findOne(long id);
	public List<PostStatusModel> findOneByUser(String userEmail);
	public List<PostStatusModel> findOneByClassroom(String nameClassroom);
	public Long delete(PostStatusModel postStatusModel);
	public int getTotalItem();
}
