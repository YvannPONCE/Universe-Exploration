package uniexp;


public class Planete {
    String name;
    double distance;
    boolean waterPresence;
    String closestPlanete;
    double closestPlaneteDistance;

    public Planete(String name, double distance, boolean waterPresence, String closestPlanete, double closestPlaneteDistance) {
        this.name = name;
        this.distance = distance;
        this.waterPresence = waterPresence;
        this.closestPlanete = closestPlanete;
        this.closestPlaneteDistance = closestPlaneteDistance;
    }

    public String getName() {
        return name;
    }

    public double getDistance() {
        return distance;
    }

    public boolean getWaterPresence() {
        return waterPresence;
    }

    public String getClosestPlanete() {
        return closestPlanete;
    }

    public double getClosestPlaneteDistance() {
        return closestPlaneteDistance;
    }

    @Override
    public String toString() {
        return "\n" + getName() + " : " + "\n    Distance : " + getDistance() + "\n    Presence d'eau : " + getWaterPresence() + "\n    Planete la pus proche : " + getClosestPlanete() + "\n    Distance jusau'à " + getClosestPlanete() + " : " + getClosestPlaneteDistance();
    }
}
