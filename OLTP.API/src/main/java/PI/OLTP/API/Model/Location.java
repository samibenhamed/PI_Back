package PI.OLTP.API.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(nullable = false )
    private String country ;

    @Column(nullable = true)
    private  String region ;

    @Column(nullable = true)
    private String city ;

    //
    @OneToMany(mappedBy = "origin" , fetch = FetchType.LAZY)
    private Set<Account> accounts = new HashSet<>() ;
    //
    @OneToMany(mappedBy = "companyLocation" , fetch = FetchType.LAZY)
    private Set<Job> jobs = new HashSet<>() ;

    //
    @OneToMany(mappedBy = "universityLocation" , fetch = FetchType.LAZY)
    private Set<Diploma> Diplomas = new HashSet<>() ;


    //






}
