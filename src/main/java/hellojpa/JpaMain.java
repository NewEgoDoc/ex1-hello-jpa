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

        Member member = new Member();
        member.setId(1L);
        member.setName("Hello");//안됨...
        //jpa에서는 transaction이 중요!

        em.persist(member);

        tx.commit();
        
        em.close();

        emf.close();
    }
}
