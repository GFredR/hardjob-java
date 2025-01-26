package org.greenfred.service.impl;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.greenfred.enums.DateTimePatternEnum;
import org.greenfred.utils.DateUtils;
import java.util.List;
import org.greenfred.entity.po.SysRole;
import org.greenfred.entity.query.SysRoleQuery;
import org.greenfred.entity.vo.PaginationResultVO;
import org.greenfred.service.SysRoleService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.greenfred.mappers.SysRoleMapper;
import org.greenfred.enums.PageSize;
import org.greenfred.entity.query.SimplePage;
 /**
 * @ Description: Service
 * @ author: 郭丰锐
 * @ date: 2025/01/22
 */
@Service("sysRoleService")
public class SysRoleServiceImpl implements SysRoleService {

	@Resource
	private SysRoleMapper<SysRole,SysRoleQuery> sysRoleMapper;

	/** 
	* 根据条件查询列表
	*/
	public List<SysRole> findListByParam(SysRoleQuery query) {
		return this.sysRoleMapper.selectList(query);
	}
	/** 
	* 根据条件查询数量
	*/
	public Integer findCountByParam(SysRoleQuery query) {
		return this.sysRoleMapper.selectCount(query);
	}
	/** 
	* 分页查询
	*/
	public PaginationResultVO<SysRole> findListByPage(SysRoleQuery query) {
		Integer count = this.findCountByParam(query);
		Integer pageSize = query.getPageSize() == null ? PageSize.SIZE15.getSize() : query.getPageSize();
		SimplePage page = new SimplePage(query.getPageNo(), count, pageSize);
		query.setSimplePage(page);
		List<SysRole> list = this.findListByParam(query);
		PaginationResultVO<SysRole> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), list);
		return result;
	}
	/** 
	* 新增
	*/
	public Integer add(SysRole bean) {
		return this.sysRoleMapper.insert(bean);
	}
	/** 
	* 批量新增
	*/
	public Integer addBatch(List<SysRole> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.sysRoleMapper.insertBatch(listBean);
	}

	/** 
	* 批量新增或修改
	*/
	public Integer addOrUpdateBatch(List<SysRole> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.sysRoleMapper.insertOrUpdateBatch(listBean);
	}
	/** 
	* 根据RoleId查询
	*/
	public SysRole getSysRoleByRoleId(Integer roleId) {
		return this.sysRoleMapper.selectByRoleId(roleId);
	}
	/** 
	* 根据RoleId更新
	*/
	public Integer updateSysRoleByRoleId(SysRole bean, Integer roleId) {
		return this.sysRoleMapper.updateByRoleId(bean,roleId);
	}
	/** 
	* 根据RoleId删除
	*/
	public Integer deleteSysRoleByRoleId(Integer roleId) {
		return this.sysRoleMapper.deleteByRoleId(roleId);
	}
}