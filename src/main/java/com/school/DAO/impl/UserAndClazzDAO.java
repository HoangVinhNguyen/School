package com.school.DAO.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.school.DAO.IUserAndClazzDAO;
import com.school.constant.SystemConstant;
import com.school.entity.ClazzEntity;
import com.school.entity.ClazzEntity;
import com.school.entity.UserAndClazzEntity;
import com.school.entity.UserEntity;
import com.school.model.ClazzModel;
import com.school.model.UserModel;
import com.school.service.IClazzService;
import com.school.service.IUserService;

@Repository
@Transactional
public class UserAndClazzDAO implements IUserAndClazzDAO{

	@Autowired
	private IUserService userService;
	
	@Autowired 
	private IClazzService clazzService;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<UserAndClazzEntity> findAll() {
		String hql = "SELECT c FROM UserAndClazzEntity c WHERE c.isDeleted=0";
		@SuppressWarnings("unchecked")
		List<UserAndClazzEntity> list = sessionFactory.getCurrentSession().createQuery(hql).list();
		return list;
	}

	@Override
	public UserAndClazzEntity findOne(UserEntity user, ClazzEntity clazz) {
		if (user != null && clazz != null) {
			String hql = "SELECT c FROM UserAndClazzEntity c WHERE c.user=?0 AND c.clazz=?1 AND c.isDeleted=0";
			List results = sessionFactory.getCurrentSession().createQuery(hql)
					.setParameter(0, user)
					.setParameter(1, clazz).getResultList();
			if (!results.isEmpty()) {
				return (UserAndClazzEntity) results.get(0);
			}
		}
		return null;
	}
	
	@Override
	public UserAndClazzEntity findOneByID(long id) {
		String hql = "SELECT c FROM UserAndClazzEntity c WHERE c.id=?0 AND c.isDeleted=0";
		List list = sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter(0, id).getResultList();
		if (!list.isEmpty()) {
			return (UserAndClazzEntity) list.get(0);
		}
		return null;
	}

	@Override
	public List<UserAndClazzEntity> findOneByUserEmail(String userEmail) {
		UserModel userCheck = userService.findByEmail(userEmail);
		if (userCheck != null) {
			UserEntity userEntity = new UserEntity();
			userEntity.loadFromDTO(userCheck);
			String hql = "SELECT c FROM UserAndClazzEntity c WHERE c.user=?0 AND c.isDeleted=0";
			List list = sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, userEntity).getResultList();
			if (!list.isEmpty()) {
				return list;
			}
		}
		return null;
	}
	
	@Override
	public List<UserAndClazzEntity> findOneByClazzName(String name) {
		ClazzModel model = clazzService.findOneByName(name);
		if (model != null) {
			ClazzEntity entity = new ClazzEntity();
			entity.loadFromDTO(model);
			String hql = "SELECT c FROM UserAndClazzEntity WHERE c.clazz=?0 AND c.isDeleted=0";
			List list = sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, entity).getResultList();
			if (!list.isEmpty()) {
				return list;
			}
		}
		return null;
	}

	@Override
	public int getTotalItem() {
		String hql = "SELECT c FROM UserAndClazzEntity c WHERE c.isDeleted=0";
		return (int) sessionFactory.getCurrentSession().createQuery(hql).uniqueResult();
	}

	@Override
	public Long save(UserAndClazzEntity entity) {
		if (entity.getId() != null) {
			UserAndClazzEntity userAndClazzEntity = findOneByID(entity.getId());
			if (userAndClazzEntity != null) {
				UserModel user = userService.findOne(entity.getUser().getId());
				ClazzModel clazz = clazzService.findOne(entity.getClazz().getId());
				if (user != null && clazz != null) {
					userAndClazzEntity.setClazz(entity.getClazz());
					userAndClazzEntity.setUser(entity.getUser());
					userAndClazzEntity.setModifiedBy(entity.getModifiedBy());
					userAndClazzEntity.setModifiedDate(entity.getModifiedDate());
					UserAndClazzEntity result = (UserAndClazzEntity) sessionFactory.getCurrentSession().merge(userAndClazzEntity);
					return result.getId();
				}
				return SystemConstant.ERROR;
			}
		}
		else {
			UserModel user = userService.findOne(entity.getUser().getId());
			ClazzModel clazz = clazzService.findOne(entity.getClazz().getId());
			if (user != null && clazz != null) {
				UserEntity userEntity = new UserEntity();
				ClazzEntity clazzEntity = new ClazzEntity();
				userEntity.loadFromDTO(user);
				clazzEntity.loadFromDTO(clazz);
				UserAndClazzEntity userAndClazzEntity = findOne(userEntity, clazzEntity); // Check duplicate.
				if (userAndClazzEntity == null) {
					sessionFactory.getCurrentSession().persist(entity);
					return entity.getUser().getId();
				}
				return SystemConstant.DUPLICATE;
			}
		}
		return SystemConstant.ERROR;
	}

	@Override
	public Long delete(UserAndClazzEntity entity) {
		if (entity.getId() != null) {
			UserAndClazzEntity userAndClazzEntity = findOneByID(entity.getId());
			if (userAndClazzEntity != null) {
				userAndClazzEntity.setIsDeleted((byte) 1);
				userAndClazzEntity.setModifiedBy(entity.getModifiedBy());
				userAndClazzEntity.setModifiedDate(entity.getModifiedDate());
				UserAndClazzEntity result = (UserAndClazzEntity) sessionFactory.getCurrentSession().merge(userAndClazzEntity);
				return result.getId();
			}
		}
		return SystemConstant.ERROR;
	}
}
