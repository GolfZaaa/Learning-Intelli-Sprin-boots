package monsterservice.service;


import jakarta.persistence.OrderBy;
import monsterservice.handleExceptionError.HandleExceptionError;
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

    // private static  final String dataNotFound = "Data Not found";

    public Monster postCreateMonsterService(Monster monster) {

        return monsterRepository.save(monster);
    }

    public List<Monster> getAllMonsterService() {
        return monsterRepository.findAll();
    }

    public Optional<Monster> getInformation(Integer id) {

        //Select * from monster where id = id;
        return monsterRepository.findById(id);
    }

    public Monster getUpdateMonsterIdService(Monster monster) throws HandleExceptionError {
        try {
            Optional<Monster> data = getInformation(monster.getId());
            if (data.isPresent()) {
                return monsterRepository.save(monster);
            } else throw new HandleExceptionError("data not found");
        } catch (HandleExceptionError ex) {
            throw new HandleExceptionError("Can't connect database !!");
        }
    }

    public boolean deleteMonsterService(Integer id) throws HandleExceptionError {
        try {
            Optional<Monster> data = getInformation(id);
            if (data.isPresent()) {
                monsterRepository.deleteById(id);
                return true;
            } else throw new HandleExceptionError("data not found");
        } catch (HandleExceptionError ex) {
            throw new HandleExceptionError("Can't cpnnect database !!");
        }
    }

    public String attackMonsterService(Integer id, Integer damage) throws HandleExceptionError {
        try {
            Optional<Monster> data = getInformation(id);
            if (data.isPresent()) {
                int healthNow = data.get().getHealth() - damage;
                if (healthNow < 0) healthNow = 0;
                int response = monsterRepository.attackMonster(id, healthNow);
                if (response != 0) {
                    return "Update success";
                } else return "Can't update";
            } else throw new HandleExceptionError("Data not found");
        } catch (HandleExceptionError ex) {
            throw new HandleExceptionError(ex.getMessage());
        }
    }
}