package PI.OLTP.API.Repository;

import PI.OLTP.API.Model.Account;
import PI.OLTP.API.Model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ImageRepository  extends JpaRepository<Image , Long> {

    @Query
    Image getImageByAccount(Account account ) ;
}
