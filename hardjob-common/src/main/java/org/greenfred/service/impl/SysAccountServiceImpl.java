package org.greenfred.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.greenfred.entity.config.AppConfig;
import org.greenfred.entity.dto.SessionUserAdminDto;
import org.greenfred.entity.po.SysAccount;
import org.greenfred.entity.po.SysMenu;
import org.greenfred.entity.query.SysAccountQuery;
import org.greenfred.entity.query.SysMenuQuery;
import org.greenfred.entity.vo.PaginationResultVO;
import org.greenfred.entity.vo.SysMenuVO;
import org.greenfred.enums.*;
import org.greenfred.exception.BusinessException;
import org.greenfred.service.SysAccountService;
import org.greenfred.service.SysMenuService;
import org.greenfred.utils.CopyTools;
import org.greenfred.utils.StringTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import org.greenfred.mappers.SysAccountMapper;
import org.greenfred.entity.query.SimplePage;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ Description: Service
 * @ author: 郭丰锐
 * @ date: 2025/01/13
 */
@Service("sysAccountService")
public class SysAccountServiceImpl implements SysAccountService {

    @Resource
    private SysAccountMapper<SysAccount, SysAccountQuery> sysAccountMapper;

    @Resource
    private SysMenuService sysMenuService;

    private Logger logger = LoggerFactory.getLogger(SysAccountServiceImpl.class);

    @Resource
    private AppConfig appConfig;

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
        return this.sysAccountMapper.updateByUserId(bean, userId);
    }

    /**
     * 根据UserId删除
     */
    @Transactional(rollbackFor = Exception.class)
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
        return this.sysAccountMapper.updateByPhone(bean, phone);
    }

    /**
     * 根据Phone删除
     */
    public Integer deleteSysAccountByPhone(String phone) {
        return this.sysAccountMapper.deleteByPhone(phone);
    }


    @Override
    public SessionUserAdminDto login(String phone, String password) throws BusinessException {
        SysAccount sysAccount = this.sysAccountMapper.selectByPhone(phone);
        if (sysAccount == null) {
            throw new BusinessException("账号或者密码错误");
        }
        if (SysAccountStatusEnum.DISABLE.getStatus().equals(sysAccount.getStatus())) {
            throw new BusinessException("账号已经被禁用");
        }
        if (!sysAccount.getPassword().equals(StringTools.encodeByMd5(password))) {
            throw new BusinessException("账号或者密码错误");
        }

        SessionUserAdminDto sessionUserAdminDto = new SessionUserAdminDto();
        sessionUserAdminDto.setUserId(sysAccount.getUserId());
        sessionUserAdminDto.setUsername(sysAccount.getUserName());

        List<SysMenu> allMenus;

        if (!StringTools.isEmpty(appConfig.getSuperAdminPhones()) && ArrayUtils.contains(appConfig.getSuperAdminPhones().split(","), sysAccount.getPhone())) {
            sessionUserAdminDto.setSuperAdmin(true);
            SysMenuQuery query = new SysMenuQuery();
            query.setFormat2Tree(false);
            query.setOrderBy("sort asc");

            allMenus = sysMenuService.findListByParam(query);

        } else {
            sessionUserAdminDto.setSuperAdmin(false);
            allMenus = sysMenuService.getAllMenuByRoleIds(sysAccount.getRoles());
        }


        List<String> permissionCodeList = new ArrayList<>();

        List<SysMenu> menuList = new ArrayList<>();
        for (SysMenu sysMenu : allMenus) {
            if (MenuTypeEnum.MENU.getType().equals(sysMenu.getMenuType())) {
                menuList.add(sysMenu);
            }
            permissionCodeList.add(sysMenu.getPermissionCode());
        }



        menuList = sysMenuService.convertLine2Tree4Menu(menuList, 0);
        if (menuList.isEmpty()) {
            throw new BusinessException("请联系管理员分配角色");
        }

        List<SysMenuVO> menuVOList = new ArrayList<>();

        menuList.forEach(item -> {
            SysMenuVO menuVO = CopyTools.copy(item, SysMenuVO.class);
            logger.info("children:{}", item.getChildren());
            menuVO.setChildren(CopyTools.copyList(item.getChildren(), SysMenuVO.class));
            menuVOList.add(menuVO);
        });

        sessionUserAdminDto.setMenuList(menuVOList);
        sessionUserAdminDto.setPermissionCodeList(permissionCodeList);


        return sessionUserAdminDto;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveSysAccount(SysAccount sysAccount) throws BusinessException {


        SysAccount phoneDb = sysAccountMapper.selectByPhone(sysAccount.getPhone());
        if (phoneDb != null && (sysAccount.getUserId() == null || !phoneDb.getUserId().equals(sysAccount.getUserId()))) {
            throw new BusinessException("手机号已经存在");
        }
        if (sysAccount.getUserId() == null) {
            Integer userId = Integer.parseInt(StringTools.getRandomNumber(5));
            sysAccount.setUserId(userId);
            sysAccount.setCreateTime(new Date());
            sysAccount.setStatus(UserStatusEnum.ENABLE.getStatus());
            sysAccount.setPassword(StringTools.encodeByMd5(sysAccount.getPassword()));
            this.sysAccountMapper.insert(sysAccount);
        } else {
            sysAccount.setStatus(null);
            sysAccount.setPassword(null);
            this.sysAccountMapper.updateByUserId(sysAccount, sysAccount.getUserId());

        }
    }
}