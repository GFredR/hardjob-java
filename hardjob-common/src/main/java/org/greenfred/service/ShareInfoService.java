package org.greenfred.service;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.greenfred.enums.DateTimePatternEnum;
import org.greenfred.utils.DateUtils;
import java.util.List;
import org.greenfred.entity.po.ShareInfo;
import org.greenfred.entity.query.ShareInfoQuery;
import org.greenfred.entity.vo.PaginationResultVO;
 /**
 * @ Description: Service
 * @ author: 郭丰锐
 * @ date: 2025/02/18
 */
public interface ShareInfoService {

	/** 
	* 根据条件查询列表
	*/
	List<ShareInfo> findListByParam(ShareInfoQuery query);

	/** 
	* 根据条件查询数量
	*/
	Integer findCountByParam(ShareInfoQuery query);

	/** 
	* 分页查询
	*/
	PaginationResultVO<ShareInfo>findListByPage(ShareInfoQuery query);

	/** 
	* 新增
	*/
	Integer add(ShareInfo bean);

	/** 
	* 批量新增
	*/
	Integer addBatch(List<ShareInfo> listBean);

	/** 
	* 批量新增或修改
	*/
	Integer addOrUpdateBatch(List<ShareInfo> listBean);

	/** 
	* 根据ShareId查询
	*/
	ShareInfo getShareInfoByShareId(Integer shareId);

	/** 
	* 根据ShareId更新
	*/
	Integer updateShareInfoByShareId(ShareInfo bean, Integer shareId);

	/** 
	* 根据ShareId删除
	*/
	Integer deleteShareInfoByShareId(Integer shareId);

}