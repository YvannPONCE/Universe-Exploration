package uniexp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.SortedMap;

import static org.junit.jupiter.api.Assertions.*;

class GPSTest {

    @Test
    void ConstructorTest() {
        GPS gps = new GPS();
    }

    @Test
    void SortTest()
    {
        GPS gps = new GPS();
        List<Planete> planeteList = gps.sortPlanetes();
        for(int i=1; i<planeteList.size() ; ++i)
        {
            assertTrue(planeteList.get(i-1).getDistance() < planeteList.get(i).getDistance());
        }
    }

    @Test
    void findPotentialPlanateTest()
    {
        GPS gps = new GPS();
        gps.sortPlanetes();
        Planete planete = gps.findPotentialPlanate();
        assertEquals("Mireille", planete.getName());
        assertEquals(400297, planete.getDistance());
        assertEquals(true, planete.getWaterPresence());
        assertEquals("Massalia", planete.getClosestPlanete());
        assertEquals(52752, planete.getClosestPlaneteDistance());
    }
}