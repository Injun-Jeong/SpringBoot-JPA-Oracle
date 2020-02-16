package jpabook.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("movie")
@Getter @Setter
public class Movie extends Item {

    @Column(name = "movie_director")
    private String director;

    @Column(name = "movie_actor")
    private String actor;
}