package com.anyue.admin.controller;

import com.anyue.admin.service.BookService;
import com.anyue.admin.vo.BookVO;
import com.anyue.common.dto.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {

     @Autowired
     private BookService bookService;

     @PostMapping
     public Result createBook(@RequestBody BookVO bookVO) {
         return bookService.createBook(bookVO);
     }

     @GetMapping("/list")
     public Result getBooks(@RequestParam long pageNumber, @RequestParam long pageSize) {
         return bookService.getBooksPage(pageNumber, pageSize);
     }

     @GetMapping("/{id}")
     public Result getBook(@PathVariable("id") String id) {
        return bookService.getBook(id);
     }

     @PutMapping()
     public Result updateBook(@RequestBody BookVO bookVO) {
        return bookService.updateBook(bookVO);
     }


}
