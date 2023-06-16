package azor.indatacorebackend.service;

import azor.indatacorebackend.dto.BookRequest;
import azor.indatacorebackend.dto.BookResponse;
import azor.indatacorebackend.entity.Book;
import azor.indatacorebackend.exceptions.BookNotFoundException;
import azor.indatacorebackend.mapper.BookMapper;
import azor.indatacorebackend.repository.BookRepository;
import com.github.javafaker.Faker;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IBookServiceImpl implements IBookService {

    BookRepository bookRepository;

    BookMapper bookMapper;

    ResourceLoader resourceLoader;
    public IBookServiceImpl(BookRepository bookRepository, BookMapper bookMapper, ResourceLoader resourceLoader) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
        this.resourceLoader = resourceLoader;

    }

    @Override
    public Page<BookResponse> getAllBooks(Pageable pageable) {

        Page<Book> books = bookRepository.findAll(pageable);

        return books.map(bookMapper::toBookResponse);
    }

    @Override
    public BookRequest addNewBook(BookRequest bookRequest) {

        Book newBook = bookMapper.toBook(bookRequest);
        bookRepository.save(newBook);

        return bookMapper.toBookRequest(newBook);
    }

    @Override
    public BookRequest addRandomNewBook() {
        Faker faker = new Faker();

        Book newBook = Book.builder()
                .title(faker.book().title())
                .genre(faker.book().genre())
                .author(faker.book().author())
                .publisher(faker.book().publisher())
                .height(faker.number().numberBetween(20,500)).build();

        bookRepository.save(newBook);
        return bookMapper.toBookRequest(newBook);
    }

    @Override
    public BookRequest editBook(Long id, BookRequest bookRequest) throws BookNotFoundException {
        Optional<Book> existingBook = bookRepository.findById(id);

        Book newBook = bookMapper.toBook(bookRequest);
        if (existingBook.isPresent()){

           Book updatedBook = existingBook.get();
           updatedBook.setTitle(newBook.getTitle());
           updatedBook.setAuthor(newBook.getAuthor());
           updatedBook.setHeight(newBook.getHeight());
           updatedBook.setPublisher(newBook.getPublisher());
           updatedBook.setGenre(newBook.getGenre());

           bookRepository.save(newBook);
           return bookMapper.toBookRequest(newBook);


        }
        else
           throw new BookNotFoundException("Book not found");


    }


    @Override
    public void deleteBookById(Long id) throws BookNotFoundException {


        Optional<Book> book = bookRepository.findById(id);

        if (book.isPresent()) bookRepository.delete(book.get());
        else throw new BookNotFoundException("book not found");

    }



    /*@PostConstruct
    public void init() {
        try {
            Resource resource = resourceLoader.getResource("classpath:data/books.csv");
            InputStream inputStream = resource.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            List<Book> books = new ArrayList<>();

            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");


                Book book = Book.builder()
                        .title(data[0])
                        .author(data[1])
                        .genre(data[2])
                        .height(Integer.parseInt(data[3]))
                        .publisher(data[4])
                        .build();
                books.add(book);




            }

            bufferedReader.close();
            books.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
