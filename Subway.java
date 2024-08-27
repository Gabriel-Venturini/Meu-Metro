public class Subway {

    private String subwayID;
    private Integer speed = 0;
    private String wagonID;
    public Wagon wagon;

    public Subway(String subwayID, String wagonID) {
        this.subwayID = subwayID;
        this.wagonID = wagonID;
        this.wagon = new Wagon(this.wagonID); // initiates a new instance of wagon
    }

    public void forward(Boolean forward) {
        if (wagon.leftDoorsState == true || wagon.rightDoorsState == true) {
            // avoid the train going forward if any door is open
            System.out.println("You can't go forward with the doors open!\n");
        }
        else if (forward) {
            if (getSpeed() >= 80) {
                // in case the pilot reaches the maximum speed
                System.out.println("You reached the maximum speed of 80Kmh!\n");
            }
            else {
                this.speed = this.speed + 10; // adds +10kmh speed
                System.out.println("Your speed: " + getSpeed());
            }
        }
    }

    public void reverse(Boolean reverse) {
        if (reverse) {
            if (getSpeed() == 0) {
                // in case the speed is zero
                System.out.println("You are not fast enough to use reverse!\n");
            }
            else {
                this.speed = this.speed - 10; // slow down by 10kmh it's speed
                System.out.println("Your speed: " + getSpeed());
            }
        }
    }

    public void stop(Boolean stop) {
        // acts like an emergency brake
        if (stop) {
            if (getSpeed() == 0) {
                System.out.println("The train is not moving!\n");
            }
            else {
                System.out.println("\nActivating emergency breaks...\n");
                while (this.speed >= 10) {
                    System.out.println("Speed: " + this.speed);
                    this.speed = this.speed - 10;
                }
                if (getSpeed() == 0) {
                    System.out.println("Train stopped succesfully!\n");
                }
            }
        }
    }

    public void openDoor(String side) {
        if (this.speed > 0) {
            // avoid the train to open the doors while the  car is moving
            System.out.println("You can't open the doors while moving!\n");
        }
        else {
            this.wagon.openDoors(side);
        }
    }

    public void closeDoor() {
        this.wagon.closeDoors();
    }

    public String getSubwayID() {
        return this.subwayID;
    }

    public Integer getSpeed() {
        return this.speed;
    }
}
