package org.greenfred.controller;

import java.util.List;
import org.greenfred.entity.po.SysRole2Menu;
import org.greenfred.entity.query.SysRole2MenuQuery;
import org.greenfred.entity.vo.PaginationResultVO;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import javax.annotation.Resource;
import org.greenfred.service.SysRole2MenuService;
import org.greenfred.controller.BaseController;
import org.greenfred.entity.vo.ResponseVO;
 /**
 * @ Description: Controller
 * @ author: 郭丰锐
 * @ date: 2025/01/22
 */
@RestController
@RequestMapping("sysRole2Menu")
public class SysRole2MenuController extends BaseController{

	@Resource
	private SysRole2MenuService sysRole2MenuService;

	/** 
	* 加载列表
	*/
	@RequestMapping("loadDataList")
	public ResponseVO loadDataList(SysRole2MenuQuery query) {
		return getSuccessResponseVO(sysRole2MenuService.findListByPage(query));
	}

	/** 
	* 新增
	*/
	@RequestMapping("add")
	public ResponseVO add(SysRole2Menu bean) {
		this.sysRole2MenuService.add(bean);
		return getSuccessResponseVO(null);
	}

	/** 
	* 批量新增
	*/
	@RequestMapping("addBatch")
	public ResponseVO addBatch(@RequestBody List<SysRole2Menu> listBean) {
		this.sysRole2MenuService.addBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/** 
	* 批量新增或修改
	*/
	@RequestMapping("addOrUpdateBatch")
	public ResponseVO addOrUpdateBatch(@RequestBody List<SysRole2Menu> listBean) {
		this.sysRole2MenuService.addOrUpdateBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/** 
	* 根据RoleIdAndMenuId查询
	*/
	@RequestMapping("getSysRole2MenuByRoleIdAndMenuId")
	public ResponseVO getSysRole2MenuByRoleIdAndMenuId(Integer roleId, Integer menuId) {
		return getSuccessResponseVO(this.sysRole2MenuService.getSysRole2MenuByRoleIdAndMenuId(roleId, menuId));
	}

	/** 
	* 根据RoleIdAndMenuId更新
	*/
	@RequestMapping("updateSysRole2MenuByRoleIdAndMenuId")
	public ResponseVO updateSysRole2MenuByRoleIdAndMenuId(SysRole2Menu bean, Integer roleId, Integer menuId) {
		this.sysRole2MenuService.updateSysRole2MenuByRoleIdAndMenuId(bean,roleId, menuId);
		return getSuccessResponseVO(null);
	}

	/** 
	* 根据RoleIdAndMenuId删除
	*/
	@RequestMapping("deleteSysRole2MenuByRoleIdAndMenuId")
	public ResponseVO deleteSysRole2MenuByRoleIdAndMenuId(Integer roleId, Integer menuId) {
		this.sysRole2MenuService.deleteSysRole2MenuByRoleIdAndMenuId(roleId, menuId);
		return getSuccessResponseVO(null);
	}
}