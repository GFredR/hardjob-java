package org.greenfred.mappers;

import org.apache.ibatis.annotations.Param;
import java.io.Serializable;
 /**
 * @ Description: Mapper
 * @ author: 郭丰锐
 * @ date: 2025/02/18
 */
public interface ExamQuestionItemMapper<T, P> extends BaseMapper {
	/** 
	* 根据ItemId查询
	*/
	T selectByItemId(@Param("itemId") Integer itemId);

	/** 
	* 根据ItemId更新
	*/
	Integer updateByItemId(@Param("bean") T t, @Param("itemId") Integer itemId);

	/** 
	* 根据ItemId删除
	*/
	Integer deleteByItemId(@Param("itemId") Integer itemId);


}