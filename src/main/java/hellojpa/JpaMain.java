package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");//이것만 실행되면 오류
        //EntityManagerFactory는 웹서버가 올라오는 시점에 단 하나만 올라오게 된다.

        EntityManager em = emf.createEntityManager();// Ctrl Alt v'
        //application 로딩 시점에 딱 하나만 만들어놔야한다.
        //실제로 논리를 진행하는 transaction 단위에서는 (ex)고객이 들어오고 나가)
        //DB connectino을 얻어서 쿼리를 날리고 작업을 할때마다 이 EntityManager를 만들어주어에한다

        //EntityManager는 사용시마다 계속 생성되고 버려(반환)지게 되는 자원이다.
        //쓰레드 간에 공유X

        //JPA 안에 모든 데이터 변경은 transaction 안에서만 실행한다.

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Member member = em.find(Member.class, 1L);
            System.out.println("member = " + member);

            member.setName("spring");

            em.persist(member);
            //어떻게 이런게 가능할까? jpa를 통해 엔티티를 가져오면 jpa가 관리를 한다
            //jpa가 변경이 되었는지 안되었는지 transaction이 커밋 되는 시점에서 다 체크를 하면서
            //무엇인가 변경 사항이 발견 되면 바꿔서 업데이트 쿼리를 날리게 된다.

            tx.commit();
        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
