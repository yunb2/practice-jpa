import entity.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

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

        // CATEGORY 추가
        Category phone = new Category("PHONE");
        em.persist(phone);

        // ITEM 추가
        Item iphone = new Item("iPhone", 20000, 10);
        Item galaxy = new Item("Galaxy", 10000, 20);
        em.persist(iphone);
        em.persist(galaxy);
        phone.addItem(iphone);
        phone.addItem(galaxy);

        // MEMBER 추가
        Member member = new Member("yunb", "seoul", "guro", "123");
        em.persist(member);

        String beforeOrder = phone.toString();

        // ORDER 생성
        Order order = new Order(member);
        em.persist(order);

        // DELIVERY 생성
        Delivery delivery = new Delivery(member);
        order.setDelivery(delivery);
        em.persist(delivery);

        // ORDER_ITEM 추가
        OrderItem orderItem1 = new OrderItem(iphone, 9);
        OrderItem orderItem2 = new OrderItem(galaxy, 17);
        order.addOrderItem(orderItem1);
        order.addOrderItem(orderItem2);
        em.persist(orderItem1);
        em.persist(orderItem2);

        String afterOrder = phone.toString();

        String jpql = "select o from Order o join o.member m where m.id = :memberId";
        List<Order> orders = em.createQuery(jpql, Order.class)
                .setParameter("memberId", member.getId())
                .getResultList();

        System.out.println("===== 주문 전 =====");
        System.out.println(beforeOrder);
        System.out.println("===== 주문 후 =====");
        System.out.println(afterOrder);
        System.out.println("===== 주문 내역 =====");
        for(Order o : orders) {
            System.out.println(o.toString());
            for(OrderItem oi : o.getOrderItems()) {
                System.out.println(oi.toString());
            }
        }

    }

}
