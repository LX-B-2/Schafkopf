package com.enginf2;

import java.util.Collections;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

import com.enginf2.card.Card;

public class Game {
    public static final int DECKSIZE = 32;
    public static final int PLAYERCOUNT = 4;
    public static final int TEAMCOUNT = 2;
    public static final int DISTRIBUTE_AT_ONCE = 4;
    public static final int ROUND_COUNT = 4;

    private final LinkedList<Card> pile;    // non-distributed cards in beginning, becomes pile in the middle later

    private final Player players[];
    private final Team teams[];     // teams[0] always playing team
    
    private Player head_player; // the player who initiated game
    private GameMode gameMode;

    private int turn;
    private int rounds_left;    

    public Game(Player players[]) {
        this.players = players;
        this.teams = new Team[TEAMCOUNT];
        this.pile = new LinkedList<>();
        this.turn = 0;
        this.gameMode = GameMode.SAUSPIEL_EICHEL;
        this.rounds_left = ROUND_COUNT;
        reset_order();
    }

    /**
     * Reset the pile to the order in the deck.
     */
    private void reset_order() {
        this.pile.clear();
        for (Card card: Card.whole_deck) {
            this.pile.add(card);
        }
    }

    /**
     * Shuffle the cards randomly.
     */
    public void shuffle() {
        Collections.shuffle(this.pile, ThreadLocalRandom.current());
    }

    /**
     * Distribute the cards to all players.
     */
    public void distribute() {
        for(int i = 0; !pile.isEmpty(); i++) {
            if (i == PLAYERCOUNT) i = 0;
            for (int j = 0; j < DISTRIBUTE_AT_ONCE; j++) {
                players[i].receiveCard(pile.pop());;
            }
        }
    }

    /**
     * Determines the player whose turn it is.
     * @return player or null if error
     */
    public Player getTurn() {
        for (Player player: players) {
            if (player.getPos() == turn) return player;
        }
        return null;
    }

    public void setGameMode(Player head_player, GameMode gameMode) {
        this.head_player = head_player;
        this.gameMode = gameMode;

        if (gameMode.is_solo_game()) {
            Team player_team = new Team(0);
            this.teams[0] = player_team;
            this.head_player.setTeam(player_team);
        }
        else {
            Team player_team = new Team(0);
            this.teams[0] = player_team;
            this.head_player.setTeam(player_team);
            for (Player player: players) {
                if (player.getHand().contains(gameMode.getCalled_card()))
                    player.setTeam(player_team);
            }
        }

        // add rest of the players other last team
        Team team = new Team(1);
        this.teams[1] = team;
        for (Player player: players) {
            if (player.getTeam() == null) {
                player.setTeam(team);
            }
        }
    }

    public boolean move_is_valid(Player player, int card_pos) {
        return gameMode.move_is_valid(player.getHand().get(card_pos), pile, player.getHand());
    }

    /**
     * Let player play card and advance game, aborts in case of invalid move.
     * @param player player who wants to play card
     * @param card_pos position of the card to be played
     */
    public void playCard(Player player, int card_pos) {
        // player plays card
        pile.add(player.playCard(card_pos));
        
        // advance turn
        turn = player.getPos()+1;
        if (turn == PLAYERCOUNT) endPile();
    }
    
    private void endPile() {
        // reset turn
        turn = 0;

        players[pile.indexOf(gameMode.find_biggest(pile))].getTeam().addCards(pile);
        pile.clear();
        
        // end round if necessary
        if (players[0].hasNoCards()) endRound();
    }

    private void endRound() {
        // rotate players
        Player player = null;
        for (int i = PLAYERCOUNT-1; i > 0; i--) {
            player = players[i-1];
            players[i-1] = players[i];
        }
        players[PLAYERCOUNT-1] = player;

        rounds_left--;
        // count points
        // determine winner
    }

    public Team[] getTeams() {
        return teams;
    }

    public boolean game_finished() {
        return rounds_left <= 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nPile: {");
        // for (Card card: pile) {
        //     sb.append(card.toString());
        //     if (card != pile.getLast()) sb.append(" ");
        // }
        sb.append("}\n\n");

        if (teams[0] == null) {
            for (Player player: players) {
                sb.append("- ");
                sb.append(player.toString());
                sb.append("\n");
            }
        } else {
            for (Team team: teams) {
                sb.append("Team " + team.getId() + " (" + team.getPoints() + "P):\n");
                for (Player player: players) {
                    if (player.getTeam().equals(team)) {
                        sb.append("  - ");
                        sb.append(player.toString());
                        sb.append("\n");
                    }
                }
            }
        }
        return sb.toString();
    }
}
