package org.greenfred.entity.query;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.greenfred.enums.DateTimePatternEnum;
import org.greenfred.utils.DateUtils;


 /**
 * @ Description: 查询对象
 * @ author: 郭丰锐
 * @ date: 2025/01/13
 */
public class SysAccountQuery extends BaseQuery {
	/** 
	* 用户ID
	*/
	private Integer userId;

	/** 
	* 手机号
	*/
	private String phone;

	private String phoneFuzzy;

	/** 
	* 用户名
	*/
	private String userName;

	private String userNameFuzzy;

	/** 
	* 密码
	*/
	private String password;

	private String passwordFuzzy;

	/** 
	* 位置
	*/
	private String position;

	private String positionFuzzy;

	/** 
	* 状态
	*/
	private Integer status;

	/** 
	* 规则
	*/
	private String roles;

	private String rolesFuzzy;

	/** 
	* 创建日期
	*/
	private Date createTime;

	private String createTimeStart;

	private String createTimeEnd;

	private Boolean queryRoles;

	 public Boolean getQueryRoles() {
		 return queryRoles;
	 }

	 public void setQueryRoles(Boolean queryRoles) {
		 this.queryRoles = queryRoles;
	 }

	 @Override
	public String toString() {
		return "用户ID:" + (userId == null ? " 空 " : userId) + ",手机号:" + (phone == null ? " 空 " : phone) + ",用户名:" + (userName == null ? " 空 " : userName) + ",密码:" + (password == null ? " 空 " : password) + ",位置:" + (position == null ? " 空 " : position) + ",状态:" + (status == null ? " 空 " : status) + ",规则:" + (roles == null ? " 空 " : roles) + ",创建日期:" + (createTime == null ? " 空 " : createTime);
	}
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

	public void setPhoneFuzzy(String phoneFuzzy) {
		this.phoneFuzzy = phoneFuzzy;
	}

	public String getPhoneFuzzy() {
		return this.phoneFuzzy;
	}

	public void setUserNameFuzzy(String userNameFuzzy) {
		this.userNameFuzzy = userNameFuzzy;
	}

	public String getUserNameFuzzy() {
		return this.userNameFuzzy;
	}

	public void setPasswordFuzzy(String passwordFuzzy) {
		this.passwordFuzzy = passwordFuzzy;
	}

	public String getPasswordFuzzy() {
		return this.passwordFuzzy;
	}

	public void setPositionFuzzy(String positionFuzzy) {
		this.positionFuzzy = positionFuzzy;
	}

	public String getPositionFuzzy() {
		return this.positionFuzzy;
	}

	public void setRolesFuzzy(String rolesFuzzy) {
		this.rolesFuzzy = rolesFuzzy;
	}

	public String getRolesFuzzy() {
		return this.rolesFuzzy;
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


}