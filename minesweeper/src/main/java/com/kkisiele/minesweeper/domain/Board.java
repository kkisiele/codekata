package com.kkisiele.minesweeper.domain;

import java.util.*;

class Board {
    private final BoardConfiguration configuration;
    private final Map<FieldLocation, Field> fields = new HashMap<>();

    public Board(BoardConfiguration configuration) {
        this.configuration = configuration;
        initialize();
    }

    private void initialize() {
        initializeFields();
        placeRandomMines();
    }

    private void initializeFields() {
        for(int x = 0; x < configuration.getSize().columns(); x++) {
            for(int y = 0; y < configuration.getSize().rows(); y++) {
                addField(FieldLocation.of(x, y), new Field());
            }
        }
    }

    private void placeRandomMines() {
        placeRandomMines(configuration.getMineRatio().numberOfMines(this));
    }

    protected void addField(FieldLocation location, Field field) {
        fields.put(location, field);
    }

    protected Field field(FieldLocation location) {
        return fields.get(location);
    }

    public boolean isValidLocation(FieldLocation location) {
        return field(location) != null;
    }

    public boolean isFieldRevealed(FieldLocation location) {
        return field(location).isRevealed();
    }

    public int numberOfRevealedFields() {
        int count = 0;
        for(Field field : fields.values()) {
            if(field.isRevealed()) {
                count++;
            }
        }
        return count;
    }

    public void revealField(FieldLocation location) throws GameOverException {
        field(location).reveal();
    }

    public boolean hasMine(FieldLocation location) {
        return field(location).hasMine();
    }

    public int numberOfFields() {
        return fields.size();
    }

    public void placeRandomMines(int mines) {
        while(mines-- > 0) {
            placeMine(randomLocation());
        }
    }

    public void placeMine(FieldLocation location) {
        field(location).placeMine();
    }

    private FieldLocation randomLocation() {
        List<FieldLocation> locations = locationsEligibleToPlaceMine();
        return locations.get((int) (Math.random() * locations.size()));
    }

    private List<FieldLocation> locationsEligibleToPlaceMine() {
        List<FieldLocation> result = new ArrayList<>();
        for(Map.Entry<FieldLocation, Field> entry : fields.entrySet()) {
            if(entry.getValue().canPlaceMine()) {
                result.add(entry.getKey());
            }
        }
        return result;
    }

    public int numberOfNeighborhoodMines(FieldLocation location) {
        int count = 0;
        for(FieldLocation neighborhoodLocation : neighborhoodLocations(location)) {
            if(hasMine(neighborhoodLocation)) {
                count++;
            }
        }
        return count;
    }

    public Set<FieldLocation> neighborhoodLocations(FieldLocation location) {
        Set<FieldLocation> result = new HashSet<>();
        for(FieldLocation aroundLocation : location.aroundLocations()) {
            if(isValidLocation(aroundLocation)) {
                result.add(aroundLocation);
            }
        }
        return result;
    }
}