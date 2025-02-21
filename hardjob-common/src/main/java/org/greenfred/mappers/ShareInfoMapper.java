package org.greenfred.mappers;

import org.apache.ibatis.annotations.Param;
import java.io.Serializable;
 /**
 * @ Description: Mapper
 * @ author: 郭丰锐
 * @ date: 2025/02/18
 */
public interface ShareInfoMapper<T, P> extends BaseMapper {
	/** 
	* 根据ShareId查询
	*/
	T selectByShareId(@Param("shareId") Integer shareId);

	/** 
	* 根据ShareId更新
	*/
	Integer updateByShareId(@Param("bean") T t, @Param("shareId") Integer shareId);

	/** 
	* 根据ShareId删除
	*/
	Integer deleteByShareId(@Param("shareId") Integer shareId);


}