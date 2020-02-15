package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.domain.item.Movie;
import jpabook.jpashop.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ItemServiceTest {

    @Autowired ItemRepository itemRepository;
    @Autowired ItemService itemService;
    
    @Test
    public void saveTest() throws Exception {
        //given
        Book book = new Book();
        book.setAuthor("injun");

        //when
        itemService.savaItem(book);
        
        //then
        assertEquals(book, itemRepository.findOne(book.getId()));
    }
    
    @Test
    public void findByNameTest() throws Exception {
        //given
        Movie movie = new Movie();
        movie.setName("parasite");

        //when
        itemService.savaItem(movie);

        //then
        List<Item> result = itemRepository.findByName("parasite");
        assertEquals(movie, result.get(0));
    }

}