package by.epam.thread;

import by.epam.entity.FerryBoat;
import by.epam.state.CarState;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class FerryBoatThread implements Runnable {

    private static final Logger LOGGER = LogManager.getLogger();
    private static FerryBoatThread instance;
    private static ReentrantLock lock = new ReentrantLock();
    private static AtomicBoolean isCreate = new AtomicBoolean(false);

    private FerryBoat ferryBoat;
    private CopyOnWriteArrayList<CarThread> registeredCars = new CopyOnWriteArrayList<>();

    private FerryBoatThread(FerryBoat ferryBoat) {
        this.ferryBoat = ferryBoat;
    }

    // Для вызова созданного объекта из любого места
    public static FerryBoatThread getInstance() {
        if (!isCreate.get()) {
            getInstance(FerryBoat.DEFAULT_CARRYING, FerryBoat.DEFAULT_SQUARE);
        }
        return instance;
    }

    // Для первой инициализации
    public static FerryBoatThread getInstance(int platformCarrying, int platformSquare) {
        if (!isCreate.get()) {
            try {
                lock.lock();
                if (instance == null) {
                    instance = new FerryBoatThread(new FerryBoat(platformCarrying, platformSquare));
                    isCreate.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    @Override
    public void run() {
        while (registeredCars.size() > 0) {

            int currentCarrying = ferryBoat.getPlatformCarrying();
            int currentSquare = ferryBoat.getPlatformSquare();
            int loadedCarsIndex = 0;

            while (true) {
                for (CarThread carThread : registeredCars) {
                    if (carThread.getCar().getState() == CarState.LOADED) {
                        continue;
                    }

                    if (currentCarrying > carThread.getCar().getWeight() && currentSquare > carThread.getCar().getSquare()) {

                        carThread.getCar().setState(CarState.LOADED);
                        LOGGER.log(Level.INFO, String.format("Автомобиль №%d загружен на корабль.", carThread.getCar().getId()));

                        currentCarrying -= carThread.getCar().getWeight();
                        currentSquare -= carThread.getCar().getSquare();

                    } else {
                        LOGGER.log(Level.INFO, String.format("Автомобиль №%d не вмещается на корабль.", carThread.getCar().getId()));
                    }
                }

                int currentLoadedCarsIndex = 0;
                for (CarThread car : registeredCars) {
                    if (car.getCar().getState() == CarState.LOADED)
                        currentLoadedCarsIndex++;
                }

                if (loadedCarsIndex == currentLoadedCarsIndex) {
                    break;
                } else {
                    loadedCarsIndex = currentLoadedCarsIndex;
                }

                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    LOGGER.log(Level.ERROR, e);
                }
            }

            LOGGER.log(Level.INFO, "Паром отправился!");

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                LOGGER.log(Level.ERROR, e);
            }

            LOGGER.log(Level.INFO, "Паром на другой сторон!");

            for (CarThread carThread : registeredCars) {
                if (carThread.getCar().getState() == CarState.LOADED) {
                    carThread.getCar().setState(CarState.REDIRECTED);
                }
            }

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                LOGGER.log(Level.ERROR, e);
            }

            LOGGER.log(Level.INFO, "Паром вернулся и готов к загрузке!");
        }
    }

    public void registerCarAndSleep(CarThread carThread) {
        registeredCars.add(carThread);

        while (carThread.getCar().getState() != CarState.REDIRECTED) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                LOGGER.log(Level.ERROR, e);
            }
        }

        for (CarThread listCarThread : registeredCars) {
            if (listCarThread.getCar().getState() == CarState.REDIRECTED) {
                registeredCars.remove(listCarThread);
            }
        }
    }
}
