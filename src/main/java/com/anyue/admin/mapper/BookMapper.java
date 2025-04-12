package com.anyue.admin.mapper;

import com.anyue.common.entity.Book;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookMapper extends BaseMapper<Book> {
    IPage<Book> selectPage(IPage<?> page);
}
