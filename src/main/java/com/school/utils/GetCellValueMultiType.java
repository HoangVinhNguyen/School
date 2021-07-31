package com.school.utils;

import org.apache.poi.ss.usermodel.Cell;

public class GetCellValueMultiType {

	public static String CellStringValueOf(Cell cell) {
		switch(cell.getCellType()) {
		case STRING:
			return cell.getStringCellValue();
		case NUMERIC:
			return String.valueOf(Math.round(cell.getNumericCellValue()));
		case FORMULA:
			break;
		case BOOLEAN:
			break;
		default:
			break;
		}
		return null;
	}
}
