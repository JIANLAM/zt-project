package com.szcti.lcloud.booksearch.api;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.szcti.lcloud.common.utils.R;

@Component
@Path("szlib")
public class SZLibSearchResource {

	private static final Logger log = LoggerFactory.getLogger(SZLibSearchResource.class);

	@Path("/book")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public R book(@QueryParam("q") String q, @QueryParam("start") String start, @QueryParam("count") String count) {

		R r = R.error("查询失败");

		if (!StringUtils.hasText(q)) {
			return R.error("查询参数不能为空");
		}
		if (!StringUtils.hasText(start)) {
			start = "1";
		}
		if (!StringUtils.hasText(count)) {
			count = "10";
		}
		List<JSONObject> list = new ArrayList<>();
		// https://www.szlib.org.cn/Search/searchshow.jsp?v_tablearray=bibliosm,serbibm,apabibibm,mmbibm,&v_index=title&v_value=%E5%8F%98%E5%BD%A2%E8%AE%B0%20&sortfield=score&sorttype=desc
		try {
			// Document doc = getDoc(
			// "https://www.szlib.org.cn/Search/searchshow.jsp?v_tablearray=bibliosm,serbibm,apabibibm,mmbibm,&v_index=title&v_value="
			// + q + "&sortfield=score&sorttype=desc&pageNum=" + count + "&v_page=" +
			// start);

			Response resp = getConnection(
					"https://www.szlib.org.cn/Search/searchshow.jsp?v_tablearray=bibliosm,serbibm,apabibibm,mmbibm,&v_index=title&v_value="
							+ q + "&sortfield=score&sorttype=desc&pageNum=" + count + "&v_page=" + start).execute();

			if (resp.statusCode() == 200) {

//				log.debug(resp.body());
				Document doc = Jsoup.parse(resp.body());
				String total = doc.select("div.total > span").get(1).text();
				JSONObject result = new JSONObject();
				result.put("total", total);
				result.put("start", start);
				result.put("count", count);

				Elements eles = doc.select("ul.booklist > li");
				for (Element element : eles) {
					JSONObject obj = new JSONObject();

					String img = element.select("a>img.img").attr("src");
					String title = element.select("h3.title>a").text().trim();
					String url = "https://www.szlib.org.cn/Search/" + element.select("h3.title>a").attr("href").trim();
					String author = element.select("div.info > span.author").text().replace("作 者：", "").trim();
					String publisher = element.select("div.info > span.publisher").text().replace("出版者：", "").trim();
					String dates = element.select("div.info > span.dates").text().replace("出版年：", "").trim();
					String summary = element.select("div.text").text().trim();

					obj.put("img", img);
					obj.put("title", title);
					obj.put("url", url);
					obj.put("author", author);
					obj.put("publisher", publisher);
					obj.put("dates", dates);
					obj.put("summary", summary);

					list.add(obj);

				}
				result.put("list", list);

				r = R.ok().put("result", result);
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
				.timeout(10 * 1000).ignoreContentType(true);

		return conn;
	}

}
