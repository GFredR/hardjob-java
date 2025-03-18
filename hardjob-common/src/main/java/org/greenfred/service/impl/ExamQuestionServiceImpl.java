package org.greenfred.service.impl;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.greenfred.entity.po.Category;
import org.greenfred.entity.po.ExamQuestionItem;
import org.greenfred.entity.query.ExamQuestionItemQuery;
import org.greenfred.enums.ResponseCodeEnum;
import org.greenfred.exception.BusinessException;
import org.greenfred.mappers.ExamQuestionItemMapper;
import org.greenfred.service.CategoryService;
import org.greenfred.utils.StringTools;
import org.springframework.format.annotation.DateTimeFormat;
import org.greenfred.enums.DateTimePatternEnum;
import org.greenfred.utils.DateUtils;

import java.util.function.Function;
import java.util.stream.Collectors;

import org.greenfred.entity.po.ExamQuestion;
import org.greenfred.entity.query.ExamQuestionQuery;
import org.greenfred.entity.vo.PaginationResultVO;
import org.greenfred.service.ExamQuestionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import org.greenfred.mappers.ExamQuestionMapper;
import org.greenfred.enums.PageSize;
import org.greenfred.entity.query.SimplePage;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ Description: Service
 * @ author: 郭丰锐
 * @ date: 2025/02/18
 */
@Service("examQuestionService")
public class ExamQuestionServiceImpl implements ExamQuestionService {

    @Resource
    private ExamQuestionMapper<ExamQuestion, ExamQuestionQuery> examQuestionMapper;

    @Resource
    private ExamQuestionItemMapper<ExamQuestionItem, ExamQuestionItemQuery> examQuestionItemMapper;

    @Resource
    private CategoryService categoryService;

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
        return this.examQuestionMapper.updateByQuestionId(bean, questionId);
    }

    /**
     * 根据QuestionId删除
     */
    public Integer deleteExamQuestionByQuestionId(Integer questionId) {
        return this.examQuestionMapper.deleteByQuestionId(questionId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveExamQuestion(ExamQuestion examQuestion, List<ExamQuestionItem> examQuestionItemList, Boolean superAdmin) throws BusinessException {
        Category category = categoryService.getCategoryByCategoryId(examQuestion.getCategoryId());
        if (category == null) {
            throw new BusinessException(ResponseCodeEnum.CODE_600);
        }

        examQuestion.setCategoryName(category.getCategoryName());

        Integer questionId = examQuestion.getQuestionId();
        if (null == questionId) {
//            Integer randomQuestionId = Integer.parseInt(StringTools.getRandomNumber(5));
            examQuestion.setCreateTime(new Date());
//            examQuestion.setQuestionId(randomQuestionId);
            this.examQuestionMapper.insert(examQuestion);
            questionId = examQuestion.getQuestionId();
        } else {
            examQuestion.setQuestionType(null);
            ExamQuestion dbInfo = this.examQuestionMapper.selectByQuestionId(questionId);
            if (!dbInfo.getCreateUserId().equals(examQuestion.getCreateUserId()) && !superAdmin) {
                throw new BusinessException(ResponseCodeEnum.CODE_600);
            }
            examQuestion.setCreateUserId(null);
            examQuestion.setCreateTime(null);
            examQuestion.setCreateUserName(null);
            this.examQuestionMapper.updateByQuestionId(examQuestion, questionId);
        }
        examQuestionItemList.forEach(item -> {
            item.setQuestionId(examQuestion.getQuestionId());
        });

        /**
         * 更新的选项
         */
        List<ExamQuestionItem> updateItemList =
                examQuestionItemList.stream().filter(item -> item.getItemId() != null).collect(Collectors.toList());

        /**
         * 新增的选项
         */
        List<ExamQuestionItem> addItemList =
                examQuestionItemList.stream().filter(item -> item.getItemId() == null).collect(Collectors.toList());

        Map<Integer, ExamQuestionItem> paramItemMap =
                updateItemList.stream().collect(Collectors.toMap(ExamQuestionItem::getItemId, Function.identity(),
                        (data1, data2) -> data2));

        ExamQuestionItemQuery itemQuery = new ExamQuestionItemQuery();
        itemQuery.setQuestionId(questionId);
        List<ExamQuestionItem> dbItemList = examQuestionItemMapper.selectList(itemQuery);

        List<Integer> delList = new ArrayList<>();

        if (!paramItemMap.isEmpty()) {
            for (ExamQuestionItem db : dbItemList) {
                if (paramItemMap.get(db.getItemId()) == null) {
                    delList.add(db.getItemId());
                }
            }
        }
        /**
         * 新增
         */
        if (!addItemList.isEmpty()) {
            examQuestionItemMapper.insertBatch(addItemList);
        }
        /**
         * 修改
         */
        if (!updateItemList.isEmpty()) {
            examQuestionItemMapper.insertOrUpdateBatch(updateItemList);
        }

        /**
         * 删除
         */
        if (!delList.isEmpty()) {
            examQuestionItemMapper.deleteBatch(delList);
        }
    }


}