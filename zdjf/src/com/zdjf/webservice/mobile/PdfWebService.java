package com.zdjf.webservice.mobile;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;



@RestController
@RequestMapping("/m/pdf")
public class PdfWebService {
	
	
	//pdf 测试
	@RequestMapping(value="/create",method=RequestMethod.POST)
	public void testCreatePDF(HttpServletRequest request,
            HttpServletResponse response) {

		Rectangle rectPageSize = new Rectangle(PageSize.A4);
		Document document = new Document(rectPageSize, 50, 50, 50, 50);

		try {
			BaseFont bfChinese = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
			Font FontChinese = new Font(bfChinese, 12, Font.NORMAL);

			PdfWriter.getInstance(document, new FileOutputStream("d:/contract.pdf"));
			document.open();
			
			
			document.addTitle("Title@sample");  
			Paragraph contractNum = new Paragraph("Hello World! ", FontChinese);
			document.add(contractNum);

			Chunk chunk1 = new Chunk("金马奖在华语圈中它历史最为悠久，并且评选条件中不设地域限制、评选对象面向所有华语电影和华语影人的奖项。", FontChinese);
			document.add(chunk1);

		} catch (FileNotFoundException | DocumentException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}

		document.close();
	}

}
