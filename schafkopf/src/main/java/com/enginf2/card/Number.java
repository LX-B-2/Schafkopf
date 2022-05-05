package com.enginf2.card;

public enum Number {
    SIEBEN("Sieben", 0, 0), 
    ACHT("Acht", 0, 1), 
    NEUN("Neun", 0, 2), 
    ZEHN("Zehn", 10, 3), 
    UNTER("Unter", 2, 4), 
    OBER("Ober", 3, 5), 
    KOENIG("Koenig", 4, 6),
    ASS("Ass", 11, 7);

    public static final Number allNumbers[] = {Number.SIEBEN, Number.ACHT, Number.NEUN, Number.ZEHN, Number.OBER, Number.UNTER, Number.KOENIG, Number.ASS};

    private final String name;
    private final int points;
    private final int value;

    Number(String n_name, int points, int value) {
        this.name = n_name;
        this.points = points;
        this.value = value;
    }

    public int getPoints() {
        return points;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return name;
    }
}
