package com.szcti.lcloud.booksearch.api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szcti.lcloud.booksearch.entity.TBookXh;
import com.szcti.lcloud.booksearch.mapper.TBookXhMapper;
import com.szcti.lcloud.common.utils.R;

@Component
@Path("xinhua")
public class XinHuaSearchResource {

	@Autowired
	private TBookXhMapper bookXhMapper;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Path("/book")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public R book(@QueryParam("q") String q, @QueryParam("start") String start, @QueryParam("count") String count) {
		if (!StringUtils.hasText(q)) {
			return R.error("查询参数不能为空");
		}

		if (!StringUtils.hasText(start)) {
			start = "0";
		}

		if (!StringUtils.hasText(count)) {
			count = "10";
		}
		EntityWrapper<TBookXh> ew = new EntityWrapper<>();

		ew.like("title", q).or().like("author", q).or().eq("isbn", q.replace("-", ""));
		PageHelper.startPage(NumberUtils.toInt(start), NumberUtils.toInt(count));
		List<TBookXh> ls = bookXhMapper.selectList(ew);
		PageInfo page = new PageInfo(ls);

		return R.ok().put("result", page);
	}
}
