package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();// Ctrl Alt v'

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Address address = new Address("city", "street", "102012");
            Address address2 = new Address("city", "street", "102012");

            System.out.println(address==address2);

            Member member1 = new Member();
            member1.setUsername("member1");
            member1.setHomeAddress(address);

            Member member2 = new Member();
            member2.setUsername("member2");
            member2.setHomeAddress(address);

            em.persist(member1);
            em.persist(member2);

            member1.getHomeAddress().setCity("newCity");

            tx.commit();
        } catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();
    }
}
