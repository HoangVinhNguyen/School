package com.school.admin.util.export;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.school.common.common.SystemConstant;
import com.school.common.entity.User;

public class UserPdfExporter extends AbstractExporter {

	public void export(List<User> listUsers, HttpServletResponse response) throws IOException {
		super.setResponseHeader(response, SystemConstant.TYPE_PDF, SystemConstant.SUFFIX_PDF);
		
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		document.open();
		
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);
		font.setColor(Color.BLUE);
		
		Paragraph paragraph = new Paragraph("List of users", font);
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(paragraph);
		
		PdfPTable table = new PdfPTable(7);
		table.setWidthPercentage(100f);
		table.setSpacingBefore(10);
		table.setWidths(new float[] {1.3f, 3.5f, 3.0f, 3.0f, 3.0f, 3.0f, 2.0f});
		writeTableHeader(table);
		writeTableData(listUsers, table);
		
		document.add(table);
		
		document.close();
	}

	private void writeTableData(List<User> listUsers, PdfPTable table) {
		for (User user : listUsers) {
			table.addCell(String.valueOf(user.getId()));
			table.addCell(user.getEmail());
			table.addCell(user.getFirstName());
			table.addCell(user.getLastName());
			table.addCell(user.getPhone());
			table.addCell(String.valueOf(user.getRoles()));
			table.addCell(String.valueOf(user.isEnabled()));
		}
	}

	private void writeTableHeader(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.BLUE);
		cell.setPadding(5);
		
		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.WHITE);
		
		cell.setPhrase(new Phrase("User ID", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("E-mail", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("First Name", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Last Name", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Phone", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Roles", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Enabled", font));
		table.addCell(cell);
	}
}
