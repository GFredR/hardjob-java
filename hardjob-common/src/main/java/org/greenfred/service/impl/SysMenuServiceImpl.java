package org.greenfred.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.greenfred.entity.constants.Constants;
import org.greenfred.entity.po.SysMenu;
import org.greenfred.entity.query.SysMenuQuery;
import org.greenfred.entity.vo.PaginationResultVO;
import org.greenfred.mappers.SysRoleMapper;
import org.greenfred.service.SysMenuService;
import org.greenfred.utils.StringTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import org.greenfred.mappers.SysMenuMapper;
import org.greenfred.enums.PageSize;
import org.greenfred.entity.query.SimplePage;

/**
 * @ Description: Service
 * @ author: 郭丰锐
 * @ date: 2025/01/22
 */
@Service("sysMenuService")
public class SysMenuServiceImpl implements SysMenuService {

    private Logger logger = LoggerFactory.getLogger(SysMenuServiceImpl.class);
    private static final String ROOT_MENU_NAME = "所有菜单";

    @Resource
    private SysMenuMapper<SysMenu, SysMenuQuery> sysMenuMapper;
    @Autowired
    private SysRoleMapper sysRoleMapper;

    /**
     * 根据条件查询列表
     */
    public List<SysMenu> findListByParam(SysMenuQuery query) {
        List<SysMenu> sysMenuList = this.sysMenuMapper.selectList(query);
        if (query.getFormat2Tree() != null && query.getFormat2Tree()) {
            SysMenu root = new SysMenu();
            root.setMenuId(Constants.DEFAULT_ROOT_MENUID);
            root.setPId(-1);
            root.setMenuName(ROOT_MENU_NAME);
            sysMenuList.add(root);

            sysMenuList = convertLine2Tree4Menu(sysMenuList, -1);
        }
        return sysMenuList;
    }

    public List<SysMenu> convertLine2Tree4Menu(List<SysMenu> dataList, Integer pId) {
        List<SysMenu> children = new ArrayList<>();
        for (SysMenu menu : dataList) {
            logger.info("Checking menu: MenuId: {}, PId: {}, target pId: {}", menu.getMenuId(), menu.getPId(), pId);
            if (menu.getMenuId() != null && menu.getPId() != null && menu.getPId().equals(pId)) {
                menu.setChildren(convertLine2Tree4Menu(dataList, menu.getMenuId()));
                children.add(menu);
            }
        }
        logger.info("dataList: {}", dataList);
        logger.info("pId: {}, children: {}", pId, children);
        return children;

    }

    @Override
    public void saveMenu(SysMenu sysMenu) {
        if (sysMenu.getMenuId() == null) {
            this.sysMenuMapper.insert(sysMenu);
        } else {
            this.sysMenuMapper.updateByMenuId(sysMenu, sysMenu.getMenuId());
        }


    }

    @Override
    public List<SysMenu> getAllMenuByRoleIds(String roleIds) {
        if (StringTools.isEmpty(roleIds)) {
            return new ArrayList<>();
        }
        int[] roleIdArray = Arrays.stream(roleIds.split(",")).mapToInt(Integer::valueOf).toArray();
        logger.info("roleIdArray:{}", roleIdArray);
        List<SysMenu> menuList = sysMenuMapper.selectAllMenuByRoleIds(roleIdArray);
        logger.info("menuList:{}", menuList);
        return sysMenuMapper.selectAllMenuByRoleIds(roleIdArray);
    }


    /**
     * 根据条件查询数量
     */
    public Integer findCountByParam(SysMenuQuery query) {
        return this.sysMenuMapper.selectCount(query);
    }

    /**
     * 分页查询
     */
    public PaginationResultVO<SysMenu> findListByPage(SysMenuQuery query) {
        Integer count = this.findCountByParam(query);
        Integer pageSize = query.getPageSize() == null ? PageSize.SIZE15.getSize() : query.getPageSize();
        SimplePage page = new SimplePage(query.getPageNo(), count, pageSize);
        query.setSimplePage(page);
        List<SysMenu> list = this.findListByParam(query);
        PaginationResultVO<SysMenu> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), list);
        return result;
    }

    /**
     * 新增
     */
    public Integer add(SysMenu bean) {
        return this.sysMenuMapper.insert(bean);
    }

    /**
     * 批量新增
     */
    public Integer addBatch(List<SysMenu> listBean) {
        if (listBean == null || listBean.isEmpty()) {
            return 0;
        }
        return this.sysMenuMapper.insertBatch(listBean);
    }

    /**
     * 批量新增或修改
     */
    public Integer addOrUpdateBatch(List<SysMenu> listBean) {
        if (listBean == null || listBean.isEmpty()) {
            return 0;
        }
        return this.sysMenuMapper.insertOrUpdateBatch(listBean);
    }

    /**
     * 根据MenuId查询
     */
    public SysMenu getSysMenuByMenuId(Integer menuId) {
        return this.sysMenuMapper.selectByMenuId(menuId);
    }

    /**
     * 根据MenuId更新
     */
    public Integer updateSysMenuByMenuId(SysMenu bean, Integer menuId) {
        return this.sysMenuMapper.updateByMenuId(bean, menuId);
    }

    /**
     * 根据MenuId删除
     */
    public Integer deleteSysMenuByMenuId(Integer menuId) {
        return this.sysMenuMapper.deleteByMenuId(menuId);
    }
}