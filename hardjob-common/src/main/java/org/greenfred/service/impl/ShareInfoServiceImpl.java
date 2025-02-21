package org.greenfred.service.impl;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.greenfred.enums.DateTimePatternEnum;
import org.greenfred.utils.DateUtils;
import java.util.List;
import org.greenfred.entity.po.ShareInfo;
import org.greenfred.entity.query.ShareInfoQuery;
import org.greenfred.entity.vo.PaginationResultVO;
import org.greenfred.service.ShareInfoService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.greenfred.mappers.ShareInfoMapper;
import org.greenfred.enums.PageSize;
import org.greenfred.entity.query.SimplePage;
 /**
 * @ Description: Service
 * @ author: 郭丰锐
 * @ date: 2025/02/18
 */
@Service("shareInfoService")
public class ShareInfoServiceImpl implements ShareInfoService {

	@Resource
	private ShareInfoMapper<ShareInfo,ShareInfoQuery> shareInfoMapper;

	/** 
	* 根据条件查询列表
	*/
	public List<ShareInfo> findListByParam(ShareInfoQuery query) {
		return this.shareInfoMapper.selectList(query);
	}
	/** 
	* 根据条件查询数量
	*/
	public Integer findCountByParam(ShareInfoQuery query) {
		return this.shareInfoMapper.selectCount(query);
	}
	/** 
	* 分页查询
	*/
	public PaginationResultVO<ShareInfo> findListByPage(ShareInfoQuery query) {
		Integer count = this.findCountByParam(query);
		Integer pageSize = query.getPageSize() == null ? PageSize.SIZE15.getSize() : query.getPageSize();
		SimplePage page = new SimplePage(query.getPageNo(), count, pageSize);
		query.setSimplePage(page);
		List<ShareInfo> list = this.findListByParam(query);
		PaginationResultVO<ShareInfo> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), list);
		return result;
	}
	/** 
	* 新增
	*/
	public Integer add(ShareInfo bean) {
		return this.shareInfoMapper.insert(bean);
	}
	/** 
	* 批量新增
	*/
	public Integer addBatch(List<ShareInfo> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.shareInfoMapper.insertBatch(listBean);
	}

	/** 
	* 批量新增或修改
	*/
	public Integer addOrUpdateBatch(List<ShareInfo> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.shareInfoMapper.insertOrUpdateBatch(listBean);
	}
	/** 
	* 根据ShareId查询
	*/
	public ShareInfo getShareInfoByShareId(Integer shareId) {
		return this.shareInfoMapper.selectByShareId(shareId);
	}
	/** 
	* 根据ShareId更新
	*/
	public Integer updateShareInfoByShareId(ShareInfo bean, Integer shareId) {
		return this.shareInfoMapper.updateByShareId(bean,shareId);
	}
	/** 
	* 根据ShareId删除
	*/
	public Integer deleteShareInfoByShareId(Integer shareId) {
		return this.shareInfoMapper.deleteByShareId(shareId);
	}
}