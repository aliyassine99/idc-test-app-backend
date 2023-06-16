package azor.indatacorebackend.service;

import azor.indatacorebackend.dto.BookRequest;
import azor.indatacorebackend.dto.BookResponse;
import azor.indatacorebackend.exceptions.BookNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBookService {

    Page<BookResponse> getAllBooks(Pageable pageable);
    BookRequest addNewBook(BookRequest bookRequest);
    BookRequest addRandomNewBook();

    BookRequest editBook(Long id, BookRequest bookRequest) throws BookNotFoundException;


    void deleteBookById(Long id) throws BookNotFoundException;



}
