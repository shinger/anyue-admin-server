package com.anyue.admin.service;

import com.anyue.admin.vo.BookVO;
import com.anyue.common.dto.Result;

public interface BookService {
    Result createBook(BookVO bookVO);

    Result getBooksPage(long pageNumber, long pageSize);

    Result getBook(String id);

    Result updateBook(BookVO bookVO);
}
