package com.enginf2;

import java.util.LinkedList;

import com.enginf2.card.Card;

public class Player {
    private LinkedList<Card> hand;  // cards on the player's hand
    private final String name;
    private Team team;
    private int pos;    // playing position (changes during game)

    public Player(String name, int pos) {
        this.hand = new LinkedList<>();
        this.name = name;
        this.pos = pos;
    }

    public Card playCard(int index) {
        return hand.remove(index);
    }

    public LinkedList<Card> getHand() {
        return hand;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
    public Team getTeam() {
        return team;
    }

    public String getName() {
        return name;
    }

    public int getPos() {
        return pos;
    }

    public void receiveCard(Card card) {
        this.hand.add(card);
    }

    public boolean hasNoCards() {
        return hand.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Player " + (pos+1) + ": " + name + " -> {");
        for (Card card: hand) {
            sb.append(card.toString());
            if (card != hand.getLast()) sb.append(" ");
        }
        sb.append("}");
        return sb.toString();
    }
}
