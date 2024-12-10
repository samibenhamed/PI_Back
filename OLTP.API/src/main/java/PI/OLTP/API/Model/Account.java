package PI.OLTP.API.Model;
import javax.validation.constraints.Email;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id ;
    @Column(insertable=false, updatable=false)
    private  String dtype ;

    @Column(name = "first_name" , nullable = false)
    private String firstName ;

    @Column(name = "last_name" , nullable = false)
    private String lastName ;

    @Email
    @Column(unique = true , nullable = false  )
    private String email ;

    @Column(unique = true , nullable = false  )
    private String passWord ;

    @Column(nullable = false )
    private String university ;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column( name = "birth_date")
    private LocalDate  birthDate ;
    // LocalDate bd= LocalDate.of(2002,1,9) ;

    @Column( nullable = false)
    private String sex ;

    @Column(nullable = true)
    private String phone ;

    //image
    @OneToOne(mappedBy = "account" , cascade = CascadeType.ALL)
    private Image image ;
    // title
    @Column(nullable = true)
    private String title ;

    // description
    @Column(nullable = true , length = 999999 )
    private String description ;
    // Links : Likeding / PersonalWebsite / Portfolio /
    @Column(name = "likedin-url",nullable = true)
    private String linkedinURL ;
    @Column( name = "personal-wesite-url", nullable = true)
    private String personalWesiteURL ;
    @Column( name = "portfolio-url", nullable = true)
    private String protfolioURL ;
    // origine
    @ManyToOne
    @JoinColumn( nullable = true )
    private Location origin ;







}
