package org.greenfred.entity.query;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.greenfred.enums.DateTimePatternEnum;
import org.greenfred.utils.DateUtils;


 /**
 * @ Description: 查询对象
 * @ author: 郭丰锐
 * @ date: 2025/01/22
 */
public class SysRoleQuery extends BaseQuery {
	/** 
	* 角色ID
	*/
	private Integer roleId;

	/** 
	* 角色名称
	*/
	private String roleName;

	private String roleNameFuzzy;

	/** 
	* 角色描述
	*/
	private String roleDesc;

	private String roleDescFuzzy;

	/** 
	* 创建时间
	*/
	private Date createTime;

	private String createTimeStart;

	private String createTimeEnd;

	/** 
	* 最后更新时间
	*/
	private Date lastUodateTime;

	private String lastUodateTimeStart;

	private String lastUodateTimeEnd;

	@Override
	public String toString() {
		return "角色ID:" + (roleId == null ? " 空 " : roleId) + ",角色名称:" + (roleName == null ? " 空 " : roleName) + ",角色描述:" + (roleDesc == null ? " 空 " : roleDesc) + ",创建时间:" + (createTime == null ? " 空 " : createTime) + ",最后更新时间:" + (lastUodateTime == null ? " 空 " : lastUodateTime);
	}
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

	public void setRoleNameFuzzy(String roleNameFuzzy) {
		this.roleNameFuzzy = roleNameFuzzy;
	}

	public String getRoleNameFuzzy() {
		return this.roleNameFuzzy;
	}

	public void setRoleDescFuzzy(String roleDescFuzzy) {
		this.roleDescFuzzy = roleDescFuzzy;
	}

	public String getRoleDescFuzzy() {
		return this.roleDescFuzzy;
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

	public void setLastUodateTimeStart(String lastUodateTimeStart) {
		this.lastUodateTimeStart = lastUodateTimeStart;
	}

	public String getLastUodateTimeStart() {
		return this.lastUodateTimeStart;
	}

	public void setLastUodateTimeEnd(String lastUodateTimeEnd) {
		this.lastUodateTimeEnd = lastUodateTimeEnd;
	}

	public String getLastUodateTimeEnd() {
		return this.lastUodateTimeEnd;
	}


}