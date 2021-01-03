import entity.Delivery;
import entity.Order;
import entity.OrderItem;
import entity.item.Album;
import entity.item.Book;
import entity.item.Movie;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        try {

            tx.begin();
            test(em);
            tx.commit();

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();

    }

    private static void test(EntityManager em) {

        /* 영속성 전이를 사용하기 전
        Delivery delivery = new Delivery();
        em.persist(delivery);

        OrderItem orderItem1 = new OrderItem();
        OrderItem orderItem2 = new OrderItem();
        em.persist(orderItem1);
        em.persist(orderItem2);

        Order order = new Order();
        order.setDelivery(delivery);
        order.addOrderItem(orderItem1);
        order.addOrderItem(orderItem2);

        em.persist(order);
        */

        // 영속성 전이를 사용한 후
        Delivery delivery = new Delivery();
        OrderItem orderItem1 = new OrderItem();
        OrderItem orderItem2 = new OrderItem();

        Order order = new Order();
        order.setDelivery(delivery);
        order.addOrderItem(orderItem1);
        order.addOrderItem(orderItem2);

        em.persist(order);
    }

}
