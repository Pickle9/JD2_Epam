package by.epam;

import by.epam.parser.CarsDataSAXParser;
import by.epam.parser.FerryBoatDataSAXParser;
import by.epam.thread.CarThread;
import by.epam.thread.FerryBoatThread;

import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        List<CarThread> cars = CarsDataSAXParser.parseCarsData("data/car_data.xml");
        Map<String, Integer> ferryBoat = FerryBoatDataSAXParser.parseFerryBoatData("data/ferry_boat_data.xml");

        ExecutorService carService = Executors.newCachedThreadPool();
        ScheduledExecutorService BoatService = Executors.newScheduledThreadPool(1);

        int platformCarrying = ferryBoat.get("platformCarrying");
        int platformSquare = ferryBoat.get("platformSquare");
        BoatService.scheduleAtFixedRate(FerryBoatThread.getInstance(platformCarrying, platformSquare),0, 1, TimeUnit.SECONDS);

        for (CarThread car : cars) {
            carService.submit(car);
            TimeUnit.SECONDS.sleep(1);
        }

        BoatService.shutdown();
        carService.shutdown();
    }
}
