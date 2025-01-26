package org.greenfred.entity.dto;

import org.greenfred.entity.po.SysMenu;
import org.greenfred.entity.vo.SysMenuVO;

import java.util.List;

public class SessionUserAdminDto {
    private Integer userId;
    private String username;
    private Boolean superAdmin;
    private List<SysMenuVO> menuList;

    public List<SysMenuVO> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<SysMenuVO> menuList) {
        this.menuList = menuList;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getSuperAdmin() {
        return superAdmin;
    }

    public void setSuperAdmin(Boolean superAdmin) {
        this.superAdmin = superAdmin;
    }
}
