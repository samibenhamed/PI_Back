package PI.OLTP.API.DTO.RequestDtos.account.account.detailed;

import PI.OLTP.API.DTO.ResponseDtos.diploma.DiplomaResponseDTO;
import PI.OLTP.API.DTO.ResponseDtos.job.UndetailedJobDTO;
import PI.OLTP.API.Model.Image;
import PI.OLTP.API.Model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class DetailedUserDTO extends DetailedAccountDTO {
    private LocalDate promotion ;

    private LocalDate graduationDate  ;

    private String bacType ;

    private int satisfactionLevel =0 ;

    private String status ;
    //
    private List<UndetailedJobDTO> jobs ;

    //
    private List<DiplomaResponseDTO> diplomas ;
    public DetailedUserDTO toDTO (User user , Image image){
        this.toDetailedAccountDTO(user , image) ;
        this.setDtype("User");
        this.setPromotion(user.getPromotion());
        this.setGraduationDate(user.getGraduationDate());
        this.setSatisfactionLevel(user.getSatisfactionLevel());
        this.setBacType(user.getBacType());
        this.setStatus(user.getStatus());
        return this  ;
    }
}
