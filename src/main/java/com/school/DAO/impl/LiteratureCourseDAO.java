package com.school.DAO.impl;

import java.util.List;

import javax.inject.Inject;

import com.school.DAO.ILiteratureCourseDAO;
import com.school.mapper.InClassroomMapper;
import com.school.mapper.LiteratureCourseMapper;
import com.school.model.ClassroomModel;
import com.school.model.InClassroomModel;
import com.school.model.LiteratureCourseModel;
import com.school.paging.Pageble;
import com.school.service.IUserService;

public class LiteratureCourseDAO extends AbstractDAO<LiteratureCourseModel> implements ILiteratureCourseDAO{

	
	@Inject
	private IUserService userService;
	
	@Override
	public List<LiteratureCourseModel> findAll(Pageble pageble) {
		String sql = "SELECT * FROM literature_course";
		return query(sql, new LiteratureCourseMapper());
	}

	@Override
	public LiteratureCourseModel findOne(long id) {
		String sql = "SELECT * FROM literature_course WHERE id = ? AND is_deleted = 0";
		List<LiteratureCourseModel> literatureCourseModel = query(sql, new LiteratureCourseMapper(), id);
		return literatureCourseModel.isEmpty() ? null : literatureCourseModel.get(0);
	}

	@Override
	public LiteratureCourseModel findOneByUser(String userEmail) {
		Long id = userService.findByEmail(userEmail);
		if (id != null) {
			String sql = "SELECT * FROM literature_course WHERE user_id = ? AND is_deleted = 0";
			List<LiteratureCourseModel> literatureCourseModel = query(sql, new LiteratureCourseMapper(), id);
			return literatureCourseModel.isEmpty() ? null : literatureCourseModel.get(0);
		}
		return null;
	}
	
	@Override
	public LiteratureCourseModel findOneByPoint(Double point) {
		String sql = "SELECT * FROM literature_course WHERE point = ? AND is_deleted = 0";
		List<LiteratureCourseModel> literatureCourseModel = query(sql, new LiteratureCourseMapper(), point);
		return literatureCourseModel.isEmpty() ? null : literatureCourseModel.get(0);
	}

	@Override
	public int getTotalItem() {
		String sql = "SELECT count(*) FROM literature_course WHERE is_deleted = 0";
		return count(sql);
	}

	@Override
	public Long save(LiteratureCourseModel literatureCourseModel) {
		LiteratureCourseModel literatureCourseModelCheck;
		if (literatureCourseModel.getId() != null) {
			literatureCourseModelCheck = findOne(literatureCourseModel.getId());
			if (literatureCourseModelCheck != null && literatureCourseModelCheck.getId() != 0) {
				String sql = "UPDATE literature_course SET user_id=?, point=?, modified_by=?, modified_date=? WHERE id=?";
				return update(sql, literatureCourseModel.getUserId(), literatureCourseModel.getPoint(),
						literatureCourseModel.getModifiedBy(), literatureCourseModel.getModifiedDate(), literatureCourseModel.getId());
			}
		}
		String sql = "INSERT INTO literature_course (user_id, point, created_by, created_date, modified_date) values (?, ?, ?, ?, ?)";
		return insert(sql, literatureCourseModel.getUserId(), literatureCourseModel.getPoint(), literatureCourseModel.getCreatedBy(), literatureCourseModel.getCreatedDate(), literatureCourseModel.getModifiedDate());
	}

	@Override
	public List<LiteratureCourseModel> findAll() {
		String sql = "SELECT * FROM literature_course  WHERE is_deleted = 0";
		return query(sql, new LiteratureCourseMapper());
	}

	@Override
	public Long delete(LiteratureCourseModel literatureCourseModel) {
		String sql = "UPDATE literature_course SET modified_by=?, modified_date=?, is_deleted = 1 WHERE id=?";
		return update(sql, literatureCourseModel.getModifiedBy(), literatureCourseModel.getModifiedDate(), literatureCourseModel.getId());
	}

}
