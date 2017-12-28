package com.offcn.utils;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.offcn.domain.QueryVO;

public class CreatePDFUtil {

	public static boolean saveChartToPDF(String baseData, QueryVO queryVO) {

		String[] data = baseData.split("base64,");

		byte[] buffer = Base64.decodeBase64(data[1]);

		try {
			
			String fileName = "D:/JavaWebSoftWare/chart/" + UUID.randomUUID().toString() + ".pdf";
			
			PdfWriter pdfWriter = new PdfWriter(fileName);

			PdfDocument pdfDocument = new PdfDocument(pdfWriter);

			Document document = new Document(pdfDocument, PageSize.A3);

			PdfFont font = PdfFontFactory.createFont("STSongStd-Light", "UniGB-UCS2-H", false);
			document.setFont(font);
			
			document.setTextAlignment(TextAlignment.CENTER);
			
			document.add(new Paragraph(queryVO.getType()+" 2016-2017 年度价格折线图"));
			
			Table table = new Table(new float[13]);
			
			table.setTextAlignment(TextAlignment.CENTER);
			
			table.setWidthPercent(100);
			
			//第一行
			for (int i = 0; i < 13; i++) {
				
				Cell cell = new Cell(1,1);
				
				if( i == 0 ){
					
					cell.add(new Paragraph());
					
				}else{
					
					cell.add(new Paragraph(String.valueOf(i)));
					
				}
				table.addCell(cell);
			}
			
			//下两行
			Map<Integer, Map<Integer, Double>> map = queryVO.getYearChartMap();
			
			Set<Entry<Integer,Map<Integer,Double>>> entrySet = map.entrySet();
			
			for (Entry<Integer, Map<Integer, Double>> entry : entrySet) {
				
				Integer key = entry.getKey();
				
				Map<Integer, Double> value = entry.getValue();
				
				for (int i = 0; i < 13; i++) {
					
					Cell cell = new Cell(1,1);
					
					if( i == 0 ){
						
						cell.add(new Paragraph(String.valueOf(key)));
						
					}else{//获取value的map集合放进去
						
						String val = String.valueOf(value.get(i));
						
						String whole = val.substring(0, val.indexOf("."));
						
						String decimal = val.substring(val.indexOf("."));
						
						if( decimal.length() >2 ){
							
							val = whole + decimal.substring(0, 3);
						}
						
						cell.add(new Paragraph(val));
						
					}
					table.addCell(cell);
				}
			}

			document.add(table);
			
			Image image = new Image(ImageDataFactory.create(buffer));
			
			document.add(image);
			
			document.close();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
}
