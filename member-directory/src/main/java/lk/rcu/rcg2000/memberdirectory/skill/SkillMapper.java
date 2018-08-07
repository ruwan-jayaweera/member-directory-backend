package lk.rcu.rcg2000.memberdirectory.skill;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SkillMapper {
    Skill requestToSkill(SkillRequest request);

    SkillResponse skillToResponse(Skill company);
}
