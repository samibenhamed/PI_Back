package PI.OLTP.API.DTO.RequestDtos.account.account.personal;

import PI.OLTP.API.DTO.ImageDTO;
import PI.OLTP.API.Model.Image;
import PI.OLTP.API.Model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
public class UserDTO extends AccountDTO {

    private LocalDate promotion ;

    private LocalDate graduationDate  ;

    private String bacType ;

    private int satisfactionLevel =0 ;

    private String status ;
    public static UserDTO entityToDto(User user  , Image image ) {
        UserDTO dto = new UserDTO() ;
        // dtype
        dto.setDtype("User");
        //id
        dto.setId(user.getId());
        //First Name
        dto.setFirstName(user.getFirstName());
        //Last Name
        dto.setLastName(user.getLastName());
        //Email
        dto.setEmail(user.getEmail());
        //PassWord
        dto.setPassWord(user.getPassWord());
        // University
        dto.setUniversity(user.getUniversity());
        //BirthDate
        dto.setBirthDate(user.getBirthDate());
        // Sex
        dto.setSex(user.getSex());
        // Phone
        dto.setPhone(user.getPhone());
        // image
        ImageDTO imgDTO = new ImageDTO() ;
        imgDTO.toDTO(image) ;
        dto.setImage( imgDTO )  ;
        // titile
        dto.setTitle(user.getTitle());
        // Description ;
        dto.setDescription(user.getDescription());
        // linkedinURL
        dto.setLinkedinURL(user.getLinkedinURL());
        // personalWesiteURL
        dto.setPersonalWesiteURL(user.getPersonalWesiteURL());
        // protfolioURL
        dto.setProtfolioURL(user.getProtfolioURL());
        //  promotion
        dto.setPromotion(user.getPromotion());
        // graduation_date
        dto.setGraduationDate(user.getGraduationDate());
        //  SattisafactionLevel
        dto.setSatisfactionLevel(user.getSatisfactionLevel());
        // Status
        dto.setStatus(user.getStatus());
        //bacType
        dto.setBacType(user.getBacType());

        return  dto ;
    }
}
