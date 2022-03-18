package com.brody.usersms;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

import org.apache.commons.io.FileUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/get")
@SpringBootApplication
public class ImageToBase64StringConversionApplication {
	
	private static String inputFilePath = "C:\\Users\\brody\\Videos\\image.jpg";
	static String outputFileName = "image";
    private static String oute;
    private static File file;

	public static void main(String[] args) throws IOException {
		SpringApplication.run(ImageToBase64StringConversionApplication.class, args);
		byte[] fileContent = readTheFileContentToAByteArray(inputFilePath);
		oute = encodeItToString(fileContent);
		byte[] decodedBytes = decodeItToBinaryContent(oute);
		file = writeToANewFile(outputFileName,decodedBytes);
		
	}
	
	@GetMapping("/get")
	@ResponseBody
	public String getTxt() {
		return oute;
		
		
	}
	
	@GetMapping("/find")
	@ResponseBody
	public File getTt() {
		return file;
		
		
	}
	
	//Convert Image File to Base64 String
	public static byte[] readTheFileContentToAByteArray(String filePath) throws IOException  {
		
		File file = new File(filePath);
		byte[] fileContent = FileUtils.readFileToByteArray(file);
		return fileContent;
		
	}
	
	public static String encodeItToString(byte[] fileContent) {
		
		String encodedString = Base64.getEncoder().encodeToString(fileContent);
		return encodedString;
	}
	
	//Convert Base64 String to Image File
	public static byte[] decodeItToBinaryContent(String encodedString) {
		byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
		return decodedBytes;
	}
	
	public static File writeToANewFile(String outputFileName, byte[] decodedBytes) throws IOException{
		File file = new File(outputFileName);
		FileUtils.writeByteArrayToFile(file, decodedBytes);
		return file;
	}
	
	
	
	

}
