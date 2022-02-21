package Main;

import Matchmaking.Matchmaking;

public class MatchmakingMain {
    public static void main(String[] args) throws Exception {
        Matchmaking matchmaking = new Matchmaking();
        matchmaking.matchMake("src/resources/people.txt");
    }
}
