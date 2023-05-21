package uniexp;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataBaseTest {

    @Test
    void getPlaneteList() {
        DataBase dataBase = new DataBase();
        List<Planete> planeteList = dataBase.getPlaneteList();
        assertEquals(20, planeteList.size());

        assertFalse(planeteList.contains(null));
    }

    @Test
    void goodNumberOfWaterPresencePlanet() {
        DataBase dataBase = new DataBase();
        List<Planete> planeteList = dataBase.getPlaneteList();
        int numberWaterPresentPlanet = 0;
        for (Planete planete : planeteList) {
            numberWaterPresentPlanet += planete.getWaterPresence() ? 1 : 0;
        }
        assertEquals(3, numberWaterPresentPlanet);
    }
}