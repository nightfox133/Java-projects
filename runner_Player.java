import java.util.Scanner;
import java.util.HashMap;

public class runner_Player {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        HashMap<String, Player> players = new HashMap<>();
        Player p1 = null;
        Player p2 = null;

        System.out.println("Welcome to the Player Test Program!");
        System.out.println("Type 'methods' to see a list of available methods.");
        System.out.println("Type 'c' to create a new Player object, or type 'q' to quit.");

        while (true) {
            System.out.println("______________________________"); //down a line
            System.out.println("Enter a command:"); //prompt
            String input = scan.nextLine().trim().toLowerCase();
            

            if (input.equals("q")) {
                break;
            } else if (input.equals("methods")) {
                Player.displayMethods();
            } else if (input.equals("c")) {
                // Player creation
                System.out.println("Enter the player's name:");
                String name = scan.nextLine();

                System.out.println("Enter the x-coordinate:");
                int x = Integer.parseInt(scan.nextLine());

                System.out.println("Enter the y-coordinate:");
                int y = Integer.parseInt(scan.nextLine());

                System.out.println("Enter health points:");
                int hp = Integer.parseInt(scan.nextLine());

                System.out.println("Enter direction (1: North, 2: South, 3: East, 4: West):");
                int direction = Integer.parseInt(scan.nextLine());

                Player newPlayer = new Player(name, x, y, direction, hp);
                players.put(name, newPlayer);
                System.out.println("Player created:\n" + newPlayer);
            } else if (players.isEmpty()) {
                System.out.println("You need to create a player first!");
            }

            // Handle specific method input
            handleMethodInput(input, scan, players);
        }

        System.out.println("Exiting the program. Goodbye!");
        scan.close();
    }

    private static void handleMethodInput(String input, Scanner scan, HashMap<String, Player> players) {
        if (input.equals("attack") || input.equals("move") || input.equals("teleport") || input.equals("getdistance")) {
            // Ask for player's name if needed
            System.out.println("Enter the name of the player to use this method:");
            String playerName = scan.nextLine();

            if (!players.containsKey(playerName)) {
                System.out.println("Player " + playerName + " not found. Please create the player first.");
                return;
            }

            Player currentPlayer = players.get(playerName);

            switch (input) {
                case "attack":
                    System.out.println("Enter the target player's name:");
                    String targetName = scan.nextLine();

                    if (!players.containsKey(targetName)) {
                        System.out.println("Target player " + targetName + " not found.");
                        return;
                    }

                    Player targetPlayer = players.get(targetName);
                    System.out.println("Enter damage value:");
                    int damage = Integer.parseInt(scan.nextLine());
                    currentPlayer.attack(targetPlayer, damage);
                    break;

                case "move":
                    System.out.println("Enter direction (1: North, 2: South, 3: East, 4: West):");
                    int direction = Integer.parseInt(scan.nextLine());
                    System.out.println("Enter units to move:");
                    int units = Integer.parseInt(scan.nextLine());
                    currentPlayer.move(direction, units);
                    System.out.println("Player moved. New position: (" + currentPlayer.getX() + ", " + currentPlayer.getY() + ")");
                    break;

                case "teleport":
                    System.out.println("Enter x-coordinate for teleport:");
                    int newX = Integer.parseInt(scan.nextLine());
                    System.out.println("Enter y-coordinate for teleport:");
                    int newY = Integer.parseInt(scan.nextLine());
                    currentPlayer.teleport(newX, newY);
                    System.out.println("Player teleported to: (" + currentPlayer.getX() + ", " + currentPlayer.getY() + ")");
                    break;

                case "getdistance":
                    System.out.println("Enter the target player's name:");
                    String targetNameForDistance = scan.nextLine();

                    if (!players.containsKey(targetNameForDistance)) {
                        System.out.println("Target player " + targetNameForDistance + " not found.");
                        return;
                    }

                    Player targetForDistance = players.get(targetNameForDistance);
                    System.out.println("Distance to " + targetNameForDistance + ": " + currentPlayer.getDistance(targetForDistance));
                    break;
            }
        } else {
            switch (input) {
                case "getnumplayers":
                    System.out.println("Number of players: " + Player.getNumPlayers());
                    break;
                case "getname":
                    System.out.println("Enter the player name:");
                    String playerNameForGetName = scan.nextLine();
                    if (players.containsKey(playerNameForGetName)) {
                        System.out.println("Player name: " + players.get(playerNameForGetName).getName());
                    } else {
                        System.out.println("Player not found.");
                    }
                    break;
                case "tostring":
                    System.out.println("Enter the player name:");
                    String playerNameForToString = scan.nextLine();
                    if (players.containsKey(playerNameForToString)) {
                        System.out.println(players.get(playerNameForToString));
                    } else {
                        System.out.println("Player not found.");
                    }
                    break;
                default:
                  if (!input.equals("c"))
                    System.out.printf("Unknown \"%s\" command. Try again.\n", input);
            }
        }
    }
}
