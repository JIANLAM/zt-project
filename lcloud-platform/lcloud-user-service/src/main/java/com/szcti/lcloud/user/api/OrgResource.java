package com.szcti.lcloud.user.api;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.szcti.lcloud.common.utils.JSONUtil;
import com.szcti.lcloud.common.utils.R;
import com.szcti.lcloud.user.entity.TOrganization;
import com.szcti.lcloud.user.entity.TOrganizationType;
import com.szcti.lcloud.user.entity.TRole;
import com.szcti.lcloud.user.entity.vo.OrganizationVO;
import com.szcti.lcloud.user.entity.vo.QueryRoleVo;
import com.szcti.lcloud.user.mapper.TOrganizationTypeMapper;
import com.szcti.lcloud.user.service.TOrganizationService;
import com.szcti.lcloud.user.service.TRoleService;

@Component
@Path("org")
public class OrgResource {

	@Autowired
	private TOrganizationService orgService;
	
	@Autowired
	private TRoleService roleService;
	@Autowired
	private TOrganizationTypeMapper organizationTypeMapper;
	
	@Path("/list")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public R rolelist(@QueryParam("jsonStr") String jsonStr) {
		try {

			QueryRoleVo qrv = new QueryRoleVo();
			if (StringUtils.isNotEmpty(jsonStr)) {
				qrv = (QueryRoleVo) JSONUtil.json2Object(jsonStr, QueryRoleVo.class);
				Long rid = qrv.getRoleId();
				TRole trole = roleService.selectById(rid);
				if(trole!=null) {
					String orgtype = trole.getOrgType();
					
					List<OrganizationVO> m1 =new ArrayList<>();
					List<TOrganization> p = orgService.selectList(new EntityWrapper<TOrganization>().eq("type", orgtype));
					for (TOrganization tOrganization : p) {
						if(tOrganization.getParentId() ==null) {//一级
							OrganizationVO ov1=new OrganizationVO();
							BeanUtils.copyProperties(tOrganization, ov1);
							m1.add(ov1);
						}
					}
					
					for (OrganizationVO ovo : m1) {
						List<OrganizationVO> m2 =new ArrayList<>();
						for (TOrganization torg : p) {
							if(torg.getParentId()!=null && torg.getParentId().longValue() == ovo.getId().longValue()) {//二级
								OrganizationVO ov2=new OrganizationVO();
								BeanUtils.copyProperties(torg, ov2);
								m2.add(ov2);
							}
						}
						ovo.setChildren(m2);
					}
					
					for (OrganizationVO ovo : m1) {
						List<OrganizationVO> m2 =ovo.getChildren();
						for (OrganizationVO o2 : m2) {
							List<OrganizationVO> m3 =new ArrayList<>();
							for (TOrganization torg : p) {
								if(torg.getParentId()!=null && torg.getParentId().longValue() == o2.getId().longValue()) {//三级
									OrganizationVO ov3=new OrganizationVO();
									BeanUtils.copyProperties(torg, ov3);
									m3.add(ov3);
								}
							}
							o2.setChildren(m3);
						}
					}
					
					
					return R.ok().put("list", m1);
				}
			}
			return R.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}
	@Path("/typelist")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public R orgtypelist(@QueryParam("jsonStr") String jsonStr) {
		try {
			
			List<TOrganizationType>  ls = organizationTypeMapper.selectList(null);
			
			return R.ok().put("list", ls);
			
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}

}
