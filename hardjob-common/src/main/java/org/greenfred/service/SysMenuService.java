package org.greenfred.service;

import java.util.List;
import org.greenfred.entity.po.SysMenu;
import org.greenfred.entity.query.SysMenuQuery;
import org.greenfred.entity.vo.PaginationResultVO;
 /**
 * @ Description: Service
 * @ author: 郭丰锐
 * @ date: 2025/01/22
 */
public interface SysMenuService {

	/** 
	* 根据条件查询列表
	*/
	List<SysMenu> findListByParam(SysMenuQuery query);

	/** 
	* 根据条件查询数量
	*/
	Integer findCountByParam(SysMenuQuery query);

	/** 
	* 分页查询
	*/
	PaginationResultVO<SysMenu>findListByPage(SysMenuQuery query);

	/** 
	* 新增
	*/
	Integer add(SysMenu bean);

	/** 
	* 批量新增
	*/
	Integer addBatch(List<SysMenu> listBean);

	/** 
	* 批量新增或修改
	*/
	Integer addOrUpdateBatch(List<SysMenu> listBean);

	/** 
	* 根据MenuId查询
	*/
	SysMenu getSysMenuByMenuId(Integer menuId);

	/** 
	* 根据MenuId更新
	*/
	Integer updateSysMenuByMenuId(SysMenu bean, Integer menuId);

	/** 
	* 根据MenuId删除
	*/
	Integer deleteSysMenuByMenuId(Integer menuId);

}