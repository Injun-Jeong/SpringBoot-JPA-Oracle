package jpabook.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("book")
@Getter @Setter
public class Book extends Item {

    @Column(name = "book_author")
    private String author;

    @Column(name = "book_isbn")
    private String isbn;
}