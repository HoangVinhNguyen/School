package com.school.service.impl;

import java.util.List;

import javax.inject.Inject;

import com.school.DAO.ILiteratureCourseDAO;
import com.school.model.LiteratureCourseModel;
import com.school.paging.Pageble;
import com.school.service.ILiteratureCourseService;

public class LiteratureCourseService implements ILiteratureCourseService {

	@Inject
	private ILiteratureCourseDAO literatureCourseDAO;

	@Override
	public List<LiteratureCourseModel> findAll(Pageble pageble) {
		return literatureCourseDAO.findAll(pageble);
	}

	@Override
	public List<LiteratureCourseModel> findAll() {
		return literatureCourseDAO.findAll();
	}

	@Override
	public LiteratureCourseModel findOne(long id) {
		return literatureCourseDAO.findOne(id);
	}

	@Override
	public LiteratureCourseModel findOneByUser(String userEmail) {
		return literatureCourseDAO.findOneByUser(userEmail);
	}

	@Override
	public LiteratureCourseModel findOneByPoint(Double point) {
		return literatureCourseDAO.findOneByPoint(point);
	}

	@Override
	public Long save(LiteratureCourseModel literatureCourseModel) {
		return literatureCourseDAO.save(literatureCourseModel);
	}

	@Override
	public Long delete(LiteratureCourseModel literatureCourseModel) {
		return literatureCourseDAO.delete(literatureCourseModel);
	}

	@Override
	public int getTotalItem() {
		return literatureCourseDAO.getTotalItem();
	}

}
