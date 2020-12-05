package com.example.demo.service.impl;

import com.example.demo.exceptions.BookNotFoundException;
import com.example.demo.repositories.BookRepository;
import com.example.demo.service.BookManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookManagerImpl implements BookManager {
    @Autowired
    BookRepository bookRepository;

    @Override
    public void borrow(long id) {
        bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
    }
}
