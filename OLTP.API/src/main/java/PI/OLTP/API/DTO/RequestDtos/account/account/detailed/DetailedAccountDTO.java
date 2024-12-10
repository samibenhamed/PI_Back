package PI.OLTP.API.DTO.RequestDtos.account.account.detailed;

import PI.OLTP.API.DTO.ResponseDtos.LocationDTO;
import PI.OLTP.API.DTO.ImageDTO;
import PI.OLTP.API.Model.Account;
import PI.OLTP.API.Model.Image;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public   class DetailedAccountDTO {
    private String dtype ;
    private long id ;
    private String firstName ;
    private String lastName ;
    private String email ;
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
    private LocationDTO origine ;

    private ImageDTO image ;

    //
    public  DetailedAccountDTO toDetailedAccountDTO (Account account  , Image image ){
        this.setId(account.getId());
        this.setFirstName(account.getFirstName());
        this.setLastName(account.getLastName());
        this.setEmail(account.getEmail());
        this.setUniversity(account.getUniversity());
        this.setBirthDate(account.getBirthDate());
        this.setSex(account.getSex());
        this.setPhone(account.getPhone());
        this.setTitle(account.getTitle());
        this.setDescription(account.getDescription());
        this.setLinkedinURL(account.getLinkedinURL());
        this.setPersonalWesiteURL(account.getPersonalWesiteURL());
        this.setProtfolioURL(account.getProtfolioURL());
        this.image.toDTO(image);
        return this ;
    }


}
