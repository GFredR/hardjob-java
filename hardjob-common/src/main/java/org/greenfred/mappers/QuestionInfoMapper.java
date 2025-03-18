package org.greenfred.mappers;

import org.apache.ibatis.annotations.Param;
import org.greenfred.entity.po.QuestionInfo;
import org.greenfred.entity.query.QuestionInfoQuery;

import java.io.Serializable;
 /**
 * @ Description: Mapper
 * @ author: 郭丰锐
 * @ date: 2025/02/18
 */
public interface QuestionInfoMapper<T, P> extends BaseMapper {
	/** 
	* 根据QuestionId查询
	*/
	T selectByQuestionId(@Param("questionId") Integer questionId);

	/** 
	* 根据QuestionId更新
	*/
	Integer updateByQuestionId(@Param("bean") T t, @Param("questionId") Integer questionId);

	/** 
	* 根据QuestionId删除
	*/
	Integer deleteByQuestionId(@Param("questionId") Integer questionId);

	void deleteBatchByQuestionId(@Param("questionIdArray")String[] questionIdArray, @Param("status")Integer status, @Param("userId")Integer userId);

	void updateQuestionByParam(@Param("status")Integer status, @Param("questionIdArray")String[] questionIdArray);

	T showDetailNext(@Param("query") P p);
}