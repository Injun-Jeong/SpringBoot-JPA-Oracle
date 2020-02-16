package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderTest {

    @Autowired
    EntityManager em;
    @Autowired
    OrderRepository orderRepository;

    @Test
    public void createOrderTest() throws Exception {
        //given
        Member member = createMember("injun", "daegu", "11st", "11111");
        Book book = createBook("good book", 10000, 10);

        int orderCount = 2;

        OrderItem orderItem = OrderItem.createOrderItem(book, book.getPrice(), orderCount);

        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        //when
        Order order = Order.createOrder(member, delivery, orderItem);
        em.persist(order);

        //then
        Order getOrder = em.find(Order.class, order.getId());

        assertEquals(order, getOrder);
        assertEquals(member, getOrder.getMember());
        assertEquals(delivery, getOrder.getDelivery());
        assertEquals(1, getOrder.getOrderItems().size());
        assertEquals(OrderStatus.ORDER, getOrder.getStatus());
    }

    @Test
    public void cancelTest() throws Exception {
        //given
        Member member = createMember("injun", "daegu", "11st", "11111");
        Book book = createBook("good book", 10000, 10);

        int orderCount = 2;

        OrderItem orderItem = OrderItem.createOrderItem(book, book.getPrice(), orderCount);

        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        Order order = Order.createOrder(member, delivery, orderItem);
        em.persist(order);

        //when
        order.cancel();

        //then
        Order getOrder = em.find(Order.class, order.getId());

        assertEquals(OrderStatus.CANCEL, order.getStatus());
    }

    @Test
    public void getTotalPriceTest() throws Exception {
        //given
        Member member = createMember("injun", "daegu", "11st", "11111");
        Book book = createBook("good book", 10000, 10);

        int orderCount = 2;

        OrderItem orderItem = OrderItem.createOrderItem(book, book.getPrice(), orderCount);

        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        //when
        Order order = Order.createOrder(member, delivery, orderItem);
        em.persist(order);

        //then
        Order getOrder = em.find(Order.class, order.getId());

        assertEquals(book.getPrice() * orderCount, getOrder.getTotalPrice());
    }

    private Book createBook(String name, int price, int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

    private Member createMember(String name, String city, String street, String zipcode) {
        Member member = new Member();
        member.setName(name);
        member.setAddress(new Address(city, street, zipcode));
        em.persist(member);
        return member;
    }

}