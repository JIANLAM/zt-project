package com.szcti.lcloud.user.api;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.szcti.lcloud.common.utils.FileUploader;
import com.szcti.lcloud.common.utils.R;

@Component
@Path("test")
public class TestMinioApi {

	@Autowired
	private FileUploader fileUploader;
	

	/**文件图片上传
	 * 
	 * application.yml 中添加配置
	
		minio: 
		  endpoint: http://192.168.1.252:9000
		  url: http://192.168.1.252:9000
		  accessKey: 7Z98QYVE7QLALU37MGOV
		  secretKey: JveBd6RCmpyj8zvLxbb6jLIjrbtY6tJdsRuExxPs
		  bucket: file
	 * 
	 * pom.xml 中添加依赖
	 * 
	    <dependency>
			<groupId>org.jboss.resteasy</groupId>
			<artifactId>resteasy-multipart-provider</artifactId>
			<version>3.0.0.Final</version>
		</dependency>
		
	 * @param request
	 * @param input
	 * @return
	 */
	@Path("/upload")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces("application/json;charset=UTF-8")
	public R upload(@Context HttpServletRequest request, MultipartFormDataInput input) {

		String url = "";
		Map<String, List<InputPart>> dataMaps = input.getFormDataMap();

		// 根据表单元素名称获取表单元素
		List<InputPart> fileParts = dataMaps.get("file");// 表单元素-文件

		// 解析获取表单文件的输入流
		InputStream inputStream;
		try {
			if (fileParts == null || fileParts.isEmpty()) {
				return R.error().put("msg", "未上传文件");
			}

			InputPart filePart = fileParts.get(0);

			inputStream = filePart.getBody(InputStream.class, null);

			url = fileUploader.upload(inputStream);
			
			inputStream.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return R.ok().put("url", url);
	}

	
}
