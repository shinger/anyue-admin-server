package com.anyue.admin.service.impl;

import com.anyue.common.entity.Category;
import com.anyue.admin.mapper.BookMapper;
import com.anyue.admin.mapper.CategoryMapper;
import com.anyue.admin.mapper.FileMapper;
import com.anyue.admin.service.BookService;
import com.anyue.admin.vo.BookVO;
import com.anyue.common.dto.Result;
import com.anyue.common.entity.Book;
import com.anyue.common.entity.BookFile;
import com.anyue.common.enums.StateEnum;
import com.anyue.common.exception.BizException;
import com.anyue.common.utils.CommonUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private FileMapper fileMapper;

    @Override
    public Result createBook(BookVO bookVO) {
        Book book = bookVO2Book(bookVO);

        bookMapper.insert(book);
        return Result.success().message("创建成功");
    }

    @Override
    public Result getBooksPage(long pageNumber, long pageSize) {
        Page<Book> page = new Page<>(pageNumber, pageSize);
        IPage<Book> bookIPage = bookMapper.selectPage(page);

        return Result.success().data(bookIPage);
    }

    @Override
    public Result getBook(String id) {
        Book book = bookMapper.selectById(id);
        if (book == null) {
            return Result.error("书本不存在");
        }
        return Result.success().data(book);
    }

    @Override
    public Result updateBook(BookVO bookVO) {
        Book book = bookVO2Book(bookVO);
        int i = bookMapper.updateById(book);
        if (i > 0) {
            return Result.success().message("修改成功");
        } else {
            return Result.error(StateEnum.UPDATE_FAILED);
        }

    }

    /**
     * 将BookVO对象转换为存储对象Book
     * @param bookVO
     * @return
     */
    private Book bookVO2Book(BookVO bookVO) {
        if (bookVO.getFileId() == null) {
            throw new BizException("未上传电子书文件");
        }
        // 分割时间
        String[] pressDateSplit = bookVO.getPressDate().split("-");
        // 获取主分类
        Integer categoryId = bookVO.getCategoryId();
        Category subCategory = categoryMapper.selectById(categoryId);
        Category mainCategory = categoryMapper.selectById(subCategory.getParentId());
        // 获取 path
        BookFile bookFile = fileMapper.selectById(bookVO.getFileId());

        String bookId = null;
        if (StringUtils.isBlank(bookVO.getBookId())) {
            bookId = CommonUtil.generateRandomID(16);
        } else {
            bookId = bookVO.getBookId();
        }


        Book book = Book.builder()
                .id(bookId)
                .title(bookVO.getTitle())
                .author(bookVO.getAuthor())
                .introduction(bookVO.getIntroduction())
                .press(bookVO.getPress())
                .pressYear(Integer.valueOf(pressDateSplit[0]))
                .pressMonth(Integer.valueOf(pressDateSplit[1]))
                .categoryId(bookVO.getCategoryId())
                .mainCategory(mainCategory.getCategoryName())
                .subCategory(subCategory.getCategoryName())
                .wordCount(bookVO.getWordCount())
                .readership(0)
                .status(bookVO.getStatus())
                .cover(bookVO.getCoverImg())
                .path(bookFile.getPath())
                .fileId(bookVO.getFileId())
                .build();

        return book;
    }


}
