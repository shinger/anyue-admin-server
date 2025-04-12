package com.anyue.admin.service.impl;

import com.anyue.common.entity.Category;
import com.anyue.admin.mapper.CategoryMapper;
import com.anyue.admin.service.CategoryService;
import com.anyue.common.dto.Result;
import com.anyue.common.enums.StateEnum;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public Result getCategories(Integer parentId) {
        List<Category> categories = categoryMapper.selectList(new LambdaQueryWrapper<Category>().eq(Category::getParentId, parentId));
        return Result.success().data(categories);
    }

    @Override
    public Result addCategory(Category category) {
        int insert = categoryMapper.insert(category);
        if (insert > 0) {
            return Result.success();
        } else {
            return Result.error(StateEnum.ADD_FAILED);
        }
    }

    @Override
    public Result getCategory(Integer id) {
        Category category = categoryMapper.selectById(id);
        return Result.success().data(category);
    }

    @Override
    public Result updateCategory(Category category) {
        int i = categoryMapper.updateById(category);
        if (i > 0) {
            return Result.success();
        } else {
            return Result.error(StateEnum.UPDATE_FAILED);
        }
    }
}
