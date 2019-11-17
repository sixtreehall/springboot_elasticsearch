package com.jerry.springboot_elasticsearch.web;

import com.jerry.springboot_elasticsearch.pojo.User;
import com.jerry.springboot_elasticsearch.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRestController {
    @Autowired
    private UserService userService;
    @PostMapping("/user")
    public boolean createUser(@RequestBody User user)
    {
        return userService.insert(user);
    }
    @GetMapping("/user/searchContent")
    public List<User> search(@RequestParam(value = "searchContent")String searchContent)
    {
        return userService.search(searchContent);
    }
    @GetMapping("/user")
    public List<User> searchUser(@RequestParam Integer pageNumber,@RequestParam Integer pageSize,@RequestParam String searchContent)
    {
        return userService.searchUser(pageNumber,pageSize,searchContent);
    }
    @GetMapping("/user2")
    public List<User> searchUserByWeight(@RequestParam String searchContent)
    {
        return userService.searchUserByWeight(searchContent);
    }
}
