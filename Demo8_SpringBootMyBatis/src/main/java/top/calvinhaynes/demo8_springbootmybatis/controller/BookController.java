package top.calvinhaynes.demo8_springbootmybatis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.calvinhaynes.demo8_springbootmybatis.pojo.Books;
import top.calvinhaynes.demo8_springbootmybatis.service.BookService;

import java.util.List;

/**
 * 本控制器
 *
 * @author CalvinHaynes
 * @date 2021 /09/16
 */
@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    /**
     * Instantiates a new Book controller.
     *
     * @param bookService the book service
     */
    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * List list.
     *
     * @return the list
     */
    @GetMapping("/allBook")
    public List<Books> list() {
        List<Books> booksList = bookService.selectAllBooks();
        for (Books books : booksList) {
            System.out.println(books);
        }
        return booksList;
    }

    /**
     * Add book string.
     *
     * @return the string
     */
    @GetMapping("/addBook")
    public String addBook() {
        bookService.addBook(new Books("4", "计算机网络：自顶向下方法", 10, "从入门到起飞"));
        return "添加成功";
    }

    /**
     * Update book string.
     *
     * @return the string
     */
    @GetMapping("/updateBook")
    public String updateBook() {
        bookService.updateBookById(new Books("4", "计算机网络：自顶向下方法", 100, "从入门到起飞"));
        return "更新成功";
    }

    /**
     * 用Postman发Post请求
     *
     * @param id id
     * @return {@link String}
     */
    @PostMapping("/del/{bookId}")
    public String deleteBook(@PathVariable("bookId") int id) {
        bookService.deleteBookById(id);
        return "删除成功";
    }

    /**
     * Query book by name books.
     *
     * @return the books
     */
    @GetMapping("/query/{name}")
    public Books queryBookByName(@PathVariable("name") String bookName) {
        Books book = bookService.queryBookByName(bookName);
        System.out.println(book);
        return book;
    }
}