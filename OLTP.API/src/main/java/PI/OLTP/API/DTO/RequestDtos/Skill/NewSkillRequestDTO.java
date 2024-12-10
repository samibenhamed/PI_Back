package PI.OLTP.API.DTO.RequestDtos.Skill;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NewSkillRequestDTO {
    private String name ;
    private int level ;
}
