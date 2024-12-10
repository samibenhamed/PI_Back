package PI.OLTP.API.Repository;

import PI.OLTP.API.Model.Diploma;
import PI.OLTP.API.Model.Job;
import PI.OLTP.API.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DiplomaRepository extends JpaRepository <Diploma,Long>{
    @Query
    public List<Diploma> getAllByUser(User user );
}
