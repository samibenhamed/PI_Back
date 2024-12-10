package PI.OLTP.API.DTO.RequestDtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginRequestDTO {
    private String email ;
    private  String passWord ;
}
