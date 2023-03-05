package monsterservice.service;

import monsterservice.handleExceptionError.HandleExceptionError;
import monsterservice.model.Monster;
import monsterservice.repository.MonsterRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
public class MonsterServiceTest {

    @InjectMocks
    private MonsterService monsterService;

    @Mock
    private MonsterRepository monsterRepository;

    private Monster mockMonster() {
        Monster mocMonster = new Monster();
        mocMonster.setId(1);
        mocMonster.setName("drake");
        mocMonster.setHealth(400);
        return mocMonster;
    }

    @Test
    void postCreateMonsterTest(){

        doReturn(mockMonster()).when(monsterRepository).save(any(Monster.class));

        Monster response = monsterService.postCreateMonsterService(new Monster());

        assertEquals(mockMonster().getId(),response.getId());
    }

    @Test
    void getAllMonsterServiceTest(){

        List<Monster> monsterList = new ArrayList<>();
        monsterList.add(mockMonster());
        doReturn(monsterList).when(monsterRepository).findAll();

        List<Monster> response = monsterService.getAllMonsterService();
        assertEquals(monsterList,response);

        //assertEquals(monsterList.get(0).getId(),response.get(0).getId();
    }

    @Test
    void updateMonsterServiceTestSuccess()throws HandleExceptionError {

        //mock findbyid
        doReturn(Optional.of(mockMonster())).when(monsterRepository).findById(any(Integer.class));

        doReturn(mockMonster()).when(monsterRepository).save(any(Monster.class));

        Monster requestMonster = new Monster();
        requestMonster.setId(1);

        Monster response = monsterService.getUpdateMonsterIdService(requestMonster);

        assertEquals(mockMonster().getId(),response.getId());

    }

    @Test
    void updateMpnsterServiceFailDataNotFound(){
        HandleExceptionError handleExceptionError
                = assertThrows(HandleExceptionError.class,
                () -> monsterService.getUpdateMonsterIdService(new Monster()));

        assertEquals("Data not found",handleExceptionError.getMessage());
    }

    @Test
    void UpdateMonsterServiceFailCanNotConnectDatabase(){
        doThrow(new RuntimeException()).when(monsterRepository).findById(any());
        HandleExceptionError handleExceptionError = assertThrows(HandleExceptionError.class,() ->
                monsterService.getUpdateMonsterIdService(new Monster()));

        assertEquals("Can't connect database",handleExceptionError.getMessage());
    }
}
