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

            //비영속
            Member member = new Member();
            member.setName("JPAHello");
            member.setId(101L);

            //영속
            System.out.println("=== Before ===");
            em.persist(member);
            System.out.println("=== After ===");

            Member findMember = em.find(Member.class, 101L);
            System.out.println("findMember.name = " + findMember.getName());
            System.out.println("findMember.id = " + findMember.getId());
            tx.commit();
        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
