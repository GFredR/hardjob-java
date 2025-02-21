package org.greenfred.mappers;

import org.apache.ibatis.annotations.Param;
import java.io.Serializable;
 /**
 * @ Description: Mapper
 * @ author: 郭丰锐
 * @ date: 2025/02/18
 */
public interface CategoryMapper<T, P> extends BaseMapper {
	/** 
	* 根据CategoryId查询
	*/
	T selectByCategoryId(@Param("categoryId") Integer categoryId);

	/** 
	* 根据CategoryId更新
	*/
	Integer updateByCategoryId(@Param("bean") T t, @Param("categoryId") Integer categoryId);

	/** 
	* 根据CategoryId删除
	*/
	Integer deleteByCategoryId(@Param("categoryId") Integer categoryId);

	void updateCategoryName(@Param("categoryName") String categoryName, @Param("categoryId") Integer categoryId);
}