package com.anyue.admin.mapper;

import com.anyue.common.entity.Book;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BookMapperTest {

    @Autowired
    private BookMapper bookMapper;

    @Test
    public void pageTest() {
        Page<Book> page = new Page<>();
        page.setCurrent(1);
        IPage<Book> bookIPage = bookMapper.selectPage(page);
        System.out.println(bookIPage.getRecords());
    }

}
