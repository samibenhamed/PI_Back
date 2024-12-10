package PI.OLTP.API.DTO.ResponseDtos.account;

import PI.OLTP.API.Model.User;
import lombok.Data;

import java.time.LocalDate;

@Data

public class UpdateUserResponseDTO extends  UpdateAccountResponseDTO{
    private LocalDate promotion ;

    private LocalDate graduationDate  ;

    private String bacType ;

    private int satisfactionLevel  ;
    public UpdateUserResponseDTO(){
        super();
    }

    public UpdateUserResponseDTO toDto(User user ){
        super.toDto(user) ;
        this.setPromotion(user.getPromotion());
        this.setGraduationDate(user.getGraduationDate());
        this.setBacType(user.getBacType());
        this.setSatisfactionLevel(user.getSatisfactionLevel()) ;
        return this ;
    }
}
