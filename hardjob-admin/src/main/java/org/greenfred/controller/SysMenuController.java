package org.greenfred.controller;

import java.util.List;
import org.greenfred.entity.po.SysMenu;
import org.greenfred.entity.query.SysMenuQuery;
import org.greenfred.entity.vo.PaginationResultVO;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import javax.annotation.Resource;
import org.greenfred.service.SysMenuService;
import org.greenfred.controller.BaseController;
import org.greenfred.entity.vo.ResponseVO;
 /**
 * @ Description: Controller
 * @ author: 郭丰锐
 * @ date: 2025/01/22
 */
@RestController
@RequestMapping("setting")
public class SysMenuController extends BaseController{

	@Resource
	private SysMenuService sysMenuService;

	/** 
	* 加载列表
	*/
	@RequestMapping("menuList")
	public ResponseVO loadDataList() {
		SysMenuQuery query = new SysMenuQuery();
		query.setFormat2Tree(true);
		query.setOrderBy("sort asc");

		List<SysMenu> sysMenuList = sysMenuService.findListByParam(query);

		return getSuccessResponseVO(sysMenuList);
	}

	/** 
	* 新增
	*/
	@RequestMapping("add")
	public ResponseVO add(SysMenu bean) {
		this.sysMenuService.add(bean);
		return getSuccessResponseVO(null);
	}

	/** 
	* 批量新增
	*/
	@RequestMapping("addBatch")
	public ResponseVO addBatch(@RequestBody List<SysMenu> listBean) {
		this.sysMenuService.addBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/** 
	* 批量新增或修改
	*/
	@RequestMapping("addOrUpdateBatch")
	public ResponseVO addOrUpdateBatch(@RequestBody List<SysMenu> listBean) {
		this.sysMenuService.addOrUpdateBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/** 
	* 根据MenuId查询
	*/
	@RequestMapping("getSysMenuByMenuId")
	public ResponseVO getSysMenuByMenuId(Integer menuId) {
		return getSuccessResponseVO(this.sysMenuService.getSysMenuByMenuId(menuId));
	}

	/** 
	* 根据MenuId更新
	*/
	@RequestMapping("updateSysMenuByMenuId")
	public ResponseVO updateSysMenuByMenuId(SysMenu bean, Integer menuId) {
		this.sysMenuService.updateSysMenuByMenuId(bean,menuId);
		return getSuccessResponseVO(null);
	}

	/** 
	* 根据MenuId删除
	*/
	@RequestMapping("deleteSysMenuByMenuId")
	public ResponseVO deleteSysMenuByMenuId(Integer menuId) {
		this.sysMenuService.deleteSysMenuByMenuId(menuId);
		return getSuccessResponseVO(null);
	}
}