package PI.OLTP.API.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Set ;
import java.time.LocalDate;
import java.util.HashSet;

@Entity
@Data
public class User extends Account {
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = true)
    private LocalDate promotion ;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "graduation-date",nullable = true)
    private LocalDate graduationDate  ;

    @Column(name = "bac-type",nullable = true)
    private String bacType ;

    @Column(name="satisfaction-level"  )
    private int satisfactionLevel =0 ;
    @Column (nullable = true)
    private String status ;

    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL ,fetch = FetchType.LAZY )
    private Set<Job> jobs = new HashSet<>() ;

    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL ,fetch = FetchType.LAZY )
    private Set<Diploma> Diplomas = new HashSet<>() ;

    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL ,fetch = FetchType.LAZY )
    private Set<Skill> skills = new HashSet<>() ;




}
