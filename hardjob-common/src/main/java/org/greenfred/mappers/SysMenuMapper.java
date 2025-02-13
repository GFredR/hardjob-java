package org.greenfred.mappers;

import org.apache.ibatis.annotations.Param;
import java.io.Serializable;
import java.util.List;

/**
 * @ Description: Mapper
 * @ author: 郭丰锐
 * @ date: 2025/01/22
 */
public interface SysMenuMapper<T, P> extends BaseMapper {
	/** 
	* 根据MenuId查询
	*/
	T selectByMenuId(@Param("menuId") Integer menuId);

	/** 
	* 根据MenuId更新
	*/
	Integer updateByMenuId(@Param("bean") T t, @Param("menuId") Integer menuId);

	/** 
	* 根据MenuId删除
	*/
	Integer deleteByMenuId(@Param("menuId") Integer menuId);

	List<T> selectAllMenuByRoleIds(@Param("roleIds") int[] roleIds);

}