package transfo;

public class Board {
    private final int MAX_PLAYERS;
    // 12 cases
    private String[] categoryOfCase = new String[]
            {"Pop", "Science", "Sports", "Rock", "Pop", "Science",
                    "Sports", "Rock", "Pop", "Science", "Sports", "Rock"};

    private int[] places;
    private int nbPlayers = 0;

    public Board(int max_players) {
        MAX_PLAYERS = max_players;
        places = new int[MAX_PLAYERS];
    }

    public String getCategory(int caseNumber) {
        return categoryOfCase[caseNumber];
    }

    public void addPlayer() {
        places[nbPlayers] = 0;
        nbPlayers++;
    }

    public int getPlacesOfPlayer(int player) {
        return places[player];
    }

    public void movePlayer(int player, String playerName, int roll) {
        places[player] = (places[player] + roll);
        if (places[player] > 11) places[player] = places[player] - 12;
        System.out.println(playerName + "'s new location is " + places[player]);
    }

}
