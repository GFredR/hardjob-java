package org.greenfred.controller;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.greenfred.annotation.GlobalInterceptor;
import org.greenfred.annotation.VerifyParam;
import org.greenfred.entity.po.SysRole2Menu;
import org.greenfred.enums.PermissionCodeEnum;
import org.greenfred.exception.BusinessException;
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
public class SysRoleController extends BaseController {

    @Resource
    private SysRoleService sysRoleService;

    /**
     * 加载列表
     */
    @RequestMapping("/loadRoles")
    @GlobalInterceptor(permissionCode = PermissionCodeEnum.SETTINGS_ROLE_LIST)
    public ResponseVO loadRoles(SysRoleQuery query) {
        query.setOrderBy("create_time desc");
        return getSuccessResponseVO(sysRoleService.findListByPage(query));
    }

    @RequestMapping("/loadAllRoles")
    @GlobalInterceptor(permissionCode = PermissionCodeEnum.SETTINGS_ROLE_LIST)
    public ResponseVO loadAllRoles() {
        SysRoleQuery query = new SysRoleQuery();
        query.setOrderBy("create_time desc");
        return getSuccessResponseVO(sysRoleService.findListByParam(query));
    }

    /**
     * 新增
     */
    @RequestMapping("/saveRole")
    @GlobalInterceptor(permissionCode = PermissionCodeEnum.SETTINGS_ROLE_EDIT)
    public ResponseVO saveRole(@VerifyParam SysRole bean,
                               String menuIds,
                               String halfMenuIds) throws BusinessException {
        this.sysRoleService.saveRole(bean, menuIds, halfMenuIds);
        return getSuccessResponseVO(null);
    }

    @RequestMapping("/saveRoleMenu")
    @GlobalInterceptor(permissionCode = PermissionCodeEnum.SETTINGS_ROLE_EDIT)
    public ResponseVO saveRoleMenu(@VerifyParam(required = true) Integer roleId, @VerifyParam(required = true) String menuIds, String halfMenuIds) throws BusinessException {
        this.sysRoleService.saveRoleMenu(roleId, menuIds, halfMenuIds);
        return getSuccessResponseVO(null);
    }

    @RequestMapping("/getRoleByRoleId")
    @GlobalInterceptor(permissionCode = PermissionCodeEnum.SETTINGS_ROLE_LIST)
    public ResponseVO getRoleByRoleId(@VerifyParam(required = true) Integer roleId) {
        SysRole sysRole = sysRoleService.getSysRoleByRoleId(roleId);
        return getSuccessResponseVO(sysRole);
    }

    /**
     * 根据RoleId删除角色和角色菜单关联表相关信息
     */
    @RequestMapping("/deleteRole")
    @GlobalInterceptor(permissionCode = PermissionCodeEnum.SETTINGS_ROLE_DEL)
    public ResponseVO deleteSysRoleByRoleId(@VerifyParam(required = true) Integer roleId) throws BusinessException {
        this.sysRoleService.deleteSysRoleByRoleId(roleId);
        return getSuccessResponseVO(null);
    }
}