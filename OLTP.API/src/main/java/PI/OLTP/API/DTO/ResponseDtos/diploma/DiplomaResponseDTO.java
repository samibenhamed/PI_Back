package PI.OLTP.API.DTO.ResponseDtos.diploma;

import PI.OLTP.API.DTO.ResponseDtos.LocationDTO;
import PI.OLTP.API.Model.Diploma;
import PI.OLTP.API.Model.Location;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class DiplomaResponseDTO {
    private  Long id ;

    private LocationDTO universityLocation  ;

    private String universityName ;

    private String diplomaType ;

    private String studyField ;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate enrollmenDate ;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate graduationDate;

    public  DiplomaResponseDTO toDTO(Diploma diploma ){
        this.setId(diploma.getId());
        Location location  = diploma.getUniversityLocation() ;
        this.setUniversityLocation(LocationDTO.entityToDTO(location));
        this.setUniversityName(diploma.getUniversityName());
        this.setDiplomaType(diploma.getDiplomaType());
        this.setStudyField(diploma.getStudyField());
        this.setEnrollmenDate(diploma.getEnrollmenDate());
        this.setGraduationDate(diploma.getGraduationDate());
        return this ;
    }
}
