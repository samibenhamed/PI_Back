package PI.OLTP.API.DTO.ResponseDtos.account;

import PI.OLTP.API.DTO.LocDto;
import PI.OLTP.API.DTO.ResponseDtos.LocationDTO;
import PI.OLTP.API.Model.Account;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAccountResponseDTO {
    private Long accountId ;
    private String firstName ;
    private String lastName ;
    private String email ;
    private String passWord ;
    private String university ;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate ;
    private String sex ;
    private String phone ;
    private String title ;
    private String description ;
    private String linkedinURL ;
    private String personalWesiteURL ;
    private String protfolioURL ;
    private LocDto origine ;

    public   UpdateAccountResponseDTO toDto(Account account ){
        this.setAccountId(account.getId()) ;
        this.setFirstName(account.getFirstName());
        this.setLastName(account.getLastName());
        this.setEmail(account.getEmail());
        this.setPassWord(account.getPassWord());
        this.setUniversity(account.getUniversity());
        this.setBirthDate(account.getBirthDate());
        this.setSex(account.getSex());
        this.setPhone(account.getPhone());
        this.setTitle(account.getTitle());
        this.setDescription(account.getDescription());
        this.setLinkedinURL(account.getLinkedinURL());
        this.setPersonalWesiteURL(account.getPersonalWesiteURL());
        this.setProtfolioURL(account.getProtfolioURL());
        this.setOrigine(new LocDto().toDTO(account.getOrigin()));

        return this  ;
    }
}
