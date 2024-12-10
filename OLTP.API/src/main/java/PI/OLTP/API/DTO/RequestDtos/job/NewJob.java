package PI.OLTP.API.DTO.RequestDtos.job;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class NewJob {
     // Note All Re Required Except For Quit Date
    //  User
     //  Location
    private String companyName ;
    private String companyWebsiteURL ;
    private String companySector ;
    private  String jobField ;
    private String jobTitle ;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate hireDate ;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate quitDate ;

    private double salary ;

    private  String salaryCurrency ;

    private int nbApplications ;

    // NB Refuse Applications
    private int nbRefusedApplications ;

    //
    private Long companyLocationId ;

    //Company Location
    // private Location companyLocation  ;

}
