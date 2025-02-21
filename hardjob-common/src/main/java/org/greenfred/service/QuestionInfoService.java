package org.greenfred.service;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.greenfred.exception.BusinessException;
import org.springframework.format.annotation.DateTimeFormat;
import org.greenfred.enums.DateTimePatternEnum;
import org.greenfred.utils.DateUtils;
import java.util.List;
import org.greenfred.entity.po.QuestionInfo;
import org.greenfred.entity.query.QuestionInfoQuery;
import org.greenfred.entity.vo.PaginationResultVO;
 /**
 * @ Description: Service
 * @ author: 郭丰锐
 * @ date: 2025/02/18
 */
public interface QuestionInfoService {

	/** 
	* 根据条件查询列表
	*/
	List<QuestionInfo> findListByParam(QuestionInfoQuery query);

	/** 
	* 根据条件查询数量
	*/
	Integer findCountByParam(QuestionInfoQuery query);

	/** 
	* 分页查询
	*/
	PaginationResultVO<QuestionInfo>findListByPage(QuestionInfoQuery query);

	/** 
	* 新增
	*/
	Integer add(QuestionInfo bean);

	/** 
	* 批量新增
	*/
	Integer addBatch(List<QuestionInfo> listBean);

	/** 
	* 批量新增或修改
	*/
	Integer addOrUpdateBatch(List<QuestionInfo> listBean);

	/** 
	* 根据QuestionId查询
	*/
	QuestionInfo getQuestionInfoByQuestionId(Integer questionId);

	/** 
	* 根据QuestionId更新
	*/
	Integer updateQuestionInfoByQuestionId(QuestionInfo bean, Integer questionId);

	/** 
	* 根据QuestionId删除
	*/
	Integer deleteQuestionInfoByQuestionId(Integer questionId);

	void saveQuestion(QuestionInfo questionInfo, Boolean isAdmin) throws BusinessException;
}