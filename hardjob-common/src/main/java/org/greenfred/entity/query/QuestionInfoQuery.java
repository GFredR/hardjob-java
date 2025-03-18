package org.greenfred.entity.query;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.greenfred.enums.DateTimePatternEnum;
import org.greenfred.utils.DateUtils;


/**
 * @ Description: 查询对象
 * @ author: 郭丰锐
 * @ date: 2025/02/19
 */
public class QuestionInfoQuery extends BaseQuery {
    /**
     * 问题ID
     */
    private Integer questionId;

    /**
     * 标题
     */
    private String title;

    private String titleFuzzy;

    /**
     * 分类ID
     */
    private Integer categoryId;

    /**
     * 分类名称
     */
    private String categoryName;

    private String categoryNameFuzzy;

    /**
     * 难度
     */
    private Integer difficultyLevel;

    /**
     * 问题描述
     */
    private String question;

    private String questionFuzzy;

    /**
     * 回答解释
     */
    private String answerAnalysis;

    private String answerAnalysisFuzzy;

    /**
     * 创建时间
     */
    private Date createTime;

    private String createTimeStart;

    private String createTimeEnd;

    /**
     * 0：未发布 1：已发布
     */
    private Integer status;

    /**
     * 创建问题的用户ID
     */
    private String createUserId;

    private String createUserIdFuzzy;

    /**
     * 创建问题的用户名
     */
    private String createUserName;

    private String createUserNameFuzzy;

    /**
     * 阅读数
     */
    private Integer readCount;

    /**
     * 收藏数
     */
    private Integer collectCount;

    /**
     * 0：内部 1：外部投稿
     */
    private String postUserType;

    private String postUserTypeFuzzy;

    /**
     *
     */
    private String questionAnswer;

    private String questionAnswerFuzzy;

    private Boolean queryTextContent;

    private String[] questionIds;

    private Integer currentId;

    public Integer getCurrentId() {
        return currentId;
    }

    public void setCurrentId(Integer currentId) {
        this.currentId = currentId;
    }

    public String[] getQuestionIds() {
        return questionIds;
    }

    public void setQuestionIds(String[] questionIds) {
        this.questionIds = questionIds;
    }

    public Boolean getQueryTextContent() {
        return queryTextContent;
    }

    public void setQueryTextContent(Boolean queryTextContent) {
        this.queryTextContent = queryTextContent;
    }

    @Override
    public String toString() {
        return "问题ID:" + (questionId == null ? " 空 " : questionId) + ",标题:" + (title == null ? " 空 " : title) + ",分类ID:" + (categoryId == null ? " 空 " : categoryId) + ",分类名称:" + (categoryName == null ? " 空 " : categoryName) + ",难度:" + (difficultyLevel == null ? " 空 " : difficultyLevel) + ",问题描述:" + (question == null ? " 空 " : question) + ",回答解释:" + (answerAnalysis == null ? " 空 " : answerAnalysis) + ",创建时间:" + (createTime == null ? " 空 " : createTime) + ",0：未发布 1：已发布:" + (status == null ? " 空 " : status) + ",创建问题的用户ID:" + (createUserId == null ? " 空 " : createUserId) + ",创建问题的用户名:" + (createUserName == null ? " 空 " : createUserName) + ",阅读数:" + (readCount == null ? " 空 " : readCount) + ",收藏数:" + (collectCount == null ? " 空 " : collectCount) + ",0：内部 1：外部投稿:" + (postUserType == null ? " 空 " : postUserType) + ",:" + (questionAnswer == null ? " 空 " : questionAnswer);
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getQuestionId() {
        return this.questionId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public void setDifficultyLevel(Integer difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public Integer getDifficultyLevel() {
        return this.difficultyLevel;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return this.question;
    }

    public void setAnswerAnalysis(String answerAnalysis) {
        this.answerAnalysis = answerAnalysis;
    }

    public String getAnswerAnalysis() {
        return this.answerAnalysis;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUserId() {
        return this.createUserId;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getCreateUserName() {
        return this.createUserName;
    }

    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }

    public Integer getReadCount() {
        return this.readCount;
    }

    public void setCollectCount(Integer collectCount) {
        this.collectCount = collectCount;
    }

    public Integer getCollectCount() {
        return this.collectCount;
    }

    public void setPostUserType(String postUserType) {
        this.postUserType = postUserType;
    }

    public String getPostUserType() {
        return this.postUserType;
    }

    public void setQuestionAnswer(String questionAnswer) {
        this.questionAnswer = questionAnswer;
    }

    public String getQuestionAnswer() {
        return this.questionAnswer;
    }

    public void setTitleFuzzy(String titleFuzzy) {
        this.titleFuzzy = titleFuzzy;
    }

    public String getTitleFuzzy() {
        return this.titleFuzzy;
    }

    public void setCategoryNameFuzzy(String categoryNameFuzzy) {
        this.categoryNameFuzzy = categoryNameFuzzy;
    }

    public String getCategoryNameFuzzy() {
        return this.categoryNameFuzzy;
    }

    public void setQuestionFuzzy(String questionFuzzy) {
        this.questionFuzzy = questionFuzzy;
    }

    public String getQuestionFuzzy() {
        return this.questionFuzzy;
    }

    public void setAnswerAnalysisFuzzy(String answerAnalysisFuzzy) {
        this.answerAnalysisFuzzy = answerAnalysisFuzzy;
    }

    public String getAnswerAnalysisFuzzy() {
        return this.answerAnalysisFuzzy;
    }

    public void setCreateTimeStart(String createTimeStart) {
        this.createTimeStart = createTimeStart;
    }

    public String getCreateTimeStart() {
        return this.createTimeStart;
    }

    public void setCreateTimeEnd(String createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }

    public String getCreateTimeEnd() {
        return this.createTimeEnd;
    }

    public void setCreateUserIdFuzzy(String createUserIdFuzzy) {
        this.createUserIdFuzzy = createUserIdFuzzy;
    }

    public String getCreateUserIdFuzzy() {
        return this.createUserIdFuzzy;
    }

    public void setCreateUserNameFuzzy(String createUserNameFuzzy) {
        this.createUserNameFuzzy = createUserNameFuzzy;
    }

    public String getCreateUserNameFuzzy() {
        return this.createUserNameFuzzy;
    }

    public void setPostUserTypeFuzzy(String postUserTypeFuzzy) {
        this.postUserTypeFuzzy = postUserTypeFuzzy;
    }

    public String getPostUserTypeFuzzy() {
        return this.postUserTypeFuzzy;
    }

    public void setQuestionAnswerFuzzy(String questionAnswerFuzzy) {
        this.questionAnswerFuzzy = questionAnswerFuzzy;
    }

    public String getQuestionAnswerFuzzy() {
        return this.questionAnswerFuzzy;
    }


}