package com.szcti.lcloud.exchange.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Component;

import io.minio.MinioClient;

@Component
public class FileUploader {


	public final static Map<String, String> FILE_TYPE_MAP = new HashMap<String, String>();
	private static MinioClient minioClient = null;

	static {
		getAllFileType(); // 初始化文件类型信息
	}

	public String upload(InputStream inputStream) {
		String url = "";

		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len;
			while ((len = inputStream.read(buffer)) > -1) {
				baos.write(buffer, 0, len);
			}
			baos.flush();
			InputStream is1 = new ByteArrayInputStream(baos.toByteArray());
			InputStream iis = new ByteArrayInputStream(baos.toByteArray());

			MinioClient minioClient = getMinioClient();

			String fileType = getFileTypeByStream(is1);
			String contentType = "application/octet-stream";
			if ("jpg".equalsIgnoreCase(fileType) || "png".equalsIgnoreCase(fileType) || "gif".equalsIgnoreCase(fileType)
					|| "bmp".equalsIgnoreCase(fileType) || "tif".equalsIgnoreCase(fileType)) {
				contentType = "image/" + fileType;
			}
			String fname = IdWorker.getIdStr() + "." + fileType;

			minioClient.putObject(SpringBootUtil.getEnvironment().getProperty("minio.bucket"), fname, iis, contentType);

			url = SpringBootUtil.getEnvironment().getProperty("minio.url")+"/"+ SpringBootUtil.getEnvironment().getProperty("minio.bucket")+"/"+fname;

			is1.close();
			iis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return url;
	}
	public String uploadPic(InputStream inputStream,String bucket) {
		String url = "";
		
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len;
			while ((len = inputStream.read(buffer)) > -1) {
				baos.write(buffer, 0, len);
			}
			baos.flush();
			InputStream is1 = new ByteArrayInputStream(baos.toByteArray());
			InputStream iis = new ByteArrayInputStream(baos.toByteArray());
			
			MinioClient minioClient = getMinioClient(bucket);
			
			String fileType = getFileTypeByStream(is1);
			String contentType = "application/octet-stream";
			if ("jpg".equalsIgnoreCase(fileType) || "png".equalsIgnoreCase(fileType) || "gif".equalsIgnoreCase(fileType)
					|| "bmp".equalsIgnoreCase(fileType) || "tif".equalsIgnoreCase(fileType)) {
				contentType = "image/" + fileType;
			}
			String fname = IdWorker.getIdStr() + "." + fileType;
			
			minioClient.putObject(bucket, fname, iis, contentType);
			
			url = SpringBootUtil.getEnvironment().getProperty("minio.url")+"/"+ bucket+"/"+fname;
			
			is1.close();
			iis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return url;
	}
	
