package bagusadinugroho.restful.controller;

import bagusadinugroho.restful.model.BookResponse;
import bagusadinugroho.restful.model.CreateBookRequest;
import bagusadinugroho.restful.model.WebResponse;
import bagusadinugroho.restful.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
