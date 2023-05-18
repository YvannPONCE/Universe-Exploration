package uniexp;


public class Planete {
    String name;
    double distance;
    boolean waterPresence;

    public Planete(String name, double distance, boolean waterPresence)
    {
        this.name = name;
        this.distance = distance;
        this.waterPresence = waterPresence;
    }
    public Planete(Planete planete)
    {
        this(planete.name, planete.distance, planete.waterPresence);
    }
    public Planete(int planetNumber)
    {
        this.name="Planete_number_"+planetNumber;
        this.distance= getRandomDistance();
        this.waterPresence= getRandomBoolean();
    }

    public String getName() {
        return name;
    }
    public double getDistance() {
        return distance;
    }
    public boolean waterPresence() {
        return waterPresence;
    }

    private boolean getRandomBoolean() {
        return Math.random() < 0.5;
    }
    private double getRandomDistance() {
        return Math.random() * ( 100000 - 10000 ) + 10000;
    }

}
