package bagusadinugroho.restful.service;

import bagusadinugroho.restful.entity.Books;
import bagusadinugroho.restful.model.BookResponse;
import bagusadinugroho.restful.model.CreateBookRequest;
import bagusadinugroho.restful.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Transactional
    public BookResponse create(CreateBookRequest request) {

        Books books = new Books();
        books.setId(UUID.randomUUID().toString());
        books.setPrice(request.getPrice());
        books.setTitle(request.getTitle());
        books.setWritter(request.getWritter());

        bookRepository.save(books);

        return BookResponse.builder()
                .id(books.getId())
                .price(books.getPrice())
                .title(books.getTitle())
                .writter(books.getWritter())
                .build();
    }

}
