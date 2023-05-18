package uniexp;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PlaneteTest {

    @org.junit.jupiter.api.Test
    void planeteCreation() {
        Planete planetes[] = new Planete[100];
        for(int i=0; i<planetes.length ;++i)
        {
            planetes[i] = new Planete(i);
        }

        for(Planete planete : planetes)
        {
            assertTrue(planete.distance >9999 && planete.distance <100001);
        }
    }
}