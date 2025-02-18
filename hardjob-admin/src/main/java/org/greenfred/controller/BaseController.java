package org.greenfred.controller;

import org.greenfred.entity.constants.Constants;
import org.greenfred.entity.dto.SessionUserAdminDto;
import org.greenfred.enums.ResponseCodeEnum;
import org.greenfred.entity.vo.ResponseVO;

import javax.servlet.http.HttpSession;

public class BaseController {
    protected static final String STATUS_SUCCESS = "success";

    protected static final String STATUS_ERROR = "error";

    protected <T> ResponseVO getSuccessResponseVO(T t) {
        ResponseVO<T> responseVO = new ResponseVO<>();
        responseVO.setStatus(STATUS_SUCCESS);
        responseVO.setCode(ResponseCodeEnum.CODE_200.getCode());
        responseVO.setInfo(ResponseCodeEnum.CODE_200.getMsg());
        responseVO.setData(t);
        return responseVO;
    }

    protected SessionUserAdminDto getUserAdminFromSession(HttpSession session) {
        SessionUserAdminDto sessionUserAdminDto = (SessionUserAdminDto) session.getAttribute(Constants.SESSION_KEY);
        return sessionUserAdminDto;
    }

}
