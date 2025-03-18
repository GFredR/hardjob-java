package org.greenfred.controller;

import java.util.ArrayList;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.greenfred.annotation.GlobalInterceptor;
import org.greenfred.annotation.VerifyParam;
import org.greenfred.entity.dto.SessionUserAdminDto;
import org.greenfred.entity.po.ExamQuestionItem;
import org.greenfred.enums.PermissionCodeEnum;
import org.greenfred.enums.QuestionTypeEnum;
import org.greenfred.enums.ResponseCodeEnum;
import org.greenfred.exception.BusinessException;
import org.greenfred.utils.JsonUtils;
import org.greenfred.utils.StringTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.greenfred.enums.DateTimePatternEnum;
import org.greenfred.utils.DateUtils;
import java.util.List;
import org.greenfred.entity.po.ExamQuestion;
import org.greenfred.entity.query.ExamQuestionQuery;
import org.greenfred.entity.vo.PaginationResultVO;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.greenfred.service.ExamQuestionService;
import org.greenfred.controller.BaseController;
import org.greenfred.entity.vo.ResponseVO;
 /**
 * @ Description: Controller
 * @ author: 郭丰锐
 * @ date: 2025/02/18
 */
@RestController("examQuestionController")
@RequestMapping("/examQuestion")
public class ExamQuestionController extends BaseController{

	 private static final Logger logger = LoggerFactory.getLogger(ExamQuestionController.class);

	@Resource
	private ExamQuestionService examQuestionService;

	/** 
	* 加载列表
	*/
	@RequestMapping("/loadDataList")
	@GlobalInterceptor(permissionCode = PermissionCodeEnum.EXAM_QUESTION_LIST)
	public ResponseVO loadDataList(ExamQuestionQuery query) {
		query.setOrderBy("question_id desc");
		query.setQueryAnswer(true);
		return getSuccessResponseVO(examQuestionService.findListByPage(query));
	}

	 @RequestMapping("/saveExamQuestion")
	 @GlobalInterceptor(permissionCode = PermissionCodeEnum.EXAM_QUESTION_EDIT)
	 public ResponseVO saveExamQuestion(HttpSession session, @VerifyParam(required = true) ExamQuestion examQuestion,
										String questionItemListJson) throws BusinessException {
		 SessionUserAdminDto sessionUserAdminDto = getUserAdminFromSession(session);
		 examQuestion.setCreateUserId(String.valueOf(sessionUserAdminDto.getUserId()));
		 examQuestion.setCreateUserName(sessionUserAdminDto.getUsername());
		 List<ExamQuestionItem> examQuestionItemList = new ArrayList<>();
		 /**
		  * 如果是选择题，需要传递选项列表，否则报错
		  */
		 if (!QuestionTypeEnum.TRUE_FALSE.getType().equals(examQuestion.getQuestionType())) {
			 if (StringTools.isEmpty(questionItemListJson)) {
				 throw new BusinessException(ResponseCodeEnum.CODE_600);
			 }
			 examQuestionItemList = JsonUtils.convertJsonArray2List(questionItemListJson, ExamQuestionItem.class);
			 logger.info("examQuestionItemList:{}", examQuestionItemList);
		 }
		 examQuestionService.saveExamQuestion(examQuestion, examQuestionItemList, sessionUserAdminDto.getSuperAdmin());
		 return getSuccessResponseVO(examQuestionService.findListByPage(null));
	 }


}