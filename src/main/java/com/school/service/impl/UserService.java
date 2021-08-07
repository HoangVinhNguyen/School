package com.school.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.school.DAO.IRoleDAO;
import com.school.DAO.IUserDAO;
import com.school.constant.SystemConstant;
import com.school.entity.RoleEntity;
import com.school.entity.UserEntity;
import com.school.model.UserModel;
import com.school.service.IUserService;
import com.school.utils.StringUtils;

@Service
public class UserService implements IUserService {

	@Autowired
	private IUserDAO userDAO;
	
	@Autowired
	private IRoleDAO roleDAO;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public UserModel findByEmailAndPasswordAndStatus(String email, String password, Integer status) {
		UserEntity userEntity = userDAO.findByEmailAndPasswordAndStatus(email, password, status);
		UserModel model = new UserModel();
		model.loadFromEntity(userEntity);
		return model;
	}

	@Override
	public UserModel findByEmail(String email) {
		UserModel model = new UserModel();
		UserEntity entity = new UserEntity();
		entity = userDAO.findByEmail(email);
		if (entity != null) {
			model.loadFromEntityNotPassword(entity);
			return model;
		}
		return null;
	}

	@Override
	public List<UserModel> findAll() {
		List<UserModel> userModels = new ArrayList<>();
		List<UserEntity> userEntities = userDAO.findAll();
		Iterator<UserEntity> itr = userEntities.iterator();
		while(itr.hasNext()) {
			UserModel model = new UserModel();
			model.loadFromEntityNotPassword(itr.next());
			userModels.add(model);
		}
		return userModels;
	}

	@Override
	public UserModel findOne(long id) {
		UserModel model = new UserModel();
		model.loadFromEntityNotPassword(userDAO.findOne(id));
		return model;
	}

	@Override
	public Long save(UserModel model, String method) {
		if (model != null) {
			model = getModifiedField(model, method);
			UserEntity userEntity = new UserEntity();
			userEntity.loadFromDTO(model);
			return userDAO.save(userEntity);
		}
		return SystemConstant.ERROR;
	}

	@Override
	public Long delete(UserModel model) {
		model = getModifiedField(model, SystemConstant.MODIFY);
		UserEntity userEntity = new UserEntity();
		userEntity.loadFromDTO(model);
		return userDAO.delete(userEntity);
	}

	@Override
	public UserModel findByUserName(String email) {
		UserModel model = new UserModel();
		model.loadFromEntity(userDAO.findByUserName(email));
		return model;
	}
	
	private UserModel getModifiedField(UserModel model, String method) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		switch (method) {
		case SystemConstant.INSERT:
			model.setPassword(passwordEncoder.encode(model.getPassword()));
			model.setModifiedDate(timestamp);
			model.setCreatedBy(authentication.getName());
			model.setCreatedDate(timestamp);
			break;
		case SystemConstant.MODIFY:
			model.setModifiedDate(timestamp);
			model.setModifiedBy(authentication.getName());
			break;
		case SystemConstant.INSERT_FILE:
			model.setPassword(passwordEncoder.encode(SystemConstant.ADMIN.toLowerCase()));
			model.setModifiedDate(timestamp);
			model.setCreatedBy(authentication.getName());
			model.setCreatedDate(timestamp);
			break;
		}
		return model;
	}

	@Override
	public Long saveList(MultipartFile file, String role) {
		try {
			InputStream fileInputStream = file.getInputStream();
			HSSFWorkbook wb = new HSSFWorkbook(fileInputStream);
			HSSFSheet sheet = wb.getSheetAt(0);
			for (Row row : sheet) // iteration over row using for each loop
			{
				if (row.getRowNum() != 0) {
					UserModel model = new UserModel();
					for (Cell cell : row) // iteration over cell using for each loop
					{
						/*
						 * _NONE(-1), NUMERIC(0), STRING(1), FORMULA(2), BLANK(3), BOOLEAN(4), ERROR(5);
						 */
						switch (cell.getColumnIndex()) {
						case 0:
							String email = generateEmailFromNameTeacher(cell.getStringCellValue());
							model.setEmail(email);
							model.setFullname(cell.getStringCellValue());
							break;
						case 1:
							Date tt = cell.getDateCellValue();
							Timestamp t = new Timestamp(tt.getYear(), tt.getMonth(), tt.getDay(), tt.getHours(),
									tt.getMinutes(), tt.getSeconds(), 0);
							model.setDob(t);
							break;
						case 2:
							Long phone = (long) cell.getNumericCellValue();
							model.setPhone(phone.toString());
							break;
						case 3:
							model.setAddress(cell.getStringCellValue());
							break;
//							String roleidS = String.valueOf(cell.getNumericCellValue());
//							String[] roleidStr = roleidS.split("\\.");
//							RoleModel roleModel = new RoleModel();
//							roleModel.setId(role);
//							model.setRole(roleModel);
//							model.setRoleId(role);
						}
					}
					RoleEntity roleEntity = roleDAO.findOneByName(role);
					if (roleEntity == null) {
						return 0L;
					}
					model.setRoleId(roleEntity.getId());
					model = getModifiedField(model, SystemConstant.INSERT_FILE);
					UserEntity userEntity = new UserEntity();
					userEntity.loadFromDTO(model);
					userDAO.save(userEntity);
				}
			}
			return 1L;
		} catch (IOException e) {
			System.out.println("save list users");
			e.printStackTrace();
			return 0L;
		}
	}
	
	private String generateEmailFromNameTeacher(String name) {
		UserModel userCheck;
		int uq = 1;
		if (name != null) {
			name = name.toLowerCase();
			name = StringUtils.removeAccent(name);
			String[] nameList = name.split(" ");
			StringBuilder mail = new StringBuilder();
			final String nameMail;
			for (int i = 0; i < nameList.length; i++) {
				if (i == nameList.length-1) {
					mail.append(nameList[i]);
					break;
				}
				mail.append(nameList[i].charAt(0));
			}
			nameMail = mail.toString();
			mail.append(SystemConstant.DOMAINNAME);
			while(true) {
				userCheck = findByEmail(mail.toString());
				if (userCheck == null) {
					return mail.toString();
				}
				else {
					mail = mail.replace(nameMail.length(), mail.length(), "");
					mail.append(uq++);
					mail.append(SystemConstant.DOMAINNAME);
				}
			}
		}
		return null;
	}
}
