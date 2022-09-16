package com.example.questapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name="post")
@Data
public class Post {

    @Id
    Long id;

    @ManyToOne(fetch = FetchType.LAZY) //user objesini databaseden hemen çekme, post objesini çektiğimde ilgili userı bana getirmene gerek yok//Çekmesini isteseydi LAZY yerine EAGER olacaktı
    @JoinColumn(name="user_id",nullable=false)
    @OnDelete(action= OnDeleteAction.CASCADE)
    @JsonIgnore //fetch de etmeyeceğiz zaten lazy, bu alanla işimiz yok diyoruz
    User user;

    String title;
    @Lob //hibernate in Stringi text olarak algılaması için böyle yoksa varchar(255) alıyor
    @Column(columnDefinition="text")
    String text;

}
