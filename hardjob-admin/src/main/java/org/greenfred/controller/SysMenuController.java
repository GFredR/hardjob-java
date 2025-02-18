package org.greenfred.controller;

import java.util.List;

import org.greenfred.annotation.GlobalInterceptor;
import org.greenfred.annotation.VerifyParam;
import org.greenfred.entity.po.SysMenu;
import org.greenfred.entity.query.SysMenuQuery;
import org.greenfred.entity.vo.PaginationResultVO;

import org.greenfred.enums.PermissionCodeEnum;
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
	@RequestMapping("/menuList")
	@GlobalInterceptor(permissionCode = PermissionCodeEnum.SETTINGS_MENU)
	public ResponseVO menuList() {
		SysMenuQuery query = new SysMenuQuery();
		query.setFormat2Tree(true);
		query.setOrderBy("sort asc");

		List<SysMenu> sysMenuList = sysMenuService.findListByParam(query);

		return getSuccessResponseVO(sysMenuList);
	}

	 @RequestMapping("/saveMenu")
	 @GlobalInterceptor(permissionCode = PermissionCodeEnum.SETTINGS_MENU_EDIT)
	 public ResponseVO saveMenu(@VerifyParam SysMenu sysMenu) {
		 sysMenuService.saveMenu(sysMenu);
		 return getSuccessResponseVO(null);
	 }

	 @RequestMapping("/deleteMenu")
	 @GlobalInterceptor(permissionCode = PermissionCodeEnum.SETTINGS_MENU_EDIT)
	 public ResponseVO deleteMenu(@VerifyParam(required = true) Integer menuId) {
		 sysMenuService.deleteSysMenuByMenuId(menuId);
		 return getSuccessResponseVO(null);
	 }


}