package org.greenfred.service;

import java.util.List;
import org.greenfred.entity.po.SysRole2Menu;
import org.greenfred.entity.query.SysRole2MenuQuery;
import org.greenfred.entity.vo.PaginationResultVO;
 /**
 * @ Description: Service
 * @ author: 郭丰锐
 * @ date: 2025/01/22
 */
public interface SysRole2MenuService {

	/** 
	* 根据条件查询列表
	*/
	List<SysRole2Menu> findListByParam(SysRole2MenuQuery query);

	/** 
	* 根据条件查询数量
	*/
	Integer findCountByParam(SysRole2MenuQuery query);

	/** 
	* 分页查询
	*/
	PaginationResultVO<SysRole2Menu>findListByPage(SysRole2MenuQuery query);

	/** 
	* 新增
	*/
	Integer add(SysRole2Menu bean);

	/** 
	* 批量新增
	*/
	Integer addBatch(List<SysRole2Menu> listBean);

	/** 
	* 批量新增或修改
	*/
	Integer addOrUpdateBatch(List<SysRole2Menu> listBean);

	/** 
	* 根据RoleIdAndMenuId查询
	*/
	SysRole2Menu getSysRole2MenuByRoleIdAndMenuId(Integer roleId, Integer menuId);

	/** 
	* 根据RoleIdAndMenuId更新
	*/
	Integer updateSysRole2MenuByRoleIdAndMenuId(SysRole2Menu bean, Integer roleId, Integer menuId);

	/** 
	* 根据RoleIdAndMenuId删除
	*/
	Integer deleteSysRole2MenuByRoleIdAndMenuId(Integer roleId, Integer menuId);

}