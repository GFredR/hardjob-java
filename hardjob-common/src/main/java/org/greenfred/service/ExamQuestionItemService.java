package org.greenfred.service;

import java.util.List;
import org.greenfred.entity.po.ExamQuestionItem;
import org.greenfred.entity.query.ExamQuestionItemQuery;
import org.greenfred.entity.vo.PaginationResultVO;
 /**
 * @ Description: Service
 * @ author: 郭丰锐
 * @ date: 2025/02/18
 */
public interface ExamQuestionItemService {

	/** 
	* 根据条件查询列表
	*/
	List<ExamQuestionItem> findListByParam(ExamQuestionItemQuery query);

	/** 
	* 根据条件查询数量
	*/
	Integer findCountByParam(ExamQuestionItemQuery query);

	/** 
	* 分页查询
	*/
	PaginationResultVO<ExamQuestionItem>findListByPage(ExamQuestionItemQuery query);

	/** 
	* 新增
	*/
	Integer add(ExamQuestionItem bean);

	/** 
	* 批量新增
	*/
	Integer addBatch(List<ExamQuestionItem> listBean);

	/** 
	* 批量新增或修改
	*/
	Integer addOrUpdateBatch(List<ExamQuestionItem> listBean);

	/** 
	* 根据ItemId查询
	*/
	ExamQuestionItem getExamQuestionItemByItemId(Integer itemId);

	/** 
	* 根据ItemId更新
	*/
	Integer updateExamQuestionItemByItemId(ExamQuestionItem bean, Integer itemId);

	/** 
	* 根据ItemId删除
	*/
	Integer deleteExamQuestionItemByItemId(Integer itemId);

}