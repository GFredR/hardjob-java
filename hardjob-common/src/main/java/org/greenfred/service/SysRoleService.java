package org.greenfred.service;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.greenfred.exception.BusinessException;
import org.springframework.format.annotation.DateTimeFormat;
import org.greenfred.enums.DateTimePatternEnum;
import org.greenfred.utils.DateUtils;

import java.util.List;

import org.greenfred.entity.po.SysRole;
import org.greenfred.entity.query.SysRoleQuery;
import org.greenfred.entity.vo.PaginationResultVO;

/**
 * @ Description: Service
 * @ author: 郭丰锐
 * @ date: 2025/01/22
 */
public interface SysRoleService {

    /**
     * 根据条件查询列表
     */
    List<SysRole> findListByParam(SysRoleQuery query);

    /**
     * 根据条件查询数量
     */
    Integer findCountByParam(SysRoleQuery query);

    /**
     * 分页查询
     */
    PaginationResultVO<SysRole> findListByPage(SysRoleQuery query);

    /**
     * 新增
     */
    Integer add(SysRole bean);

    /**
     * 批量新增
     */
    Integer addBatch(List<SysRole> listBean);

    /**
     * 批量新增或修改
     */
    Integer addOrUpdateBatch(List<SysRole> listBean);

    /**
     * 根据RoleId查询
     */
    SysRole getSysRoleByRoleId(Integer roleId);

    /**
     * 根据RoleId更新
     */
    Integer updateSysRoleByRoleId(SysRole bean, Integer roleId);

    /**
     * 根据RoleId删除
     */
    Integer deleteSysRoleByRoleId(Integer roleId) throws BusinessException;

    void saveRole(SysRole sysRole, String menuIds, String halfMenuIds) throws BusinessException;

    void saveRoleMenu(Integer roleId, String menuIds, String halfMenuIds) throws BusinessException;


}