package PI.OLTP.API.DTO.ResponseDtos.account;

import PI.OLTP.API.Model.Admin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class UpdateAdminResponseDTO extends  UpdateAccountResponseDTO{
    public UpdateAdminResponseDTO(){
        super();
    }

    public UpdateAdminResponseDTO toDto(Admin admin){

        return (UpdateAdminResponseDTO)super.toDto(admin) ;
    }
}
