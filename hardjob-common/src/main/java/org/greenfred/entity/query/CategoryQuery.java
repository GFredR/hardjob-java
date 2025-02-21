package org.greenfred.entity.query;



 /**
 * @ Description: 查询对象
 * @ author: 郭丰锐
 * @ date: 2025/02/18
 */
public class CategoryQuery extends BaseQuery {
	/** 
	* 分类ID
	*/
	private Integer categoryId;

	/** 
	* 名称
	*/
	private String categoryName;

	private String categoryNameFuzzy;

	/** 
	* 排序
	*/
	private Integer sort;

	/** 
	* 图标路径
	*/
	private String iconPath;

	private String iconPathFuzzy;

	/** 
	* 背景颜色
	*/
	private String bgColor;

	private String bgColorFuzzy;

	/** 
	* 0：问题分类 1：考题分类 2：问题分类和考题分类
	*/
	private Integer type;

	private Integer[] types;

	 public Integer[] getTypes() {
		 return types;
	 }

	 public void setTypes(Integer[] types) {
		 this.types = types;
	 }

	 @Override
	public String toString() {
		return "分类ID:" + (categoryId == null ? " 空 " : categoryId) + ",名称:" + (categoryName == null ? " 空 " : categoryName) + ",排序:" + (sort == null ? " 空 " : sort) + ",图标路径:" + (iconPath == null ? " 空 " : iconPath) + ",背景颜色:" + (bgColor == null ? " 空 " : bgColor) + ",0：问题分类 1：考题分类 2：问题分类和考题分类:" + (type == null ? " 空 " : type);
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryName() {
		return this.categoryName;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getSort() {
		return this.sort;
	}

	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}

	public String getIconPath() {
		return this.iconPath;
	}

	public void setBgColor(String bgColor) {
		this.bgColor = bgColor;
	}

	public String getBgColor() {
		return this.bgColor;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getType() {
		return this.type;
	}

	public void setCategoryNameFuzzy(String categoryNameFuzzy) {
		this.categoryNameFuzzy = categoryNameFuzzy;
	}

	public String getCategoryNameFuzzy() {
		return this.categoryNameFuzzy;
	}

	public void setIconPathFuzzy(String iconPathFuzzy) {
		this.iconPathFuzzy = iconPathFuzzy;
	}

	public String getIconPathFuzzy() {
		return this.iconPathFuzzy;
	}

	public void setBgColorFuzzy(String bgColorFuzzy) {
		this.bgColorFuzzy = bgColorFuzzy;
	}

	public String getBgColorFuzzy() {
		return this.bgColorFuzzy;
	}


}