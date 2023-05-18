package uniexp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class PlaneteTest {

    @Test
    void planeteCreationTest() {
        Planete[] planetes = new Planete[100];
        for(int i=0; i<planetes.length ;++i)
        {
            planetes[i] = new Planete(i);
        }

        for(Planete planete : planetes)
        {
            assertTrue(planete.getDistance() >9999 && planete.getDistance() <100001);
        }
    }
}