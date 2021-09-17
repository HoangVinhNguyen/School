package com.school.admin.config;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.school.common.common.SystemConstant;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String dirName = SystemConstant.PHOTOS_OF_USERS_FOLDER;
		Path userPhotosDir = Paths.get(dirName);
		String userPhotosPath = userPhotosDir.toFile().getAbsolutePath();
		
		StringBuilder srcRe = new StringBuilder();
		srcRe.append(SystemConstant.FORWARD_SLASH);
		srcRe.append(dirName);
		srcRe.append(SystemConstant.FORWARD_SLASH_2_START);
		
		StringBuilder srcLction = new StringBuilder();
		srcLction.append(SystemConstant.FORWARD_SLASH_FILE);
		srcLction.append(userPhotosPath);
		srcLction.append(SystemConstant.FORWARD_SLASH);
		
		registry.addResourceHandler(srcRe.toString()).addResourceLocations(srcLction.toString());
	}
}
