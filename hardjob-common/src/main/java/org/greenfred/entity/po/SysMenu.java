package org.greenfred.entity.po;

import java.io.Serializable;
import java.util.List;


/**
 * @ Description: 
 * @ author: 郭丰锐
 * @ date: 2025/01/22
 */
public class SysMenu implements Serializable {
	/** 
	* 菜单id
	*/

	private Integer menuId;

	/** 
	* 菜单名称
	*/

	private String menuName;

	/** 
	* 菜单类型
	*/

	private Integer menuType;

	/** 
	* 菜单跳转的url
	*/

	private String menuUrl;

	/** 
	* 上级菜单ID
	*/

	private Integer pId;

	/** 
	* 菜单排序
	*/

	private Integer sort;

	/** 
	* 权限编码
	*/

	private String permissionCode;

	/** 
	* 图标
	*/

	private String icon;

	private List<SysMenu> children;

	public List<SysMenu> getChildren() {
		return children;
	}

	public void setChildren(List<SysMenu> children) {
		this.children = children;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public Integer getMenuId() {
		return this.menuId;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuName() {
		return this.menuName;
	}

	public void setMenuType(Integer menuType) {
		this.menuType = menuType;
	}

	public Integer getMenuType() {
		return this.menuType;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public String getMenuUrl() {
		return this.menuUrl;
	}

	public void setPId(Integer pId) {
		this.pId = pId;
	}

	public Integer getPId() {
		return this.pId;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getSort() {
		return this.sort;
	}

	public void setPermissionCode(String permissionCode) {
		this.permissionCode = permissionCode;
	}

	public String getPermissionCode() {
		return this.permissionCode;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getIcon() {
		return this.icon;
	}

	@Override
	public String toString() {
		return "菜单id:" + (menuId == null ? " 空 " : menuId) + ",菜单名称:" + (menuName == null ? " 空 " : menuName) + ",菜单类型:" + (menuType == null ? " 空 " : menuType) + ",菜单跳转的url:" + (menuUrl == null ? " 空 " : menuUrl) + ",上级菜单ID:" + (pId == null ? " 空 " : pId) + ",菜单排序:" + (sort == null ? " 空 " : sort) + ",权限编码:" + (permissionCode == null ? " 空 " : permissionCode) + ",图标:" + (icon == null ? " 空 " : icon);
	}
}