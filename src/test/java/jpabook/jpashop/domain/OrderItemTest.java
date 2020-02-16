package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class OrderItemTest {

    @Autowired EntityManager em;

    @Test
    public void createOrderItemTest() throws Exception {
        //given
        Book book = createBook("good book", 10000, 10);

        int orderCount = 2;

        //when
        OrderItem orderItem = OrderItem.createOrderItem(book, book.getPrice(), orderCount);
        em.persist(orderItem);

        //then
        OrderItem getOrderItem = em.find(OrderItem.class, orderItem.getId());

        assertEquals(orderCount, getOrderItem.getCount());
        assertEquals(10 - orderCount, book.getStockQuantity());
        assertEquals(book, getOrderItem.getItem());
        assertEquals(book.getPrice(), getOrderItem.getOrderPrice());
    }

    @Test
    public void cancelTest() throws Exception {
        //given
        Book book = createBook("good book", 10000, 10);

        int orderCount = 2;

        OrderItem orderItem = OrderItem.createOrderItem(book, book.getPrice(), orderCount);
        em.persist(orderItem);

        //when
        orderItem.cancel();

        //then
        OrderItem getOrderItem = em.find(OrderItem.class, orderItem.getId());

        assertEquals(10, getOrderItem.getItem().getStockQuantity());
    }

    @Test
    public void getTotalPriceTest() throws Exception {
        //given
        Book book = createBook("good book", 10000, 10);

        int orderCount = 2;

        OrderItem orderItem = OrderItem.createOrderItem(book, book.getPrice(), orderCount);
        em.persist(orderItem);

        //when
        int totalPrice = orderItem.getTotalPrice();

        //then
        assertEquals(book.getPrice() * orderCount, totalPrice);
    }

    private Book createBook(String name, int price, int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

}