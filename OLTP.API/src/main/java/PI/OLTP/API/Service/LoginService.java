package PI.OLTP.API.Service;

import PI.OLTP.API.DTO.RequestDtos.LoginRequestDTO;
import PI.OLTP.API.DTO.ResponseDtos.LoginResponseDTO;
import PI.OLTP.API.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {

    private final AccountRepository accountRepository ;
    @Autowired
    public LoginService(AccountRepository accountRepository){this.accountRepository=accountRepository;}

     public LoginResponseDTO checkLogin(LoginRequestDTO data ){
         List<Object[]> result = accountRepository.checkLogin(data.getEmail(), data.getPassWord()) ;
         LoginResponseDTO response = new LoginResponseDTO() ;
         if(result.size()==1){
             response.setDtype(result.get(0)[0].toString() );
             response.setId( Long.parseLong(result.get(0)[1].toString()));
         }
        return  response ;
     }
}
