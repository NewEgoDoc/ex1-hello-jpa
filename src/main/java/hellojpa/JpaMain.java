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

            Member member = new Member();
            member.setUsername("member1");
            member.setHomeAddress(new Address("city1","street","1") );

            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("피자");
            member.getFavoriteFoods().add("족발");



            em.persist(member);
            
            em.flush();
            em.clear();

            Member findMember = em.find(Member.class, member.getId());


            //findMember.setHomeAddress(new Address("newCity", a.get)); 이렇게 통째로 바꿔야 한다는 것을 잊지 말자!

            findMember.getFavoriteFoods().remove("치킨");
            findMember.getFavoriteFoods().add("한식식");

            findMember.getAddressHistory().remove(new AddressEntity("old1","street","1"));
            /*이래서 equal hashcode가 완벽하게 구현되어야한다!*/
            findMember.getAddressHistory().add(new AddressEntity("newCity1","street","1"));


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
