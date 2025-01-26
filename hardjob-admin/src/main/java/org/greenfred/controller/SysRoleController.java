package org.greenfred.controller;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.greenfred.enums.DateTimePatternEnum;
import org.greenfred.utils.DateUtils;
import java.util.List;
import org.greenfred.entity.po.SysRole;
import org.greenfred.entity.query.SysRoleQuery;
import org.greenfred.entity.vo.PaginationResultVO;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import javax.annotation.Resource;
import org.greenfred.service.SysRoleService;
import org.greenfred.controller.BaseController;
import org.greenfred.entity.vo.ResponseVO;
 /**
 * @ Description: Controller
 * @ author: 郭丰锐
 * @ date: 2025/01/22
 */
@RestController
@RequestMapping("sysRole")
public class SysRoleController extends BaseController{

	@Resource
	private SysRoleService sysRoleService;

	/** 
	* 加载列表
	*/
	@RequestMapping("loadDataList")
	public ResponseVO loadDataList(SysRoleQuery query) {
		return getSuccessResponseVO(sysRoleService.findListByPage(query));
	}

	/** 
	* 新增
	*/
	@RequestMapping("add")
	public ResponseVO add(SysRole bean) {
		this.sysRoleService.add(bean);
		return getSuccessResponseVO(null);
	}

	/** 
	* 批量新增
	*/
	@RequestMapping("addBatch")
	public ResponseVO addBatch(@RequestBody List<SysRole> listBean) {
		this.sysRoleService.addBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/** 
	* 批量新增或修改
	*/
	@RequestMapping("addOrUpdateBatch")
	public ResponseVO addOrUpdateBatch(@RequestBody List<SysRole> listBean) {
		this.sysRoleService.addOrUpdateBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/** 
	* 根据RoleId查询
	*/
	@RequestMapping("getSysRoleByRoleId")
	public ResponseVO getSysRoleByRoleId(Integer roleId) {
		return getSuccessResponseVO(this.sysRoleService.getSysRoleByRoleId(roleId));
	}

	/** 
	* 根据RoleId更新
	*/
	@RequestMapping("updateSysRoleByRoleId")
	public ResponseVO updateSysRoleByRoleId(SysRole bean, Integer roleId) {
		this.sysRoleService.updateSysRoleByRoleId(bean,roleId);
		return getSuccessResponseVO(null);
	}

	/** 
	* 根据RoleId删除
	*/
	@RequestMapping("deleteSysRoleByRoleId")
	public ResponseVO deleteSysRoleByRoleId(Integer roleId) {
		this.sysRoleService.deleteSysRoleByRoleId(roleId);
		return getSuccessResponseVO(null);
	}
}