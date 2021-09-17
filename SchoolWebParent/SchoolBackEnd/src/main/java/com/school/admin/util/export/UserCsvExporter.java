package com.school.admin.util.export;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.school.common.entity.User;

public class UserCsvExporter extends AbstractExporter {

	public void export(List<User> listUsers, HttpServletResponse response) throws IOException {
		super.setResponseHeader(response, "text/csv", ".csv");
		ICsvBeanWriter csvWirter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
		
		String[] csvHeader = {"User ID", "E-mail", "First Name", "Last Name", "Roles", "Enabled"};
		String[] fieldMapping = {"id", "email", "firstName", "lastName", "roles", "enabled"};
		
		csvWirter.writeHeader(csvHeader);
		listUsers.stream().forEach(user -> {
			try {
				csvWirter.write(user, fieldMapping);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		csvWirter.close();
	}
}
