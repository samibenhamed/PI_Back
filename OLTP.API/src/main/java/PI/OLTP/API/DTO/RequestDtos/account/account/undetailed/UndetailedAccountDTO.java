package PI.OLTP.API.DTO.RequestDtos.account.account.undetailed;

import PI.OLTP.API.DTO.ImageDTO;
import PI.OLTP.API.DTO.ResponseDtos.LocationDTO;
import PI.OLTP.API.Model.Account;
import PI.OLTP.API.Model.Image;
import PI.OLTP.API.Repository.ImageRepository;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
public class UndetailedAccountDTO {
    private String dtype ;
    private Long id ;
    private ImageDTO image ;
    private String firstName ;
    private String lastName ;
    private String title ;
    private String university ;
    private LocationDTO origine ;

    public UndetailedAccountDTO toUndetailedAccountDTO(Account account){
        this.setId(account.getId());
        this.setFirstName(account.getFirstName());
        this.setLastName(account.getLastName());
        this.setTitle(account.getTitle());
        this.setUniversity(account.getUniversity());
        this.setOrigine(LocationDTO.entityToDTO(account.getOrigin()));
    return this ;
    }

}
