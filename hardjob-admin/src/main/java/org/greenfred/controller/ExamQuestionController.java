package org.greenfred.controller;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.greenfred.enums.DateTimePatternEnum;
import org.greenfred.utils.DateUtils;
import java.util.List;
import org.greenfred.entity.po.ExamQuestion;
import org.greenfred.entity.query.ExamQuestionQuery;
import org.greenfred.entity.vo.PaginationResultVO;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import javax.annotation.Resource;
import org.greenfred.service.ExamQuestionService;
import org.greenfred.controller.BaseController;
import org.greenfred.entity.vo.ResponseVO;
 /**
 * @ Description: Controller
 * @ author: 郭丰锐
 * @ date: 2025/02/18
 */
@RestController
@RequestMapping("examQuestion")
public class ExamQuestionController extends BaseController{

	@Resource
	private ExamQuestionService examQuestionService;

	/** 
	* 加载列表
	*/
	@RequestMapping("loadDataList")
	public ResponseVO loadDataList(ExamQuestionQuery query) {
		return getSuccessResponseVO(examQuestionService.findListByPage(query));
	}

	/** 
	* 新增
	*/
	@RequestMapping("add")
	public ResponseVO add(ExamQuestion bean) {
		this.examQuestionService.add(bean);
		return getSuccessResponseVO(null);
	}

	/** 
	* 批量新增
	*/
	@RequestMapping("addBatch")
	public ResponseVO addBatch(@RequestBody List<ExamQuestion> listBean) {
		this.examQuestionService.addBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/** 
	* 批量新增或修改
	*/
	@RequestMapping("addOrUpdateBatch")
	public ResponseVO addOrUpdateBatch(@RequestBody List<ExamQuestion> listBean) {
		this.examQuestionService.addOrUpdateBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/** 
	* 根据QuestionId查询
	*/
	@RequestMapping("getExamQuestionByQuestionId")
	public ResponseVO getExamQuestionByQuestionId(Integer questionId) {
		return getSuccessResponseVO(this.examQuestionService.getExamQuestionByQuestionId(questionId));
	}

	/** 
	* 根据QuestionId更新
	*/
	@RequestMapping("updateExamQuestionByQuestionId")
	public ResponseVO updateExamQuestionByQuestionId(ExamQuestion bean, Integer questionId) {
		this.examQuestionService.updateExamQuestionByQuestionId(bean,questionId);
		return getSuccessResponseVO(null);
	}

	/** 
	* 根据QuestionId删除
	*/
	@RequestMapping("deleteExamQuestionByQuestionId")
	public ResponseVO deleteExamQuestionByQuestionId(Integer questionId) {
		this.examQuestionService.deleteExamQuestionByQuestionId(questionId);
		return getSuccessResponseVO(null);
	}
}