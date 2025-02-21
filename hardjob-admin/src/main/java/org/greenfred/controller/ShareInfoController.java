package org.greenfred.controller;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.greenfred.enums.DateTimePatternEnum;
import org.greenfred.utils.DateUtils;
import java.util.List;
import org.greenfred.entity.po.ShareInfo;
import org.greenfred.entity.query.ShareInfoQuery;
import org.greenfred.entity.vo.PaginationResultVO;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import javax.annotation.Resource;
import org.greenfred.service.ShareInfoService;
import org.greenfred.controller.BaseController;
import org.greenfred.entity.vo.ResponseVO;
 /**
 * @ Description: Controller
 * @ author: 郭丰锐
 * @ date: 2025/02/18
 */
@RestController
@RequestMapping("shareInfo")
public class ShareInfoController extends BaseController{

	@Resource
	private ShareInfoService shareInfoService;

	/** 
	* 加载列表
	*/
	@RequestMapping("loadDataList")
	public ResponseVO loadDataList(ShareInfoQuery query) {
		return getSuccessResponseVO(shareInfoService.findListByPage(query));
	}

	/** 
	* 新增
	*/
	@RequestMapping("add")
	public ResponseVO add(ShareInfo bean) {
		this.shareInfoService.add(bean);
		return getSuccessResponseVO(null);
	}

	/** 
	* 批量新增
	*/
	@RequestMapping("addBatch")
	public ResponseVO addBatch(@RequestBody List<ShareInfo> listBean) {
		this.shareInfoService.addBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/** 
	* 批量新增或修改
	*/
	@RequestMapping("addOrUpdateBatch")
	public ResponseVO addOrUpdateBatch(@RequestBody List<ShareInfo> listBean) {
		this.shareInfoService.addOrUpdateBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/** 
	* 根据ShareId查询
	*/
	@RequestMapping("getShareInfoByShareId")
	public ResponseVO getShareInfoByShareId(Integer shareId) {
		return getSuccessResponseVO(this.shareInfoService.getShareInfoByShareId(shareId));
	}

	/** 
	* 根据ShareId更新
	*/
	@RequestMapping("updateShareInfoByShareId")
	public ResponseVO updateShareInfoByShareId(ShareInfo bean, Integer shareId) {
		this.shareInfoService.updateShareInfoByShareId(bean,shareId);
		return getSuccessResponseVO(null);
	}

	/** 
	* 根据ShareId删除
	*/
	@RequestMapping("deleteShareInfoByShareId")
	public ResponseVO deleteShareInfoByShareId(Integer shareId) {
		this.shareInfoService.deleteShareInfoByShareId(shareId);
		return getSuccessResponseVO(null);
	}
}