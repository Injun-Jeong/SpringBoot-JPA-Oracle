package jpabook.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("album")
@Getter @Setter
public class Album extends Item {

    @Column(name = "album_artist")
    private String artist;

    @Column(name = "album_etc")
    private String etc;
}
