package com.jerry.springboot_elasticsearch.dao;

import com.jerry.springboot_elasticsearch.pojo.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface UserDao extends ElasticsearchRepository<User,Long> {
    Page<User> findUserByName(String name, Pageable pageable);
}
