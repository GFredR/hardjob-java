package org.greenfred.service.impl;

import java.util.List;

import org.greenfred.entity.dto.SessionUserAdminDto;
import org.greenfred.entity.po.SysAccount;
import org.greenfred.entity.query.SysAccountQuery;
import org.greenfred.entity.vo.PaginationResultVO;
import org.greenfred.enums.SysAccountStatusEnum;
import org.greenfred.service.SysAccountService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.greenfred.mappers.SysAccountMapper;
import org.greenfred.enums.PageSize;
import org.greenfred.entity.query.SimplePage;
 /**
 * @ Description: Service
 * @ author: 郭丰锐
 * @ date: 2025/01/13
 */
@Service("sysAccountService")
public class SysAccountServiceImpl implements SysAccountService {

	@Resource
	private SysAccountMapper<SysAccount,SysAccountQuery> sysAccountMapper;

	/** 
	* 根据条件查询列表
	*/
	public List<SysAccount> findListByParam(SysAccountQuery query) {
		return this.sysAccountMapper.selectList(query);
	}
	/** 
	* 根据条件查询数量
	*/
	public Integer findCountByParam(SysAccountQuery query) {
		return this.sysAccountMapper.selectCount(query);
	}
	/** 
	* 分页查询
	*/
	public PaginationResultVO<SysAccount> findListByPage(SysAccountQuery query) {
		Integer count = this.findCountByParam(query);
		Integer pageSize = query.getPageSize() == null ? PageSize.SIZE15.getSize() : query.getPageSize();
		SimplePage page = new SimplePage(query.getPageNo(), count, pageSize);
		query.setSimplePage(page);
		List<SysAccount> list = this.findListByParam(query);
		PaginationResultVO<SysAccount> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), list);
		return result;
	}
	/** 
	* 新增
	*/
	public Integer add(SysAccount bean) {
		return this.sysAccountMapper.insert(bean);
	}
	/** 
	* 批量新增
	*/
	public Integer addBatch(List<SysAccount> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.sysAccountMapper.insertBatch(listBean);
	}

	/** 
	* 批量新增或修改
	*/
	public Integer addOrUpdateBatch(List<SysAccount> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.sysAccountMapper.insertOrUpdateBatch(listBean);
	}
	/** 
	* 根据UserId查询
	*/
	public SysAccount getSysAccountByUserId(Integer userId) {
		return this.sysAccountMapper.selectByUserId(userId);
	}
	/** 
	* 根据UserId更新
	*/
	public Integer updateSysAccountByUserId(SysAccount bean, Integer userId) {
		return this.sysAccountMapper.updateByUserId(bean,userId);
	}
	/** 
	* 根据UserId删除
	*/
	public Integer deleteSysAccountByUserId(Integer userId) {
		return this.sysAccountMapper.deleteByUserId(userId);
	}
	/** 
	* 根据Phone查询
	*/
	public SysAccount getSysAccountByPhone(String phone) {
		return this.sysAccountMapper.selectByPhone(phone);
	}
	/** 
	* 根据Phone更新
	*/
	public Integer updateSysAccountByPhone(SysAccount bean, String phone) {
		return this.sysAccountMapper.updateByPhone(bean,phone);
	}
	/** 
	* 根据Phone删除
	*/
	public Integer deleteSysAccountByPhone(String phone) {
		return this.sysAccountMapper.deleteByPhone(phone);
	}

	 @Override
	 public SessionUserAdminDto login(String phone, String password) {
		 SysAccount sysAccount = this.sysAccountMapper.selectByPhone(phone);
		 if(sysAccount == null){
			 throw new RuntimeException("账号或者密码错误");
		 }
		 if(SysAccountStatusEnum.DISABLE.getStatus().equals(sysAccount.getStatus())){
			 throw new RuntimeException("账号已经被禁用");
		 }
		 if(!sysAccount.getPassword().equals(password)){
			 throw new RuntimeException("账号或者密码错误");
		 }

		 SessionUserAdminDto sessionUserAdminDto = new SessionUserAdminDto();
		 sessionUserAdminDto.setUserId(sysAccount.getUserId());
		 sessionUserAdminDto.setUsername(sysAccount.getUserName());
		 sessionUserAdminDto.setSuperAdmin(true);

		 return sessionUserAdminDto;
	 }
 }