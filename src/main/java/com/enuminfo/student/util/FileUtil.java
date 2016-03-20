/**
 * 
 */
package com.enuminfo.student.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Kumar
 */
public class FileUtil {

	public static byte[] convertFileToByteArray(MultipartFile multiFile, String photoPath, String folderName, Integer id) {
		byte[] bFile = null;
		try {
			File photoFile = new File(photoPath + File.separator + folderName + File.separator + String.valueOf(id));
			if (!photoFile.exists()) photoFile.mkdirs();
			File imgfile = new File(photoFile.getAbsolutePath() + File.separator  + multiFile.getOriginalFilename());
			multiFile.transferTo(imgfile);
			bFile = new byte[(int) imgfile.length()];
            FileInputStream fileInputStream = new FileInputStream(imgfile);
            fileInputStream.read(bFile);
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
		return bFile;
	}
	
	public static void convertByteArrayToFile(byte[] bFile, String photoPath, String folderName, Integer id, String imgName) {
		File photoFile = new File(photoPath + File.separator + folderName + File.separator + String.valueOf(id));
		if (!photoFile.exists()) {
			try{
				photoFile.mkdirs();
				File file = new File(photoFile.getAbsolutePath() + File.separator + imgName);
				if (!file.exists()) file.createNewFile();
				FileOutputStream fos = new FileOutputStream(file); 
				fos.write(bFile);
				fos.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
