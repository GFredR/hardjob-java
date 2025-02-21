package org.greenfred.controller;

import java.util.List;
import org.greenfred.entity.po.ExamQuestionItem;
import org.greenfred.entity.query.ExamQuestionItemQuery;
import org.greenfred.entity.vo.PaginationResultVO;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import javax.annotation.Resource;
import org.greenfred.service.ExamQuestionItemService;
import org.greenfred.controller.BaseController;
import org.greenfred.entity.vo.ResponseVO;
 /**
 * @ Description: Controller
 * @ author: 郭丰锐
 * @ date: 2025/02/18
 */
@RestController
@RequestMapping("examQuestionItem")
public class ExamQuestionItemController extends BaseController{

	@Resource
	private ExamQuestionItemService examQuestionItemService;

	/** 
	* 加载列表
	*/
	@RequestMapping("loadDataList")
	public ResponseVO loadDataList(ExamQuestionItemQuery query) {
		return getSuccessResponseVO(examQuestionItemService.findListByPage(query));
	}

	/** 
	* 新增
	*/
	@RequestMapping("add")
	public ResponseVO add(ExamQuestionItem bean) {
		this.examQuestionItemService.add(bean);
		return getSuccessResponseVO(null);
	}

	/** 
	* 批量新增
	*/
	@RequestMapping("addBatch")
	public ResponseVO addBatch(@RequestBody List<ExamQuestionItem> listBean) {
		this.examQuestionItemService.addBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/** 
	* 批量新增或修改
	*/
	@RequestMapping("addOrUpdateBatch")
	public ResponseVO addOrUpdateBatch(@RequestBody List<ExamQuestionItem> listBean) {
		this.examQuestionItemService.addOrUpdateBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/** 
	* 根据ItemId查询
	*/
	@RequestMapping("getExamQuestionItemByItemId")
	public ResponseVO getExamQuestionItemByItemId(Integer itemId) {
		return getSuccessResponseVO(this.examQuestionItemService.getExamQuestionItemByItemId(itemId));
	}

	/** 
	* 根据ItemId更新
	*/
	@RequestMapping("updateExamQuestionItemByItemId")
	public ResponseVO updateExamQuestionItemByItemId(ExamQuestionItem bean, Integer itemId) {
		this.examQuestionItemService.updateExamQuestionItemByItemId(bean,itemId);
		return getSuccessResponseVO(null);
	}

	/** 
	* 根据ItemId删除
	*/
	@RequestMapping("deleteExamQuestionItemByItemId")
	public ResponseVO deleteExamQuestionItemByItemId(Integer itemId) {
		this.examQuestionItemService.deleteExamQuestionItemByItemId(itemId);
		return getSuccessResponseVO(null);
	}
}