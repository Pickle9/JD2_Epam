package by.epam.entity;

import by.epam.state.CarState;

import java.util.Objects;

public class Car {

    private long id;
    private CarType type;
    private int weight;
    private int square;
    private CarState state;

    public Car(long id, CarType type, int weight, int square) {
        this.id = id;
        this.type = type;
        this.weight = weight;
        this.square = square;
        state = CarState.WAITING_FOR_LOAD;
    }

    public long getId() {
        return id;
    }

    public CarType getType() {
        return type;
    }

    public int getWeight() {
        return weight;
    }

    public int getSquare() {
        return square;
    }

    public CarState getState() {
        return state;
    }

    public void setState(CarState state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id &&
                weight == car.weight &&
                square == car.square &&
                type == car.type &&
                state == car.state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, weight, square, state);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", type=" + type +
                ", weight=" + weight +
                ", square=" + square +
                ", state=" + state +
                '}';
    }
}
