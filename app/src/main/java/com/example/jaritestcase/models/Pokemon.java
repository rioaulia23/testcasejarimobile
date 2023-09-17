package com.example.jaritestcase.models;

import java.util.List;

public class Pokemon {
    public String id, name, supertype, hp, evolvesFrom, number, artist, rarity, flavorText, imagePath;
    public List<String> subtypes, types, retreatCost;
    public List<Attack> attacks;
    public List<Weakness> weaknesses;
    public List<Resistance> resistances;
    public int convertedRetreatCost;

    public List<Integer> nationalPokedexNumbers;

    public Images images;

    public Pokemon(String id, String name, String imagePath) {
        this.id = id;
        this.name = name;
        this.imagePath = imagePath;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSupertype() {
        return supertype;
    }

    public void setSupertype(String supertype) {
        this.supertype = supertype;
    }

    public List<String> getSubtypes() {
        return subtypes;
    }

    public void setSubtypes(List<String> subtypes) {
        this.subtypes = subtypes;
    }

    public String getHp() {
        return hp;
    }

    public void setHp(String hp) {
        this.hp = hp;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public String getEvolvesFrom() {
        return evolvesFrom;
    }

    public void setEvolvesFrom(String evolvesFrom) {
        this.evolvesFrom = evolvesFrom;
    }

    public List<Attack> getAttacks() {
        return attacks;
    }

    public void setAttacks(List<Attack> attacks) {
        this.attacks = attacks;
    }

    public List<Weakness> getWeaknesses() {
        return weaknesses;
    }

    public void setWeaknesses(List<Weakness> weaknesses) {
        this.weaknesses = weaknesses;
    }

    public List<Resistance> getResistances() {
        return resistances;
    }

    public void setResistances(List<Resistance> resistances) {
        this.resistances = resistances;
    }

    public List<String> getRetreatCost() {
        return retreatCost;
    }

    public void setRetreatCost(List<String> retreatCost) {
        this.retreatCost = retreatCost;
    }

    public int getConvertedRetreatCost() {
        return convertedRetreatCost;
    }

    public void setConvertedRetreatCost(int convertedRetreatCost) {
        this.convertedRetreatCost = convertedRetreatCost;
    }


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public String getFlavorText() {
        return flavorText;
    }

    public void setFlavorText(String flavorText) {
        this.flavorText = flavorText;
    }

    public List<Integer> getNationalPokedexNumbers() {
        return nationalPokedexNumbers;
    }

    public void setNationalPokedexNumbers(List<Integer> nationalPokedexNumbers) {
        this.nationalPokedexNumbers = nationalPokedexNumbers;
    }


    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }


    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }


}

