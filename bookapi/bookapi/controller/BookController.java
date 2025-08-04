package com.letrasvivas.bookapi.controller;

import com.letrasvivas.bookapi.model.Book;
import com.letrasvivas.bookapi.service.BookService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {
  private final BookService service;
  public BookController(BookService service) {
    this.service = service;
  }

  @GetMapping
  public List<Book> all() {
    return service.getAllBooks();
  }

  @GetMapping("/{id}")
  public Optional<Book> one(@PathVariable Long id) {
    return service.getBookById(id);
  }

  @PostMapping
  public Book create(@RequestBody Book book) {
    return service.createBook(book);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    try {
      service.deleteBook(id);
      return ResponseEntity.noContent().build();
    } catch (EntityNotFoundException e) {
      return ResponseEntity.notFound().build(); // En caso que no exista el id
    }
  }

  @GetMapping("/search")
  public ResponseEntity<List<Book>> search(@RequestParam String title) {
    if (title == null || title.trim().isEmpty()) {
      return ResponseEntity.badRequest().build();
    }
    List<Book> books = service.searchBooks(title);
    if (books.isEmpty()) {
      return ResponseEntity.notFound().build(); //Validacion para la busqueda de libos
                                              // Devolviendo un 404 Not Found en caso que no exista
    }
    return ResponseEntity.ok(books);
  }
}
