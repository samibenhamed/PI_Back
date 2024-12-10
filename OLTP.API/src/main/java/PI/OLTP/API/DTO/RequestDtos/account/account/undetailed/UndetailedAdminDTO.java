package PI.OLTP.API.DTO.RequestDtos.account.account.undetailed;

import PI.OLTP.API.Model.Admin;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UndetailedAdminDTO extends  UndetailedAccountDTO {
    public UndetailedAdminDTO toUndetailedAdminDTO(Admin admin){
        this.toUndetailedAccountDTO(admin) ;
        this.setDtype("Admin");
        return this ;
    }
}
