package uniexp;

import uniexp.galaxy.Galaxy;
import uniexp.galaxy.graph.GraphReader;

import java.util.List;

public class GPS {

    //1 Recuperez la liste des planètes
    //2 Trier ces planète par distance
    //3 récupérer la planète contenant de l'eau la plus proche
    //4 Former une galaxie constituée de planètes
    //5 Trouver le chemin menant à la planète choisie

    private List<Planete> planeteList;
    private Galaxy galaxy;

    //1
    public GPS()
    {
        DataBase dataBase = new DataBase();
        planeteList = dataBase.getPlaneteList();
        buildGalaxy();

    }

    // Implement the sorting algorithme of your choice on the member variable distance
    private List<Planete> sortPlanetes()
    {
        return null;
    }

    //3
    private Planete findPotentialPlanate()
    {
         return null;
    }

    //4
    private void buildGalaxy()
    {
        String grpahInput = "";
        for(Planete planete : planeteList)
        {
            grpahInput += " "+planete.getName()+" "+planete.getClosestPlanete()+" "+planete.getClosestPlaneteDistance();
        }
        galaxy = GraphReader.galaxy(grpahInput);
    }

    //5
    private List<Planete> findTrajectory()
    {
         return null;
    }
}
