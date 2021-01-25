package com.school.controller.teacher.api;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.school.constant.SystemConstant;
import com.school.model.ClassroomModel;
import com.school.model.CourseModel;
import com.school.model.TeacherClassroomModel;
import com.school.model.UserModel;
import com.school.service.IClassroomService;
import com.school.service.ICourseService;
import com.school.service.ITeacherClassroomService;
import com.school.utils.HttpUtil;
import com.school.utils.SessionUtil;

@WebServlet(urlPatterns = {"/api-teacher-inclassroom"})
public class GetClassroomAPI extends HttpServlet {

	private static final long serialVersionUID = 5503266442311578320L;
	
	@Inject
	private IClassroomService classroomService;
	
	@Inject
	private ITeacherClassroomService teacherClassroomService;
	
	@Inject
	private ICourseService courseService;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES,false);
		mapper.configure(SerializationFeature.INDENT_OUTPUT,true);
		mapper.setSerializationInclusion(Include.NON_NULL);
		HashMap<String, Set<String>> mapResult = new HashMap<String, Set<String>>();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		UserModel model = (UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL");
		if (model.getRole().getCode().equals(SystemConstant.TEACHER)) {
			List<TeacherClassroomModel> teacherClassroomModel = teacherClassroomService.findAllByTeacherEmail(model.getEmail());
			Set<String> listClassName = new HashSet<String>();
			Set<String> listCourseName = new HashSet<String>();
			for (TeacherClassroomModel tempModel : teacherClassroomModel) {
				ClassroomModel classroomModel = classroomService.findOne(tempModel.getClassroomId());
				listClassName.add(classroomModel.getName());
				CourseModel course = courseService.findOne(tempModel.getCourseId());
				listCourseName.add(course.getName());
			}
			
			mapResult.put("listClassName", listClassName);
			mapResult.put("listCourseName", listCourseName);
			mapper.writeValue(resp.getOutputStream(), mapResult);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		UserModel model = (UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL");
		ClassroomModel classroomModel =  HttpUtil.of(req.getReader()).toModel(ClassroomModel.class);
		if (model.getRole().getCode().equals(SystemConstant.TEACHER)) {
			List<TeacherClassroomModel> listClassOfStudent = teacherClassroomService.findAllByClassroom(classroomModel.getName());
			
			mapper.writeValue(resp.getOutputStream(), listClassOfStudent);
		}
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		UserModel model = (UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL");
		TeacherClassroomModel teacherClassroomModel =  HttpUtil.of(req.getReader()).toModel(TeacherClassroomModel.class);
		if (model.getRole().getCode().equals(SystemConstant.TEACHER)) {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			teacherClassroomModel.setModifiedBy(model.getFullname());
			teacherClassroomModel.setModifiedDate(timestamp);
			Long id = teacherClassroomService.savePoint(teacherClassroomModel);
			mapper.writeValue(resp.getOutputStream(), id);
		}
	}
	
}
