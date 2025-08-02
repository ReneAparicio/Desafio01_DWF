package com.letrasvivas.bookapi.controller;

import com.letrasvivas.bookapi.model.Book;
import com.letrasvivas.bookapi.service.BookService;
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
  public void delete(@PathVariable Long id) {
    service.deleteBook(id);
  }

  @GetMapping("/search")
  public List<Book> search(@RequestParam String title) {
    return service.searchBooks(title);
  }
}


