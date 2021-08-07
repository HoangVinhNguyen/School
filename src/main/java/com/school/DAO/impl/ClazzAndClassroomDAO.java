package com.school.DAO.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.school.DAO.IClazzAndClassroomDAO;
import com.school.constant.SystemConstant;
import com.school.entity.ClassroomEntity;
import com.school.entity.ClazzAndClassroomEntity;
import com.school.entity.ClazzEntity;
import com.school.model.ClassroomModel;
import com.school.model.ClazzModel;
import com.school.service.IClassroomService;
import com.school.service.IClazzService;

@Repository
@Transactional
public class ClazzAndClassroomDAO implements IClazzAndClassroomDAO{

	@Autowired
	private IClazzService clazzService;
	
	@Autowired 
	private IClassroomService classroomService;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<ClazzAndClassroomEntity> findAll() {
		String hql = "SELECT c FROM ClazzAndClassroomEntity c WHERE c.isDeleted=0";
		@SuppressWarnings("unchecked")
		List<ClazzAndClassroomEntity> list = sessionFactory.getCurrentSession().createQuery(hql).list();
		return list;
	}

	@Override
	public ClazzAndClassroomEntity findOne(ClazzEntity clazz, ClassroomEntity classroom) {
		if (clazz != null && classroom != null) {
			String hql = "SELECT c FROM ClazzAndClassroomEntity c WHERE c.clazz=?0 AND c.classroom=?1 AND c.isDeleted=0";
			List results = sessionFactory.getCurrentSession().createQuery(hql)
					.setParameter(0, clazz)
					.setParameter(1, classroom).getResultList();
			if (!results.isEmpty()) {
				return (ClazzAndClassroomEntity) results.get(0);
			}
		}
		return null;
	}
	
	@Override
	public ClazzAndClassroomEntity findOneById(long id) {
		String hql = "SELECT c FROM ClazzAndClassroomEntity c WHERE c.id=?0 AND c.isDeleted=0";
		List list = sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter(0, id).getResultList();
		if (!list.isEmpty()) {
			return (ClazzAndClassroomEntity) list.get(0);
		}
		return null;
	}

	@Override
	public List<ClazzAndClassroomEntity> findOneByClazzName(String name) {
		ClazzModel classModel = clazzService.findOneByName(name);
		if (classModel != null) {
			ClazzEntity clazzEntity = new ClazzEntity();
			clazzEntity.loadFromDTO(classModel);
			String hql = "SELECT c FROM ClazzAndClassroomEntity c WHERE c.clazz=?0 AND c.isDeleted=0";
			List list = sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, clazzEntity).getResultList();
			if (!list.isEmpty()) {
				return list;
			}
		}
		return null;
	}
	
	@Override
	public List<ClazzAndClassroomEntity> findOneByClassroomName(String name) {
		ClassroomModel classroomModel = classroomService.findOneByName(name);
		if (classroomModel != null) {
			ClassroomEntity classroomEntity = new ClassroomEntity();
			classroomEntity.loadFromDTO(classroomModel);
			String hql = "SELECT c FROM ClazzAndClassroomEntity WHERE c.classroom=?0 AND c.isDeleted=0";
			List list = sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, classroomEntity).getResultList();
			if (!list.isEmpty()) {
				return list;
			}
		}
		return null;
	}

	@Override
	public int getTotalItem() {
		String hql = "SELECT c FROM ClazzAndClassroomEntity c WHERE c.isDeleted=0";
		return (int) sessionFactory.getCurrentSession().createQuery(hql).uniqueResult();
	}

	@Override
	public Long save(ClazzAndClassroomEntity entity) {
		if (entity.getId() != null) {
			ClazzAndClassroomEntity entityCheck = findOneById(entity.getId());
			if (entityCheck != null) {
				ClazzModel clazzModel = clazzService.findOne(entity.getClazz().getId());
				ClassroomModel classroomModel = classroomService.findOne(entity.getClassroom().getId());
				if (clazzModel != null && classroomModel != null) {
					entityCheck.setClazz(entity.getClazz());
					entityCheck.setClassroom(entity.getClassroom());
					entityCheck.setModifiedBy(entity.getModifiedBy());
					entityCheck.setModifiedDate(entity.getModifiedDate());
					ClazzAndClassroomEntity result = (ClazzAndClassroomEntity) sessionFactory.getCurrentSession().merge(entityCheck);
					return result.getId();
				}
				return SystemConstant.ERROR;
			}
		}
		else {
			ClazzModel clazzModel = clazzService.findOne(entity.getClazz().getId());
			ClassroomModel classroomModel = classroomService.findOne(entity.getClassroom().getId());
			if (clazzModel != null && classroomModel != null) {
				ClazzEntity clazzEntity = new ClazzEntity();
				ClassroomEntity classroomEntity = new ClassroomEntity();
				clazzEntity.loadFromDTO(clazzModel);
				classroomEntity.loadFromDTO(classroomModel);
				ClazzAndClassroomEntity entityCheck = findOne(clazzEntity, classroomEntity); // Check duplicate.
				if (entityCheck == null) {
					sessionFactory.getCurrentSession().persist(entity);
					return entity.getId();
				}
				return SystemConstant.DUPLICATE;
			}
		}
		return SystemConstant.ERROR;
	}

	@Override
	public Long delete(ClazzAndClassroomEntity entity) {
		if (entity.getId() != null) {
			ClazzAndClassroomEntity userAndCourseEntity = findOneById(entity.getId());
			if (userAndCourseEntity != null) {
				userAndCourseEntity.setIsDeleted((byte) 1);
				userAndCourseEntity.setModifiedBy(entity.getModifiedBy());
				userAndCourseEntity.setModifiedDate(entity.getModifiedDate());
				ClazzAndClassroomEntity result = (ClazzAndClassroomEntity) sessionFactory.getCurrentSession().merge(userAndCourseEntity);
				return result.getId();
			}
		}
		return SystemConstant.ERROR;
	}
}
