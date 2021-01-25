package com.school.service.impl;

import java.util.List;

import javax.inject.Inject;

import com.school.DAO.IPostStatusDAO;
import com.school.model.PostStatusModel;
import com.school.paging.Pageble;
import com.school.service.IPostStatusService;

public class PostStatusService implements IPostStatusService{

	@Inject
	private IPostStatusDAO postStatusDAO;

	@Override
	public List<PostStatusModel> findAll(Pageble pageble) {
		return postStatusDAO.findAll(pageble);
	}

	@Override
	public List<PostStatusModel> findAll() {
		return postStatusDAO.findAll();
	}

	@Override
	public PostStatusModel findOne(long id) {
		return postStatusDAO.findOne(id);
	}

	@Override
	public List<PostStatusModel> findOneByUser(String userEmail) {
		return postStatusDAO.findOneByUser(userEmail);
	}

	@Override
	public List<PostStatusModel> findOneByClassroom(String nameClassroom) {
		return postStatusDAO.findOneByClassroom(nameClassroom);
	}

	@Override
	public Long delete(PostStatusModel postStatusModel) {
		return postStatusDAO.delete(postStatusModel);
	}

	@Override
	public int getTotalItem() {
		return postStatusDAO.getTotalItem();
	}
}
