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
    @GlobalInterceptor(permissionCode = PermissionCodeEnum.SETTINGS_ACCOUNT_EDIT)
    public ResponseVO saveAccount(@VerifyParam(required = true) SysAccount account) throws BusinessException {
        sysAccountService.saveSysAccount(account);
        return getSuccessResponseVO(null);
    }

    @RequestMapping("/updatePassword")
    @GlobalInterceptor(permissionCode = PermissionCodeEnum.SETTINGS_ACCOUNT_UPDATE_PASSWORD)
    public ResponseVO updatePassword(@VerifyParam(required = true) Integer userId,
                                     @VerifyParam(required = true, regex = VerifyRegexEnum.PASSWORD) String password) throws BusinessException {
        SysAccount sysAccount = new SysAccount();
        sysAccount.setPassword(StringTools.encodeByMd5(password));
        sysAccountService.updateSysAccountByUserId(sysAccount, userId);
        return getSuccessResponseVO(null);
    }

    @RequestMapping("/updateStatus")
    @GlobalInterceptor(permissionCode = PermissionCodeEnum.SETTINGS_ACCOUNT_OP_STATUS)
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
    @GlobalInterceptor(permissionCode = PermissionCodeEnum.SETTINGS_ACCOUNT_DEL)
    public ResponseVO deleteAccount(@VerifyParam(required = true) Integer userId) throws BusinessException {
        SysAccount sysAccount = sysAccountService.getSysAccountByUserId(userId);
        if (!StringTools.isEmpty(appConfig.getSuperAdminPhones()) && ArrayUtils.contains(appConfig.getSuperAdminPhones().split(","), sysAccount.getPhone())) {
            throw new BusinessException("超级管理员不能删除");
        }

        sysAccountService.deleteSysAccountByUserId(userId);
        return getSuccessResponseVO(null);
    }


}