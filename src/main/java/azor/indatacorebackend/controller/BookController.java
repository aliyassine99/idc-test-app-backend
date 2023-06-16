package azor.indatacorebackend.controller;

import azor.indatacorebackend.dto.BookRequest;
import azor.indatacorebackend.dto.HttpResponse;
import azor.indatacorebackend.service.IBookService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

import static java.util.Map.of;
import static org.springframework.http.HttpStatus.*;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
@RequestMapping("/book")
public class BookController {

    IBookService iBookService;

    public BookController(IBookService iBookService) {
        this.iBookService = iBookService;
    }



    @GetMapping("")
    public ResponseEntity<HttpResponse> getAllBooks(@RequestParam int page,
                                                    @RequestParam int size){

        try {
            Pageable pageable = PageRequest.of(page,size);


            return ResponseEntity.ok().body(
                    HttpResponse.builder()
                            .message("Books")
                            .timeStamp(new Date())
                            .data(of("books", iBookService.getAllBooks(pageable)))
                            .status(OK)
                            .statusCode(OK.value())
                            .build()



            );

        }
        catch (Exception e){
            return ResponseEntity.ok().body(
                    HttpResponse.builder()
                            .message(e.getMessage())
                            .timeStamp(new Date())
                            .status(BAD_REQUEST)
                            .statusCode(BAD_REQUEST.value())
                            .build()



            );
        }

    }


    @PostMapping("/add")
    public ResponseEntity<HttpResponse> addBook(@RequestBody BookRequest bookRequest){
        try {

            return  ResponseEntity.ok().body(
                    HttpResponse.builder()
                            .message("Book added successfully")
                            .data(of("book", iBookService.addNewBook(bookRequest)))
                            .timeStamp(new Date())
                            .status(CREATED)
                            .statusCode(CREATED.value())
                            .build()
            );
        }
        catch (Exception e){
            return ResponseEntity.ok().body(
                    HttpResponse.builder()
                            .message(e.getMessage())
                            .status(BAD_REQUEST)
                            .statusCode(BAD_REQUEST.value())
                            .build()



            );
        }
    }


    @PostMapping("/random/add")
    public ResponseEntity<HttpResponse> addRandomBook(){
        try {

            return  ResponseEntity.ok().body(
                    HttpResponse.builder()
                            .message("Book added successfully")
                            .data(of("book", iBookService.addRandomNewBook()))
                            .timeStamp(new Date())
                            .status(CREATED)
                            .statusCode(CREATED.value())
                            .build()
            );
        }
        catch (Exception e){
            return ResponseEntity.ok().body(
                    HttpResponse.builder()
                            .message(e.getMessage())
                            .status(BAD_REQUEST)
                            .statusCode(BAD_REQUEST.value())
                            .build()



            );
        }
    }

    @PutMapping("{bookId}")
    public ResponseEntity<HttpResponse> editBook(@PathVariable Long bookId,
                                                 @RequestBody BookRequest bookRequest
                                                 ){
        try {

            return  ResponseEntity.ok().body(
                    HttpResponse.builder()
                            .message("Book Edited Successfully")
                            .data(of("book", iBookService.editBook(bookId,bookRequest)))
                            .timeStamp(new Date())
                            .status(CREATED)
                            .statusCode(CREATED.value())
                            .build()
            );
        }
        catch (Exception e){
            return ResponseEntity.ok().body(
                    HttpResponse.builder()
                            .message(e.getMessage())
                            .status(BAD_REQUEST)
                            .statusCode(BAD_REQUEST.value())
                            .build()



            );
        }
    }


    @DeleteMapping("{bookId}")
    public ResponseEntity<HttpResponse> deleteBook(@PathVariable Long bookId){

        try {

            iBookService.deleteBookById(bookId);

            return  ResponseEntity.ok().body(
                    HttpResponse.builder()
                            .message("Book Deleted Successfully")
                            .timeStamp(new Date())
                            .status(ACCEPTED)
                            .statusCode(ACCEPTED.value())
                            .build()
            );
        }
        catch (Exception e){
            return ResponseEntity.ok().body(
                    HttpResponse.builder()
                            .message(e.getMessage())
                            .status(BAD_REQUEST)
                            .statusCode(BAD_REQUEST.value())
                            .build()



            );
        }
    }










}
