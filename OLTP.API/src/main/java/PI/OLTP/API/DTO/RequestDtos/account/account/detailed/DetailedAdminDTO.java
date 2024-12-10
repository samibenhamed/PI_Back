package PI.OLTP.API.DTO.RequestDtos.account.account.detailed;

import PI.OLTP.API.Model.Admin;
import PI.OLTP.API.Model.Image;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DetailedAdminDTO extends DetailedAccountDTO {


    public   DetailedAdminDTO  toDetailedAdminDTO(Admin  admin  , Image image){
          this.toDetailedAccountDTO(admin , image) ;
          this.setDtype("Admin");
       return  this ;
   }

}
