package PI.OLTP.API.Repository;

import PI.OLTP.API.Model.Skill;
import PI.OLTP.API.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SkillRepository extends JpaRepository<Skill , Long > {
    List<Skill> findAllByUser(User user) ;
}
