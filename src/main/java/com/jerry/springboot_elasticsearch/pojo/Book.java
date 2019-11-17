package com.jerry.springboot_elasticsearch.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
@Document(indexName = "bookindex",type = "book")
@Setter
@Getter
@ToString
public class Book implements Serializable {
    private static final long serialVersionUID = -3986639635862116354L;
    private Long id;
    private String name;
    private Integer price;
}
