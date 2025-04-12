package com.anyue.admin.service;


import com.anyue.common.entity.Category;
import com.anyue.common.dto.Result;

public interface CategoryService {
    Result getCategories(Integer parentId);

    Result addCategory(Category category);

    Result getCategory(Integer id);

    Result updateCategory(Category category);
}
