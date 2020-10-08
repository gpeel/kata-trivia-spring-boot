package trivia.thegame;

public class Board {
    private final int MAX_PLAYERS;
    // 12 cases
    private String[] cellsCategory = new String[]
            {"Pop", "Science", "Sports", "Rock", "Pop", "Science",
                    "Sports", "Rock", "Pop", "Science", "Sports", "Rock"};

    private int[] playersCell;
    private int nbPlayers = 0;

    public Board(int max_players) {
        MAX_PLAYERS = max_players;
        playersCell = new int[MAX_PLAYERS];
    }

    public String getCellCategory(int cellNumber) {
        return cellsCategory[cellNumber];
    }

    public void addPlayer() {
        playersCell[nbPlayers] = 0;
        nbPlayers++;
    }

    public int getPlaceOfPlayer(int player) {
        return playersCell[player];
    }

    public void movePlayerWithRoll(int player, String playerName, int roll) {
        playersCell[player] = (playersCell[player] + roll) % 12;
        System.out.println(playerName + "'s new location is " + playersCell[player]);
    }

}
