package org.greenfred.entity.query;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.greenfred.enums.DateTimePatternEnum;
import org.greenfred.utils.DateUtils;


 /**
 * @ Description: 查询对象
 * @ author: 郭丰锐
 * @ date: 2025/02/18
 */
public class ExamQuestionQuery extends BaseQuery {
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
	* 问题类型 0：判断 1：单选题 2：多选
	*/
	private Integer questionType;

	/** 
	* 问题描述
	*/
	private String question;

	private String questionFuzzy;

	/** 
	* 答案
	*/
	private String questionAnswer;

	private String questionAnswerFuzzy;

	/** 
	* 答案解析
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
	* 0：内部 1：外部投稿
	*/
	private Integer postUserType;

	private Boolean queryAnswer;

	 public Boolean getQueryAnswer() {
		 return queryAnswer;
	 }

	 public void setQueryAnswer(Boolean queryAnswer) {
		 this.queryAnswer = queryAnswer;
	 }

	 @Override
	public String toString() {
		return "问题ID:" + (questionId == null ? " 空 " : questionId) + ",标题:" + (title == null ? " 空 " : title) + ",分类ID:" + (categoryId == null ? " 空 " : categoryId) + ",分类名称:" + (categoryName == null ? " 空 " : categoryName) + ",难度:" + (difficultyLevel == null ? " 空 " : difficultyLevel) + ",问题类型 0：判断 1：单选题 2：多选:" + (questionType == null ? " 空 " : questionType) + ",问题描述:" + (question == null ? " 空 " : question) + ",答案:" + (questionAnswer == null ? " 空 " : questionAnswer) + ",答案解析:" + (answerAnalysis == null ? " 空 " : answerAnalysis) + ",创建时间:" + (createTime == null ? " 空 " : createTime) + ",0：未发布 1：已发布:" + (status == null ? " 空 " : status) + ",创建问题的用户ID:" + (createUserId == null ? " 空 " : createUserId) + ",创建问题的用户名:" + (createUserName == null ? " 空 " : createUserName) + ",0：内部 1：外部投稿:" + (postUserType == null ? " 空 " : postUserType);
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

	public void setQuestionType(Integer questionType) {
		this.questionType = questionType;
	}

	public Integer getQuestionType() {
		return this.questionType;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getQuestion() {
		return this.question;
	}

	public void setQuestionAnswer(String questionAnswer) {
		this.questionAnswer = questionAnswer;
	}

	public String getQuestionAnswer() {
		return this.questionAnswer;
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

	public void setPostUserType(Integer postUserType) {
		this.postUserType = postUserType;
	}

	public Integer getPostUserType() {
		return this.postUserType;
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

	public void setQuestionAnswerFuzzy(String questionAnswerFuzzy) {
		this.questionAnswerFuzzy = questionAnswerFuzzy;
	}

	public String getQuestionAnswerFuzzy() {
		return this.questionAnswerFuzzy;
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


}