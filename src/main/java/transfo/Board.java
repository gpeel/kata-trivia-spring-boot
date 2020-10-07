package transfo;

public class Board {
    // 12 cases
    String[] categoryOfCase = new String[]
            {"Pop", "Science", "Sports", "Rock", "Pop", "Science",
                    "Sports", "Rock", "Pop", "Science", "Sports", "Rock"};

    public String getCategory(int caseNumber) {
        return categoryOfCase[caseNumber];
    }
}
