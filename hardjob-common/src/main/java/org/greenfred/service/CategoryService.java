package org.greenfred.service;

import java.util.List;
import org.greenfred.entity.po.Category;
import org.greenfred.entity.query.CategoryQuery;
import org.greenfred.entity.vo.PaginationResultVO;
import org.greenfred.enums.CategoryTypeEnum;
import org.greenfred.exception.BusinessException;

/**
 * @ Description: Service
 * @ author: 郭丰锐
 * @ date: 2025/02/18
 */
public interface CategoryService {

	/** 
	* 根据条件查询列表
	*/
	List<Category> findListByParam(CategoryQuery query);

	/** 
	* 根据条件查询数量
	*/
	Integer findCountByParam(CategoryQuery query);

	/** 
	* 分页查询
	*/
	PaginationResultVO<Category>findListByPage(CategoryQuery query);

	/** 
	* 新增
	*/
	Integer add(Category bean);

	/** 
	* 批量新增
	*/
	Integer addBatch(List<Category> listBean);

	/** 
	* 批量新增或修改
	*/
	Integer addOrUpdateBatch(List<Category> listBean);

	/** 
	* 根据CategoryId查询
	*/
	Category getCategoryByCategoryId(Integer categoryId);

	/** 
	* 根据CategoryId更新
	*/
	Integer updateCategoryByCategoryId(Category bean, Integer categoryId);

	/** 
	* 根据CategoryId删除
	*/
	Integer deleteCategoryByCategoryId(Integer categoryId);

	void saveCategory(Category category);

	void changeSort(String categoryIds);

	List<Category> loadAllCategoryByType(Integer type) throws BusinessException;
}