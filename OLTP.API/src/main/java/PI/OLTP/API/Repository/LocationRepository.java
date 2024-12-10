package PI.OLTP.API.Repository;

import PI.OLTP.API.Model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location,Long> {
}
