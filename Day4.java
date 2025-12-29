// 1. Stopwatch (Class & Basic Object Use)
class Stopwatch {
    
    private boolean running;
    private int ticks;

    public Stopwatch() {
        this.running = false;
        this.ticks = 0;
    }

    public void start() {
        this.running = true;
    }

    public void stop() {
        this.running = false;
    }

    public void reset() {
        this.ticks = 0;
    }

    public int elapsed() {
        return this.ticks;
    }

    public boolean isRunning() {
        return this.running;
    }

    public void incrementTick() {
        if (this.running) {
            this.ticks++;
        }
    }
}

class Day4_1 {
    public static void main(String[] args) {
      
        Stopwatch sw = new Stopwatch();

        System.out.println("--- Test 1: Counting to 1000 ---");
        
        sw.start();

        for (int i = 0; i < 1000; i++) {

            if (sw.isRunning()) {
                sw.incrementTick();
            }
        }

        sw.stop();

        System.out.println("Expected: 1000");
        System.out.println("Actual:   " + sw.elapsed());

        sw.reset();
        System.out.println("After reset: " + sw.elapsed());
    }
}

// 2. Rectangle with Encapsulation
class Rectangle {
    private double width;
    private double height;
    
    public void setWidth(double w) 
    {
        if(w > 0) {
            this.width = w;
        }
    } 
    
    public void setHeight(double h)
    {
        if(h > 0) {
            this.height = h;
        }
    }
    
    public double getWidth() {
        return width;
    }
    public double getHeight() {
        return height;
    }
    
    public double area() {
        return width * height;
    }
    public double perimeter() {
        return (2 * (height + width));
    }
}

class Day4_2 {
    
    public static void main(String[] args) {
        Rectangle r = new Rectangle();
        r.setWidth(10);
        r.setHeight(5);
        System.out.println(r.area());      
        System.out.println(r.perimeter());
        r.setWidth(0); 
    }
}

// 3. Student with Read‑only ID (final)
class Student {
    final int id;
    private String name;
    
    public Student(int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    } 
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}

class Day4_3 {
    
    public static void main(String[] args) {
        Student s = new Student(101);
        s.setName("Alice");
        System.out.println(s.getId());
    
    }
}

// 4. MathUtil (Static Utility Methods)
class MathUtil {
    public static int max(int a, int b)
    {
        if(a > b) {
            return a;
        }
        else {
            return b;
        }
    }
    public static int clamp(int v, int min, int max)
    {
        if(v > max) {
            return max;
        }
        else if(v < min) {
            return min;
        }
        else {
            return v;
        }
    }
}

class Day4_4 {
    
    public static void main(String[] args) {
        System.out.println(MathUtil.max(7, 3));            // 7
        System.out.println(MathUtil.clamp(15, 0, 10));     // 10
        System.out.println(MathUtil.clamp(-3, -2, 5));     // -2

    }
}

// 5. Config (Static Constants)
class Config
{
    public static final String APP_NAME = "MyApp";
    public static final String VERSION = "v1.0.0";
    public static final int MAX_USERS = 100;
    
}

class Day4_5 {
    
    public static void main(String[] args)
    {
        System.out.println("App Name: " + Config.APP_NAME);
        System.out.println("Version: " + Config.VERSION);
        System.out.println("Max Users: " + Config.MAX_USERS);
    }
}

// 6. BankAccount with Access Control
class BankAccount
{
    private String owner;
    private double balance;
    
    public BankAccount(String owner, double balance) 
    {
        this.owner = owner;
        this.balance = balance;
    }
    
    public void deposit(double amount)
    {
        if(amount > 0) {
            balance += amount;
        }
        else {
            System.out.println("Enter positive amount.");
        }
    }
    public void withdraw(double amount) 
    {
        if(amount > balance) {
            System.out.println("Insufficient balance.");
        }
        else {
            balance -= amount;
        }
    } 
    public String getOwner()
    {
        return owner;
    }
    public double getBalance()
    {
        return balance;
    }
    
    protected void applyFee(double fee) {
        if (balance >= fee) {
            balance -= fee; 
            System.out.println("Internal Fee Applied: $" + fee);
        } else {
            System.out.println("Insufficient funds to apply fee.");
        }
    }
}

class Day4_6 {
    
    public static void main(String[] args)
    {
        BankAccount acc = new BankAccount("Sarthak", 1000.0);
        acc.deposit(500);
        acc.withdraw(300);
        System.out.println(acc.getBalance()); 
    }
}

// 7. Counter with Static Instance Count
class Counter
{
    private int value;
    public static int instances;
    
    public Counter()
    {
        instances++;
    }
    
    public void increment() 
    {
        value++;
    }
    public void decrement()
    {
        value--;
    }
    public int getValue() {
        return value;
    }
}

class Day4_7 {
    
    public static void main(String[] args) {
        Counter a = new Counter();
        Counter b = new Counter();
        Counter c = new Counter();
        System.out.println(Counter.instances);
    }
}

// 8. Temperature (Immutable-like Pattern)
class Temperature
{
     private final double celsius;
     
     public Temperature(double celsius) {
        this.celsius = celsius;
    }
     
     public double toFahrenheit()
     {
         return (celsius * (9.0/5.0)) + 32;
     }
     public Temperature withCelsius(double c) {
        return new Temperature(c);
    }

}

class Day4_8 {
    
    public static void main(String[] args)
    {
        Temperature t = new Temperature(25.0);
        System.out.println(t.toFahrenheit()); // 77.0
        Temperature t2 = t.withCelsius(30.0); 
    }
}

// 9. AccessDemo (Modifiers in Same Package)
class AccessDemo
{
    public void A()
    {
        System.out.println("public A");
    }
    
    private void B()
    {
        System.out.println("private B");
    }
    
