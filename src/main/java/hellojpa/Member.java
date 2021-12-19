package hellojpa;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity //이걸 꼭 넣어야한다 - JPA를 사용하겠다 관리해야할 친구임을 알려줌
public class Member {

    @Id
    private Long id;
    private String name;
    //Getter, Setter …

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
