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
}