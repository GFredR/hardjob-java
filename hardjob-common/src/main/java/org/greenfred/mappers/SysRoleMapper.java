package org.greenfred.mappers;

import org.apache.ibatis.annotations.Param;
import java.io.Serializable;
 /**
 * @ Description: Mapper
 * @ author: 郭丰锐
 * @ date: 2025/01/22
 */
public interface SysRoleMapper<T, P> extends BaseMapper {
	/** 
	* 根据RoleId查询
	*/
	T selectByRoleId(@Param("roleId") Integer roleId);

	/** 
	* 根据RoleId更新
	*/
	Integer updateByRoleId(@Param("bean") T t, @Param("roleId") Integer roleId);

	/** 
	* 根据RoleId删除
	*/
	Integer deleteByRoleId(@Param("roleId") Integer roleId);


}