package org.greenfred.controller;

import java.util.List;

import org.greenfred.annotation.GlobalInterceptor;
import org.greenfred.annotation.VerifyParam;
import org.greenfred.entity.po.Category;
import org.greenfred.entity.query.CategoryQuery;
import org.greenfred.entity.vo.PaginationResultVO;

import org.greenfred.enums.CategoryTypeEnum;
import org.greenfred.enums.PermissionCodeEnum;
import org.greenfred.enums.ResponseCodeEnum;
import org.greenfred.exception.BusinessException;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import javax.annotation.Resource;
import org.greenfred.service.CategoryService;
import org.greenfred.controller.BaseController;
import org.greenfred.entity.vo.ResponseVO;
 /**
 * @ Description: Controller
 * @ author: 郭丰锐
 * @ date: 2025/02/18
 */
@RestController
@RequestMapping("category")
public class CategoryController extends BaseController{

	@Resource
	private CategoryService categoryService;

	/** 
	* 加载列表
	*/
	@RequestMapping("/loadAllCategory")
	@GlobalInterceptor(permissionCode = PermissionCodeEnum.CATEGORY_LIST)
	public ResponseVO loadAllCategory(CategoryQuery query) {
		query.setOrderBy("sort asc");
		return getSuccessResponseVO(categoryService.findListByParam(query));
	}

	 @RequestMapping("/saveCategory")
	 @GlobalInterceptor(permissionCode = PermissionCodeEnum.CATEGORY_EDIT)
	 public ResponseVO saveCategory(Category category) {
		 categoryService.saveCategory(category);
		 return getSuccessResponseVO(null);
	 }

	 @RequestMapping("/delCategory")
	 @GlobalInterceptor(permissionCode = PermissionCodeEnum.CATEGORY_DEL)
	 public ResponseVO delCategory(@VerifyParam(required = true) Integer categoryId) {
		 categoryService.deleteCategoryByCategoryId(categoryId);
		 return getSuccessResponseVO(null);
	 }

	 @RequestMapping("/changeSort")
	 @GlobalInterceptor(permissionCode = PermissionCodeEnum.CATEGORY_EDIT)
	 public ResponseVO changeSort(@VerifyParam(required = true) String categoryIds) {
		 categoryService.changeSort(categoryIds);
		 return getSuccessResponseVO(null);
	 }

	 @RequestMapping("/loadAllCategoryByType")
	 @GlobalInterceptor(permissionCode = PermissionCodeEnum.CATEGORY_LIST)
	 public ResponseVO loadAllCategoryByType(@VerifyParam(required = true) Integer type) throws BusinessException {

		 List<Category> list = categoryService.loadAllCategoryByType(type);
		 return getSuccessResponseVO(list);
	 }


}