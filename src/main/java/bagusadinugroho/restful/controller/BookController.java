package bagusadinugroho.restful.controller;

import bagusadinugroho.restful.entity.Books;
import bagusadinugroho.restful.model.BookResponse;
import bagusadinugroho.restful.model.CreateBookRequest;
import bagusadinugroho.restful.model.WebResponse;
import bagusadinugroho.restful.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping(
            path = "/books",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<BookResponse> create(@RequestBody CreateBookRequest request) {
        BookResponse bookResponse = bookService.create(request);
        return WebResponse.<BookResponse>builder().data(bookResponse).build();
    }

    @GetMapping(
            path = "/books",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<BookResponse>> list(Books books) {
        List<BookResponse> bookResponses = bookService.list();
        return WebResponse.<List<BookResponse>>builder().data(bookResponses).build();
    }

    @GetMapping(
            path = "/books/{bookId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<BookResponse> get(@PathVariable("bookId") String bookId) {
        BookResponse bookResponse = bookService.get(bookId);
        return WebResponse.<BookResponse>builder().data(bookResponse).build();
    }
}
