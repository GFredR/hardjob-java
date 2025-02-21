package org.greenfred.service;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.greenfred.enums.DateTimePatternEnum;
import org.greenfred.utils.DateUtils;
import java.util.List;
import org.greenfred.entity.po.ExamQuestion;
import org.greenfred.entity.query.ExamQuestionQuery;
import org.greenfred.entity.vo.PaginationResultVO;
 /**
 * @ Description: Service
 * @ author: 郭丰锐
 * @ date: 2025/02/18
 */
public interface ExamQuestionService {

	/** 
	* 根据条件查询列表
	*/
	List<ExamQuestion> findListByParam(ExamQuestionQuery query);

	/** 
	* 根据条件查询数量
	*/
	Integer findCountByParam(ExamQuestionQuery query);

	/** 
	* 分页查询
	*/
	PaginationResultVO<ExamQuestion>findListByPage(ExamQuestionQuery query);

	/** 
	* 新增
	*/
	Integer add(ExamQuestion bean);

	/** 
	* 批量新增
	*/
	Integer addBatch(List<ExamQuestion> listBean);

	/** 
	* 批量新增或修改
	*/
	Integer addOrUpdateBatch(List<ExamQuestion> listBean);

	/** 
	* 根据QuestionId查询
	*/
	ExamQuestion getExamQuestionByQuestionId(Integer questionId);

	/** 
	* 根据QuestionId更新
	*/
	Integer updateExamQuestionByQuestionId(ExamQuestion bean, Integer questionId);

	/** 
	* 根据QuestionId删除
	*/
	Integer deleteExamQuestionByQuestionId(Integer questionId);

}