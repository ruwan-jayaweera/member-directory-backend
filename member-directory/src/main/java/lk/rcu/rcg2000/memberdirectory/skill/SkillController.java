package lk.rcu.rcg2000.memberdirectory.skill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/skills")
public class SkillController implements SkillApi {

    @Autowired
    private SkillService skillService;

    @Autowired
    private SkillMapper skillMapper;

    @Override
    @GetMapping("/{id}")
    public SkillResponse getSkill(@PathVariable final String id) {
        final Skill skill = skillService.findOne(id);
        return skillMapper.skillToResponse(skill);
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Skill createSkill(@RequestBody @Valid final SkillRequest skillRequest) {
        final Skill skill = skillMapper.requestToSkill(skillRequest);
        return skillService.create(skill);
    }

    @Override
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateSkill(@PathVariable final String id,
                              @RequestBody @Valid final SkillRequest skillRequest) {
        final Skill skill = skillMapper.requestToSkill(skillRequest);
        skillService.update(id, skill);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSkill(@PathVariable final String id) {
        skillService.delete(id);
    }
}
