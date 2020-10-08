package trivia.thegame;

public class Board {
    private final int MAX_PLAYERS;
    // 12 cases
    private String[] cellsCategory = new String[]
            {"Pop", "Science", "Sports", "Rock", "Pop", "Science",
                    "Sports", "Rock", "Pop", "Science", "Sports", "Rock"};

    private int[] cellNumberOfPlayers;
    private int nbPlayers = 0;

    public Board(int max_players) {
        MAX_PLAYERS = max_players;
        cellNumberOfPlayers = new int[MAX_PLAYERS];
    }

    public String getCellCategory(int cellNumber) {
        return cellsCategory[cellNumber];
    }

    public void addPlayer() {
        cellNumberOfPlayers[nbPlayers] = 0;
        nbPlayers++;
    }

    public int getPlaceOfPlayer(int player) {
        return cellNumberOfPlayers[player];
    }

    public void movePlayerWithRoll(int player, String playerName, int roll) {
        cellNumberOfPlayers[player] = (cellNumberOfPlayers[player] + roll) % 12;
        System.out.println(playerName + "'s new location is " + cellNumberOfPlayers[player]);
    }

}
