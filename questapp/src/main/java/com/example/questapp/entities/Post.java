package com.example.questapp.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="post")
@Data
public class Post {

    @Id
    Long id;
    Long userId;
    String title;
    @Lob //hibernate in Stringi text olarak algılaması için böyle yoksa varchar(255) alıyor
    @Column(columnDefinition="text")
    String text;

}
