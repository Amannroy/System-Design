// SportsCar class demonstrates encapsulation
class SportsCar {
    // These variables are private - hidden from outside
    private String brand;
    private String model;
    private boolean isEngineOn = false;
    private int currentSpeed = 0;
    private int currentGear = 0;
    private String tyreCompany; // extra variable to show use of setter/getter

    // Constructor to set car brand and model
    public SportsCar(String brand, String model) {
        this.brand = brand;
        this.model = model;
    }

    // Getter method - allows controlled access to speed
    public int getSpeed() {
        return currentSpeed;
    }

    // Getter for tyre company
    public String getTyreCompany() {
        return tyreCompany;
    }

    // Setter for tyre company - allows safe update
    public void setTyreCompany(String tyreCompany) {
        this.tyreCompany = tyreCompany;
    }

    // Start engine method
    public void startEngine() {
        isEngineOn = true;
        System.out.println(brand + " " + model + " : Engine starts with a roar!");
    }

    // Change gear
    public void shiftGear(int gear) {
        this.currentGear = gear;
        System.out.println(brand + " " + model + " : Shifted to gear " + currentGear);
    }

    // Accelerate the car
    public void accelerate() {
        if (!isEngineOn) {
            System.out.println(brand + " " + model + " : Engine is off! Cannot accelerate.");
            return;
        }
        currentSpeed += 20;
        System.out.println(brand + " " + model + " : Accelerating to " + currentSpeed + " km/h");
    }

    // Brake the car
    public void brake() {
        currentSpeed -= 20;
        if (currentSpeed < 0) currentSpeed = 0;
        System.out.println(brand + " " + model + " : Braking! Speed is now " + currentSpeed + " km/h");
    }

    // Stop the engine
    public void stopEngine() {
        isEngineOn = false;
        currentGear = 0;
        currentSpeed = 0;
        System.out.println(brand + " " + model + " : Engine turned off.");
    }
}

// Main class to test encapsulation
public class Encapsulation {
    public static void main(String[] args) {
        // Create object
        SportsCar mySportsCar = new SportsCar("Ford", "Mustang");

        // Interact with car using public methods
        mySportsCar.startEngine();
        mySportsCar.shiftGear(1);
        mySportsCar.accelerate();
        mySportsCar.shiftGear(2);
        mySportsCar.accelerate();
        mySportsCar.brake();
        mySportsCar.stopEngine();

        // ❌ Direct access to speed is not allowed
        // mySportsCar.currentSpeed = 500;

        // ✅ Safe access using getter
        System.out.println("Current Speed of My Sports Car is " + mySportsCar.getSpeed());
    }
}
