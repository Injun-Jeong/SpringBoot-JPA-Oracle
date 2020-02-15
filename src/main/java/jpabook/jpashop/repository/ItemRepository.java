package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    /**
     * register item
     */
    public void save(Item item) {
        if (item.getId() == null) {
            em.persist(item);
        } else {
            em.merge(item);
        }
    }

    /**
     * find a item by id
     */
    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    /**
     * view all item
     */
    public List<Item> findAll() {
        String jpql = "select i from Item i";
        return em.createQuery(jpql, Item.class)
                .getResultList();
    }

    /**
     * find some item by name
     */
    public List<Item> findByName(String itemName) {
        String jpql = "select i from Item i where i.name = :itemName";
        return em.createQuery(jpql, Item.class)
                .setParameter("itemName", itemName)
                .getResultList();
    }
}
