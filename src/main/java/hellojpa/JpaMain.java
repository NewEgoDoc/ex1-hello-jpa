package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");//이것만 실행되면 오류
        //EntityManagerFactory는 웹서버가 올라오는 시점에 단 하나만 올라오게 된다.

        EntityManager em = emf.createEntityManager();// Ctrl Alt v'

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {

            Movie movie = new Movie();
            movie.setDirector("aaaa");
            movie.setActor("bbbb");
            movie.setName("바람과 함께 사라지다");
            movie.setPrice(100000);

            em.persist(movie);

            em.flush();
            em.clear();
            Movie findMovie = em.find(Movie.class, movie.getId());
            System.out.println("movie1 = " + findMovie.getName());
            tx.commit();
        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
