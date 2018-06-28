package pl.sdacademy.spring.car_dealer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.sdacademy.spring.car_dealer.controller.CarDataController;
import pl.sdacademy.spring.car_dealer.controller.SellingController;
import pl.sdacademy.spring.car_dealer.repository.*;
import pl.sdacademy.spring.car_dealer.service.CarDataService;
import pl.sdacademy.spring.car_dealer.service.DefaultCarDataService;
import pl.sdacademy.spring.car_dealer.service.DefaultSellingService;
import pl.sdacademy.spring.car_dealer.service.SellingService;

@Configuration
public class ApplicationConfiguration {

    // --- repositories ---

    @Bean
    public HardDriveVehicleRepository hardDriveVehicleRepository() {
        return new HardDriveVehicleRepository("vehicles.ser");
    }

    @Bean
    public HardDriveCustomerRepository hardDriveCustomerRepository() {
        return new HardDriveCustomerRepository("customers.ser");
    }

    @Bean
    public HardDrivePurchaseRepository hardDrivePurchaseRepository() {
        return new HardDrivePurchaseRepository("purchases.ser");
    }

    // --- services ---
    @Bean
    public DefaultCarDataService defaultCarDataService(VehicleRepository vehicleRepo) {
        return new DefaultCarDataService(vehicleRepo);
    }

    @Bean
    public DefaultSellingService defaultSellingService(
            VehicleRepository vehicleRepo, CustomerRepository customerRepo, PurchaseRepository purchaseRepo) {
        return new DefaultSellingService(vehicleRepo, customerRepo, purchaseRepo);
    }

    // --- controllers ---
    @Bean
    public CarDataController carDataController(CarDataService carDataService) {
        return new CarDataController(carDataService);
    }

    @Bean
    public SellingController sellingController(SellingService sellingService) {
        return new SellingController(sellingService);
    }

    //
    @Bean
    public Application application(CarDataController carDataController, SellingController sellingController) {
        Application application = new Application();
        application.setCarDataController(carDataController);
        application.setSellingController(sellingController);
        return application;
    }
}
