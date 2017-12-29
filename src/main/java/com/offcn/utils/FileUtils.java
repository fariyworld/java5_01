package com.offcn.utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.commons.codec.binary.Base64;

public class FileUtils {

	public static String getPicStrByImages(String fileName) {
		
		return Base64.encodeBase64String(getByteArrForImages(fileName));
	}

	public static byte[] getByteArrForImages(String fileName) {

		File file = new File(fileName);

		try {
			
			if (!file.exists()) {

				throw new FileNotFoundException(fileName);
				
			} else {
				
				ByteArrayOutputStream bos = new ByteArrayOutputStream((int) file.length());
				
				BufferedInputStream in = null;

				try {
					
					in = new BufferedInputStream(new FileInputStream(file));
					
					short bufSize = 1024;
					
					byte[] buffer = new byte[bufSize];
					
					int len1;
					
					while (-1 != (len1 = in.read(buffer, 0, bufSize))) {
						
						bos.write(buffer, 0, len1);
					}
					bos.flush();

					return bos.toByteArray();
					
				} finally {

					if (in != null) {
						in.close();
					}

					bos.close();
				}
			}
		} catch (Exception e) {

			System.out.println(e.getMessage());
			return null;
		}
	}
}
