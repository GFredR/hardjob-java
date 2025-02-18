package org.greenfred.controller;

import org.greenfred.annotation.GlobalInterceptor;
import org.greenfred.annotation.VerifyParam;
import org.greenfred.entity.constants.Constants;
import org.greenfred.entity.dto.CreateImageCode;
import org.greenfred.entity.dto.SessionUserAdminDto;
import org.greenfred.entity.po.SysAccount;
import org.greenfred.entity.vo.ResponseVO;
import org.greenfred.enums.VerifyRegexEnum;
import org.greenfred.exception.BusinessException;
import org.greenfred.service.SysAccountService;
import org.greenfred.utils.StringTools;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
public class LoginController extends BaseController {

    @Resource
    private SysAccountService sysAccountService;

    @RequestMapping("/checkCode")
    public void checkCode(HttpServletResponse response, HttpSession session, Integer type) throws IOException {
        CreateImageCode vCode = new CreateImageCode(150, 50, 5, 10);
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        String code = vCode.getCode();
        if (type == null || type == 0) {
            session.setAttribute(Constants.CHECK_CODE_KEY, code);
        } else {
            session.setAttribute(Constants.CHECK_CODE_KEY_EMAIL, code);
        }
        vCode.write(response.getOutputStream());
    }

    @RequestMapping("/login")
    @GlobalInterceptor(checkLogin = false)
    public ResponseVO login(HttpSession session, @VerifyParam(regex = VerifyRegexEnum.PHONE) String phone,
                            @VerifyParam(required = true) String password,
                            @VerifyParam(required = true) String checkCode) throws BusinessException {

        if (!checkCode.equalsIgnoreCase((String) session.getAttribute(Constants.CHECK_CODE_KEY))) {
            throw new BusinessException("图片验证码错误");
        }
        SessionUserAdminDto userAdminDto = sysAccountService.login(phone, password);
        session.setAttribute(Constants.SESSION_KEY, userAdminDto);
        return getSuccessResponseVO(userAdminDto);
    }

    @RequestMapping("/logout")
    @GlobalInterceptor(checkLogin = false)
    public ResponseVO logout(HttpSession session) {
        session.invalidate();
        return getSuccessResponseVO(null);
    }

    @RequestMapping("/updateMyPwd")
    @GlobalInterceptor
    public ResponseVO updateMyPwd(HttpSession session, @VerifyParam(regex = VerifyRegexEnum.PASSWORD) String password) {
        SessionUserAdminDto userAdminDto = getUserAdminFromSession(session);
        SysAccount sysAccount = new SysAccount();
        sysAccount.setPassword(StringTools.encodeByMd5(password));
        sysAccountService.updateSysAccountByUserId(sysAccount, userAdminDto.getUserId());
        return getSuccessResponseVO(null);
    }
}
