package PI.OLTP.API.DTO.ResponseDtos.job;

import PI.OLTP.API.DTO.ResponseDtos.LocationDTO;
import PI.OLTP.API.Model.Job;
import PI.OLTP.API.Model.Location;
import com.fasterxml.jackson.annotation.JsonFormat;
import jdk.javadoc.doclet.Taglet;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class PersonalJobDetailsDTO {
    private Long id ;
    private String companyName ;
    private String companyWebsiteURL ;
    private String companySector ;

    private  String jobField ;
    private  String jobTitle ;
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
    private LocationDTO companyLocation  ;
    // ToDTO

    public  PersonalJobDetailsDTO toDTO(Job job ){
        this.setId(job.getId());

        this.setCompanyName(job.getCompanyName());
        this.setCompanyWebsiteURL(job.getCompanyWebsiteURL());
        this.setCompanySector(job.getCompanySector());
        this.setJobField(job.getJobField());
        this.setJobTitle(job.getJobTitle());
        this.setHireDate(job.getHireDate());
        this.setQuitDate(job.getQuitDate());
        this.setSalary(job.getSalary());
        this.setSalaryCurrency(job.getSalaryCurrency());
        this.setNbApplications(job.getNbApplications());
        this.setNbRefusedApplications(job.getNbRefusedApplications());
        Location companyLocation = job.getCompanyLocation() ;
        this.setCompanyLocation(LocationDTO.entityToDTO(companyLocation));
        return this ;
    }
}
