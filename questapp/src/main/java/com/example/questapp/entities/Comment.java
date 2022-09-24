package com.example.questapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name="comment")
@Data
public class Comment {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY) //user objesini databaseden hemen çekme, post objesini çektiğimde ilgili userı bana getirmene gerek yok//Çekmesini isteseydi LAZY yerine EAGER olacaktı
    @JoinColumn(name="post_id",nullable=false)
    @OnDelete(action= OnDeleteAction.CASCADE)
    @JsonIgnore //fetch de etmeyeceğiz zaten lazy, bu alanla işimiz yok diyoruz
    Post post;

    @ManyToOne(fetch = FetchType.LAZY) //user objesini databaseden hemen çekme, post objesini çektiğimde ilgili userı bana getirmene gerek yok//Çekmesini isteseydi LAZY yerine EAGER olacaktı
    @JoinColumn(name="user_id",nullable=false)
    @OnDelete(action= OnDeleteAction.CASCADE)
    @JsonIgnore //fetch de etmeyeceğiz zaten lazy, bu alanla işimiz yok diyoruz
    User user;

    @Lob
    @Column(columnDefinition="text")
    String text;
}
