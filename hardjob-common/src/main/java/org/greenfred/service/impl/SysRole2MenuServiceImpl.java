package org.greenfred.service.impl;

import java.util.List;
import org.greenfred.entity.po.SysRole2Menu;
import org.greenfred.entity.query.SysRole2MenuQuery;
import org.greenfred.entity.vo.PaginationResultVO;
import org.greenfred.service.SysRole2MenuService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.greenfred.mappers.SysRole2MenuMapper;
import org.greenfred.enums.PageSize;
import org.greenfred.entity.query.SimplePage;
 /**
 * @ Description: Service
 * @ author: 郭丰锐
 * @ date: 2025/01/22
 */
@Service("sysRole2MenuService")
public class SysRole2MenuServiceImpl implements SysRole2MenuService {

	@Resource
	private SysRole2MenuMapper<SysRole2Menu,SysRole2MenuQuery> sysRole2MenuMapper;

	/** 
	* 根据条件查询列表
	*/
	public List<SysRole2Menu> findListByParam(SysRole2MenuQuery query) {
		return this.sysRole2MenuMapper.selectList(query);
	}
	/** 
	* 根据条件查询数量
	*/
	public Integer findCountByParam(SysRole2MenuQuery query) {
		return this.sysRole2MenuMapper.selectCount(query);
	}
	/** 
	* 分页查询
	*/
	public PaginationResultVO<SysRole2Menu> findListByPage(SysRole2MenuQuery query) {
		Integer count = this.findCountByParam(query);
		Integer pageSize = query.getPageSize() == null ? PageSize.SIZE15.getSize() : query.getPageSize();
		SimplePage page = new SimplePage(query.getPageNo(), count, pageSize);
		query.setSimplePage(page);
		List<SysRole2Menu> list = this.findListByParam(query);
		PaginationResultVO<SysRole2Menu> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), list);
		return result;
	}
	/** 
	* 新增
	*/
	public Integer add(SysRole2Menu bean) {
		return this.sysRole2MenuMapper.insert(bean);
	}
	/** 
	* 批量新增
	*/
	public Integer addBatch(List<SysRole2Menu> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.sysRole2MenuMapper.insertBatch(listBean);
	}

	/** 
	* 批量新增或修改
	*/
	public Integer addOrUpdateBatch(List<SysRole2Menu> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.sysRole2MenuMapper.insertOrUpdateBatch(listBean);
	}
	/** 
	* 根据RoleIdAndMenuId查询
	*/
	public SysRole2Menu getSysRole2MenuByRoleIdAndMenuId(Integer roleId, Integer menuId) {
		return this.sysRole2MenuMapper.selectByRoleIdAndMenuId(roleId, menuId);
	}
	/** 
	* 根据RoleIdAndMenuId更新
	*/
	public Integer updateSysRole2MenuByRoleIdAndMenuId(SysRole2Menu bean, Integer roleId, Integer menuId) {
		return this.sysRole2MenuMapper.updateByRoleIdAndMenuId(bean,roleId, menuId);
	}
	/** 
	* 根据RoleIdAndMenuId删除
	*/
	public Integer deleteSysRole2MenuByRoleIdAndMenuId(Integer roleId, Integer menuId) {
		return this.sysRole2MenuMapper.deleteByRoleIdAndMenuId(roleId, menuId);
	}
}