public class Player {

    private String name;
    private int hp;
    private int x;
    private int y;
    private int direction;

    private static int numPlayers = 0;

    // Default constructor
    public Player() {
        numPlayers++;
        this.name = "P" + numPlayers; // Default player name
        this.x = 0;
        this.y = 0;
        this.direction = 1; // North
        this.hp = 20; // Default health
    }

    // Constructor with name, x, and y
    public Player(String name, int x, int y) {
        numPlayers++;
        this.name = name;
        this.x = x;
        this.y = y;
        this.direction = 1; // Default to North
        this.hp = 20;
    }

    // Constructor with all parameters
    public Player(String name, int x, int y, int direction, int hp) {
        numPlayers++;
        this.name = name;
        this.x = x;
        this.y = y;
        this.direction = (direction >= 1 && direction <= 4) ? direction : 1; // Validating direction
        this.hp = hp;
    }

    // Getters
    public static int getNumPlayers() {
        return numPlayers;
    }

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHp() {
        return hp;
    }

    public int getDirection() {
        return direction;
    }

    // Setters
    public void setHp(int hp) {
        this.hp = Math.max(0, hp); // Ensure health doesn't go below 0
    }

    public void setDirection(int direction) {
        if (direction >= 1 && direction <= 4) {
            this.direction = direction;
        }
    }

    // Move player based on direction and units
    public void move(int direction, int units) {
        setDirection(direction);
        switch (direction) {
            case 1: // North
                y += units;
                break;
            case 2: // South
                y -= units;
                break;
            case 3: // East
                x += units;
                break;
            case 4: // West
                x -= units;
                break;
        }
    }

    // Teleport player to specific coordinates
    public void teleport(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Attack another player
    public void attack(Player target, int damage) {
        if (target.getHp() > 0) {
            int targetHp = target.getHp() - damage;
            target.setHp(targetHp);
            System.out.println(name + " attacked " + target.getName() + " for " + damage + " damage.");
            System.out.println(target.getName() + "'s remaining health: " + target.getHp());
        } else {
            System.out.println(target.getName() + " is already defeated!");
        }
    }

    // Get distance from another player
    public double getDistance(Player target) {
        return Math.sqrt(Math.pow(target.getX() - x, 2) + Math.pow(target.getY() - y, 2));
    }

    // String representation of the player
    @Override
    public String toString() {
        return "Name: " + name + "\nHealth: " + hp + "\nCoordinates: (" + x + ", " + y + ")\nDirection: " + direction;
    }

    // Display list of available methods
    public static void displayMethods() {
        System.out.println("Available methods:");
        System.out.println("1. getNumPlayers");
        System.out.println("2. getName");
        System.out.println("3. getX");
        System.out.println("4. getY");
        System.out.println("5. getHp");
        System.out.println("6. getDirection");
        System.out.println("7. toString");
        System.out.println("8. move");
        System.out.println("9. attack");
        System.out.println("10. teleport");
        System.out.println("11. getDistance");
    }
}


