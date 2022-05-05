package com.enginf2;

import java.util.List;

import com.enginf2.card.Card;
import com.enginf2.card.Color;
import com.enginf2.card.Number;

public enum GameMode {
    SAUSPIEL_EICHEL("Sauspiel Eichel", new Number[] {Number.OBER, Number.UNTER}, Color.HERZ, Card.EICHEL_ASS),
    SAUSPIEL_GRAS("Sauspiel Gras", new Number[] {Number.OBER, Number.UNTER}, Color.HERZ, Card.GRAS_ASS),
    SAUSPIEL_SCHELLEN("Sauspiel Schellen", new Number[] {Number.OBER, Number.UNTER}, Color.HERZ, Card.SCHELLEN_ASS),
    
    GEIER("Geier", new Number[] {Number.OBER}, null, null),
    WENZ("Wenz", new Number[] {Number.OBER}, null, null),
    
    EICHEL_WENZ("Eichel Wenz", new Number[] {Number.UNTER}, Color.EICHEL, null),
    GRAS_WENZ("Gras Wenz", new Number[] {Number.UNTER}, Color.GRAS, null),
    HERZ_WENZ("Herz Wenz", new Number[] {Number.UNTER}, Color.HERZ, null),
    SCHELLEN_WENZ("Schellen Wenz", new Number[] {Number.UNTER}, Color.SCHELLEN, null),
    
    EICHEL_SOLO("Eichel Solo", new Number[] {Number.OBER, Number.UNTER}, Color.EICHEL, null),
    GRAS_SOLO("Gras Solo", new Number[] {Number.OBER, Number.UNTER}, Color.GRAS, null),
    HERZ_SOLO("Herz Solo", new Number[] {Number.OBER, Number.UNTER}, Color.HERZ, null),
    SCHELLEN_SOLO("Schellen Solo", new Number[] {Number.OBER, Number.UNTER}, Color.SCHELLEN, null);

    public static final GameMode all_modes[] = {GameMode.GEIER, GameMode.WENZ, 
        GameMode.SAUSPIEL_EICHEL, GameMode.SAUSPIEL_GRAS, GameMode.SAUSPIEL_SCHELLEN, 
        GameMode.EICHEL_WENZ, GameMode.GRAS_WENZ, GameMode.HERZ_WENZ, GameMode.SCHELLEN_WENZ, 
        GameMode.EICHEL_SOLO, GameMode.GRAS_SOLO, GameMode.HERZ_SOLO, GameMode.SCHELLEN_SOLO};

    private static final int WEGLAUFEN_CARDS = 4;
    private static final int SCHMIERGRENZE_CALLED_CARD = 2;

    private final String name;
    private final Number trump_number[];
    private final Color trump_color;
    private final Card called_card;

    GameMode(String name, Number trump_number[], Color trump_color, Card called_card) {
        this.name = name;
        this.trump_number = trump_number;
        this.trump_color = trump_color;
        this.called_card = called_card;
    }

    public boolean is_solo_game() {
        return called_card == null;
    }

    public boolean move_is_valid(Card card, List<Card> pile, List<Card> playersCards) {
        if (pile.isEmpty()) {  // no cards in pile
            // rules for weglaufen
            if (card.getColor().equals(this.called_card.getColor()) && // player wants to play called color
                playersCards.stream().anyMatch(c -> c.equals(this.called_card)) &&  // player has called card...
                playersCards.stream().filter(c -> c.getColor().equals(this.called_card.getColor())).count() < WEGLAUFEN_CARDS) // player can't "weglaufen"...
                return false;
            return true;
        } 

        Card first = pile.get(0);

        // first played color is trump
        if (is_trump(first) && playersCards.stream().anyMatch(c -> is_trump(c))) {  // player has trump
            return is_trump(card);
        }
        // first played color is not trump
        else if (playersCards.stream().anyMatch(c -> c.getColor().equals(first.getColor()))) { // player has first played color
            if (first.getColor().equals(card.getColor()) && !is_trump(card)) {  // player wants to play correct color
                // correct response to calling of a card
                if ((first.getColor().equals(this.called_card.getColor()) &&            // first color is called color
                    playersCards.stream().anyMatch(c -> c.equals(this.called_card)) &&  // player has called card...
                    !card.equals(this.called_card)))                                    // ...but doesn't want to play it
                    return false;
                return true;
            }
            else {  // player wants to play wrong color
                return false;
            }
        }
        // player doesn't have first played color
        else {
            // called ass can't be "geschmiert"
            if (card.equals(this.called_card) &&                    // player wants to play called card
                playersCards.size() > SCHMIERGRENZE_CALLED_CARD)    // player has more than 2 cards left
                return false;
            return true;
        }
    }

    private boolean is_trump(Card card) {
        return card.getColor().equals(trump_color) || 
            (card.getNumber() != null &&
            (card.getNumber().equals(trump_number[0]) || card.getNumber().equals(trump_number[1])));
    }

    public Card find_biggest(List<Card> cards) {
        List<Card> cards_trump = cards.stream().filter(c -> is_trump(c)).toList();
        if (cards_trump.isEmpty()) {
            return cards.stream()
                .filter(c -> c.getColor().equals(cards.get(0).getColor()))
                .sorted((c1, c2) -> Integer.compare(c1.getNumber().getValue(), c2.getNumber().getValue()))
                .reduce((first, second) -> second).get();
        }
        else {
            return cards_trump.stream()
                .sorted((c1, c2) -> Integer.compare(c1.getColor().getSoloValue(), c2.getColor().getSoloValue()))
                .reduce((first, second) -> second).get();
        }
    }

    public Card getCalled_card() {
        return called_card;
    }

    public String getName() {
        return name;
    }
}
