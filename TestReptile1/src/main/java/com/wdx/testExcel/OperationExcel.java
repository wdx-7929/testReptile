package com.wdx.testExcel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * 	测试操作excel表
 * @author Administrator
 *
 */
public class OperationExcel {
	
	public static void main(String[] args) {
		operationTable();
	}
	
	public static final String TEST_WORKBOOK_NAME = "src/main/java/com/wdx/testExcel/test.xlsx";
	
	public static FileInputStream is = null;
	public static Workbook wb = null;
	
	/**
	 * 创建一个excel表
	 */
	public static void createExcel() {
		//生成Workbook
		HSSFWorkbook wb = new HSSFWorkbook();
		//添加Worksheet(不添加生成会报错)
		Sheet sheet1 = wb.createSheet("一号工作簿");
		Sheet sheet2 = wb.createSheet("二号工作簿");
		Sheet sheet3 = wb.createSheet("三号工作簿");
		OutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(TEST_WORKBOOK_NAME);
			wb.write(outputStream);
			outputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 	打开excel表
	 */
	public static void openExcel() {
		try {
			is = new FileInputStream(TEST_WORKBOOK_NAME);
			wb = WorkbookFactory.create(is);
			is.close();
			
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("张数：" + wb.getNumberOfSheets());
		System.out.println("第三张名字：" + wb.getSheetName(2));
	}
	
	/**
	 * 	操作工作簿中的单元格，一定要先创建在操作不然报空指针
	 */
	public static void operationTable() {
		openExcel();
		Sheet at = wb.getSheetAt(1);
		System.out.println(at == null);
		//创建行并插入
		Row row = at.createRow(0);
		System.out.println(row.getRowNum());
		//创建单元格并插入
		Cell cell = row.createCell(0);
		//设定单元格的值
		at.getRow(0).getCell(0).setCellValue("123");
		//读取单元格的值
		System.out.println(at.getRow(0).getCell(0).getStringCellValue());
		OutputStream io = null;
		//保存到文件
		try {
			io = new FileOutputStream(TEST_WORKBOOK_NAME);
			wb.write(io);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
