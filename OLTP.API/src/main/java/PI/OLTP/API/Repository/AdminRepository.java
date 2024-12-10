package PI.OLTP.API.Repository;


import PI.OLTP.API.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Long> {
}
