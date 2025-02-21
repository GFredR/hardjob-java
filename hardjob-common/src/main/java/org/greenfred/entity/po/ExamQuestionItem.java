package org.greenfred.entity.po;

import java.io.Serializable;


 /**
 * @ Description: 
 * @ author: 郭丰锐
 * @ date: 2025/02/18
 */
public class ExamQuestionItem implements Serializable {
	/** 
	* 选项ID
	*/

	private Integer itemId;

	/** 
	* 问题ID
	*/

	private Integer questionId;

	/** 
	* 标题
	*/

	private String title;

	/** 
	* 排序
	*/

	private String sort;

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getItemId() {
		return this.itemId;
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

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getSort() {
		return this.sort;
	}

	@Override
	public String toString() {
		return "选项ID:" + (itemId == null ? " 空 " : itemId) + ",问题ID:" + (questionId == null ? " 空 " : questionId) + ",标题:" + (title == null ? " 空 " : title) + ",排序:" + (sort == null ? " 空 " : sort);
	}
}