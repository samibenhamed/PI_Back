package PI.OLTP.API.DTO.RequestDtos.account.newAccount;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor

public class NewUserDTO extends NewAccountDTO {
    // promotion
    private LocalDate promotion ;
    // graduation_date
    private LocalDate graduationDate  ;

    // bac_type
    private String bacType ;
    // SattisafactionLevel
    private int satisfactionLevel =0 ;
    // Status
    private String status ;
}
