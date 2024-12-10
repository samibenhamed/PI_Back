package PI.OLTP.API.DTO.ResponseDtos.Skill;

import PI.OLTP.API.Model.Skill;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class NewSkillResponseDTO {

    private Long id ;
    private String name ;
    private int level ;

    public  NewSkillResponseDTO toDto (Skill skill ){

        this.id = skill.getId();
        this.name = skill.getName() ;
        this.level = skill.getLevel();
        return this ;
    }

}
