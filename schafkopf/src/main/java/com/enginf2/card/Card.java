package com.enginf2.card;

public enum Card {
    EICHEL_SIEBEN(Color.EICHEL, Number.SIEBEN), 
    EICHEL_ACHT(Color.EICHEL, Number.ACHT), 
    EICHEL_NEUN(Color.EICHEL, Number.NEUN), 
    EICHEL_ZEHN(Color.EICHEL, Number.ZEHN), 
    EICHEL_UNTER(Color.EICHEL, Number.UNTER), 
    EICHEL_OBER(Color.EICHEL, Number.OBER), 
    EICHEL_KOENIG(Color.EICHEL, Number.KOENIG), 
    EICHEL_ASS(Color.EICHEL, Number.ASS),

    GRAS_SIEBEN(Color.GRAS, Number.SIEBEN), 
    GRAS_ACHT(Color.GRAS, Number.ACHT), 
    GRAS_NEUN(Color.GRAS, Number.NEUN), 
    GRAS_ZEHN(Color.GRAS, Number.ZEHN), 
    GRAS_UNTER(Color.GRAS, Number.UNTER), 
    GRAS_OBER(Color.GRAS, Number.OBER), 
    GRAS_KOENIG(Color.GRAS, Number.KOENIG), 
    GRAS_ASS(Color.GRAS, Number.ASS),

    HERZ_SIEBEN(Color.HERZ, Number.SIEBEN), 
    HERZ_ACHT(Color.HERZ, Number.ACHT), 
    HERZ_NEUN(Color.HERZ, Number.NEUN), 
    HERZ_ZEHN(Color.HERZ, Number.ZEHN), 
    HERZ_UNTER(Color.HERZ, Number.UNTER), 
    HERZ_OBER(Color.HERZ, Number.OBER), 
    HERZ_KOENIG(Color.HERZ, Number.KOENIG), 
    HERZ_ASS(Color.HERZ, Number.ASS),

    SCHELLEN_SIEBEN(Color.SCHELLEN, Number.SIEBEN), 
    SCHELLEN_ACHT(Color.SCHELLEN, Number.ACHT), 
    SCHELLEN_NEUN(Color.SCHELLEN, Number.NEUN), 
    SCHELLEN_ZEHN(Color.SCHELLEN, Number.ZEHN), 
    SCHELLEN_UNTER(Color.SCHELLEN, Number.UNTER), 
    SCHELLEN_OBER(Color.SCHELLEN, Number.OBER), 
    SCHELLEN_KOENIG(Color.SCHELLEN, Number.KOENIG), 
    SCHELLEN_ASS(Color.SCHELLEN, Number.ASS);
    
    public static final Card whole_deck[] = {
        EICHEL_SIEBEN, EICHEL_ACHT, EICHEL_NEUN, EICHEL_ZEHN, EICHEL_UNTER, EICHEL_OBER, EICHEL_KOENIG, EICHEL_ASS,
        GRAS_SIEBEN, GRAS_ACHT, GRAS_NEUN, GRAS_ZEHN, GRAS_UNTER, GRAS_OBER, GRAS_KOENIG, GRAS_ASS,
        HERZ_SIEBEN, HERZ_ACHT, HERZ_NEUN, HERZ_ZEHN, HERZ_UNTER, HERZ_OBER, HERZ_KOENIG, HERZ_ASS,
        SCHELLEN_SIEBEN, SCHELLEN_ACHT, SCHELLEN_NEUN, SCHELLEN_ZEHN, SCHELLEN_UNTER, SCHELLEN_OBER, SCHELLEN_KOENIG, SCHELLEN_ASS};
    

    private final Color color;
    private final Number number;

    private Card(Color color, Number number) {
        this.color = color;
        this.number = number;
    }

    public Color getColor() {
        return color;
    }

    public Number getNumber() {
        return number;
    }

    public int getPoints() {
        return this.number.getPoints();
    }

    @Override
    public String toString() {
        return "{" + color.toString() + " " + number.toString() + "}";
    }
}
