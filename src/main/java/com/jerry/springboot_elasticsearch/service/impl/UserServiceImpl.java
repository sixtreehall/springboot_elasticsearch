package com.jerry.springboot_elasticsearch.service.impl;

import com.google.common.collect.Lists;
import com.jerry.springboot_elasticsearch.dao.UserDao;
import com.jerry.springboot_elasticsearch.pojo.User;
import com.jerry.springboot_elasticsearch.service.UserService;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public boolean insert(User user) {
        boolean flag=false;
        try {
            userDao.save(user);
            flag=true;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public List<User> search(String searchContent) {
        QueryStringQueryBuilder builder=new QueryStringQueryBuilder(searchContent);
        System.out.println("查询的语句:"+builder);
        Iterable<User> searchResult=userDao.search(builder);
        List<User> list= Lists.newArrayList(searchResult);
        return list;
    }

    @Override
    public List<User> searchUser(Integer pageNumber, Integer pageSize, String searchContent) {
        Pageable pageable=PageRequest.of(pageNumber,pageSize);
        QueryStringQueryBuilder builder=new QueryStringQueryBuilder(searchContent);
        SearchQuery searchQuery=new NativeSearchQueryBuilder().withPageable(pageable).withQuery(builder).build();
        System.out.println("查询的语句:"+searchQuery.getQuery().toString());
        Page<User> searchPageResults=userDao.search(searchQuery);
        return searchPageResults.getContent();
    }

    @Override
    public List<User> searchByName(Integer pageNumber, Integer pageSize, String name) {
        Page<User> searchPageResults=userDao.findUserByName(name, PageRequest.of(pageNumber,pageSize));
        return searchPageResults.getContent();
    }

    @Override
    public List<User> searchUserByWeight(String searchContent) {
        FunctionScoreQueryBuilder functionScoreQueryBuilder= QueryBuilders.functionScoreQuery(QueryBuilders.matchQuery("name",searchContent));
        System.out.println("查询的语句："+functionScoreQueryBuilder.toString());
        Iterable<User> searchResult=userDao.search(functionScoreQueryBuilder);
        List<User> list=Lists.newArrayList(searchResult);
        return list;
    }
}
