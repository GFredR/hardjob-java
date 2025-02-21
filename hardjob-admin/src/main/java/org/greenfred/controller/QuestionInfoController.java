package org.greenfred.controller;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.greenfred.annotation.GlobalInterceptor;
import org.greenfred.entity.dto.SessionUserAdminDto;
import org.greenfred.enums.PermissionCodeEnum;
import org.springframework.format.annotation.DateTimeFormat;
import org.greenfred.enums.DateTimePatternEnum;
import org.greenfred.utils.DateUtils;

import java.util.List;

import org.greenfred.entity.po.QuestionInfo;
import org.greenfred.entity.query.QuestionInfoQuery;
import org.greenfred.entity.vo.PaginationResultVO;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.greenfred.service.QuestionInfoService;
import org.greenfred.controller.BaseController;
import org.greenfred.entity.vo.ResponseVO;

/**
 * @ Description: Controller
 * @ author: 郭丰锐
 * @ date: 2025/02/18
 */
@RestController
@RequestMapping("questionInfo")
public class QuestionInfoController extends BaseController {

    @Resource
    private QuestionInfoService questionInfoService;

    /**
     * 加载列表
     */
    @RequestMapping("/loadDataList")
    @GlobalInterceptor(permissionCode = PermissionCodeEnum.QUESTION_LIST)
    public ResponseVO loadDataList(QuestionInfoQuery query) {
        query.setOrderBy("question_id desc");
        query.setQueryTextContent(true);
        return getSuccessResponseVO(questionInfoService.findListByPage(query));
    }

    @RequestMapping("/saveQuestion")
    @GlobalInterceptor(permissionCode = PermissionCodeEnum.QUESTION_EDIT)
    public ResponseVO saveQuestion(HttpSession session, QuestionInfo questionInfo) {
        SessionUserAdminDto userAdminDto = getUserAdminFromSession(session);
        questionInfo.setCreateUserId(String.valueOf(userAdminDto.getUserId()));
        questionInfo.setCreateUserName(userAdminDto.getUsername());
        questionInfoService.saveQuestion(questionInfo, userAdminDto.getSuperAdmin());
        return getSuccessResponseVO(questionInfoService.findListByPage(query));
    }


}