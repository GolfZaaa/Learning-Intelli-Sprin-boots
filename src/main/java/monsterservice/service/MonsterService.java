package monsterservice.service;


import monsterservice.model.Monster;
import monsterservice.repository.MonsterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class MonsterService {

    @Autowired
    private MonsterRepository monsterRepository;

    public Monster postCreateMonsterService(Monster monster){

        return monsterRepository.save(monster);
    }

    public List<Monster> getAllMonsterService(){
        return monsterRepository.findAll();
    }

    public Optional<Monster> getInformation (Integer id){

        //Select * from monster where id = id;
        return monsterRepository.findById(id);

    }
    public Monster getUpdateMonsterIdService(Monster monster){
        return monster;
    }

    public boolean deleteMonsterService(Integer id){
        return id > 0;
    }
    public Monster attackMonsterService(Integer id, Integer health){
        Monster monster = new Monster();
        monster.setId(id);
        monster.setHealth(health);
        return monster;
    }
}

