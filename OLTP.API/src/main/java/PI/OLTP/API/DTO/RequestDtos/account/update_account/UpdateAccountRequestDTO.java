package PI.OLTP.API.DTO.RequestDtos.account.update_account;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Lob;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class UpdateAccountRequestDTO {

    private String firstName ;
    private String lastName ;
    private String email ;
    private String passWord ;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate ;
    private String sex ;
    private String phone ;

    private String title ;
    private String description ;
    private String linkedinURL ;
    private String personalWesiteURL ;
    private String protfolioURL ;
    private Long origineId  ;

}
