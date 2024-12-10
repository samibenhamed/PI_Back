package PI.OLTP.API.Repository;

import PI.OLTP.API.Model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account,Long> {
    @Query(
            value =  "SELECT dtype , id from account where email= ?1 and pass_word=?2",
            nativeQuery = true)
    List<Object[]> checkLogin(String email , String passWord );

    @Query
    Account findAccountByEmail(String email);

    @Query
    Account findAccountByPassWord(String passWord ) ;

//    @Query(value = "delete  from account where id=?" , nativeQuery = true)
//    void removeAccount(Long id) ;

}
