package PI.OLTP.API.Repository;

import PI.OLTP.API.Model.Job;
import PI.OLTP.API.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<Job,Long> {
    public List<Job> getAllByUser(User user );

}
