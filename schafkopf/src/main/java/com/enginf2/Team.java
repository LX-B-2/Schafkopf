package com.enginf2;

import java.util.LinkedList;

import com.enginf2.card.Card;

public class Team {
    private LinkedList<Card> won_cards;
    private final int id;

    public Team(int id) {
        this.id = id;
        this.won_cards = new LinkedList<>();
    }
    
    /**
     * Calculate the points of this team with the cards won.
     * @return number of points of the team
     */
    public int getPoints() {
        int points = 0;
        for (Card card: won_cards) {
            points += card.getPoints();
        }
        return points;
    }

    /**
     * Add a pile that this team has won.
     * @param pile exactly four cards.
     */
    public void addCards(LinkedList<Card> pile) {
        if (pile.size() != 4) return;
        this.won_cards.addAll(pile);
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Teams: \n- Team " + (id+1) + " -> {");
        for (Card card: won_cards) {
            sb.append(card.toString());
            if (card == won_cards.getLast()) sb.append(" ");
        }
        return sb.toString();
    }
}
