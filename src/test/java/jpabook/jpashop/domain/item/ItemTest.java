package jpabook.jpashop.domain.item;

import jpabook.jpashop.exception.NotEnoughStockException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ItemTest {

    @Autowired EntityManager em;

    @Test
    public void removeStockTest() throws Exception {
        //given
        Book book = createBook(10);

        int quantity = 2;

        //when
        book.removeStock(quantity);

        //then
        assertEquals(8, em.find(Book.class, book.getId()).getStockQuantity());
    }

    @Test
    public void removeOverStockTest() throws Exception {
        //given
        Book book = createBook(10);

        int quantity = 11;

        //then
        assertThrows(NotEnoughStockException.class, () -> {
            book.removeStock(quantity);
        });
    }

    private Book createBook(int stockQuantity) {
        Book book = new Book();
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

}