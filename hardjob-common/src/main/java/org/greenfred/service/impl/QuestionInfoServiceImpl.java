package org.greenfred.service.impl;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.greenfred.entity.po.Category;
import org.greenfred.enums.ResponseCodeEnum;
import org.greenfred.exception.BusinessException;
import org.greenfred.service.CategoryService;
import org.springframework.format.annotation.DateTimeFormat;
import org.greenfred.enums.DateTimePatternEnum;
import org.greenfred.utils.DateUtils;

import java.util.List;

import org.greenfred.entity.po.QuestionInfo;
import org.greenfred.entity.query.QuestionInfoQuery;
import org.greenfred.entity.vo.PaginationResultVO;
import org.greenfred.service.QuestionInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import org.greenfred.mappers.QuestionInfoMapper;
import org.greenfred.enums.PageSize;
import org.greenfred.entity.query.SimplePage;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ Description: Service
 * @ author: 郭丰锐
 * @ date: 2025/02/18
 */
@Service("questionInfoService")
public class QuestionInfoServiceImpl implements QuestionInfoService {

    @Resource
    private QuestionInfoMapper<QuestionInfo, QuestionInfoQuery> questionInfoMapper;

    @Resource
    private CategoryService categoryService;

    /**
     * 根据条件查询列表
     */
    public List<QuestionInfo> findListByParam(QuestionInfoQuery query) {
        return this.questionInfoMapper.selectList(query);
    }

    /**
     * 根据条件查询数量
     */
    public Integer findCountByParam(QuestionInfoQuery query) {
        return this.questionInfoMapper.selectCount(query);
    }

    /**
     * 分页查询
     */
    public PaginationResultVO<QuestionInfo> findListByPage(QuestionInfoQuery query) {
        Integer count = this.findCountByParam(query);
        Integer pageSize = query.getPageSize() == null ? PageSize.SIZE15.getSize() : query.getPageSize();
        SimplePage page = new SimplePage(query.getPageNo(), count, pageSize);
        query.setSimplePage(page);
        List<QuestionInfo> list = this.findListByParam(query);
        PaginationResultVO<QuestionInfo> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), list);
        return result;
    }

    /**
     * 新增
     */
    public Integer add(QuestionInfo bean) {
        return this.questionInfoMapper.insert(bean);
    }

    /**
     * 批量新增
     */
    public Integer addBatch(List<QuestionInfo> listBean) {
        if (listBean == null || listBean.isEmpty()) {
            return 0;
        }
        return this.questionInfoMapper.insertBatch(listBean);
    }

    /**
     * 批量新增或修改
     */
    public Integer addOrUpdateBatch(List<QuestionInfo> listBean) {
        if (listBean == null || listBean.isEmpty()) {
            return 0;
        }
        return this.questionInfoMapper.insertOrUpdateBatch(listBean);
    }

    /**
     * 根据QuestionId查询
     */
    public QuestionInfo getQuestionInfoByQuestionId(Integer questionId) {
        return this.questionInfoMapper.selectByQuestionId(questionId);
    }

    /**
     * 根据QuestionId更新
     */
    public Integer updateQuestionInfoByQuestionId(QuestionInfo bean, Integer questionId) {
        return this.questionInfoMapper.updateByQuestionId(bean, questionId);
    }

    /**
     * 根据QuestionId删除
     */
    public Integer deleteQuestionInfoByQuestionId(Integer questionId) {
        return this.questionInfoMapper.deleteByQuestionId(questionId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveQuestion(QuestionInfo questionInfo, Boolean isAdmin) throws BusinessException {
        Category category = categoryService.getCategoryByCategoryId(questionInfo.getCategoryId());
        questionInfo.setCategoryName(category.getCategoryName());
        if (null == questionInfo.getQuestionId()) {
            questionInfo.setCreateTime(new Date());
            this.questionInfoMapper.insert(questionInfo);
        } else {
            QuestionInfo dbInfo = this.questionInfoMapper.selectByQuestionId(questionInfo.getQuestionId());
            if (!dbInfo.getCreateUserId().equals(questionInfo.getCreateUserId()) && !isAdmin) {
                throw new BusinessException(ResponseCodeEnum.CODE_600);
            }
            questionInfo.setCreateUserId(null);
            questionInfo.setCreateUserName(null);
            questionInfo.setCreateTime(null);
            this.questionInfoMapper.updateByQuestionId(questionInfo, questionInfo.getQuestionId());
        }
    }
}