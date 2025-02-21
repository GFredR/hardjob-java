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
public class ShareInfoQuery extends BaseQuery {
	/** 
	* 分享ID
	*/
	private Integer shareId;

	/** 
	* 标题
	*/
	private String title;

	private String titleFuzzy;

	/** 
	* 封面类型：0：无封面 1：横幅 2：小图标
	*/
	private Integer coverType;

	/** 
	* 封面路径
	*/
	private String coverPath;

	private String coverPathFuzzy;

	/** 
	* 内容
	*/
	private String content;

	private String contentFuzzy;

	/** 
	* 创建日期
	*/
	private Date createTime;

	private String createTimeStart;

	private String createTimeEnd;

	/** 
	* 0：未发布 1：已发布
	*/
	private Integer status;

	/** 
	* 创建分享的用户ID
	*/
	private String createUserId;

	private String createUserIdFuzzy;

	/** 
	* 创建分享的用户名
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
	* 0：内部 1外部投稿
	*/
	private Integer postUserType;

	@Override
	public String toString() {
		return "分享ID:" + (shareId == null ? " 空 " : shareId) + ",标题:" + (title == null ? " 空 " : title) + ",封面类型：0：无封面 1：横幅 2：小图标:" + (coverType == null ? " 空 " : coverType) + ",封面路径:" + (coverPath == null ? " 空 " : coverPath) + ",内容:" + (content == null ? " 空 " : content) + ",创建日期:" + (createTime == null ? " 空 " : createTime) + ",0：未发布 1：已发布:" + (status == null ? " 空 " : status) + ",创建分享的用户ID:" + (createUserId == null ? " 空 " : createUserId) + ",创建分享的用户名:" + (createUserName == null ? " 空 " : createUserName) + ",阅读数:" + (readCount == null ? " 空 " : readCount) + ",收藏数:" + (collectCount == null ? " 空 " : collectCount) + ",0：内部 1外部投稿:" + (postUserType == null ? " 空 " : postUserType);
	}
	public void setShareId(Integer shareId) {
		this.shareId = shareId;
	}

	public Integer getShareId() {
		return this.shareId;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return this.title;
	}

	public void setCoverType(Integer coverType) {
		this.coverType = coverType;
	}

	public Integer getCoverType() {
		return this.coverType;
	}

	public void setCoverPath(String coverPath) {
		this.coverPath = coverPath;
	}

	public String getCoverPath() {
		return this.coverPath;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContent() {
		return this.content;
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

	public void setCoverPathFuzzy(String coverPathFuzzy) {
		this.coverPathFuzzy = coverPathFuzzy;
	}

	public String getCoverPathFuzzy() {
		return this.coverPathFuzzy;
	}

	public void setContentFuzzy(String contentFuzzy) {
		this.contentFuzzy = contentFuzzy;
	}

	public String getContentFuzzy() {
		return this.contentFuzzy;
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