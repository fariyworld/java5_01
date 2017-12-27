package com.offcn.utils;

import java.util.UUID;

import org.apache.commons.codec.binary.Base64;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;

public class CreatePDFUtil {

	public static boolean saveChartToPDF(String baseData) {

		String[] data = baseData.split("base64,");

		byte[] buffer = Base64.decodeBase64(data[1]);

		try {
			
			String fileName = "D:/JavaWebSoftWare/chart/" + UUID.randomUUID().toString() + ".pdf";
			
			PdfWriter pdfWriter = new PdfWriter(fileName);

			PdfDocument pdfDocument = new PdfDocument(pdfWriter);

			Document document = new Document(pdfDocument, PageSize.A4);

			PdfFont font = PdfFontFactory.createFont("STSongStd-Light", "UniGB-UCS2-H", false);
			document.setFont(font);

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
