package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");//이것만 실행되면 오류

        EntityManager em = emf.createEntityManager();// Ctrl Alt v'
        //application 로딩 시점에 딱 하나만 만들어놔야한다.
        //실제로 논리를 진행하는 transaction 단위에서는 (ex)고객이 들어오고 나가)
        //DB connectino을 얻어서 쿼리를 날리고 작업을 할때마다 이 EntityManager를 만들어주어에한다
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Member member = em.find(Member.class, 1L);
            System.out.println("member = " + member);

            member.setName("spring");

            em.persist(member);


            tx.commit();
        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
