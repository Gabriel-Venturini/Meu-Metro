public class Door {
    public static String open() {
        // open doors
        // static methods so we dont need to instance a new object every time
        return "doors are opening...\n";
    }

    public static String close() {
        // close doors
        return "doors are closing...\n";
    }
}