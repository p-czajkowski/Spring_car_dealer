package pl.sdacademy.spring.car_dealer;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import pl.sdacademy.spring.car_dealer.controller.CarDataController;
import pl.sdacademy.spring.car_dealer.controller.SellingController;
import pl.sdacademy.spring.car_dealer.repository.*;
import pl.sdacademy.spring.car_dealer.service.CarDataService;
import pl.sdacademy.spring.car_dealer.service.DefaultCarDataService;
import pl.sdacademy.spring.car_dealer.service.DefaultSellingService;
import pl.sdacademy.spring.car_dealer.service.SellingService;

@Configuration
@PropertySource("classpath:application.properties")
public class ApplicationConfiguration {

    @Bean
    static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer () {
        return new PropertySourcesPlaceholderConfigurer();
    }

    // --- repositories ---

    @Bean
    public HardDriveVehicleRepository hardDriveVehicleRepository(
            @Value("${repository.vehicle.hardDriveLocation}") String vehicleRepositoryLocation) {
        return new HardDriveVehicleRepository(vehicleRepositoryLocation);
    }

    @Bean
    public HardDriveCustomerRepository hardDriveCustomerRepository(
            @Value("${repository.customer.hardDriveLocation}") String customerRepositoryLocation) {
        return new HardDriveCustomerRepository(customerRepositoryLocation);
    }

    @Bean
    public HardDrivePurchaseRepository hardDrivePurchaseRepository(
            @Value("${repository.purchase.hardDriveLocation}") String purchaseRepositoryLocation
    ) {
        return new HardDrivePurchaseRepository(purchaseRepositoryLocation);
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
    @Bean(autowire = Autowire.BY_TYPE)
    public Application application() {
        return new Application();
    }
}
