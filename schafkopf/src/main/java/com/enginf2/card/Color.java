package com.enginf2.card;

public enum Color {
    SCHELLEN("Schellen", 0), 
    HERZ("Herz", 1), 
    GRAS("Gras", 2), 
    EICHEL("Eichel", 3);

    public static final Color allColors[] = {Color.SCHELLEN, Color.HERZ, Color.GRAS, Color.EICHEL};
    private final int soloValue;
    private final String name;

    Color(String n_name, int soloValue) {
        this.name = n_name;
        this.soloValue = soloValue;
    }

    public int getSoloValue() {
        return soloValue;
    }

    @Override
    public String toString() {
        return name;
    }
}
