package org.greenfred.entity.po;

import java.io.Serializable;


 /**
 * @ Description: 
 * @ author: 郭丰锐
 * @ date: 2025/01/22
 */
public class SysRole2Menu implements Serializable {
	/** 
	* 角色ID
	*/

	private Integer roleId;

	/** 
	* 菜单ID
	*/

	private Integer menuId;

	/** 
	* 0:半选 1：全选
	*/

	private Integer checkType;

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public Integer getMenuId() {
		return this.menuId;
	}

	public void setCheckType(Integer checkType) {
		this.checkType = checkType;
	}

	public Integer getCheckType() {
		return this.checkType;
	}

	@Override
	public String toString() {
		return "角色ID:" + (roleId == null ? " 空 " : roleId) + ",菜单ID:" + (menuId == null ? " 空 " : menuId) + ",0:半选 1：全选:" + (checkType == null ? " 空 " : checkType);
	}
}