package org.greenfred.service.impl;

import java.util.List;
import org.greenfred.entity.po.ExamQuestionItem;
import org.greenfred.entity.query.ExamQuestionItemQuery;
import org.greenfred.entity.vo.PaginationResultVO;
import org.greenfred.service.ExamQuestionItemService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.greenfred.mappers.ExamQuestionItemMapper;
import org.greenfred.enums.PageSize;
import org.greenfred.entity.query.SimplePage;
 /**
 * @ Description: Service
 * @ author: 郭丰锐
 * @ date: 2025/02/18
 */
@Service("examQuestionItemService")
public class ExamQuestionItemServiceImpl implements ExamQuestionItemService {

	@Resource
	private ExamQuestionItemMapper<ExamQuestionItem,ExamQuestionItemQuery> examQuestionItemMapper;

	/** 
	* 根据条件查询列表
	*/
	public List<ExamQuestionItem> findListByParam(ExamQuestionItemQuery query) {
		return this.examQuestionItemMapper.selectList(query);
	}
	/** 
	* 根据条件查询数量
	*/
	public Integer findCountByParam(ExamQuestionItemQuery query) {
		return this.examQuestionItemMapper.selectCount(query);
	}
	/** 
	* 分页查询
	*/
	public PaginationResultVO<ExamQuestionItem> findListByPage(ExamQuestionItemQuery query) {
		Integer count = this.findCountByParam(query);
		Integer pageSize = query.getPageSize() == null ? PageSize.SIZE15.getSize() : query.getPageSize();
		SimplePage page = new SimplePage(query.getPageNo(), count, pageSize);
		query.setSimplePage(page);
		List<ExamQuestionItem> list = this.findListByParam(query);
		PaginationResultVO<ExamQuestionItem> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), list);
		return result;
	}
	/** 
	* 新增
	*/
	public Integer add(ExamQuestionItem bean) {
		return this.examQuestionItemMapper.insert(bean);
	}
	/** 
	* 批量新增
	*/
	public Integer addBatch(List<ExamQuestionItem> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.examQuestionItemMapper.insertBatch(listBean);
	}

	/** 
	* 批量新增或修改
	*/
	public Integer addOrUpdateBatch(List<ExamQuestionItem> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.examQuestionItemMapper.insertOrUpdateBatch(listBean);
	}
	/** 
	* 根据ItemId查询
	*/
	public ExamQuestionItem getExamQuestionItemByItemId(Integer itemId) {
		return this.examQuestionItemMapper.selectByItemId(itemId);
	}
	/** 
	* 根据ItemId更新
	*/
	public Integer updateExamQuestionItemByItemId(ExamQuestionItem bean, Integer itemId) {
		return this.examQuestionItemMapper.updateByItemId(bean,itemId);
	}
	/** 
	* 根据ItemId删除
	*/
	public Integer deleteExamQuestionItemByItemId(Integer itemId) {
		return this.examQuestionItemMapper.deleteByItemId(itemId);
	}
}