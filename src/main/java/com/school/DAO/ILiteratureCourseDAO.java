package com.school.DAO;

import java.util.List;

import com.school.model.LiteratureCourseModel;
import com.school.paging.Pageble;

public interface ILiteratureCourseDAO extends GenericDAO<LiteratureCourseModel>{

	public List<LiteratureCourseModel> findAll(Pageble pageble);
	public List<LiteratureCourseModel> findAll();
	public LiteratureCourseModel findOne(long id);
	public LiteratureCourseModel findOneByUser(String userEmail);
	public LiteratureCourseModel findOneByPoint(Double point);
	public Long save(LiteratureCourseModel literatureCourseModel);
	public Long delete(LiteratureCourseModel literatureCourseModel);
	public int getTotalItem();
}
