// This class demonstrates STATIC POLYMORPHISM (Method Overloading)
class ManualCar {
    // Fields to store car details and state
    private String brand;
    private String model;
    private boolean isEngineOn;
    private int currentSpeed;
    private int currentGear;

    // Constructor to initialize car object
    public ManualCar(String brand, String model) {
        this.brand = brand;
        this.model = model;
        this.isEngineOn = false;  // engine initially off
        this.currentSpeed = 0;    // speed initially 0
        this.currentGear = 0;     // gear initially neutral
    }

    // Method to start the engine
    public void startEngine() {
        isEngineOn = true;
        System.out.println(brand + " " + model + " : Engine started.");
    }

    // Method to stop the engine
    public void stopEngine() {
        isEngineOn = false;
        currentSpeed = 0;
        System.out.println(brand + " " + model + " : Engine turned off.");
    }

    // Method Overloading (Static Polymorphism)
    // accelerate() with no parameters - default speed increase
    public void accelerate() {
        if (!isEngineOn) {
            System.out.println(brand + " " + model + " : Cannot accelerate! Engine is off.");
            return;
        }
        currentSpeed += 20;
        System.out.println(brand + " " + model + " : Accelerating to " + currentSpeed + " km/h");
    }

    // accelerate() with speed parameter - custom speed increase
    public void accelerate(int speed) {
        if (!isEngineOn) {
            System.out.println(brand + " " + model + " : Cannot accelerate! Engine is off.");
            return;
        }
        currentSpeed += speed;
        System.out.println(brand + " " + model + " : Accelerating to " + currentSpeed + " km/h");
    }

    // Method to apply brake - decreases speed
    public void brake() {
        currentSpeed -= 20;
        if (currentSpeed < 0) {
            currentSpeed = 0;
        }
        System.out.println(brand + " " + model + " : Braking! Speed is now "
                           + currentSpeed + " km/h");
    }

    // Method to change gear
    public void shiftGear(int gear) {
        currentGear = gear;
        System.out.println(brand + " " + model + " : Shifted to gear " + currentGear);
    }
}


// Main class to test ManualCar behavior
public class StaticPolymorphism {
    public static void main(String[] args) {
        // Creating object of ManualCar
        ManualCar myManualCar = new ManualCar("Suzuki", "WagonR");

        // Calling methods to show static polymorphism in action
        myManualCar.startEngine();       // Starts engine
        myManualCar.accelerate();        // Calls accelerate() with no parameter
        myManualCar.accelerate(40);      // Calls overloaded accelerate(int speed)
        myManualCar.brake();             // Applies brake
        myManualCar.stopEngine();        // Stops engine
    }
}
