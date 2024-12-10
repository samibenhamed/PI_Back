package PI.OLTP.API.DTO.ResponseDtos;

import PI.OLTP.API.Model.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationDTO {
    private Long id ;
    private String country ;
    private  String region ;
    private String city ;

    public static LocationDTO entityToDTO(Location location){
        LocationDTO locationDTO = new LocationDTO() ;
        locationDTO.setId(location.getId());
        locationDTO.setCountry(location.getCountry());
        locationDTO.setRegion(location.getRegion());
        locationDTO.setCity(location.getCity());
        return  locationDTO ;
    }

}
