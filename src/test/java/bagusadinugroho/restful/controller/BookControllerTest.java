package bagusadinugroho.restful.controller;

import bagusadinugroho.restful.entity.Books;
import bagusadinugroho.restful.model.BookResponse;
import bagusadinugroho.restful.model.CreateBookRequest;
import bagusadinugroho.restful.model.WebResponse;
import bagusadinugroho.restful.repository.BookRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        bookRepository.deleteAll();
    }

    @Test
    void testCreateSuccess() throws Exception {
        CreateBookRequest request = new CreateBookRequest();
        request.setPrice("20000");
        request.setTitle("judultest");
        request.setWritter("penulistest");

        mockMvc.perform(
                post("/books")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<BookResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
                assertEquals("20000", response.getData().getPrice());
                assertEquals("judultest", response.getData().getTitle());
                assertEquals("penulistest", response.getData().getWritter());

                assertTrue(bookRepository.existsById(response.getData().getId()));
        });
    }

//    @Test
//    void testCreateBadRequest() throws Exception {
//        CreateBookRequest request = new CreateBookRequest();
//        request.setPrice("");
//        request.setTitle("");
//        request.setWritter("");
//
//        mockMvc.perform(
//                post("/books")
//                        .accept(MediaType.APPLICATION_JSON)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(request))
//        ).andExpectAll(
//                status().isBadRequest()
//        ).andDo(result -> {
//            WebResponse<BookResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
//            });
//                assertNotNull(response.getErrors());
//        });
//    }

    @Test
    void listBooksSuccess() throws Exception {
        for (int i = 0; i < 5; i++) {
            Books books = new Books();
            books.setId("test" + i);
            books.setPrice("limapoloh");
            books.setTitle("iniTitle");
            books.setWritter("bukansembarangpenulis");
            bookRepository.save(books);
        }

        mockMvc.perform(
                get("/books")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpectAll(
            status().isOk()
        ).andDo(result -> {
            WebResponse<List<BookResponse>> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
                assertNull(response.getErrors());
                assertEquals(5, response.getData().size());
        });
    }

//    @Test
//    void listBoooksNotFound() throws Exception {
//        mockMvc.perform(
//                get("/books")
//                        .accept(MediaType.APPLICATION_JSON)
//                        .contentType(MediaType.APPLICATION_JSON)
//        ).andExpectAll(
//                status().isNotFound()
//        ).andDo(result -> {
//            WebResponse<List<BookResponse>> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
//            });
//            assertNotNull(response.getErrors());
//        });
//    }

    @Test
    void getBooksSuccess() throws Exception {
        Books books = new Books();
        books.setId("test");
        books.setPrice("limapoloh");
        books.setTitle("iniTitle");
        books.setWritter("bukansembarangpenulis");
        bookRepository.save(books);

        mockMvc.perform(
                get("/books/test")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<BookResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertNull(response.getErrors());
            assertEquals(books.getId(), response.getData().getId());
            assertEquals(books.getPrice(), response.getData().getPrice());
            assertEquals(books.getTitle(), response.getData().getTitle());
            assertEquals(books.getWritter(), response.getData().getWritter());
        });
    }
}