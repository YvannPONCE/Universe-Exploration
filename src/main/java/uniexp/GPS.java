package uniexp;

import org.w3c.dom.events.EventException;
import uniexp.galaxy.Galaxy;
import uniexp.galaxy.graph.Edge;
import uniexp.galaxy.graph.GraphReader;
import uniexp.galaxy.graph.Vertex;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class GPS {

    //1 Recuperez la liste des planètes
    //2 Trier ces planète par distance
    //3 récupérer la planète contenant de l'eau la plus proche
    //4 Former une galaxie constituée de planètes
    //5 Vérifiez que la planète choisie est atteignable
    //5 Trouver le chemin menant à la planète choisie

    private List<Planete> planeteList;
    Planete earth;
    private Galaxy galaxy;

    //1
    public GPS()
    {
        DataBase dataBase = new DataBase();
        planeteList = dataBase.getPlaneteList();

        earth = planeteList.stream()
                .parallel()
                .filter(x->x.getName().equals("Earth"))
                .findFirst()
                .orElse(null);
        if(earth == null)
        {
            throw new EventException((short) 1, "Earth is not in the universe");
        }
        buildGalaxy();


    }
    Galaxy getGalaxy()
    {
        return galaxy;
    }

    // Implement the sorting algorithme of your choice on the member variable distance
    // Selection sorting choose here
    public List<Planete> sortPlanetes()
    {
        for(int i=0; i<planeteList.size() ; ++i)
        {
            int minValueIndex = i;
            for(int j=i+1 ; j< planeteList.size() ; ++j)
            {
                if(planeteList.get(minValueIndex).distance > (planeteList.get(j).distance))
                {
                    minValueIndex = j;
                }
            }
            swap(planeteList, i, minValueIndex);
        }
        return planeteList;
    }
    private static <T> void swap(List<T> array, int i, int j) {
        T tmp = array.get(i);
        array.set(i, array.get(j));
        array.set(j, tmp);
    }

    //Use streams to find the first potential planet were we there is water.
    public Planete findPotentialPlanate()
    {
         Planete planete = planeteList.stream()
                 .parallel()
                 .filter(x -> x.getWaterPresence()==true)
                 .findFirst()
                 .orElse(null);

         System.out.println(planete);
         return planete;
    }

    //4
    private void buildGalaxy()
    {
        String grpahInput = "";
        for(Planete planete : planeteList)
        {
            grpahInput += " "+planete.getName()+" "+planete.getClosestPlanete()+" "+planete.getClosestPlaneteDistance();
        }
        grpahInput = grpahInput.strip();
        System.out.println(grpahInput);
        galaxy = GraphReader.galaxy(grpahInput);
    }


    public boolean isPlaneteReachable(Planete planete)
    {
        Map<Vertex, Integer> graphMap = new HashMap<>();


        for(Edge edge : galaxy.edges()) {
            Vertex origin = edge.origin();
            Vertex destination = edge.destination();

            if (graphMap.containsKey(origin) && graphMap.containsKey(destination)) {
                if (graphMap.get(origin) != graphMap.get(destination)) {
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
        for(Vertex vertex : galaxy.vertices())
        {
            if(graphMap.containsKey(vertex)==false)
            {
                graphMap.put(vertex, graphMap.values().stream().max(Comparator.naturalOrder()).orElse(0) + 1);
            }
        }
        System.out.println(graphMap);
        return true;
    }
    void changeGroup(Map<Vertex,Integer> graphMap, int oldGroup, int newGroup)
    {
        graphMap.replaceAll( (k,v)->v==oldGroup? v=newGroup : v);
    }

    //5
    private List<Planete> findTrajectory()
    {
         return null;
    }
}
