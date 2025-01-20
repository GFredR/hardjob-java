package org.greenfred.enums;

public enum SysAccountStatusEnum {
    DISABLE(0, "禁用"),
    ENABLE(1, "启用");
    private Integer status;

    private String msg;

    SysAccountStatusEnum(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
