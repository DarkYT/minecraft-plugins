package com.endwork.api.ranks;

public class Rank {

    private int strength;
    private String name, initials, color;

    public Rank(int strength, String name, String initials, String color) {
        this.strength = strength;
        this.name = name;
        this.initials = initials;
        this.color = color;
    }

    public int getStrength() {
        return strength;
    }

    public String getName() {
        return name;
    }

    public String getInitials() {
        return initials;
    }

    public String getColor() {
        return color;
    }

    public int getId() {
        return RankAPI.getRankId(getName());
    }

}
