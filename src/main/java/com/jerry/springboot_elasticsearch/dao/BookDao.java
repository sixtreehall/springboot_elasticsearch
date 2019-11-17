package com.jerry.springboot_elasticsearch.dao;

import com.jerry.springboot_elasticsearch.pojo.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface BookDao extends ElasticsearchRepository<Book,String> {
    List<Book> findByNameAndPrice(String name,Integer price);
    List<Book> findByNameOrPrice(String name,Integer price);
    Page<Book> findByNameNot(String name, Pageable page);
    Page<Book> findByPriceBetween(int price,Pageable page);
    Page<Book> findByNameLike(String name,Pageable page);
    @Query("{\"bool\":{\"must\":{\"term\":{\"message\":\"?0\"}}}}")
    Page<Book> findByMessage(String message,Pageable pageable);
}
