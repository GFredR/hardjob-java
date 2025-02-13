package org.greenfred.controller;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.greenfred.annotation.GlobalInterceptor;
import org.greenfred.annotation.VerifyParam;
import org.greenfred.entity.config.AppConfig;
import org.greenfred.entity.po.SysAccount;
import org.greenfred.entity.query.SysAccountQuery;

import org.greenfred.enums.PermissionCodeEnum;
import org.greenfred.enums.ResponseCodeEnum;
import org.greenfred.enums.UserStatusEnum;
import org.greenfred.enums.VerifyRegexEnum;
import org.greenfred.exception.BusinessException;
import org.greenfred.utils.StringTools;
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
@RestController("sysAccountController")
@RequestMapping("/settings")
public class SysAccountController extends BaseController {

    @Resource
    private SysAccountService sysAccountService;

    @Resource
    private AppConfig appConfig;

    /**
     * 加载列表
     */
    @RequestMapping("/loadAccountList")
    @GlobalInterceptor(permissionCode = PermissionCodeEnum.SETTINGS_ACCOUNT_LIST)
    public ResponseVO loadAccountList(SysAccountQuery query) {
        query.setOrderBy("create_time desc");
        query.setQueryRoles(true);
        return getSuccessResponseVO(sysAccountService.findListByPage(query));
    }

    @RequestMapping("/saveAccount")
    @GlobalInterceptor
    public ResponseVO saveAccount(@VerifyParam(required = true) SysAccount account) throws BusinessException {
        sysAccountService.saveSysAccount(account);
        return getSuccessResponseVO(null);
    }

    @RequestMapping("/updatePassword")
    @GlobalInterceptor
    public ResponseVO updatePassword(@VerifyParam(required = true) Integer userId,
                                     @VerifyParam(required = true, regex = VerifyRegexEnum.PASSWORD) String password) throws BusinessException {
        SysAccount sysAccount = new SysAccount();
        sysAccount.setPassword(StringTools.encodeByMd5(password));
        sysAccountService.updateSysAccountByUserId(sysAccount, userId);
        return getSuccessResponseVO(null);
    }

    @RequestMapping("/updateStatus")
    @GlobalInterceptor
    public ResponseVO updateStatus(@VerifyParam(required = true) Integer userId,
                                     @VerifyParam(required = true) Integer status) throws BusinessException {
        UserStatusEnum userStatusEnum = UserStatusEnum.getByStatus(status);
        if (userStatusEnum == null) {
            throw new BusinessException(ResponseCodeEnum.CODE_600);
        }

        SysAccount sysAccount = new SysAccount();
        sysAccount.setStatus(status);
        sysAccountService.updateSysAccountByUserId(sysAccount, userId);
        return getSuccessResponseVO(null);
    }

    @RequestMapping("/deleteAccount")
    @GlobalInterceptor
    public ResponseVO deleteAccount(@VerifyParam(required = true) Integer userId) throws BusinessException {
        SysAccount sysAccount = sysAccountService.getSysAccountByUserId(userId);
        if (!StringTools.isEmpty(appConfig.getSuperAdminPhones()) && ArrayUtils.contains(appConfig.getSuperAdminPhones().split(","), sysAccount.getPhone())) {
            throw new BusinessException("超级管理员不能删除");
        }

        sysAccountService.deleteSysAccountByUserId(userId);
        return getSuccessResponseVO(null);
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
        this.sysAccountService.updateSysAccountByUserId(bean, userId);
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
        this.sysAccountService.updateSysAccountByPhone(bean, phone);
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