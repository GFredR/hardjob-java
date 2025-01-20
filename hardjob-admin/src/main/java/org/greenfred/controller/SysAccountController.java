package org.greenfred.controller;

import java.util.List;
import org.greenfred.entity.po.SysAccount;
import org.greenfred.entity.query.SysAccountQuery;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import javax.annotation.Resource;
import org.greenfred.service.SysAccountService;
import org.greenfred.entity.vo.ResponseVO;
 /**
 * @ Description: Controller
 * @ author: 郭丰锐
 * @ date: 2025/01/13
 */
@RestController
@RequestMapping("sysAccount")
public class SysAccountController extends BaseController{

	@Resource
	private SysAccountService sysAccountService;

	/** 
	* 加载列表
	*/
	@RequestMapping("loadDataList")
	public ResponseVO loadDataList(SysAccountQuery query) {
		return getSuccessResponseVO(sysAccountService.findListByPage(query));
	}

	/** 
	* 新增
	*/
	@RequestMapping("add")
	public ResponseVO add(SysAccount bean) {
		this.sysAccountService.add(bean);
		return getSuccessResponseVO(null);
	}

	/** 
	* 批量新增
	*/
	@RequestMapping("addBatch")
	public ResponseVO addBatch(@RequestBody List<SysAccount> listBean) {
		this.sysAccountService.addBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/** 
	* 批量新增或修改
	*/
	@RequestMapping("addOrUpdateBatch")
	public ResponseVO addOrUpdateBatch(@RequestBody List<SysAccount> listBean) {
		this.sysAccountService.addOrUpdateBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/** 
	* 根据UserId查询
	*/
	@RequestMapping("getSysAccountByUserId")
	public ResponseVO getSysAccountByUserId(Integer userId) {
		return getSuccessResponseVO(this.sysAccountService.getSysAccountByUserId(userId));
	}

	/** 
	* 根据UserId更新
	*/
	@RequestMapping("updateSysAccountByUserId")
	public ResponseVO updateSysAccountByUserId(SysAccount bean, Integer userId) {
		this.sysAccountService.updateSysAccountByUserId(bean,userId);
		return getSuccessResponseVO(null);
	}

	/** 
	* 根据UserId删除
	*/
	@RequestMapping("deleteSysAccountByUserId")
	public ResponseVO deleteSysAccountByUserId(Integer userId) {
		this.sysAccountService.deleteSysAccountByUserId(userId);
		return getSuccessResponseVO(null);
	}
	/** 
	* 根据Phone查询
	*/
	@RequestMapping("getSysAccountByPhone")
	public ResponseVO getSysAccountByPhone(String phone) {
		return getSuccessResponseVO(this.sysAccountService.getSysAccountByPhone(phone));
	}

	/** 
	* 根据Phone更新
	*/
	@RequestMapping("updateSysAccountByPhone")
	public ResponseVO updateSysAccountByPhone(SysAccount bean, String phone) {
		this.sysAccountService.updateSysAccountByPhone(bean,phone);
		return getSuccessResponseVO(null);
	}

	/** 
	* 根据Phone删除
	*/
	@RequestMapping("deleteSysAccountByPhone")
	public ResponseVO deleteSysAccountByPhone(String phone) {
		this.sysAccountService.deleteSysAccountByPhone(phone);
		return getSuccessResponseVO(null);
	}
}