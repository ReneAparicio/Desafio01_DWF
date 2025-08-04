package com.letrasvivas.bookapi.service;

import com.letrasvivas.bookapi.model.Book;
import com.letrasvivas.bookapi.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
  private final BookRepository repo;
  public BookService(BookRepository repo) {
    this.repo = repo;
  }

  public List<Book> getAllBooks() {
    return repo.findAll();
  }
  public Optional<Book> getBookById(Long id) {
    return repo.findById(id);
  }
  public Book createBook(Book book) {
    return repo.save(book);
  }
  public void deleteBook(Long id) {
    if (!repo.existsById(id)){ //Validación si el libro existe
      throw new EntityNotFoundException("Book not found with id: " + id);
    }
    repo.deleteById(id);
    }
  public List<Book> searchBooks(String title) {
    return repo.findByTitleContainingIgnoreCase(title);
  }
}


