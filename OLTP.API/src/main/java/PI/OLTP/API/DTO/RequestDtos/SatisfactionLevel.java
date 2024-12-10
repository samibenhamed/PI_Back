package PI.OLTP.API.DTO.RequestDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SatisfactionLevel {
    private  Long userId ;
    private int satisfactionLevel ;
}
