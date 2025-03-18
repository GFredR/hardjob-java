package org.greenfred.service.impl;

import java.util.Collections;
import java.util.List;
import org.greenfred.entity.po.Category;
import org.greenfred.entity.query.CategoryQuery;
import org.greenfred.entity.vo.PaginationResultVO;
import org.greenfred.enums.CategoryTypeEnum;
import org.greenfred.enums.ResponseCodeEnum;
import org.greenfred.exception.BusinessException;
import org.greenfred.service.CategoryService;
import org.greenfred.utils.StringTools;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.greenfred.mappers.CategoryMapper;
import org.greenfred.enums.PageSize;
import org.greenfred.entity.query.SimplePage;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ Description: Service
 * @ author: 郭丰锐
 * @ date: 2025/02/18
 */
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

	@Resource
	private CategoryMapper<Category,CategoryQuery> categoryMapper;

	/** 
	* 根据条件查询列表
	*/
	public List<Category> findListByParam(CategoryQuery query) {
		return this.categoryMapper.selectList(query);
	}
	/** 
	* 根据条件查询数量
	*/
	public Integer findCountByParam(CategoryQuery query) {
		return this.categoryMapper.selectCount(query);
	}
	/** 
	* 分页查询
	*/
	public PaginationResultVO<Category> findListByPage(CategoryQuery query) {
		Integer count = this.findCountByParam(query);
		Integer pageSize = query.getPageSize() == null ? PageSize.SIZE15.getSize() : query.getPageSize();
		SimplePage page = new SimplePage(query.getPageNo(), count, pageSize);
		query.setSimplePage(page);
		List<Category> list = this.findListByParam(query);
		PaginationResultVO<Category> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), list);
		return result;
	}
	/** 
	* 新增
	*/
	public Integer add(Category bean) {
		return this.categoryMapper.insert(bean);
	}
	/** 
	* 批量新增
	*/
	public Integer addBatch(List<Category> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.categoryMapper.insertBatch(listBean);
	}

	/** 
	* 批量新增或修改
	*/
	public Integer addOrUpdateBatch(List<Category> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.categoryMapper.insertOrUpdateBatch(listBean);
	}
	/** 
	* 根据CategoryId查询
	*/
	public Category getCategoryByCategoryId(Integer categoryId) {
		return this.categoryMapper.selectByCategoryId(categoryId);
	}
	/** 
	* 根据CategoryId更新
	*/
	public Integer updateCategoryByCategoryId(Category bean, Integer categoryId) {
		return this.categoryMapper.updateByCategoryId(bean,categoryId);
	}
	/** 
	* 根据CategoryId删除
	*/
	public Integer deleteCategoryByCategoryId(Integer categoryId) {
		return this.categoryMapper.deleteByCategoryId(categoryId);
	}

	 @Override
	 @Transactional(rollbackFor = Exception.class)
	 public void saveCategory(Category category) {
		if (category.getCategoryId() == null) {
			CategoryQuery dbCategoryQuery = new CategoryQuery();
			Integer count = this.categoryMapper.selectCount(dbCategoryQuery);
//			Integer categoryId = Integer.parseInt(StringTools.getRandomNumber(5));;
//			category.setCategoryId(categoryId);
			category.setSort(count + 1);
			this.categoryMapper.insert(category);
		} else {
			this.categoryMapper.updateByCategoryId(category, category.getCategoryId());
		}

		CategoryQuery categoryQuery = new CategoryQuery();
		categoryQuery.setCategoryName(category.getCategoryName());
		categoryQuery.setType(category.getType());
		Integer count = this.categoryMapper.selectCount(categoryQuery);

		if (count > 1) {
			throw new RuntimeException("分类名称不能重复");
		}

		if (null == category.getCategoryName()) {
			return;
		}
		Category dbInfo = categoryMapper.selectByCategoryId(category.getCategoryId());
		if (!dbInfo.getCategoryName().equals(category.getCategoryName())) {
			categoryMapper.updateCategoryName(category.getCategoryName(), category.getCategoryId());
		}
	 }

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void changeSort(String categoryIds) {
		String[] categoryIdArr = categoryIds.split(",");
		Integer index = 1;
		for (String categoryIdStr : categoryIdArr) {
			Integer categoryId = Integer.parseInt(categoryIdStr);
			Category category = new Category();
			category.setSort(index);
			categoryMapper.updateByCategoryId(category, categoryId);
			index++;
		}
	}

	@Override
	public List<Category> loadAllCategoryByType(Integer type) throws BusinessException {
		CategoryTypeEnum typeEnum = CategoryTypeEnum.getByType(type);
		if (typeEnum == null) {
			throw new BusinessException(ResponseCodeEnum.CODE_600);
		}
		CategoryQuery categoryQuery = new CategoryQuery();
		categoryQuery.setOrderBy("sort asc");
		categoryQuery.setTypes(new Integer[]{typeEnum.getType(), CategoryTypeEnum.QUESTION_EXAM.getType()});

		return this.categoryMapper.selectList(categoryQuery);
	}


}