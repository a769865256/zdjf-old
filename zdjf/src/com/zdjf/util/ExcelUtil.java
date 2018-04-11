package com.zdjf.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
/**
 * excel工具类
 * @author chenrg
 *
 */
public class ExcelUtil {
	/**
	 * excel 导出
	 * @param excelTitle //大标题
	 * @param unit   //单位
	 * @param title  //sheet页标题
	 * @param header //第一行标题
	 * @param data   //数据源
	 * @param out    //outputstream
	 * @param pattrn //时间格式
	 */
	public static void exportExcelLscs(String excelTitle,String unit,String title,String[] header,List<?> data,FileOutputStream out,String pattrn){
		//生成一个workbork
		HSSFWorkbook workbook = new HSSFWorkbook();
		//生成一个sheet页
		HSSFSheet sheet = workbook.createSheet(title);
		//生成HSSFRow 行
		int datalength = header.length;	 //总列数
		HSSFRow row = sheet.createRow(0);//第一行用来放大标题
		HSSFCell c = row.createCell((short) 0);	//合并单元格
		c.setCellValue(excelTitle);
		sheet.addMergedRegion(new Region(0, (short)0, 0, (short)datalength));
		
		row = sheet.createRow(1);//第二行用来放小标题
		for(int i=0;i<header.length;i++){
			HSSFCell cell = row.createCell((short) i);
			cell.setCellValue(header[i]);
		}
		//遍历集合数据，产生数据行  
        int index = 1;
        for(int j=0;j<data.size();j++){
        	index ++;
        	row = sheet.createRow(index);
    		HSSFCell cell0 = row.createCell((short) 0);
    		cell0.setCellValue(1);
    		
    		HSSFCell cell1 = row.createCell((short) 1);
    		cell0.setCellValue(1);
    		
    		HSSFCell cell2 = row.createCell((short) 2);
    		cell0.setCellValue(1);
    		
    		HSSFCell cell3 = row.createCell((short) 3);
    		cell0.setCellValue(1);
    		
    		HSSFCell cell4 = row.createCell((short) 4);
    		cell0.setCellValue(1);
        }
    	try {
			workbook.write(out);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	} 
}
