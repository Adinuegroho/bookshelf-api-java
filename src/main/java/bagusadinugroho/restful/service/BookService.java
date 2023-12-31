package bagusadinugroho.restful.service;

import bagusadinugroho.restful.entity.Books;
import bagusadinugroho.restful.model.BookResponse;
import bagusadinugroho.restful.model.CreateBookRequest;
import bagusadinugroho.restful.model.UpdateBookRequest;
import bagusadinugroho.restful.repository.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.awt.print.Book;
import java.util.List;
import java.util.UUID;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ValidationService validationService;

    private BookResponse toBooksResponse(Books books) {
        return BookResponse.builder()
                .id(books.getId())
                .price(books.getPrice())
                .title(books.getTitle())
                .writter(books.getWritter())
                .build();
    }

    @Transactional
    public BookResponse create(CreateBookRequest request) {

        Books books = new Books();
        books.setId(UUID.randomUUID().toString());
        books.setPrice(request.getPrice());
        books.setTitle(request.getTitle());
        books.setWritter(request.getWritter());

        bookRepository.save(books);

        return toBooksResponse(books);
    }

    @Transactional(readOnly = true)
    public List<BookResponse> list(){
        List<Books> bookss = bookRepository.findAll();
        return bookss.stream().map(this::toBooksResponse).toList();
    }

    public BookResponse get(String booksId) {
        Books books = bookRepository.findBooksById(booksId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Books is not found"));

        return toBooksResponse(books);
    }

    @Transactional
    public BookResponse update(UpdateBookRequest request) {
        validationService.validate(request);

        Books books = bookRepository.findBooksById(request.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book is not found"));

        books.setPrice(request.getPrice());
        books.setTitle(request.getTitle());
        books.setWritter(request.getWritter());
        bookRepository.save(books);

        return toBooksResponse(books);
    }

    @Transactional
    public void delete(String bookId) {
        Books books = bookRepository.findBooksById(bookId)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book is not found"));
        bookRepository.delete(books);
    }
}
