package uniexp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class PlaneteTest {

    @Test
    void planeteCreationTest() {
        Planete planete = new Planete("Eluvia", 100000, true, "Madiba", 260000);
        assertEquals("Eluvia", planete.getName());
        assertEquals(100000, planete.getDistance());
        assertTrue(planete.getWaterPresence());
        assertEquals("Madiba", planete.getClosestPlanete());
        assertEquals(260000, planete.getClosestPlaneteDistance());
    }
}