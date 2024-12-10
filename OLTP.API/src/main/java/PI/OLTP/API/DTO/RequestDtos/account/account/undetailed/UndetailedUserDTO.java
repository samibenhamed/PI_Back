package PI.OLTP.API.DTO.RequestDtos.account.account.undetailed;

import PI.OLTP.API.Model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class UndetailedUserDTO extends UndetailedAccountDTO{
    private LocalDate promotion ;
    private List<String> skills ;
    public UndetailedUserDTO toUndetailedUserDTO(User user ){
        this.toUndetailedAccountDTO(user) ;
        this.setDtype("User");
        this.setPromotion(user.getPromotion());
        return this ;
    }
}
