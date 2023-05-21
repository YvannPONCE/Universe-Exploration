package uniexp;

import org.junit.jupiter.api.Test;
import uniexp.galaxy.graph.Edge;
import uniexp.galaxy.graph.Graph;
import uniexp.galaxy.graph.Vertex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Double.parseDouble;
import static org.junit.jupiter.api.Assertions.*;

class GPSTest {

    @Test
    void ConstructorTest() {
        GPS gps = new GPS();
    }

    @Test
    void SortTest() {
        GPS gps = new GPS();
        List<Planete> planeteList = gps.sortPlanetes();
        for (int i = 1; i < planeteList.size(); ++i) {
            assertTrue(planeteList.get(i - 1).getDistance() < planeteList.get(i).getDistance());
        }
    }

    @Test
    void findPotentialPlanateTest() {
        GPS gps = new GPS();
        gps.sortPlanetes();
        Planete planete = gps.findPotentialPlanate();
        assertEquals("Mireille", planete.getName());
        assertEquals(400297, planete.getDistance());
        assertTrue(planete.getWaterPresence());
        assertEquals("Massalia", planete.getClosestPlanete());
        assertEquals(52752, planete.getClosestPlaneteDistance());
    }

    void validateGalaxyFormationTest() {
        GPS gps = new GPS();
        //testGraphMethods("Galaxy", gps.getGalaxy());
        int goodVertex = 0;
        for (Vertex vertex : gps.getGalaxy().vertices()) {
            try {
                parseDouble(vertex.getTag());
            } catch (NumberFormatException e) {
                ++goodVertex;
            }
        }
        assertEquals(gps.getGalaxy().nbVertices(), goodVertex);
    }

    private void testGraphMethods(String name, Graph G) {
        System.out.println(name);
        System.out.println(G);

        System.out.println("\nVertices enumeration:");
        for (Vertex vertex : G.vertices())
            System.out.print(vertex.getTag() + " ");
        System.out.println();

        System.out.println("\nEdges enumeration:");
        for (Edge edge : G.edges())
            System.out.print("(" + edge.origin() + "," + edge.destination() + ") ");
        System.out.println();

        System.out.println("\nAdjacents enumeration:");
        for (Vertex vertex : G.vertices()) {
            System.out.print("Adjacents of vertex " + vertex + ": ");
            for (Vertex adjacent : G.adjacents(vertex))
                System.out.print(adjacent + " ");
            System.out.println();
        }

        System.out.println("\nIncidents enumeration:");
        for (Vertex vertex : G.vertices()) {
            System.out.println("Incident edges of vertex " + vertex + ":");
            for (Edge edge : G.incidents(vertex))
                System.out.println("   " + edge + ", origin = " + edge.origin() + ", destination = " + edge.destination());
            System.out.println();
        }

        System.out.println("\nIn-degree of vertices:");
        for (Vertex vertex : G.vertices())
            System.out.println("in-degree(" + vertex + ") = " + G.inDegree(vertex));
        System.out.println();

        System.out.println("\nOut-degree of vertices:");
        for (Vertex vertex : G.vertices())
            System.out.println("out-degree(" + vertex + ") = " + G.outDegree(vertex));
        System.out.println();

        System.out.println("\n(total) degree of vertices:");
        for (Vertex vertex : G.vertices())
            System.out.println("degree(" + vertex + ") = " + G.degree(vertex));
        System.out.println();
    }

    @Test
    void isPlaneteReachableTest() {
        GPS gps = new GPS();
        List<Planete> planeteList = gps.sortPlanetes();
        Planete planete1 = planeteList.stream()
                .parallel()
                .filter(x -> x.getName().equals("Ceres"))
                .findFirst()
                .orElse(null);
        Planete planete2 = planeteList.stream()
                .parallel()
                .filter(x -> x.getName().equals("Hygiea"))
                .findFirst()
                .orElse(null);

        assertFalse(gps.isPlaneteReachable(planete1));
        assertTrue(gps.isPlaneteReachable(planete2));
    }

    @Test
    void findTrajectory() {
        GPS gps = new GPS();
        List<Planete> planeteList = gps.sortPlanetes();
        Planete planete = planeteList.stream()
                .parallel()
                .filter(x -> x.getName().equals("Mireille"))
                .findFirst()
                .orElse(null);

        List<Vertex> vertexList = gps.findTrajectory(planete);
        List<String> planeteNames = vertexList.stream()
                .parallel()
                .map(x -> x.getTag())
                .collect(Collectors.toList());

        List<String> expected = new ArrayList<>(Arrays.asList("Earth", "Fortuna", "Psyche", "Massalia", "Mireille"));
        assertEquals(expected, planeteNames);
    }
}