package org.greenfred.service.impl;

import java.util.ArrayList;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.greenfred.entity.po.SysAccount;
import org.greenfred.entity.po.SysRole2Menu;
import org.greenfred.entity.query.SysAccountQuery;
import org.greenfred.entity.query.SysRole2MenuQuery;
import org.greenfred.enums.MenuCheckTypeEnum;
import org.greenfred.enums.ResponseCodeEnum;
import org.greenfred.exception.BusinessException;
import org.greenfred.mappers.SysAccountMapper;
import org.greenfred.mappers.SysRole2MenuMapper;
import org.greenfred.utils.StringTools;
import org.springframework.format.annotation.DateTimeFormat;
import org.greenfred.enums.DateTimePatternEnum;
import org.greenfred.utils.DateUtils;

import java.util.List;

import org.greenfred.entity.po.SysRole;
import org.greenfred.entity.query.SysRoleQuery;
import org.greenfred.entity.vo.PaginationResultVO;
import org.greenfred.service.SysRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import org.greenfred.mappers.SysRoleMapper;
import org.greenfred.enums.PageSize;
import org.greenfred.entity.query.SimplePage;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ Description: Service
 * @ author: 郭丰锐
 * @ date: 2025/01/22
 */
@Service("sysRoleService")
public class SysRoleServiceImpl implements SysRoleService {

    @Resource
    private SysRoleMapper<SysRole, SysRoleQuery> sysRoleMapper;

    @Resource
    private SysRole2MenuMapper<SysRole2Menu, SysRole2MenuQuery> sysRole2MenuMapper;

    @Resource
    private SysAccountMapper<SysAccount, SysAccountQuery> sysAccountMapper;

    /**
     * 根据条件查询列表
     */
    public List<SysRole> findListByParam(SysRoleQuery query) {
        return this.sysRoleMapper.selectList(query);
    }

    /**
     * 根据条件查询数量
     */
    public Integer findCountByParam(SysRoleQuery query) {
        return this.sysRoleMapper.selectCount(query);
    }

    /**
     * 分页查询
     */
    public PaginationResultVO<SysRole> findListByPage(SysRoleQuery query) {
        Integer count = this.findCountByParam(query);
        Integer pageSize = query.getPageSize() == null ? PageSize.SIZE15.getSize() : query.getPageSize();
        SimplePage page = new SimplePage(query.getPageNo(), count, pageSize);
        query.setSimplePage(page);
        List<SysRole> list = this.findListByParam(query);
        PaginationResultVO<SysRole> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), list);
        return result;
    }

    /**
     * 新增
     */
    public Integer add(SysRole bean) {
        return this.sysRoleMapper.insert(bean);
    }

    /**
     * 批量新增
     */
    public Integer addBatch(List<SysRole> listBean) {
        if (listBean == null || listBean.isEmpty()) {
            return 0;
        }
        return this.sysRoleMapper.insertBatch(listBean);
    }

    /**
     * 批量新增或修改
     */
    public Integer addOrUpdateBatch(List<SysRole> listBean) {
        if (listBean == null || listBean.isEmpty()) {
            return 0;
        }
        return this.sysRoleMapper.insertOrUpdateBatch(listBean);
    }

    /**
     * 根据RoleId查询
     */
    public SysRole getSysRoleByRoleId(Integer roleId) {
        SysRole sysRole = this.sysRoleMapper.selectByRoleId(roleId);
        List<Integer> selectMenuIds = this.sysRole2MenuMapper.selectMenuIdsByRoleIds(new String[]{String.valueOf(roleId)});
        sysRole.setMenuIds(selectMenuIds);
        return sysRole;
    }

    /**
     * 根据RoleId更新
     */
    public Integer updateSysRoleByRoleId(SysRole bean, Integer roleId) {
        return this.sysRoleMapper.updateByRoleId(bean, roleId);
    }

    /**
     * 根据RoleId删除
     * 如果角色在被使用则不能删除
     */
    @Transactional(rollbackFor = Exception.class)
    public Integer deleteSysRoleByRoleId(Integer roleId) throws BusinessException {
        SysAccountQuery sysAccountQuery = new SysAccountQuery();
        sysAccountQuery.setRoles(String.valueOf(roleId));
        Integer accountCount = sysAccountMapper.selectCount(sysAccountQuery);
        if (accountCount > 0) {
            throw new BusinessException("角色正在被使用，无法删除");
        }
        // 删除角色菜单关联表相关信息
        sysRole2MenuMapper.deleteByRoleId(roleId);

        return this.sysRoleMapper.deleteByRoleId(roleId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveRole(SysRole sysRole, String menuIds, String halfMenuIds) throws BusinessException {

        Integer roleId = sysRole.getRoleId();
        Boolean addMenu = false;

        // 根据RoleId查询，如果不存在则新增，否则更新
        SysRole role = sysRoleMapper.selectByRoleId(roleId);
        if (role == null) {
            Date curDate = new Date();
            sysRole.setCreateTime(curDate);
            sysRole.setLastUodateTime(curDate);
//            roleId = Integer.parseInt(StringTools.getRandomNumber(5));
//            sysRole.setRoleId(roleId);
            this.sysRoleMapper.insert(sysRole);

            addMenu = true;
        } else {
            sysRole.setCreateTime(null);
            this.sysRoleMapper.updateByRoleId(sysRole, roleId);
        }

        SysRoleQuery sysRoleQuery = new SysRoleQuery();
        sysRoleQuery.setRoleName(sysRole.getRoleName());

        Integer roleCount = sysRoleMapper.selectCount(sysRoleQuery);
        if (roleCount > 1) {
            throw new BusinessException("角色名称重复");
        }

        if (addMenu) {
            saveRoleMenu(roleId, menuIds, halfMenuIds);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveRoleMenu(Integer roleId, String menuIds, String halfMenuIds) throws BusinessException {
        if (menuIds == null && roleId == null) {
            throw new BusinessException(ResponseCodeEnum.CODE_600.getMsg());
        }

        List<SysRole2Menu> role2MenuList = new ArrayList<>();

        SysRole2MenuQuery sysRole2MenuQuery = new SysRole2MenuQuery();
        sysRole2MenuQuery.setRoleId(roleId);

        // 删除角色菜单关联表相关信息
        sysRole2MenuMapper.deleteByRoleId(roleId);

        String[] menuIdsArray = menuIds.split(",");
        String[] halfMenuIdsArray = StringTools.isEmpty(halfMenuIds) ? new String[]{} : halfMenuIds.split(",");

        convertMenuId2RoleMenu(role2MenuList, roleId, menuIdsArray, MenuCheckTypeEnum.ALL);
        convertMenuId2RoleMenu(role2MenuList, roleId, halfMenuIdsArray, MenuCheckTypeEnum.HALF);


        if (!role2MenuList.isEmpty()) {
            sysRole2MenuMapper.insertBatch(role2MenuList);
        }
    }



    private void convertMenuId2RoleMenu(List<SysRole2Menu> role2MenuList, Integer roleId, String[] menuIdArray,
                                        MenuCheckTypeEnum checkType) {
        for (String menuId : menuIdArray) {
            SysRole2Menu sysRole2Menu = new SysRole2Menu();
            sysRole2Menu.setRoleId(roleId);
            sysRole2Menu.setMenuId(Integer.parseInt(menuId));
            sysRole2Menu.setCheckType(checkType.getType());
            role2MenuList.add(sysRole2Menu);
        }
    }
}