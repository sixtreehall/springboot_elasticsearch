package com.jerry.springboot_elasticsearch.service;

import com.jerry.springboot_elasticsearch.pojo.User;
import java.util.List;

public interface UserService {
    boolean insert(User user);
    List<User> search(String searchContent);
    List<User> searchByName(Integer pageNumber,Integer pageSize,String name);
    List<User> searchUser(Integer pageNumber,Integer pageSize,String searchContent);
    List<User> searchUserByWeight(String searchContent);
}
