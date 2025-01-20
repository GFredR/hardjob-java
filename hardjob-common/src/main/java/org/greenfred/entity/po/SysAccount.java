package org.greenfred.entity.po;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;
import org.greenfred.enums.DateTimePatternEnum;
import org.greenfred.utils.DateUtils;


 /**
 * @ Description: 
 * @ author: 郭丰锐
 * @ date: 2025/01/13
 */
public class SysAccount implements Serializable {
	/** 
	* 用户ID
	*/

	private Integer userId;

	/** 
	* 手机号
	*/

	private String phone;

	/** 
	* 用户名
	*/

	private String userName;

	/** 
	* 密码
	*/
	@JsonIgnore
	private String password;

	/** 
	* 位置
	*/

	private String position;

	/** 
	* 状态
	*/

	private Integer status;

	/** 
	* 规则
	*/

	private String roles;

	/** 
	* 创建日期
	*/

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPosition() {
		return this.position;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getRoles() {
		return this.roles;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	@Override
	public String toString() {
		return "用户ID:" + (userId == null ? " 空 " : userId) + ",手机号:" + (phone == null ? " 空 " : phone) + ",用户名:" + (userName == null ? " 空 " : userName) + ",密码:" + (password == null ? " 空 " : password) + ",位置:" + (position == null ? " 空 " : position) + ",状态:" + (status == null ? " 空 " : status) + ",规则:" + (roles == null ? " 空 " : roles) + ",创建日期:" + (createTime == null ? " 空 " : DateUtils.format(createTime, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern()));
	}
}