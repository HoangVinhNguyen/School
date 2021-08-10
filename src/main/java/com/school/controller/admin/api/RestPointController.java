package com.school.controller.admin.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.school.constant.SystemConstant;
import com.school.model.PointModel;
import com.school.service.IPointService;

@RestController
@RequestMapping(value= {"/admin"})
public class RestPointController {

	@Autowired
	private IPointService pointService;
	
	@RequestMapping(value={"/api-admin-point"}, method=RequestMethod.GET)
	public List<PointModel> getAllPoints(HttpServletRequest request) {
		return pointService.findAll();
	}
	
	@RequestMapping(value={"/api-admin-point"}, method=RequestMethod.POST)
	public ResponseEntity<?> getCreatePoint(HttpServletRequest request, @RequestBody PointModel model) {
		Long rs = pointService.save(model, SystemConstant.INSERT);
		if (rs == SystemConstant.DUPLICATE) {
			return new ResponseEntity<>(SystemConstant.DUPLICATE_RES.getBytes(SystemConstant.CHARSET_UTF_8), HttpStatus.NOT_ACCEPTABLE);
		}
		else if (rs == SystemConstant.ERROR) {
			return new ResponseEntity<>(SystemConstant.ERROR_RES, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(SystemConstant.OK_RES.getBytes(SystemConstant.CHARSET_UTF_8), HttpStatus.OK);
	}
	
	@RequestMapping(value={"/api-admin-point-point"}, method=RequestMethod.PUT)
	public ResponseEntity<?> getCreateSavePoint(HttpServletRequest request, @RequestBody PointModel model) {
		Long rs = pointService.savePoint(model, SystemConstant.MODIFY);
		if (rs == SystemConstant.DUPLICATE) {
			return new ResponseEntity<>(SystemConstant.DUPLICATE_RES.getBytes(SystemConstant.CHARSET_UTF_8), HttpStatus.NOT_ACCEPTABLE);
		}
		else if (rs == SystemConstant.ERROR) {
			return new ResponseEntity<>(SystemConstant.ERROR_RES, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(SystemConstant.OK_RES.getBytes(SystemConstant.CHARSET_UTF_8), HttpStatus.OK);
	}
	
	@RequestMapping(value={"/api-admin-point"}, method=RequestMethod.PUT)
	public ResponseEntity<?> getUpdatePoint(HttpServletRequest request, @RequestBody PointModel model) {
		Long rs = pointService.save(model, SystemConstant.MODIFY);
		if (rs == SystemConstant.DUPLICATE) {
			return new ResponseEntity<>(SystemConstant.DUPLICATE_RES.getBytes(SystemConstant.CHARSET_UTF_8), HttpStatus.NOT_ACCEPTABLE);
		}
		else if (rs == SystemConstant.ERROR) {
			return new ResponseEntity<>(SystemConstant.ERROR_RES, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(SystemConstant.OK_RES.getBytes(SystemConstant.CHARSET_UTF_8), HttpStatus.OK);
	}
	
	@RequestMapping(value={"/api-admin-point"}, method= RequestMethod.DELETE)
	public ResponseEntity<?> getDeletePoint(HttpServletRequest request, @RequestBody PointModel model) {
		Long rs = pointService.delete(model, SystemConstant.MODIFY);
		if (rs == SystemConstant.ERROR) {
			return new ResponseEntity<>(SystemConstant.DELETE_FAILED_RES.getBytes(SystemConstant.CHARSET_UTF_8), HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<>(SystemConstant.DELETE_OK_RES.getBytes(SystemConstant.CHARSET_UTF_8), HttpStatus.OK);
	}
}
