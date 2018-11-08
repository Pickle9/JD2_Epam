package by.epam.entity;

import java.util.Objects;

public class FerryBoat {

    public static final int DEFAULT_CARRYING = 7000;
    public static final int DEFAULT_SQUARE = 25;
    private int platformCarrying;
    private int platformSquare;

    public FerryBoat(int platformCarrying, int platformSquare) {
        this.platformCarrying = platformCarrying;
        this.platformSquare = platformSquare;
    }

    public int getPlatformCarrying() {
        return platformCarrying;
    }

    public int getPlatformSquare() {
        return platformSquare;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FerryBoat ferryBoat = (FerryBoat) o;
        return platformCarrying == ferryBoat.platformCarrying &&
                platformSquare == ferryBoat.platformSquare;
    }

    @Override
    public int hashCode() {
        return Objects.hash(platformCarrying, platformSquare);
    }

    @Override
    public String toString() {
        return "FerryBoat{" +
                "platformCarrying=" + platformCarrying +
                ", platformSquare=" + platformSquare +
                '}';
    }
}