	public String uploadFile(InputStream inputStream,String fileName) {
		String url = "";

		try {

			MinioClient minioClient = getMinioClient();
			
			minioClient.putObject(SpringBootUtil.getEnvironment().getProperty("minio.bucket"), fileName, inputStream, "application/octet-stream");

			url = SpringBootUtil.getEnvironment().getProperty("minio.url")+"/"+ SpringBootUtil.getEnvironment().getProperty("minio.bucket")+"/"+fileName;

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return url;
	}
	public String uploadFile(InputStream inputStream,String fileName,String bucket) {
		String url = "";
		
		try {
			
			MinioClient minioClient = getMinioClient(bucket);
			
			minioClient.putObject(bucket, fileName, inputStream, "application/octet-stream");
			
			url = SpringBootUtil.getEnvironment().getProperty("minio.url")+"/"+ bucket+"/"+fileName;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return url;
	}

	private MinioClient getMinioClient() throws Exception {
		if (minioClient == null) {

			minioClient = new MinioClient(SpringBootUtil.getEnvironment().getProperty("minio.endpoint"), 
					SpringBootUtil.getEnvironment().getProperty("minio.accessKey"), 
					SpringBootUtil.getEnvironment().getProperty("minio.secretKey"));
			
			String bucket = SpringBootUtil.getEnvironment().getProperty("minio.bucket");
			
			boolean isExist = minioClient.bucketExists(bucket);
			if (!isExist) {
				minioClient.makeBucket(bucket);
				
				 StringBuilder builder = new StringBuilder();
			      builder.append("{\n");
			      builder.append("    \"Statement\": [\n");
			      builder.append("        {\n");
			      builder.append("            \"Action\": [\n");
			      builder.append("                \"s3:GetBucketLocation\",\n");
			      builder.append("                \"s3:ListBucket\"\n");
			      builder.append("            ],\n");
			      builder.append("            \"Effect\": \"Allow\",\n");
			      builder.append("            \"Principal\": \"*\",\n");
			      builder.append("            \"Resource\": \"arn:aws:s3:::"+bucket+"\"\n");
			      builder.append("        },\n");
			      builder.append("        {\n");
			      builder.append("            \"Action\": \"s3:GetObject\",\n");
			      builder.append("            \"Effect\": \"Allow\",\n");
			      builder.append("            \"Principal\": \"*\",\n");
			      builder.append("            \"Resource\": \"arn:aws:s3:::"+bucket+"/*\"\n");
			      builder.append("        }\n");
			      builder.append("    ],\n");
			      builder.append("    \"Version\": \"2012-10-17\"\n");
			      builder.append("}\n");
			      minioClient.setBucketPolicy(bucket, builder.toString());//设置公开访问
			}
		}
		return minioClient;
	}
	private MinioClient getMinioClient(String bucket) throws Exception {
		if (minioClient == null) {
			
			minioClient = new MinioClient(SpringBootUtil.getEnvironment().getProperty("minio.endpoint"), 
					SpringBootUtil.getEnvironment().getProperty("minio.accessKey"), 
					SpringBootUtil.getEnvironment().getProperty("minio.secretKey"));
			
			//String bucket = SpringBootUtil.getEnvironment().getProperty("minio.bucket");
			
			boolean isExist = minioClient.bucketExists(bucket);
			if (!isExist) {
				minioClient.makeBucket(bucket);
				
				StringBuilder builder = new StringBuilder();
				builder.append("{\n");
				builder.append("    \"Statement\": [\n");
				builder.append("        {\n");
				builder.append("            \"Action\": [\n");
				builder.append("                \"s3:GetBucketLocation\",\n");
				builder.append("                \"s3:ListBucket\"\n");
				builder.append("            ],\n");
				builder.append("            \"Effect\": \"Allow\",\n");
				builder.append("            \"Principal\": \"*\",\n");
				builder.append("            \"Resource\": \"arn:aws:s3:::"+bucket+"\"\n");
				builder.append("        },\n");
				builder.append("        {\n");
				builder.append("            \"Action\": \"s3:GetObject\",\n");
				builder.append("            \"Effect\": \"Allow\",\n");
				builder.append("            \"Principal\": \"*\",\n");
				builder.append("            \"Resource\": \"arn:aws:s3:::"+bucket+"/*\"\n");
				builder.append("        }\n");
				builder.append("    ],\n");
				builder.append("    \"Version\": \"2012-10-17\"\n");
				builder.append("}\n");
				minioClient.setBucketPolicy(bucket, builder.toString());//设置公开访问
			}
		}
		return minioClient;
	}

	public final static String getFileTypeByStream(InputStream iis) {

		String extName = "unknow";
		try {

			byte[] b = new byte[50];

			iis.read(b);

			String filetypeHex = String.valueOf(getFileHexString(b));
			Iterator<Entry<String, String>> entryiterator = FILE_TYPE_MAP.entrySet().iterator();
			while (entryiterator.hasNext()) {
				Entry<String, String> entry = entryiterator.next();
				String fileTypeHexValue = entry.getValue();
				if (filetypeHex.toUpperCase().startsWith(fileTypeHexValue)) {
					extName = entry.getKey();
					break;
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		return extName;
	}

	
	public final static String getFileHexString(byte[] b) {
		StringBuilder stringBuilder = new StringBuilder();
		if (b == null || b.length <= 0) {
			return null;
		}
		for (int i = 0; i < b.length; i++) {
			int v = b[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

	private static void getAllFileType() {
		FILE_TYPE_MAP.put("jpg", "FFD8FF"); // JPEG (jpg)
		FILE_TYPE_MAP.put("png", "89504E47"); // PNG (png)
		FILE_TYPE_MAP.put("gif", "47494638"); // GIF (gif)
		FILE_TYPE_MAP.put("tif", "49492A00"); // TIFF (tif)
		FILE_TYPE_MAP.put("bmp", "424D"); // Windows Bitmap (bmp)
		FILE_TYPE_MAP.put("dwg", "41433130"); // CAD (dwg)
		FILE_TYPE_MAP.put("html", "68746D6C3E"); // HTML (html)
		FILE_TYPE_MAP.put("rtf", "7B5C727466"); // Rich Text Format (rtf)
		FILE_TYPE_MAP.put("xml", "3C3F786D6C");
		FILE_TYPE_MAP.put("zip", "504B0304");
		FILE_TYPE_MAP.put("rar", "52617221");
		FILE_TYPE_MAP.put("psd", "38425053"); // Photoshop (psd)
		FILE_TYPE_MAP.put("eml", "44656C69766572792D646174653A"); // Email [thorough only] (eml)
		FILE_TYPE_MAP.put("dbx", "CFAD12FEC5FD746F"); // Outlook Express (dbx)
		FILE_TYPE_MAP.put("pst", "2142444E"); // Outlook (pst)
		FILE_TYPE_MAP.put("xls", "D0CF11E0"); // MS Word
		FILE_TYPE_MAP.put("doc", "D0CF11E0"); // MS Excel 注意：word 和 excel的文件头一样
		FILE_TYPE_MAP.put("mdb", "5374616E64617264204A"); // MS Access (mdb)
		FILE_TYPE_MAP.put("wpd", "FF575043"); // WordPerfect (wpd)
		FILE_TYPE_MAP.put("eps", "252150532D41646F6265");
		FILE_TYPE_MAP.put("ps", "252150532D41646F6265");
		FILE_TYPE_MAP.put("pdf", "255044462D312E"); // Adobe Acrobat (pdf)
		FILE_TYPE_MAP.put("qdf", "AC9EBD8F"); // Quicken (qdf)
		FILE_TYPE_MAP.put("pwl", "E3828596"); // Windows Password (pwl)
		FILE_TYPE_MAP.put("wav", "57415645"); // Wave (wav)
		FILE_TYPE_MAP.put("avi", "41564920");
		FILE_TYPE_MAP.put("ram", "2E7261FD"); // Real Audio (ram)
		FILE_TYPE_MAP.put("rm", "2E524D46"); // Real Media (rm)
		FILE_TYPE_MAP.put("mpg", "000001BA"); //
		FILE_TYPE_MAP.put("mov", "6D6F6F76"); // Quicktime (mov)
		FILE_TYPE_MAP.put("asf", "3026B2758E66CF11"); // Windows Media (asf)
		FILE_TYPE_MAP.put("mid", "4D546864"); // MIDI (mid)
	}
}