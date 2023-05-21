package uniexp;

import org.w3c.dom.events.EventException;
import uniexp.galaxy.Galaxy;
import uniexp.galaxy.graph.Edge;
import uniexp.galaxy.graph.GraphReader;
import uniexp.galaxy.graph.Vertex;

import java.util.*;

import static java.lang.Math.*;

public class GPS {

    //1 Recuperez la liste des planètes
    //2 Trier ces planète par distance
    //3 récupérer la planète contenant de l'eau la plus proche
    //4 Former une galaxie constituée de planètes
    //5 Vérifiez que la planète choisie est atteignable
    //5 Trouver le chemin menant à la planète choisie

    Planete earth;
    private final List<Planete> planeteList;
    private Galaxy galaxy;

    //1
    public GPS() {
        DataBase dataBase = new DataBase();
        planeteList = dataBase.getPlaneteList();

        earth = planeteList.stream()
                .parallel()
                .filter(x -> x.getName().equals("Earth"))
                .findFirst()
                .orElse(null);
        if (earth == null) {
            throw new EventException((short) 1, "Earth is not in the universe");
        }
        buildGalaxy();


    }

    private static <T> void swap(List<T> array, int i, int j) {
        T tmp = array.get(i);
        array.set(i, array.get(j));
        array.set(j, tmp);
    }

    public static void main(String[] args) {
        GPS gps = new GPS();

        System.out.println("------Initialisation du GPS------");
        System.out.println();
        System.out.println("La liste des planete triées par distance est :");
        System.out.println(gps.sortPlanetes());
        System.out.println();
        System.out.println("-------------------------------------------------------\n");
        System.out.println("La planete choisie pour l'exploration est :");
        Planete potentialPlanete = gps.findPotentialPlanate();
        System.out.println(potentialPlanete);
        System.out.println();
        System.out.println("-------------------------------------------------------\n");
        System.out.println("La planete est atteignable : " + gps.isPlaneteReachable(potentialPlanete));
        System.out.println();
        System.out.println("-------------------------------------------------------\n");
        System.out.println("Le chemin le plus court pour y parvenir est :");
        System.out.println(gps.findTrajectory(potentialPlanete));
        System.out.println();
        System.out.println("-------------------------------------------------------\n");
        System.out.println("Merci pour votre coopération !");

    }

    Galaxy getGalaxy() {
        return galaxy;
    }

    // Implement the sorting algorithme of your choice on the member variable distance
    // Selection sorting choose here
    public List<Planete> sortPlanetes() {
        for (int i = 0; i < planeteList.size(); ++i) {
            int minValueIndex = i;
            for (int j = i + 1; j < planeteList.size(); ++j) {
                if (planeteList.get(minValueIndex).distance > (planeteList.get(j).distance)) {
                    minValueIndex = j;
                }
            }
            swap(planeteList, i, minValueIndex);
        }
        return planeteList;
    }

    //Use streams to find the first potential planet were we there is water.
    public Planete findPotentialPlanate() {
        return planeteList.stream()
                .parallel()
                .filter(Planete::getWaterPresence)
                .findFirst()
                .orElse(null);
    }

    //4
    private void buildGalaxy() {
        String grpahInput = "";
        for (Planete planete : planeteList) {
            grpahInput += new StringBuilder(" " + planete.getName() + " " + planete.getClosestPlanete() + " " + planete.getClosestPlaneteDistance()).toString();
        }
        grpahInput = grpahInput.strip();
        galaxy = GraphReader.galaxy(grpahInput);
    }

    public boolean isPlaneteReachable(Planete planete) {
        Map<Vertex, Integer> graphMap = new HashMap<>();


        for (Edge edge : galaxy.edges()) {
            Vertex origin = edge.origin();
            Vertex destination = edge.destination();

            if (graphMap.containsKey(origin) && graphMap.containsKey(destination)) {
                if (graphMap.get(origin).equals(graphMap.get(destination))==false) {
                    int min = min(graphMap.get(destination), graphMap.get(origin));
                    int max = max(graphMap.get(destination), graphMap.get(origin));
                    changeGroup(graphMap, max, min);
                }
            } else if (graphMap.containsKey(origin)) {
                graphMap.put(destination, graphMap.get(origin));
            } else if (graphMap.containsKey(destination)) {
                graphMap.put(origin, graphMap.get(destination));
            } else {
                int newGroupValue = graphMap.values().stream().max(Comparator.naturalOrder()).orElse(0) + 1;
                graphMap.put(origin, newGroupValue);
                graphMap.put(destination, newGroupValue);
            }
        }
        for (Vertex vertex : galaxy.vertices()) {
            if (!graphMap.containsKey(vertex)) {
                graphMap.put(vertex, graphMap.values().stream().max(Comparator.naturalOrder()).orElse(0) + 1);
            }
        }
        Vertex startVertex = galaxy.getVertex(earth.getName());
        Vertex destinationVertex = galaxy.getVertex(planete.getName());
        return graphMap.get(startVertex).equals(graphMap.get(destinationVertex));
    }

    void changeGroup(Map<Vertex, Integer> graphMap, int oldGroup, int newGroup) {
        graphMap.replaceAll((k, v) -> v == oldGroup ? newGroup : v);
    }

    //5
    public List<Vertex> findTrajectory(Planete planete) {
        Vertex destination = galaxy.getVertex(planete.getName());
        Vertex start = galaxy.getVertex(earth.getName());
        if (start == null || destination == null) {
            return Collections.emptyList();
        }

        Map<Vertex, Double> dijkstraMap = new HashMap<>();
        Map<Vertex, List<Vertex>> shortestPathMap = new HashMap<>();
        shortestPathMap.put(start, new ArrayList<>());
        Set<Vertex> visitedVertex = new HashSet<>();
        for (Vertex vertex : galaxy.vertices()) {
            dijkstraMap.put(vertex, Double.MAX_VALUE);
        }
        dijkstraMap.replace(start, 0D);
        Vertex currentVertex = start;

        do {
            for (Vertex vertex : galaxy.adjacents(currentVertex)) {
                double weight = galaxy.findEdge(currentVertex, vertex).weight();
                if (dijkstraMap.get(currentVertex) + weight < dijkstraMap.get(vertex)) {
                    dijkstraMap.replace(vertex, dijkstraMap.get(currentVertex) + weight);
                    List<Vertex> shortestPath = new ArrayList<>(shortestPathMap.get(currentVertex));
                    shortestPath.add(currentVertex);
                    shortestPathMap.put(vertex, shortestPath);
                }
            }

            Optional<Map.Entry<Vertex, Double>> currentVertexOPT = dijkstraMap.entrySet().stream()
                    .sorted((x1, x2) -> x1.getValue().compareTo(x2.getValue()))
                    .filter(x -> !visitedVertex.contains(x.getKey()))
                    .findFirst();
            if(currentVertexOPT.isPresent())
            {
                currentVertex = currentVertexOPT.get().getKey();
            }
            else
            {
                break;
            }

            visitedVertex.add(currentVertex);
        } while (!visitedVertex.containsAll(dijkstraMap.keySet()));

        List<Vertex> shortestPathToDestination = shortestPathMap.get(destination);
        shortestPathToDestination.add(destination);

        return shortestPathToDestination;
    }
}
