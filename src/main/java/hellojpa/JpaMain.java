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
            Member member1 = new Member();
            member1.setUsername("Hello1");

            Member member2 = new Member();
            member2.setUsername("Hello2");

            em.persist(member1);
            em.persist(member2);

            em.flush();
            em.clear();

            Member m1 = em.find(Member.class, member1.getId());
            System.out.println("m1.getClass() = " + m1.getClass());
            Member reference = em.getReference(Member.class, member1.getId());
            System.out.println("reference.getClass() = " + reference.getClass());
            

            //System.out.println("m2 == m1 : " + (m2.getClass()==m1.getClass());

//            Member findMember = em.getReference(Member.class, member.getId()); //= em.find(Member.class, 1L);
//            System.out.println("findMember = " + findMember);
//            System.out.println("findMember = " + findMember.getId());
//            System.out.println("findMember = " + findMember.getUsername());

            tx.commit();
        } catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();
    }

    private static void printMember(Member member) {
        String username = member.getUsername();
        System.out.println("username = " + username);
    }

    private static void printMemberAndTeam(Member member) {
        String username = member.getUsername();
        System.out.println("username = " + username);
        Team team = member.getTeam();
        System.out.println("team = " + team.getName());
    }
}
