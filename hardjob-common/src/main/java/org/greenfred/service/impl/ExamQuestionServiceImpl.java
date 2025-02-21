package org.greenfred.service.impl;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.greenfred.enums.DateTimePatternEnum;
import org.greenfred.utils.DateUtils;
import java.util.List;
import org.greenfred.entity.po.ExamQuestion;
import org.greenfred.entity.query.ExamQuestionQuery;
import org.greenfred.entity.vo.PaginationResultVO;
import org.greenfred.service.ExamQuestionService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.greenfred.mappers.ExamQuestionMapper;
import org.greenfred.enums.PageSize;
import org.greenfred.entity.query.SimplePage;
 /**
 * @ Description: Service
 * @ author: 郭丰锐
 * @ date: 2025/02/18
 */
@Service("examQuestionService")
public class ExamQuestionServiceImpl implements ExamQuestionService {

	@Resource
	private ExamQuestionMapper<ExamQuestion,ExamQuestionQuery> examQuestionMapper;

	/** 
	* 根据条件查询列表
	*/
	public List<ExamQuestion> findListByParam(ExamQuestionQuery query) {
		return this.examQuestionMapper.selectList(query);
	}
	/** 
	* 根据条件查询数量
	*/
	public Integer findCountByParam(ExamQuestionQuery query) {
		return this.examQuestionMapper.selectCount(query);
	}
	/** 
	* 分页查询
	*/
	public PaginationResultVO<ExamQuestion> findListByPage(ExamQuestionQuery query) {
		Integer count = this.findCountByParam(query);
		Integer pageSize = query.getPageSize() == null ? PageSize.SIZE15.getSize() : query.getPageSize();
		SimplePage page = new SimplePage(query.getPageNo(), count, pageSize);
		query.setSimplePage(page);
		List<ExamQuestion> list = this.findListByParam(query);
		PaginationResultVO<ExamQuestion> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), list);
		return result;
	}
	/** 
	* 新增
	*/
	public Integer add(ExamQuestion bean) {
		return this.examQuestionMapper.insert(bean);
	}
	/** 
	* 批量新增
	*/
	public Integer addBatch(List<ExamQuestion> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.examQuestionMapper.insertBatch(listBean);
	}

	/** 
	* 批量新增或修改
	*/
	public Integer addOrUpdateBatch(List<ExamQuestion> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.examQuestionMapper.insertOrUpdateBatch(listBean);
	}
	/** 
	* 根据QuestionId查询
	*/
	public ExamQuestion getExamQuestionByQuestionId(Integer questionId) {
		return this.examQuestionMapper.selectByQuestionId(questionId);
	}
	/** 
	* 根据QuestionId更新
	*/
	public Integer updateExamQuestionByQuestionId(ExamQuestion bean, Integer questionId) {
		return this.examQuestionMapper.updateByQuestionId(bean,questionId);
	}
	/** 
	* 根据QuestionId删除
	*/
	public Integer deleteExamQuestionByQuestionId(Integer questionId) {
		return this.examQuestionMapper.deleteByQuestionId(questionId);
	}
}