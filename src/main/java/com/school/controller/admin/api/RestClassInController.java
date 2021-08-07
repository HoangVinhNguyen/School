package com.school.controller.admin.api;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.school.constant.SystemConstant;
import com.school.model.ClazzModel;
import com.school.service.IClazzService;

@RestController
@RequestMapping(value= {"/admin"})
public class RestClassInController {

	@Autowired
	private IClazzService clazzService;
	
	@RequestMapping(value={"/api-admin-class"}, method=RequestMethod.GET)
	public List<ClazzModel> getAllClassIns(HttpServletRequest request) {
		return clazzService.findAll();
	}
	
	@RequestMapping(value={"/api-admin-class"}, method=RequestMethod.POST)
	public ResponseEntity<?> getCreateClassIn(HttpServletRequest request, @RequestBody ClazzModel clazzModel) {
		Long rs = clazzService.save(clazzModel, SystemConstant.INSERT);
		if (rs == SystemConstant.DUPLICATE) {
			return new ResponseEntity<>(SystemConstant.DUPLICATE_RES.getBytes(SystemConstant.CHARSET_UTF_8), HttpStatus.NOT_ACCEPTABLE);
		}
		else if (rs == SystemConstant.ERROR) {
			return new ResponseEntity<>(SystemConstant.ERROR_RES, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(SystemConstant.OK_RES.getBytes(SystemConstant.CHARSET_UTF_8), HttpStatus.OK);
	}
	
	@RequestMapping(value={"/api-admin-class"}, method=RequestMethod.PUT)
	public ResponseEntity<?> getUpdateClassIn(HttpServletRequest request, @RequestBody ClazzModel clazzModel) {
		Long rs = clazzService.save(clazzModel, SystemConstant.MODIFY);
		if (rs == SystemConstant.DUPLICATE) {
			return new ResponseEntity<>(SystemConstant.DUPLICATE_RES.getBytes(SystemConstant.CHARSET_UTF_8), HttpStatus.NOT_ACCEPTABLE);
		}
		else if (rs == SystemConstant.ERROR) {
			return new ResponseEntity<>(SystemConstant.ERROR_RES, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(SystemConstant.OK_RES.getBytes(SystemConstant.CHARSET_UTF_8), HttpStatus.OK);
	}
	
	@RequestMapping(value={"/api-admin-class"}, method= RequestMethod.DELETE)
	public ResponseEntity<?> getDeleteClassIn(HttpServletRequest request, @RequestBody ClazzModel clazzModel) {
		Long rs = clazzService.delete(clazzModel);
		if (rs == SystemConstant.ERROR) {
			return new ResponseEntity<>(SystemConstant.DELETE_FAILED_RES.getBytes(SystemConstant.CHARSET_UTF_8), HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<>(SystemConstant.DELETE_OK_RES.getBytes(SystemConstant.CHARSET_UTF_8), HttpStatus.OK);
	}
	
	@RequestMapping(value={"/api-admin-class-file"}, method= RequestMethod.POST)
	public Long sendFileClassIn(@RequestParam(name="file") MultipartFile file) {
		return clazzService.saveList(file);
	}
	
	@RequestMapping(value={"/api-admin-class-file-form-download"}, method=RequestMethod.GET)
	public void download(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		clazzService.downloadForm(request, response);
	}
	
	@RequestMapping(value={"/api-admin-class-file-report-download"}, method=RequestMethod.GET)
	public void downloadReport(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		clazzService.getReport(request, response);
	}
}
