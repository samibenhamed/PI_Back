package PI.OLTP.API.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Diploma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @ManyToOne
    private User user ;

    @ManyToOne
    @JoinColumn(name = "univesiry_location_id")
    private Location universityLocation ;

    @Column(name = "universit_name" , nullable = false )
    private String universityName ;

    @Column(name = "diploma_type" , nullable = false )
    private String diplomaType ;

    @Column(name = "study_field" , nullable = false )
    private String studyField ;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "enrollment-date" , nullable = false )
    private LocalDate enrollmenDate ;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "graduation-date" , nullable = true )

    private LocalDate graduationDate;

}
