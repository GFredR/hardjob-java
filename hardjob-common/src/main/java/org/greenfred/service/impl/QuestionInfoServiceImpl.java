package org.greenfred.service.impl;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.greenfred.entity.constants.Constants;
import org.greenfred.entity.dto.ImportErrorItem;
import org.greenfred.entity.dto.SessionUserAdminDto;
import org.greenfred.entity.po.Category;
import org.greenfred.entity.po.ExamQuestion;
import org.greenfred.enums.*;
import org.greenfred.exception.BusinessException;
import org.greenfred.mappers.ACommonMapper;
import org.greenfred.service.CategoryService;
import org.greenfred.utils.ExcelUtils;
import org.greenfred.utils.StringTools;
import org.greenfred.utils.VerifyUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.greenfred.utils.DateUtils;

import java.util.function.Function;
import java.util.stream.Collectors;

import org.greenfred.entity.po.QuestionInfo;
import org.greenfred.entity.query.QuestionInfoQuery;
import org.greenfred.entity.vo.PaginationResultVO;
import org.greenfred.service.QuestionInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import org.greenfred.mappers.QuestionInfoMapper;
import org.greenfred.entity.query.SimplePage;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
    private ACommonMapper commonMapper;

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
            Integer questionId = Integer.parseInt(StringTools.getRandomNumber(5));
            questionInfo.setQuestionId(questionId);
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

    @Override
    public void delQuestionBatch(String questionIds, Integer userId) throws BusinessException {
        String[] questionIdArray = questionIds.split(",");
        if (userId != null) {
            QuestionInfoQuery infoQuery = new QuestionInfoQuery();
            infoQuery.setQuestionIds(questionIdArray);
            List<QuestionInfo> questionInfoList = this.questionInfoMapper.selectList(infoQuery);
            List<QuestionInfo> currentUserDataList = questionInfoList.stream().filter(questionInfo -> !questionInfo.getCreateUserId().equals(String.valueOf(userId)))
                                                        .collect(Collectors.toList());
            if (!currentUserDataList.isEmpty()) {
                throw new BusinessException(ResponseCodeEnum.CODE_600);
            }
        }
        this.questionInfoMapper.deleteBatchByQuestionId(questionIdArray, PostStatusEnum.NO_POST.getStatus(), userId);
    }

    public void updateStatus(String questionIds, Integer status) {
        QuestionInfoQuery params = new QuestionInfoQuery();
        params.setQuestionIds(questionIds.split(","));
        QuestionInfo questionInfo = new QuestionInfo();
        questionInfo.setStatus(status);
        updateByParam(questionInfo, params);
    }

    @Override
    public void updateByParam(QuestionInfo questionInfo, QuestionInfoQuery params) {
        questionInfoMapper.updateQuestionByParam(questionInfo.getStatus(), params.getQuestionIds());
    }

    @Override
    public List<ImportErrorItem> importQuestion(MultipartFile file, SessionUserAdminDto userAdminDto) throws BusinessException {
        List<Category> categoryList = categoryService.loadAllCategoryByType(CategoryTypeEnum.QUESTION.getType());

        Map<String, Category> categoryMap = categoryList.stream().collect(Collectors.toMap(Category::getCategoryName,
                Function.identity(), (data1, data2) -> data2));

        List<List<String>> dataList = ExcelUtils.readExcel(file, Constants.EXCEL_TITLE_QUESTION, 1);
        /**
         * 错误列
         */
        List<ImportErrorItem> errorList = new ArrayList<>();
        /**
         * 数据列
         */
        List<QuestionInfo> questionInfoList = new ArrayList<>();

        Integer dataRowNum = 2;
        for (List<String> row : dataList) {
            if (errorList.size() > Constants.LENGTH_50) {
                throw new BusinessException("错误数据超过"+Constants.LENGTH_50+"条，请认真检查数据后重新上传");
            }
            dataRowNum++;
            List<String> errorItemList = new ArrayList<>();
            Integer index = 0;

            String title = row.get(index++);
            if (StringTools.isEmpty(title) || title.length() > Constants.LENGTH_150) {
                errorItemList.add("标题不能为空，且不能超过"+Constants.LENGTH_150+"个字符");
            }
            String categoryName = row.get(index++);
            Category category = categoryMap.get(categoryName);
            if (null == category) {
                errorItemList.add("分类名称不存在");
            }

            String difficultyLevel = row.get(index++);
            Integer difficultyLevelInt = null;
            if (VerifyUtils.verify(VerifyRegexEnum.POSITIVE_INTEGER, difficultyLevel)) {
                difficultyLevelInt = Integer.parseInt(difficultyLevel);
                if (difficultyLevelInt > 5) {
                    errorItemList.add("难度等级不能大于5");
                }
            } else {
                errorItemList.add("难度等级只能为正整数");
            }

            String question = row.get(index++);
            String answerAnalysis = row.get(index++);
            if (StringTools.isEmpty(answerAnalysis)) {
                errorItemList.add("答案解析不能为空");
            }
            if (!errorItemList.isEmpty() || errorList.isEmpty()) {
                ImportErrorItem errorItem = new ImportErrorItem();
                errorItem.setRowNum(dataRowNum);
                errorItem.setErrorItemList(errorItemList);
                errorList.add(errorItem);
                continue;
            }
            QuestionInfo questionInfo = new QuestionInfo();
            questionInfo.setTitle(title);
            questionInfo.setCategoryId(category.getCategoryId());
            questionInfo.setCategoryName(categoryName);
            questionInfo.setDifficultyLevel(difficultyLevelInt);
            questionInfo.setQuestion(question);
            questionInfo.setAnswerAnalysis(answerAnalysis);
            questionInfo.setCreateTime(new Date());
            questionInfo.setStatus(PostStatusEnum.NO_POST.getStatus());
            questionInfo.setCreateUserId(String.valueOf(userAdminDto.getUserId()));
            questionInfo.setCreateUserName(userAdminDto.getUsername());
            questionInfoList.add(questionInfo);
        }

        if (questionInfoList.isEmpty()) {
            return errorList;
        }

        this.questionInfoMapper.insertBatch(questionInfoList);

        return errorList;
    }

    @Override
    public QuestionInfo showDetail(QuestionInfoQuery query, Integer nextType, Integer currentId, Boolean updateReadCount) throws BusinessException {
        if (nextType == null) {
            query.setQuestionId(currentId);
        } else {
            query.setQuestionId(nextType);
            query.setCurrentId(currentId);
        }
        QuestionInfo questionInfo = questionInfoMapper.showDetailNext(query);
        if (questionInfo == null && nextType == null) {
            throw new BusinessException("内容不存在");
        } else if (questionInfo != null && nextType == -1) {
            throw new BusinessException("已经是第一条了");
        } else if (questionInfo != null && nextType == 1) {
            throw new BusinessException("已经是最后一条了");
        }
        if (updateReadCount && questionInfo != null) {
        commonMapper.updateCount(Constants.TABLE_NAME_QUESTION_INFO, 1, null, currentId);
        questionInfo.setReadCount(questionInfo.getReadCount() + 1);
        }
        return questionInfo;
    }


}