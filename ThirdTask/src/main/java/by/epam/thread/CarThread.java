package by.epam.thread;

import by.epam.entity.Car;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CarThread implements Runnable {

    private static final Logger LOGGER = LogManager.getLogger();
    private Car car;

    public CarThread(Car car) {
        this.car = car;
    }

    @Override
    public void run() {
        LOGGER.log(Level.INFO, String.format("Автомобиль №%d подъехал к переправе.", car.getId()));

        FerryBoatThread.getInstance().registerCarAndSleep(this);

        LOGGER.log(Level.INFO, String.format("Автомобиль №%d переправлен.", car.getId()));
    }

    public Car getCar() {
        return car;
    }
}
