package org.greenfred.service;

import java.util.List;

import org.greenfred.entity.dto.SessionUserAdminDto;
import org.greenfred.entity.po.SysAccount;
import org.greenfred.entity.query.SysAccountQuery;
import org.greenfred.entity.vo.PaginationResultVO;
import org.greenfred.exception.BusinessException;

/**
 * @ Description: Service
 * @ author: 郭丰锐
 * @ date: 2025/01/13
 */
public interface SysAccountService {

	/** 
	* 根据条件查询列表
	*/
	List<SysAccount> findListByParam(SysAccountQuery query);

	/** 
	* 根据条件查询数量
	*/
	Integer findCountByParam(SysAccountQuery query);

	/** 
	* 分页查询
	*/
	PaginationResultVO<SysAccount>findListByPage(SysAccountQuery query);

	/** 
	* 新增
	*/
	Integer add(SysAccount bean);

	/** 
	* 批量新增
	*/
	Integer addBatch(List<SysAccount> listBean);

	/** 
	* 批量新增或修改
	*/
	Integer addOrUpdateBatch(List<SysAccount> listBean);

	/** 
	* 根据UserId查询
	*/
	SysAccount getSysAccountByUserId(Integer userId);

	/** 
	* 根据UserId更新
	*/
	Integer updateSysAccountByUserId(SysAccount bean, Integer userId);

	/** 
	* 根据UserId删除
	*/
	Integer deleteSysAccountByUserId(Integer userId);

	/** 
	* 根据Phone查询
	*/
	SysAccount getSysAccountByPhone(String phone);

	/** 
	* 根据Phone更新
	*/
	Integer updateSysAccountByPhone(SysAccount bean, String phone);

	/** 
	* 根据Phone删除
	*/
	Integer deleteSysAccountByPhone(String phone);

	SessionUserAdminDto login(String phone, String password) throws BusinessException;

}