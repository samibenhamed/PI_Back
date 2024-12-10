package PI.OLTP.API.DTO;

import PI.OLTP.API.Model.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class LocDto {
    private Long id ;
    private String country ;
    private  String region ;
    private String city ;

    public  LocDto toDTO (Location location ){
        this.setId(location.getId());
        this.setCountry(location.getCountry());
        this.setRegion(location.getRegion());
        this.setCity(location.getCity());
        return this ;

    }

}
