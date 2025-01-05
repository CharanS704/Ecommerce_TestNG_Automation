package com.ecommerce.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {

	public static void main(String args[]) throws EncryptedDocumentException, IOException {
		ReadExcel.readExcel();
	}

	public static ArrayList<String> readExcel() throws EncryptedDocumentException, IOException {
		ArrayList<String> list = new ArrayList<String>();

		FileInputStream inputStream = new FileInputStream(
				(new File(System.getProperty("user.dir") + "//src//test//resource//resources//TestData.xlsx")));
		Workbook workBook = WorkbookFactory.create(inputStream);
		Sheet sheet = workBook.getSheetAt(0);

		for (Row row : sheet) {
			for (Cell cell : row) {
				if (cell.getCellType().equals(CellType.STRING)) {
					list.add(cell.getStringCellValue());
					System.out.println(list);
				}

			}

		}
		list.remove(0);
		list.forEach(item -> System.out.println(item));

		return list;
	}

}
