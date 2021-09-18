package com.school.admin.util.export;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.school.common.common.SystemConstant;
import com.school.common.entity.User;

public class UserCsvExporter extends AbstractExporter {

	public void export(List<User> listUsers, HttpServletResponse response) throws IOException {
		super.setResponseHeader(response, SystemConstant.TYPE_CSV, SystemConstant.SUFFIX_CSV);
		ICsvBeanWriter csvWirter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
		
		String[] csvHeader = {"User ID", "E-mail", "First Name", "Last Name", "Phone", "Roles", "Enabled"};
		String[] fieldMapping = {"id", "email", "firstName", "lastName", "phone", "roles", "enabled"};
		
		csvWirter.writeHeader(csvHeader);
		listUsers.stream().forEach(user -> {
			try {
				StringBuilder phone = new StringBuilder();
				phone.append(user.getPhone());
				phone.append("\t");
				user.setPhone(phone.toString());
				csvWirter.write(user, fieldMapping);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		csvWirter.close();
	}
}
