public class Wagon {

    public Boolean leftDoorsState = false;
    public Boolean rightDoorsState = false;
    private String wagonID;

    public Wagon(String wagonID) {
        this.wagonID = wagonID;
    }

    public void light(Boolean isOpening) {
        if (isOpening) {
            System.out.println("Warning lights blinding...\n");
        }
    }

    public void openDoors(String side) {
        if (leftDoorsState == true || rightDoorsState == true) {
            // in case its already open
            System.out.println("Doors are already open!");
        }
        else {
            // blinds the lights when opening doors
            light(true);
    
            if (side.equals("left")) {
                // open the left doors
                System.out.println("Left " + Door.open());
                this.leftDoorsState = true;
            } else if (side.equals("right")) {
                // open the right doors
                System.out.println("Right " + Door.open());
                this.rightDoorsState = true;
            } else if (side.equals("both")) {
                // open both sides
                System.out.println("All " + Door.open());
                this.leftDoorsState = true;
                this.rightDoorsState = true;
            }
        }
    }

    public void closeDoors() {
        if (leftDoorsState == false && rightDoorsState == false) {
            // in case its closed already
            System.out.println("Doors are already closed!\n");
        }
        else {
            // blinds the lights when opening doors
            light(true);

            // closes all doors
            System.out.println("All " + Door.close());
            this.leftDoorsState = false;
            this.rightDoorsState = false;
        }
        
    }

    public String getWagonID() {
        return this.wagonID;
    }
}
