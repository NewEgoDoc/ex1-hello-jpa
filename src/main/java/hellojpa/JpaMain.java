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
            //저장
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

            //조회
            Member findMember = em.find(Member.class, member.getId());
            Team team1 = findMember.getTeam();
            System.out.println("team1 = " + team1.getName());

            //
            Team newTeam = em.find(Team.class, 100L);
            findMember.setTeam(newTeam);

            tx.commit();
        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
