package org.greenfred.mappers;

import org.apache.ibatis.annotations.Param;
import java.io.Serializable;
import java.util.List;

/**
 * @ Description: Mapper
 * @ author: 郭丰锐
 * @ date: 2025/01/22
 */
public interface SysRole2MenuMapper<T, P> extends BaseMapper {
	/** 
	* 根据RoleIdAndMenuId查询
	*/
	T selectByRoleIdAndMenuId(@Param("roleId") Integer roleId, @Param("menuId") Integer menuId);

	/** 
	* 根据RoleIdAndMenuId更新
	*/
	Integer updateByRoleIdAndMenuId(@Param("bean") T t, @Param("roleId") Integer roleId, @Param("menuId") Integer menuId);

	/** 
	* 根据RoleIdAndMenuId删除
	*/
	Integer deleteByRoleIdAndMenuId(@Param("roleId") Integer roleId, @Param("menuId") Integer menuId);

	List<Integer> selectMenuIdsByRoleIds(@Param("roleIds") String[] roleIds);

	void deleteByRoleId(@Param("roleId") Integer roleId);

}