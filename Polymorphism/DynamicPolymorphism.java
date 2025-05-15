// Abstract class = Cannot be instantiated, only extended
// Provides base for all car types with some common methods and some abstract (empty) methods
abstract class Car {
    // Common properties shared by all car types
    protected String brand;
    protected String model;
    protected boolean isEngineOn;
    protected int currentSpeed;

    // Constructor to initialize common attributes
    public Car(String brand, String model) {
        this.brand = brand;
        this.model = model;
        this.isEngineOn = false;
        this.currentSpeed = 0;
    }

    // Non-abstract (concrete) method - common to all cars
    public void startEngine() {
        isEngineOn = true;
        System.out.println(brand + " " + model + " : Engine started.");
    }

    // Common method - stops engine and resets speed
    public void stopEngine() {
        isEngineOn = false;
        currentSpeed = 0;
        System.out.println(brand + " " + model + " : Engine turned off.");
    }

    // Abstract methods = No body, must be implemented by subclasses
    // Used to achieve Dynamic Polymorphism
    public abstract void accelerate();
    public abstract void brake();
}

// ManualCar is a specific type of Car => Inherits from Car
class ManualCar extends Car {
    private int currentGear;

    // Constructor - uses super() to call parent constructor
    public ManualCar(String brand, String model) {
        super(brand, model);
        this.currentGear = 0;
    }

    // ManualCar-specific method
    public void shiftGear(int gear) {
        currentGear = gear;
        System.out.println(brand + " " + model + " : Shifted to gear " + currentGear);
    }

    // Override accelerate method - specific logic for ManualCar
    @Override
    public void accelerate() {
        if (!isEngineOn) {
            System.out.println(brand + " " + model + " : Cannot accelerate! Engine is off.");
            return;
        }
        currentSpeed += 20;
        System.out.println(brand + " " + model + " : Accelerating to " + currentSpeed + " km/h");
    }

    // Override brake method - ManualCar brakes differently
    @Override
    public void brake() {
        currentSpeed -= 20;
        if (currentSpeed < 0) currentSpeed = 0;
        System.out.println(brand + " " + model + " : Braking! Speed is now " + currentSpeed + " km/h");
    }
}

// ElectricCar is another specific type of Car
class ElectricCar extends Car {
    private int batteryLevel;

    // Constructor - also calls super()
    public ElectricCar(String brand, String model) {
        super(brand, model);
        this.batteryLevel = 100;
    }

    // ElectricCar-specific method
    public void chargeBattery() {
        batteryLevel = 100;
        System.out.println(brand + " " + model + " : Battery fully charged!");
    }

    // Override accelerate - battery level impacts acceleration
    @Override
    public void accelerate() {
        if (!isEngineOn) {
            System.out.println(brand + " " + model + " : Cannot accelerate! Engine is off.");
            return;
        }
        if (batteryLevel <= 0) {
            System.out.println(brand + " " + model + " : Battery dead! Cannot accelerate.");
            return;
        }
        batteryLevel -= 10;
        currentSpeed += 15;
        System.out.println(brand + " " + model + " : Accelerating to " + currentSpeed
                           + " km/h. Battery at " + batteryLevel + "%.");
    }

    // Override brake - includes regenerative braking message
    @Override
    public void brake() {
        currentSpeed -= 15;
        if (currentSpeed < 0) currentSpeed = 0;
        System.out.println(brand + " " + model + " : Regenerative braking! Speed is now "
                           + currentSpeed + " km/h. Battery at " + batteryLevel + "%.");
    }
}

public class DynamicPolymorphism {
    public static void main(String[] args) {
        // Polymorphism: Parent class reference holding child object
        Car myManualCar = new ManualCar("Suzuki", "WagonR");
        myManualCar.startEngine();
        myManualCar.accelerate(); // Calls ManualCar's version
        myManualCar.accelerate();
        myManualCar.brake();      // Calls ManualCar's version
        myManualCar.stopEngine();

        System.out.println("----------------------");

        // Polymorphism: Same Car reference, but object is ElectricCar
        Car myElectricCar = new ElectricCar("Tesla", "Model S");
        myElectricCar.startEngine();
        myElectricCar.accelerate(); // Calls ElectricCar's version
        myElectricCar.accelerate();
        myElectricCar.brake();      // Calls ElectricCar's version
        myElectricCar.stopEngine();
    }
}
