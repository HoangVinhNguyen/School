package com.school.DAO.impl;

import java.util.List;

import javax.inject.Inject;

import com.school.DAO.IPostStatusDAO;
import com.school.mapper.PostStatusMapper;
import com.school.model.ClassroomModel;
import com.school.model.PostStatusModel;
import com.school.paging.Pageble;
import com.school.service.IClassroomService;
import com.school.service.IUserService;

public class PostStatusDAO extends AbstractDAO<PostStatusModel> implements IPostStatusDAO{

	@Inject
	private IUserService userService;
	
	@Inject
	private IClassroomService classroomService;
	
	@Override
	public List<PostStatusModel> findAll(Pageble pageble) {
		String sql = "SELECT * FROM post_status AND is_deleted = 0";
		return query(sql, new PostStatusMapper());
	}

	@Override
	public PostStatusModel findOne(long id) {
		String sql = "SELECT * FROM post_status WHERE id = ? AND is_deleted = 0";
		List<PostStatusModel> postStatus = query(sql, new PostStatusMapper(), id);
		return postStatus.isEmpty() ? null : postStatus.get(0);
	}

	@Override
	public List<PostStatusModel> findOneByUser(String userEmail) {
		Long id = userService.findByEmail(userEmail);
		if (id != null && id > 0) {
			String sql = "SELECT * FROM post_status WHERE user = ? AND is_deleted = 0";
			List<PostStatusModel> postStatus = query(sql, new PostStatusMapper(), id);
			return postStatus.isEmpty() ? null : postStatus;
		}
		return null;
	}
	
	@Override
	public List<PostStatusModel> findOneByClassroom(String nameClassroom) {
		ClassroomModel classroom = classroomService.findOneByName(nameClassroom);
		if (classroom != null) {
			String sql = "SELECT * FROM post_status WHERE user = ? AND is_deleted = 0";
			List<PostStatusModel> postStatus = query(sql, new PostStatusMapper(), classroom.getId());
			return postStatus.isEmpty() ? null : postStatus;
		}
		return null;
	}

	@Override
	public int getTotalItem() {
		String sql = "SELECT count(*) FROM post_status WHERE is_deleted = 0";
		return count(sql);
	}

	@Override
	public List<PostStatusModel> findAll() {
		String sql = "SELECT * FROM post_status  WHERE is_deleted = 0";
		return query(sql, new PostStatusMapper());
	}

	@Override
	public Long delete(PostStatusModel postStatusModel) {
		String sql = "UPDATE post_status SET modified_by=?, modified_date=?, is_deleted = 1 WHERE id=?";
		return update(sql, postStatusModel.getModifiedBy(), postStatusModel.getModifiedDate(), postStatusModel.getId());
	}

}
