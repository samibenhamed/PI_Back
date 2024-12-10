package PI.OLTP.API.DTO.ResponseDtos.job;

import PI.OLTP.API.DTO.ResponseDtos.LocationDTO;
import PI.OLTP.API.Model.Job;
import PI.OLTP.API.Model.Location;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class UndetailedJobDTO {
    private Long id ;
    private String companyName ;
    private String companyWebsiteURL ;


    private  String jobTitle ;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate hireDate ;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate quitDate ;

    private LocationDTO companyLocation  ;

    public  UndetailedJobDTO toDTO(Job job ){
        this.setId(job.getId());

        this.setCompanyName(job.getCompanyName());
        this.setCompanyWebsiteURL(job.getCompanyWebsiteURL());
        this.setJobTitle(job.getJobTitle());
        this.setHireDate(job.getHireDate());
        this.setQuitDate(job.getQuitDate());
        Location companyLocation = job.getCompanyLocation() ;
        this.setCompanyLocation(LocationDTO.entityToDTO(companyLocation));
        return this ;
    }

}
