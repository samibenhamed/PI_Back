package PI.OLTP.API.DTO.RequestDtos.account.account.personal;

import PI.OLTP.API.DTO.ResponseDtos.LocationDTO;
import PI.OLTP.API.DTO.ImageDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    private String dtype ;

    private long id ;

    private String firstName ;

    private String lastName ;

    private String email ;

    private String passWord ;

    private String university ;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate ;

    private String sex ;

    private String phone ;


    private ImageDTO image ;

    private String title ;

    private String description ;

    private String linkedinURL ;
    private String personalWesiteURL ;
    private String protfolioURL ;

    private LocationDTO origine ;

}
