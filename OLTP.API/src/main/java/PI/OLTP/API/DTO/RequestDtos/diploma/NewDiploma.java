package PI.OLTP.API.DTO.RequestDtos.diploma;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class NewDiploma {


    private Long  universityLocationId ;

    private String universityName ;

    private String diplomaType ;

    private String studyField ;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate enrollmenDate ;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate graduationDate;


}