    void C()
    {
        System.out.println("default C");
    }
    
    protected void D()
    {
        System.out.println("Protected D");
    }
}

class Day4_9 {
    
    public static void main(String[] args)
    {
        
        AccessDemo obj = new AccessDemo();
        obj.A();
//      obj.B(); cant be called since its private
        obj.C();
        obj.D();
    }
}

// 10. IDGenerator (Static Sequencing)
class IDGenerator
{
    private static int next = 1000;

    public static int nextId()
    {
        return next++;
    }
}

class User
{
    
    private final int id; 
    private String name;
    public User(String name) {
        this.name = name;
        this.id = IDGenerator.nextId();
    }

    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
}

class Day4_10 {
    
    public static void main(String[] args)
    {
        User u1 = new User("A");
        User u2 = new User("B");
        System.out.println(u1.getId()); 
        System.out.println(u2.getId());
    }
}

// 11. LibraryBook with Encapsulation & Static Catalog Count
class LibraryBook {
    private String title, author;
    private boolean issued;
    public static int totalBooks;
    
    public LibraryBook(String title, String author) {
        totalBooks++;
        this.title = title;
        this.author = author;
        this.issued = false;
    }
    
    public void issue() {
        if (!issued) {
            issued = true;
            System.out.println("Success: " + title + " is now issued.");
        } else {
            System.out.println("Failed: " + title + " is already issued!");
        }
    } 
    
    public void returnBook() {
        if (issued) {
            issued = false;
            System.out.println("Success: " + title + " has been returned.");
        } else {
            System.out.println("Failed: " + title + " was not issued to you.");
        }
    }
    
    public boolean isIssued() {
        return issued;
    }
}

class Day4_11 {
    public static void main(String[] args) {
        LibraryBook b = new LibraryBook("1984", "Orwell");
        
        System.out.println("Total Books: " + LibraryBook.totalBooks);
        
        b.issue();
        b.issue();
        b.returnBook();
        
        System.out.println("Is book currently issued? " + b.isIssued());
    }
}

// 12.  Vector2D with Final Constants and Static Factory
class Vector2D {
    private final double x;
    private final double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static final Vector2D ZERO = new Vector2D(0, 0);
    public static final Vector2D UNIT_X = new Vector2D(1, 0);
    public static final Vector2D UNIT_Y = new Vector2D(0, 1);

    public static Vector2D fromPolar(double r, double theta) {
        double x = Math.cos(theta) * r;
        double y = Math.sin(theta) * r;
        return new Vector2D(x, y);
    }

    public Vector2D add(Vector2D other) {
        return new Vector2D(this.x + other.x, this.y + other.y);
    }

    public Vector2D scale(double s) {
        return new Vector2D(this.x * s, this.y * s);
    }

    public double magnitude() {
        return Math.sqrt(x * x + y * y);
    }
}

class Day4_12 {
    public static void main(String[] args) {
        Vector2D v = Vector2D.fromPolar(2, Math.PI / 2);
        Vector2D w = Vector2D.UNIT_X.add(v);
        
        System.out.println("Vector W Magnitude: " + w.magnitude());
    }
}

// 13. Employee with Encapsulation + Salary Rules
class Employee
{
    private String name;
    private double salary;
    
    void setName(String name) {
        if(name.isEmpty()) {
            System.out.println("Error: Name cannot be empty.");
        }
        else {
            this.name = name;
        }
    }
    
    void setSalary(double s) 
    {
        if(s < 10000 || s > 500000) {
            System.out.println("Error: Enter salary in range 10000–500000.");
        }
        else {
            this.salary = s;
        }
        
        
    }
    void raise(double percent) 
    {
        if(percent > 0 && percent <= 100) {
            double increaseAmount = this.salary * (percent / 100);
            this.salary += increaseAmount;
            System.out.println("Salary raised by " + percent + "%. New Salary: " + this.salary);
        } else {
            System.out.println("Error: Raise percentage must be between 0 and 100.");
        
        }
    }

}

class Day4_13 {
    public static void main(String[] args)
    {
        Employee e = new Employee();
        e.setName("");           // prints 
        e.setSalary(9000);       // prints 
        e.setSalary(120000);
        e.raise(10);            // salary -> 132000
        e.raise(200);           // prints invalid

    }
}

// 14.  AccountNumber (Final Immutability + Static Checksum)
class AccountNumber
{
    private final String digits;
    
    public static boolean isValid(String s) {
        if (s == null || s.length() != 12) {
            return false;
        }

        for (char c : s.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        
        return true;
    }
    
    public AccountNumber(String s) {
        if (isValid(s)) {
            this.digits = s;
        } else {
            System.out.println("Invalid account number");
            this.digits = "000000000000"; 
        }
    }
}

class Day4_14 {
    public static void main(String[] args)
    {
        AccountNumber a = new AccountNumber("123456789012"); // valid
        AccountNumber b = new AccountNumber("ABC");          
    }
}

// 15. SingletonLogger (Static Instance + Private Constructor)
class SingletonLogger
{
    private static SingletonLogger instance;
    
    private SingletonLogger() 
    {
        System.out.println("Singleton Instance Created!");
    }
    
    private static final String PREFIX = "[APP] ";
    
    public static SingletonLogger getInstance() 
    {//create on first call
        if(instance == null) {
            instance = new SingletonLogger();
        }
        return instance;
    }
    public void log(String msg)
    {
        System.out.println(PREFIX + msg);// prints "[APP] " + msg
    }


}

class Day4_15 {
    public static void main(String[] args)
    {
        SingletonLogger l1 = SingletonLogger.getInstance();
        SingletonLogger l2 = SingletonLogger.getInstance();
        System.out.println(l1 == l2); // true
        l1.log("Started");
    }
}