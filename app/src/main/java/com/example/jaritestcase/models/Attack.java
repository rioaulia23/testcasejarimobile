package com.example.jaritestcase.models;

import java.util.List;

public class Attack {
    public String name, damage, text;
    public List<String> cost;
    public int convertedEnergyCost;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getCost() {
        return cost;
    }

    public void setCost(List<String> cost) {
        this.cost = cost;
    }

    public int getConvertedEnergyCost() {
        return convertedEnergyCost;
    }

    public void setConvertedEnergyCost(int convertedEnergyCost) {
        this.convertedEnergyCost = convertedEnergyCost;
    }

    public String getDamage() {
        return damage;
    }

    public void setDamage(String damage) {
        this.damage = damage;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}