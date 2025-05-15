// Interface defines the basic functions every Car must have
interface Car {
    void startEngine();
    void shiftGear(int gear);
    void accelerate();
    void brake();
    void stopEngine();
}

// SportsCar implements the Car interface and provides actual working code for each function
class SportsCar implements Car {
    String brand;
    String model;
    boolean isEngineOn = false;
    int currentSpeed = 0;
    int currentGear = 0;

    // Constructor to initialize car brand and model
    public SportsCar(String brand, String model){
        this.brand = brand;
        this.model = model;
    }

    // Starts the car engine
    @Override
    public void startEngine() {
        isEngineOn = true;
        System.out.println(brand + " " + model + " : Engine starts with a roar!");
    }

    // Changes the gear if engine is on
    @Override
    public void shiftGear(int gear) {
        if (!isEngineOn) {
            System.out.println(brand + " " + model + " : Engine is off! Cannot Shift Gear.");
            return;
        }
        this.currentGear = gear;
        System.out.println(brand + " " + model + " : Shifted to gear " + currentGear);
    }

    // Increases the speed if engine is on
    @Override 
    public void accelerate() {
        if (!isEngineOn) {
            System.out.println(brand + " " + model + " : Engine is off! Cannot Shift Gear.");
            return;
        }
        currentSpeed += 20;
        System.out.println(brand + " " + model + " : Accelerating to " + currentSpeed + " km/h");
    }

    // Decreases the speed by 20, but not below 0
    @Override
    public void brake() {
        currentSpeed -= 20;
        if (currentSpeed < 0) currentSpeed = 0;
        System.out.println(brand + " " + model + " : Braking! Speed is now " + currentSpeed + " km/h");
    }

    // Stops the engine and resets gear and speed
    @Override
    public void stopEngine() {
        isEngineOn = false;
        currentGear = 0;
        currentSpeed = 0;
        System.out.println(brand + " " + model + " : Engine turned off.");
    }
}

// Main class to test the SportsCar
class Main {
    public static void main(String[] args) {
        // Create a new SportsCar object using Car interface
        Car myCar = new SportsCar("Ford", "Mustang");

        // Simulate driving actions
        myCar.startEngine();    // Starts the engine
        myCar.shiftGear(1);     // Shift to 1st gear
        myCar.accelerate();     // Speed up
        myCar.shiftGear(1);     // Stay in same gear
        myCar.accelerate();     // Speed up again
        myCar.brake();          // Slow down
        myCar.stopEngine();     // Turn off engine
    }
}
