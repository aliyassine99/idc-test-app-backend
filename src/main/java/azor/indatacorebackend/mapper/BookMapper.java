package azor.indatacorebackend.mapper;

import azor.indatacorebackend.dto.BookRequest;
import azor.indatacorebackend.dto.BookResponse;
import azor.indatacorebackend.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {


    public BookRequest toBookRequest(Book book) {
        return BookRequest.builder()
                .title(book.getTitle())
                .author(book.getAuthor())
                .genre(book.getGenre())
                .height(book.getHeight())
                .publisher(book.getPublisher())
                .build();
    }

    public  Book toBook(BookRequest bookRequest) {
        return Book.builder()
                .title(bookRequest.getTitle())
                .author(bookRequest.getAuthor())
                .genre(bookRequest.getGenre())
                .height(bookRequest.getHeight())
                .publisher(bookRequest.getPublisher())
                .build();
    }

    public BookResponse toBookResponse(Book book) {
        return BookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .genre(book.getGenre())
                .height(book.getHeight())
                .publisher(book.getPublisher())
                .build();
    }

    public  Book toBook(BookResponse bookResponse) {
        return Book.builder()
                .id(bookResponse.getId())
                .title(bookResponse.getTitle())
                .author(bookResponse.getAuthor())
                .genre(bookResponse.getGenre())
                .height(bookResponse.getHeight())
                .publisher(bookResponse.getPublisher())
                .build();
    }
}
