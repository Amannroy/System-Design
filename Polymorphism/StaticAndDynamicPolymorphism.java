// -------------------- Abstract Base Class --------------------

// Abstract class that defines common structure and behavior for all cars
abstract class Car {
    protected String brand;
    protected String model;
    protected boolean isEngineOn;
    protected int currentSpeed;

    // Constructor to initialize brand and model of car
    public Car(String brand, String model) {
        this.brand = brand;
        this.model = model;
        this.isEngineOn = false;
        this.currentSpeed = 0;
    }

    // Common functionality: starting the engine
    public void startEngine() {
        isEngineOn = true;
        System.out.println(brand + " " + model + " : Engine started.");
    }

    // Common functionality: stopping the engine
    public void stopEngine() {
        isEngineOn = false;
        currentSpeed = 0;
        System.out.println(brand + " " + model + " : Engine turned off.");
    }

    // Abstract methods (must be overridden by child classes) → used for dynamic polymorphism
    public abstract void accelerate();             // no-argument version
    public abstract void accelerate(int speed);    // overloaded version
    public abstract void brake();                  // common but with different implementations
}


// ManualCar is a specific type of Car (inherits Car)
class ManualCar extends Car {
    private int currentGear;

    // Constructor: call superclass constructor and initialize gear
    public ManualCar(String brand, String model) {
        super(brand, model);
        this.currentGear = 0;
    }

    // Specific feature only in manual cars
    public void shiftGear(int gear) {
        currentGear = gear;
        System.out.println(brand + " " + model + " : Shifted to gear " + currentGear);
    }

    // Overridden method → example of dynamic polymorphism
    @Override
    public void accelerate() {
        if (!isEngineOn) {
            System.out.println(brand + " " + model + " : Cannot accelerate! Engine is off.");
            return;
        }
        currentSpeed += 20;
        System.out.println(brand + " " + model + " : Accelerating to " + currentSpeed + " km/h");
    }

    // Method Overloading + Overriding → static + dynamic polymorphism
    @Override
    public void accelerate(int speed) {
        if (!isEngineOn) {
            System.out.println(brand + " " + model + " : Cannot accelerate! Engine is off.");
            return;
        }
        currentSpeed += speed;
        System.out.println(brand + " " + model + " : Accelerating to " + currentSpeed + " km/h");
    }

    // Braking implementation specific to manual car
    @Override
    public void brake() {
        currentSpeed -= 20;
        if (currentSpeed < 0) currentSpeed = 0;
        System.out.println(brand + " " + model + " : Braking! Speed is now " + currentSpeed + " km/h");
    }
}


// ElectricCar is another type of Car (inherits Car)
class ElectricCar extends Car {
    private int batteryLevel;

    // Constructor: call superclass constructor and initialize battery
    public ElectricCar(String brand, String model) {
        super(brand, model);
        this.batteryLevel = 100;
    }

    // Extra feature specific to electric cars
    public void chargeBattery() {
        batteryLevel = 100;
        System.out.println(brand + " " + model + " : Battery fully charged!");
    }

    // Overridden method → different implementation for electric car
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
        System.out.println(brand + " " + model + " : Accelerating to " + currentSpeed +
                           " km/h. Battery at " + batteryLevel + "%.");
    }

    // Overloaded + Overridden version with different behavior
    @Override
    public void accelerate(int speed) {
        if (!isEngineOn) {
            System.out.println(brand + " " + model + " : Cannot accelerate! Engine is off.");
            return;
        }
        if (batteryLevel <= 0) {
            System.out.println(brand + " " + model + " : Battery dead! Cannot accelerate.");
            return;
        }
        batteryLevel -= 10 + speed;  // speed affects battery consumption
        currentSpeed += speed;
        System.out.println(brand + " " + model + " : Accelerating to " + currentSpeed +
                           " km/h. Battery at " + batteryLevel + "%.");
    }

    // Braking with battery regeneration
    @Override
    public void brake() {
        currentSpeed -= 15;
        if (currentSpeed < 0) currentSpeed = 0;
        System.out.println(brand + " " + model +
                           " : Regenerative braking! Speed is now " + currentSpeed +
                           " km/h. Battery at " + batteryLevel + "%.");
    }
}


// Main class to run the program
public class StaticAndDynamicPolymorphism {
    public static void main(String[] args) {
        // Polymorphic reference → Car refers to ManualCar object
        Car myManualCar = new ManualCar("Ford", "Mustang");
        myManualCar.startEngine();
        myManualCar.accelerate();        // calls ManualCar's version
        myManualCar.accelerate();        // dynamic + static polymorphism
        myManualCar.brake();             // ManualCar's brake
        myManualCar.stopEngine();

        System.out.println("----------------------");

        // Polymorphic reference → Car refers to ElectricCar object
        Car myElectricCar = new ElectricCar("Tesla", "Model S");
        myElectricCar.startEngine();
        myElectricCar.accelerate();      // ElectricCar's accelerate
        myElectricCar.accelerate();      // same method called again
        myElectricCar.brake();           // ElectricCar's brake
        myElectricCar.stopEngine();
    }
}
