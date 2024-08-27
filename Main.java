public class Main {
    public static void main(String[] args) {
        Subway newSubway = new Subway("L1", "L256");
        System.out.println("Subway ID: " + newSubway.getSubwayID());
        System.out.println("Wagon ID: " + newSubway.wagon.getWagonID());
    }
}
