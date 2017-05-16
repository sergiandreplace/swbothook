package com.blindbugs.swbot.domain.people;

import java.util.List;

public class People {
    private final String name;
    private final String birthYear;
    private final List<Integer> films;
    private final String gender;
    private final String hairColor;
    private final String height;
    private final int homeWorld;
    private final String mass;
    private final String skinColor;
    private final String created;
    private final String edited;
    private final List<Integer> species;
    private final List<Integer> starShips;
    private final List<Integer> vehicles;

    public People(Builder builder) {
        this.name = builder.name;
        this.hairColor = builder.hairColor;
        this.birthYear = builder.birthYear;
        this.gender = builder.gender;
        this.edited = builder.edited;
        this.height = builder.height;
        this.species = builder.species;
        this.films = builder.films;
        this.homeWorld = builder.homeWorld;
        this.skinColor = builder.skinColor;
        this.starShips = builder.starShips;
        this.created = builder.created;
        this.mass = builder.mass;
        this.vehicles = builder.vehicles;
    }

    public String getName() {
        return name;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public List<Integer> getFilms() {
        return films;
    }

    public String getGender() {
        return gender;
    }

    public String getHairColor() {
        return hairColor;
    }

    public String getHeight() {
        return height;
    }

    public int getHomeWorld() {
        return homeWorld;
    }

    public String getMass() {
        return mass;
    }

    public String getSkinColor() {
        return skinColor;
    }

    public String getCreated() {
        return created;
    }

    public String getEdited() {
        return edited;
    }

    public List<Integer> getSpecies() {
        return species;
    }

    public List<Integer> getStarShips() {
        return starShips;
    }

    public List<Integer> getVehicles() {
        return vehicles;
    }


    public static final class Builder {
        private String name;
        private String birthYear;
        private List<Integer> films;
        private String gender;
        private String hairColor;
        private String height;
        private int homeWorld;
        private String mass;
        private String skinColor;
        private String created;
        private String edited;
        private List<Integer> species;
        private List<Integer> starShips;
        private List<Integer> vehicles;

        public Builder() {
        }

        public Builder name(String name) {
            this.name= name;
            return this;
        }

        public Builder birthYear(String birthYear) {
            this.birthYear = birthYear;
            return this;
        }

        public Builder films(List<Integer> films) {
            this.films = films;
            return this;
        }

        public Builder gender(String gender) {
            this.gender = gender;
            return this;
        }

        public Builder hairColor(String hairColor) {
            this.hairColor = hairColor;
            return this;
        }

        public Builder height(String height) {
            this.height = height;
            return this;
        }

        public Builder homeWorld(int homeWorld) {
            this.homeWorld = homeWorld;
            return this;
        }

        public Builder mass(String mass) {
            this.mass = mass;
            return this;
        }

        public Builder skinColor(String skinColor) {
            this.skinColor = skinColor;
            return this;
        }

        public Builder created(String created) {
            this.created = created;
            return this;
        }

        public Builder edited(String edited) {
            this.edited = edited;
            return this;
        }

        public Builder species(List<Integer> species) {
            this.species = species;
            return this;
        }

        public Builder starShips(List<Integer> starShips) {
            this.starShips = starShips;
            return this;
        }

        public Builder vehicles(List<Integer> vehicles) {
            this.vehicles = vehicles;
            return this;
        }

        public People build() {
            People people = new People(this);
            return people;
        }
    }
}
