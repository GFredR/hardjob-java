package org.greenfred.controller;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.greenfred.annotation.GlobalInterceptor;
import org.greenfred.annotation.VerifyParam;
import org.greenfred.entity.dto.ImportErrorItem;
import org.greenfred.entity.dto.SessionUserAdminDto;
import org.greenfred.enums.PermissionCodeEnum;
import org.greenfred.enums.PostStatusEnum;
import org.greenfred.exception.BusinessException;
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
import org.springframework.web.multipart.MultipartFile;

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
    public ResponseVO saveQuestion(HttpSession session, QuestionInfo questionInfo) throws BusinessException {
        SessionUserAdminDto userAdminDto = getUserAdminFromSession(session);
        questionInfo.setCreateUserId(String.valueOf(userAdminDto.getUserId()));
        questionInfo.setCreateUserName(userAdminDto.getUsername());
        questionInfoService.saveQuestion(questionInfo, userAdminDto.getSuperAdmin());
        return getSuccessResponseVO(null);
    }

    @RequestMapping("/delQuestion")
    @GlobalInterceptor(permissionCode = PermissionCodeEnum.QUESTION_EDIT)
    public ResponseVO delQuestion(HttpSession session, @VerifyParam(required = true) Integer questionId) throws BusinessException {
        SessionUserAdminDto userAdminDto = getUserAdminFromSession(session);
        questionInfoService.delQuestionBatch(String.valueOf(questionId),
                userAdminDto.getSuperAdmin() ? null : userAdminDto.getUserId());
        return getSuccessResponseVO(null);
    }

    @RequestMapping("/delQuestionBatch")
    @GlobalInterceptor(permissionCode = PermissionCodeEnum.QUESTION_DEL_BATCH)
    public ResponseVO delQuestionBatch(@VerifyParam(required = true) String questionIds) throws BusinessException {
        questionInfoService.delQuestionBatch(questionIds, null);
        return getSuccessResponseVO(null);
    }

    @RequestMapping("/postQuestion")
    @GlobalInterceptor(permissionCode = PermissionCodeEnum.QUESTION_POST)
    public ResponseVO postQuestion(@VerifyParam(required = true) String questionIds) throws BusinessException {
        questionInfoService.updateStatus(questionIds, PostStatusEnum.POST.getStatus());
        return getSuccessResponseVO(null);
    }

    @RequestMapping("/cancelPostQuestion")
    @GlobalInterceptor(permissionCode = PermissionCodeEnum.QUESTION_POST)
    public ResponseVO cancelPostQuestion(@VerifyParam(required = true) String questionIds) throws BusinessException {
        questionInfoService.updateStatus(questionIds, PostStatusEnum.NO_POST.getStatus());
        return getSuccessResponseVO(null);
    }

    @RequestMapping("/importQuestion")
    @GlobalInterceptor(permissionCode = PermissionCodeEnum.QUESTION_IMPORT)
    public ResponseVO importQuestion(HttpSession session, MultipartFile file) throws BusinessException {
        SessionUserAdminDto userAdminDto = getUserAdminFromSession(session);
        List<ImportErrorItem> errorItemList = questionInfoService.importQuestion(file, userAdminDto);
        return getSuccessResponseVO(errorItemList);
    }

    @RequestMapping("/showQuestionDetailNext")
    @GlobalInterceptor(permissionCode = PermissionCodeEnum.QUESTION_LIST)
    public ResponseVO showQuestionDetailNext(QuestionInfoQuery questionInfoQuery,
                                             @VerifyParam(required = true) Integer currentId,
                                             Integer nextType) throws BusinessException {
        QuestionInfo questionInfo = questionInfoService.showDetail(questionInfoQuery, nextType, currentId, false);
        return getSuccessResponseVO(null);
    }
}