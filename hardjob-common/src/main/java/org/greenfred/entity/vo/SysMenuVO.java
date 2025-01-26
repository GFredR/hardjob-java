package org.greenfred.entity.vo;

import org.greenfred.entity.po.SysMenu;

import java.util.List;

public class SysMenuVO {
    /**
     * 菜单id
     */

    private Integer menuId;

    /**
     * 菜单名称
     */

    private String menuName;

    /**
     * 菜单类型
     */

    private Integer menuType;

    /**
     * 菜单跳转的url
     */

    private String menuUrl;


    /**
     * 图标
     */

    private String icon;

    private List<SysMenuVO> children;

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Integer getMenuType() {
        return menuType;
    }

    public void setMenuType(Integer menuType) {
        this.menuType = menuType;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<SysMenuVO> getChildren() {
        return children;
    }

    public void setChildren(List<SysMenuVO> children) {
        this.children = children;
    }
}
