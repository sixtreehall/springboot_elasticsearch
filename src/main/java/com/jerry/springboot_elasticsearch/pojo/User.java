package com.jerry.springboot_elasticsearch.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

@Document(indexName = "userindex",type = "user")
@Setter
@Getter
@ToString
public class User implements Serializable {

    private static final long serialVersionUID = -5555520886226463701L;

    private Long id;
    private String name;
    private Integer age;
    private String description;
    private String createtm;

    public User() {
    }

    public User(Long id, String name, Integer age, String description, String createtm) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.description = description;
        this.createtm = createtm;
    }

}
