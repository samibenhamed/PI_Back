package PI.OLTP.API.Service;

import PI.OLTP.API.DTO.LocDto;
import PI.OLTP.API.Model.Location;
import PI.OLTP.API.Repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class  LocationService {
    private  final LocationRepository repo ;
    @Autowired
    public LocationService(LocationRepository repo){
        this.repo=repo ;
    }

    public Location getLocationById(Long id ){
        return repo.findById(id).orElseThrow();
    }

    public List<LocDto> getAllLocations(){
        List<Location> locations = repo.findAll() ;
        List<LocDto> dtos = new ArrayList<>() ;
        for (Location location:locations) {
            dtos.add(new LocDto().toDTO(location)) ;
        }
        return dtos ;

    }
}
