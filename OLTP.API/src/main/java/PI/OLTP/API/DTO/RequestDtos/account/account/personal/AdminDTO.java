package PI.OLTP.API.DTO.RequestDtos.account.account.personal;

import PI.OLTP.API.DTO.ImageDTO;
import PI.OLTP.API.Model.Admin;
import PI.OLTP.API.Model.Image;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdminDTO extends AccountDTO {
    public static AdminDTO entityToDTO (Admin admin , Image image ){
        AdminDTO dto = new AdminDTO() ;
        // dtype
        dto.setDtype("Admin");
        //id
        dto.setId(admin.getId());
        //First Name
        dto.setFirstName(admin.getFirstName());
        //Last Name
        dto.setLastName(admin.getLastName());
        //Email
        dto.setEmail(admin.getEmail());
        //PassWord
        dto.setPassWord(admin.getPassWord());
        // University
        dto.setUniversity(admin.getUniversity());
        //BirthDate
        dto.setBirthDate(admin.getBirthDate());
        // Sex
        dto.setSex(admin.getSex());
        // Phone
        dto.setPhone(admin.getPhone());
        // image
        ImageDTO imgDTO = new ImageDTO() ;
        imgDTO.toDTO(image) ;
         dto.setImage( imgDTO )  ;
        // titile
        dto.setTitle(admin.getTitle());
        // Description ;
        dto.setDescription(admin.getDescription());
        // linkedinURL
        dto.setLinkedinURL(admin.getLinkedinURL());
        // personalWesiteURL
        dto.setPersonalWesiteURL(admin.getPersonalWesiteURL());
        // protfolioURL
                dto.setProtfolioURL(admin.getProtfolioURL());
        /*Origine LocationDTO Set It After Creating The DTO using the Location
         entity ToDTO method in the service */
        return dto ;

    }
}
