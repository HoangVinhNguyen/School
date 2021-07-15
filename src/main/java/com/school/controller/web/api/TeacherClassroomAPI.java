package com.school.controller.web.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.school.entity.ClassroomEntity;
import com.school.entity.CourseEntity;
import com.school.entity.TeacherClassroomEntity;
import com.school.entity.UserEntity;
import com.school.service.IClassroomService;
import com.school.service.ICourseService;
import com.school.service.ITeacherClassroomService;
import com.school.utils.HttpUtil;
import com.school.utils.SessionUtil;

@WebServlet(urlPatterns = {"/api-web-point"})
public class TeacherClassroomAPI extends HttpServlet {

	private static final long serialVersionUID = -2348705716145031169L;
	
	@Inject
	private ITeacherClassroomService teacherClassroomService;
	
	@Inject
	private IClassroomService classroomService;
	
	@Inject
	private ICourseService courseService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES,false);
		mapper.configure(SerializationFeature.INDENT_OUTPUT,true);
		mapper.setSerializationInclusion(Include.NON_NULL);
		Map<String, List<String>> mapResult = new HashMap<String, List<String>>();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		UserEntity model = (UserEntity) SessionUtil.getInstance().getValue(req, "USERMODEL");
		if (model != null && model.getRole().getCode().equals(SystemConstant.STUDENT)) {
			List<TeacherClassroomEntity> teacherClassroomEntity = teacherClassroomService.findAllByStudentEmail(model.getEmail());
			List<String> listClassName = new ArrayList<String>();
			List<String> listCourseName = new ArrayList<String>();
			List<String> listCoursePoint = new ArrayList<String>();
			for (TeacherClassroomEntity tempModel : teacherClassroomEntity) {
				ClassroomEntity classroomEntity = classroomService.findOne(tempModel.getClassroomId());
				listClassName.add(classroomEntity.getName());
				CourseEntity course = courseService.findOne(tempModel.getCourseId());
				listCourseName.add(course.getName());
				listCoursePoint.add(tempModel.getPoint().toString());
			}
			
			mapResult.put("listClassName", listClassName);
			mapResult.put("listCourseName", listCourseName);
			mapResult.put("listCoursePoint", listCoursePoint);
			mapper.writeValue(resp.getOutputStream(), mapResult);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		UserEntity model = (UserEntity) SessionUtil.getInstance().getValue(req, "USERMODEL");
		CourseEntity courseEntity =  HttpUtil.of(req.getReader()).toModel(CourseEntity.class);
		CourseEntity course = courseService.findOneByName(courseEntity.getName());
		TeacherClassroomEntity classOfStudent = new TeacherClassroomEntity();
		if (model.getRole().getCode().equals(SystemConstant.STUDENT)) {
			List<TeacherClassroomEntity> teacherClassroomEntity = teacherClassroomService.findAllByStudentEmail(model.getEmail());
			for (TeacherClassroomEntity tempModel : teacherClassroomEntity) {
				if (course.getId().equals(tempModel.getCourseId())) {
					classOfStudent = tempModel;
				}
			}
			mapper.writeValue(resp.getOutputStream(), classOfStudent);
		}
	}
}
