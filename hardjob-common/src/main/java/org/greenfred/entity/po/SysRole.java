package org.greenfred.entity.po;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.greenfred.enums.DateTimePatternEnum;
import org.greenfred.utils.DateUtils;


 /**
 * @ Description: 
 * @ author: 郭丰锐
 * @ date: 2025/01/22
 */
public class SysRole implements Serializable {
	/** 
	* 角色ID
	*/

	private Integer roleId;

	/** 
	* 角色名称
	*/

	private String roleName;

	/** 
	* 角色描述
	*/

	private String roleDesc;

	/** 
	* 创建时间
	*/

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	/** 
	* 最后更新时间
	*/

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date lastUodateTime;

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public String getRoleDesc() {
		return this.roleDesc;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setLastUodateTime(Date lastUodateTime) {
		this.lastUodateTime = lastUodateTime;
	}

	public Date getLastUodateTime() {
		return this.lastUodateTime;
	}

	@Override
	public String toString() {
		return "角色ID:" + (roleId == null ? " 空 " : roleId) + ",角色名称:" + (roleName == null ? " 空 " : roleName) + ",角色描述:" + (roleDesc == null ? " 空 " : roleDesc) + ",创建时间:" + (createTime == null ? " 空 " : DateUtils.format(createTime, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern())) + ",最后更新时间:" + (lastUodateTime == null ? " 空 " : DateUtils.format(lastUodateTime, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern()));
	}
}