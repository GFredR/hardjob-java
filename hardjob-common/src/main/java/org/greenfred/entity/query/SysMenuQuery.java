package org.greenfred.entity.query;



 /**
 * @ Description: 查询对象
 * @ author: 郭丰锐
 * @ date: 2025/01/22
 */
public class SysMenuQuery extends BaseQuery {
	/** 
	* 菜单id
	*/
	private Integer menuId;

	/** 
	* 菜单名称
	*/
	private String menuName;

	private String menuNameFuzzy;

	/** 
	* 菜单类型
	*/
	private Integer menuType;

	/** 
	* 菜单跳转的url
	*/
	private String menuUrl;

	private String menuUrlFuzzy;

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

	private String permissionCodeFuzzy;

	/** 
	* 图标
	*/
	private String icon;

	private String iconFuzzy;

	private Boolean format2Tree;

	 public Boolean getFormat2Tree() {
		 return format2Tree;
	 }

	 public void setFormat2Tree(Boolean format2Tree) {
		 this.format2Tree = format2Tree;
	 }

	 @Override
	public String toString() {
		return "菜单id:" + (menuId == null ? " 空 " : menuId) + ",菜单名称:" + (menuName == null ? " 空 " : menuName) + ",菜单类型:" + (menuType == null ? " 空 " : menuType) + ",菜单跳转的url:" + (menuUrl == null ? " 空 " : menuUrl) + ",上级菜单ID:" + (pId == null ? " 空 " : pId) + ",菜单排序:" + (sort == null ? " 空 " : sort) + ",权限编码:" + (permissionCode == null ? " 空 " : permissionCode) + ",图标:" + (icon == null ? " 空 " : icon);
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

	public void setMenuNameFuzzy(String menuNameFuzzy) {
		this.menuNameFuzzy = menuNameFuzzy;
	}

	public String getMenuNameFuzzy() {
		return this.menuNameFuzzy;
	}

	public void setMenuUrlFuzzy(String menuUrlFuzzy) {
		this.menuUrlFuzzy = menuUrlFuzzy;
	}

	public String getMenuUrlFuzzy() {
		return this.menuUrlFuzzy;
	}

	public void setPermissionCodeFuzzy(String permissionCodeFuzzy) {
		this.permissionCodeFuzzy = permissionCodeFuzzy;
	}

	public String getPermissionCodeFuzzy() {
		return this.permissionCodeFuzzy;
	}

	public void setIconFuzzy(String iconFuzzy) {
		this.iconFuzzy = iconFuzzy;
	}

	public String getIconFuzzy() {
		return this.iconFuzzy;
	}


}