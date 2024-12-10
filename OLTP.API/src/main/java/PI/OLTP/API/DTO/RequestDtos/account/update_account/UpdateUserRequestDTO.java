package PI.OLTP.API.DTO.RequestDtos.account.update_account;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
public class UpdateUserRequestDTO extends  UpdateAccountRequestDTO{
    private LocalDate promotion ;

    private LocalDate graduationDate  ;

    private String bacType ;

    private int satisfactionLevel  ;

}
