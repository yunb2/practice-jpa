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

        Album album = new Album("album", 20000, 1000, "yun", "...");
        em.persist(album);

        Book book = new Book("book", 15000, 2000, "yun", "123abc789");
        em.persist(book);

        Movie movie = new Movie("movie", 10000, 500, "yun", "yun");
        em.persist(movie);

    }

}
