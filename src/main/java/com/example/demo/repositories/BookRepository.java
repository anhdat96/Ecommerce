package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
//    @Query("SELECT u FROM Book u WHERE u.date < now()")
//    List<Book> findAllActiveUsers();

//    @Query("SELECT u.id,u.name FROM User u WHERE u.date < now()")
//    List<Object> specific();
}
