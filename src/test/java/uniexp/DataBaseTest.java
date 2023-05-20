package uniexp;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataBaseTest {

    @Test
    void getPlaneteList() {
        DataBase dataBase = new DataBase();
        List<Planete> planeteList = dataBase.getPlaneteList();
        assertTrue(planeteList.size()==20);

        assertFalse(planeteList.contains(null));
    }
}