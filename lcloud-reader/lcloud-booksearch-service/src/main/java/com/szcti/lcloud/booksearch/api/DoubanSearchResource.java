package com.szcti.lcloud.booksearch.api;

import java.io.BufferedInputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.szcti.lcloud.booksearch.entity.TSearchDouban;
import com.szcti.lcloud.booksearch.mapper.TSearchDoubanMapper;
import com.szcti.lcloud.common.utils.FileUploader;
import com.szcti.lcloud.common.utils.R;

@Component
@Path("douban")
public class DoubanSearchResource {
	
	private static final Logger log = LoggerFactory.getLogger(DoubanSearchResource.class);

	@Autowired
	TSearchDoubanMapper searchResultMapper;
	@Autowired
	private FileUploader fileUploader;
	
	@Path("/book")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public R book(@QueryParam("q") String q,@QueryParam("start") String start,@QueryParam("count") String count, @Context HttpServletRequest request) {
		//https://api.douban.com/v2/book/search
		
		R r =R.error("查询失败");
		
		if(!StringUtils.hasText(q)) {
			return R.error("查询参数不能为空");
		}
		if(!StringUtils.hasText(start)) {
			start="0";
		}
		if(!StringUtils.hasText(count)) {
			count="10";
		}
		try {
			Response resp = getConnection("https://api.douban.com/v2/book/search")//
					.data("q", q)//
					.data("start", start)//
					.data("count", count)//
					.execute();
			if(resp.statusCode()==200) {
				log.debug(resp.body());
				JSONObject obj = JSON.parseObject(resp.body());
				JSONArray ja = obj.getJSONArray("books");
				for (int i = 0;i<ja.size();i++) {
					JSONObject jobj = ja.getJSONObject(i);
					Long id = jobj.getLong("id");
					TSearchDouban res = searchResultMapper.selectById(id);
					
					String image = jobj.getString("image");
					if(res !=null) {
						jobj.put("image", res.getLocalImage());
					}else {
						try {
							
							BufferedInputStream inputStream= getConnection(image).execute().bodyStream();
							String local_image = fileUploader.upload(inputStream);
							
							res =new TSearchDouban();
							res.setId(id);
							res.setBookinfo(jobj.toJSONString());
							res.setImage(image);
							res.setTitle(jobj.getString("title"));
							res.setIsbn10(jobj.getString("isbn10"));
							res.setIsbn13(jobj.getString("isbn13"));
							
							res.setLocalImage(local_image);
							searchResultMapper.insert(res);
							jobj.put("image", res.getLocalImage());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					
				}
				r=R.ok().put("result", obj);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}
	@Path("/comments")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public R comments(@QueryParam("id") String id,@QueryParam("start") String start,@QueryParam("count") String count) {
		
		R r =R.error("查询失败");
		
		if(!StringUtils.hasText(id)) {
			return R.error("查询参数不能为空");
		}
		if(!StringUtils.hasText(start)) {
			start="0";
		}
		if(!StringUtils.hasText(count)) {
			count="10";
		}
		try {
			Response resp = getConnection("https://api.douban.com/v2/book/"+id+"/comments")//
					.data("start", start)//
					.data("count", count)//
					.execute();
			if(resp.statusCode()==200) {
				log.debug(resp.body());
				JSONObject obj = JSON.parseObject(resp.body());
				r.put("result", obj);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}

	
	public static Connection getConnection(String url) {
		Connection conn = Jsoup.connect(url)
				.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
				.header("Accept-Encoding", "gzip, deflate, sdch").header("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6")
				.header("Cache-Control", "max-age=0")
				.header("User-Agent",
						"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36")
				.timeout(10 * 1000)
				.ignoreContentType(true)
				;

		return conn;
	}

	public static Connection getConnection(String url, String ip, int port, String type) {
		Connection conn = getConnection(url);
		InetSocketAddress isa = new InetSocketAddress(ip, port);
		if ("socks".equalsIgnoreCase(type)) {
			Proxy proxy = new Proxy(Type.SOCKS, isa);
			conn.proxy(proxy);
		} else {
			Proxy proxy = new Proxy(Type.HTTP, isa);
			conn.proxy(proxy);
		}
		return conn;
	}
	
}
