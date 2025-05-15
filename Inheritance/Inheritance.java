// ✅ Base class representing common features of all cars
class Car {
    // 🔓 'protected' allows access in subclasses and within the same package
    protected String brand;
    protected String model;
    protected boolean isEngineOn;
    protected int currentSpeed;

    // 🔧 Constructor to initialize brand and model, and set default values
    public Car(String brand, String model){
        this.brand = brand;
        this.model = model;
        this.isEngineOn = false;  // Engine is off by default
        this.currentSpeed = 0;    // Car is at rest initially
    }

    // 🚗 Starts the engine (accessible to all)
    public void startEngine(){
        isEngineOn = true;
        System.out.println(brand + " " + model + " : Engine started.");
    }

    // 🛑 Stops the engine and resets speed to 0
    public void stopEngine(){
        isEngineOn = false;
        currentSpeed = 0;
        System.out.println(brand + " " + model + " : Engine turned off.");
    }

    // 🏎️ Increases speed if engine is on
    public void accelerate(){
        if(!isEngineOn){
            System.out.println(brand + " " + model + " : Cannot accelerate! Engine is off.");
            return;
        }
        currentSpeed += 20;  // Increases speed by 20 km/hr
        System.out.println(brand + " " + model + " : Accelerating to " + currentSpeed + " km/hr");
    }

    // 🛑 Reduces speed by 20 km/hr
    public void brake(){
        currentSpeed -= 20;
        if(currentSpeed < 0) currentSpeed = 0;  // Prevents negative speed
        System.out.println(brand + " " + model + " : Braking! Speed is now " + currentSpeed + " km/hr");
    }
}

// 🚘 Subclass representing a manual car — inherits from Car
class ManualCar extends Car {
    private int currentGear;  // ⚙️ Only ManualCar has gear system

    // 🔧 Constructor calls super constructor to initialize brand and model
    public ManualCar(String brand, String model){
        super(brand, model);
        this.currentGear = 0;  // Default gear is neutral
    }

    // 🔁 ManualCar-specific method to shift gears
    public void shiftGear(int gear){
        this.currentGear = gear;
        System.out.println(brand + " " + model + " : Shifted to gear " + currentGear);
    }
}

// 🔋 Subclass representing an electric car — inherits from Car
class ElectricCar extends Car {
    private int batteryLevel;  // 🔋 Only ElectricCar has battery level

    // 🔧 Constructor calls super constructor to initialize brand and model
    public ElectricCar(String brand, String model){
        super(brand, model);
        this.batteryLevel = 100;  // Default battery level
    }

    // 🔌 ElectricCar-specific method to recharge battery
    public void chargeBattery(){
        batteryLevel = 100;
        System.out.println(brand + " " + model + " : Battery fully charged!");
    }
}

// 🧪 Main class to test Inheritance and access modifiers
public class Inheritance {
    public static void main(String[] args){
        // 🧪 Creating ManualCar object and testing its methods
        ManualCar myManualCar = new ManualCar("Suzuki", "WagonR");
        myManualCar.startEngine();       // Common method
        myManualCar.shiftGear(1);        // Specific to ManualCar
        myManualCar.accelerate();        // Common method
        myManualCar.brake();             // Common method
        myManualCar.stopEngine();        // Common method

        System.out.println("------------------");

        // 🧪 Creating ElectricCar object and testing its methods
        ElectricCar myElectricCar = new ElectricCar("Tesla", "Model S");
        myElectricCar.chargeBattery();   // Specific to ElectricCar
        myElectricCar.startEngine();     // Common method
        myElectricCar.accelerate();      // Common method
        myElectricCar.brake();           // Common method
        myElectricCar.stopEngine();      // Common method
    }
}
