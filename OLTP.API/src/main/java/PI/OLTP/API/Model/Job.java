package PI.OLTP.API.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Job {
    // ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    // UserId
    @ManyToOne
    @JoinColumn(nullable = false)
    private User user ;


    //CompanyName
    @Column(name = "company-name" ,nullable = false)
    private String companyName ;

    // CompanyWebsite
    @Column(name = "company-website-url" ,nullable = true)
    private String companyWebsiteURL ;

    // Company Sector
    @Column(name = "company-sector" ,nullable = false)
    private String companySector ;

    // Job Field
    @Column(name = "job-field" ,nullable = false)
    private  String jobField ;

    //
    @Column(name = "job-title" ,nullable = false)
     private String jobTitle ;

    // HireDate
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "hire-date" ,nullable = false)
    private LocalDate hireDate ;

    // Quit Date
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "quit-date" ,nullable = true )
    private LocalDate quitDate ;

    //Salary
    @Column(nullable = false)
    private double salary ;

    // SalaryCurrency
    @Column( name = "salary-currency", nullable = false)
    private  String salaryCurrency ;

    // NB Application
    @Column( name = "nb-job-applications", nullable = false )
    private int nbApplications ;

    // NB Refuse Applications
    @Column( name = "nb-refused-job-applications", nullable = false )
    private int nbRefusedApplications ;

    //Company Location
    @ManyToOne
    @JoinColumn(nullable = false)
    private Location companyLocation  ;



}
