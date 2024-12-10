package PI.OLTP.API.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Image {

    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Id
    private Long id ;

    @OneToOne
    private Account account ;

    @Column(columnDefinition = "text")
    private String name ;

    private String type ;

    private byte[] bytes ;


}
