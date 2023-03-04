package monsterservice.controller;

import monsterservice.model.Monster;
import monsterservice.service.MonsterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("monster")
public class MonsterController {

    @Autowired
    private MonsterService monsterService;

    @GetMapping("/greeting")
    public String getGreeting(){
        return "Hi there ! DKub 9อวิรุทธ์ ไชยสงคราม";
    }

    @PostMapping("/create")
    public Monster postCreate(@RequestBody Monster monster){
        Monster response = monsterService.postCreateMonsterService(monster);
        return response;
    }

    @GetMapping("/get-all")
    public List<Monster> getAll(){
        return monsterService.getAllMonsterService();
    }

    @GetMapping("/get-information")
    public Optional<Monster> getInformation(@RequestHeader Integer id){
        return monsterService.getInformation(id);
    }

    @PutMapping("/update")
    public Monster putUpdate(@RequestBody Monster monster){
    return monsterService.getUpdateMonsterIdService(monster);
    }

    @DeleteMapping("/delete")
    public  boolean delete(@RequestHeader Integer id){
        return monsterService.deleteMonsterService(id);
    }

    @PutMapping("/attack")
    public Monster putAttack(@RequestHeader Integer id,@RequestHeader Integer health ){
        Monster attack = monsterService.attackMonsterService(id, health);
        return attack;
    }
}
