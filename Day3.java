import java.util.Scanner;
import java.util.Stack;
import java.util.ArrayList;
import java.util.Collections;

// 1. Expression Evaluator
class ExpressionEvaluator {
    public static void main(String[] args) {
        System.out.println(evaluate("10 + 2 * 6"));
    }

    public static int evaluate(String exp) {
        Stack<Integer> num = new Stack<>();
        Stack<Character> op = new Stack<>();

        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);

            if (Character.isWhitespace(c)) {
                continue;
            }

            if (Character.isDigit(c)) {
                StringBuilder sbuf = new StringBuilder();
                while (i < exp.length() && Character.isDigit(exp.charAt(i))) {
                    sbuf.append(exp.charAt(i));
                    i++;
                }
                num.push(Integer.parseInt(sbuf.toString()));
                i--;
            } 
            else if (c == '+' || c == '-' || c == '*' || c == '/') {
                while (!op.isEmpty() && precedence(c, op.peek())) {
                    if (num.size() < 2) {
                        System.out.println("Error: Invalid Expression.");
                        return Integer.MIN_VALUE;
                    }
                    int val2 = num.pop();
                    int val1 = num.pop();
                    num.push(applyOp(op.pop(), val1, val2));
                }
                op.push(c);
            } else {
                System.out.println("Error: Invalid character '" + c + "' found.");
                return Integer.MIN_VALUE;
            }
        }

        while (!op.isEmpty()) {
            if (num.size() < 2) {
                System.out.println("Error: Expression ended unexpectedly.");
                return Integer.MIN_VALUE;
            }
            int val2 = num.pop();
            int val1 = num.pop();
            num.push(applyOp(op.pop(), val1, val2));
        }

        if (num.isEmpty()) return 0;
        return num.pop();
    }

    public static boolean precedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')') return false;
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) return false;
        return true;
    }

    public static int applyOp(char op, int a, int b) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/':
                if (b == 0) {
                    System.out.println("Error: Cannot divide by zero.");
                    System.exit(0);
                }
                return a / b;
        }
        return 0;
    }
}

// 2. Command-Line Gradebook
class Gradebook {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of students: ");
        int n = sc.nextInt();

        String[] names = new String[n];
        int[] grades = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter name and grade for student " + (i + 1) + ": ");
            names[i] = sc.next();
            grades[i] = sc.nextInt();
        }

        while (true) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Print all");
            System.out.println("2. Print topper");
            System.out.println("3. Class average");
            System.out.println("4. Search by name");
            System.out.println("5. Curve grades (+5)");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            if (choice < 1 || choice > 6) {
                System.out.println("Invalid choice. Please try again.");
                continue;
            }

            if (choice == 6) {
                System.out.println("Exiting...");
                break;
            }

            switch (choice) {
                case 1:
                    printAll(names, grades);
                    break;
                case 2:
                    printTopper(names, grades);
                    break;
                case 3:
                    printClassAverage(grades);
                    break;
                case 4:
                    searchByName(sc, names, grades);
                    break;
                case 5:
                    curveGrades(grades);
                    break;
            }
        }
        sc.close();
    }

    public static void printAll(String[] names, int[] grades) {
        System.out.println("--- All Students ---");
        for (int i = 0; i < names.length; i++) {
            System.out.println(names[i] + ":" + grades[i] );
        }
        System.out.println();
    }

    public static void printTopper(String[] names, int[] grades) {
        if (grades.length == 0) return;
        
        int max = grades[0];
        int index = 0;
        
        for (int i = 1; i < grades.length; i++) {
            if (grades[i] > max) {
                max = grades[i];
                index = i;
            }
        }
        System.out.println("Topper: " + names[index] + " :" + max);
    }

    public static void printClassAverage(int[] grades) {
        if (grades.length == 0) return;
        
        double sum = 0;
        for (int grade : grades) {
            sum += grade;
        }
        double avg = sum / grades.length;
        System.out.printf("Average: %.2f%n", avg);
    }

    public static void searchByName(Scanner sc, String[] names, int[] grades) {
        System.out.print("Enter name to search: ");
        String targetName = sc.next();
        boolean found = false;

        for (int i = 0; i < names.length; i++) {
            if (names[i].equalsIgnoreCase(targetName)) {
                System.out.println("Found: " + names[i] + " :" + grades[i] );
                found = true;
                break; 
            }
        }

        if (!found) {
            System.out.println("Student not found.");
        }
    }

    public static void curveGrades(int[] grades) {
        for (int i = 0; i < grades.length; i++) {
            grades[i] += 5;
            if (grades[i] > 100) {
                grades[i] = 100;
            }
        }
        System.out.println("Grades have been curved.");
    }
}

// 3. Robust String Analytics
class StringAnalytics {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String text = "";

        do {
            System.out.println("Enter a line of text:");
            if (sc.hasNextLine()) {
                text = sc.nextLine();
            }
        } while (text.trim().isEmpty());

        analyzeText(text);
        sc.close();
    }

    public static int[] countVowels(String s) {
        int vowels[] = new int[5];

        for (int i = 0; i < s.length(); i++) {
            char c = Character.toLowerCase(s.charAt(i));

            if (c == 'a') vowels[0]++;
            else if (c == 'e') vowels[1]++;
            else if (c == 'i') vowels[2]++;
            else if (c == 'o') vowels[3]++;
            else if (c == 'u') vowels[4]++;
        }

        return vowels;
    }

    public static void analyzeText(String text) {
        int numWords = 0;
        int numSentences = 0;
        int numDigits = 0;
        int numLetters = 0;

        String longestWord = "";
        String newText = text + " ";
        StringBuilder currentWord = new StringBuilder();

        int[] v = countVowels(text);

        for (int i = 0; i < newText.length(); i++) {
            char c = newText.charAt(i);

            if (Character.isWhitespace(c)) {
                if (currentWord.length() > 0) {
                    numWords++;
                    if (currentWord.length() > longestWord.length()) {
                        longestWord = currentWord.toString();
                    }
                    currentWord.setLength(0);
                }
            } else {
                currentWord.append(c);
            }
        }

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            if (c == '.' || c == '!' || c == '?') {
                numSentences++;
            }
            if (Character.isDigit(c)) {
                numDigits++;
            } else if (Character.isLetter(c)) {
                numLetters++;
            }
        }

        System.out.println("Words: " + numWords);
        System.out.println("Sentences: " + numSentences);
        System.out.println("Digits: " + numDigits + ", Letters: " + numLetters);
        System.out.println("Longest word: \"" + longestWord + "\"");
        
        System.out.println("Vowel freq: a=" + v[0] + " e=" + v[1] +
                           " i=" + v[2] + " o=" + v[3] + " u=" + v[4]);
    }
}

// 4. Mini ATM Simulator
class MiniATM {
    static double balance = 1000.0;
    static Scanner sc = new Scanner(System.in);
    static int pin = 1234;
    
    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\n--- ATM Menu ---");
            System.out.println("1) Deposit\n2) Withdraw\n3) Check Balance\n4) Change PIN\n5) Exit");
            System.out.print("Enter option: ");
            
            if (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                sc.next(); 
                continue;
            }
            int choice = sc.nextInt();

            switch (choice) {
                case 1: deposit(); break;
                case 2: withdraw(); break;
                case 3: printBalance(); break;
                case 4: changePin(); break;
                case 5: 
                    running = false; 
                    System.out.println("Exiting..."); 
                    break;
                default: 
                    System.out.println("Invalid option.");
            }
        }
    }

    
    public static void deposit() {
        System.out.print("Amount to be deposited: ");
        double amt = sc.nextDouble();
        if (amt < 0) {
            System.out.println("Negative amount not allowed.");
            return;
        }
        balance += amt;
        System.out.printf("New balance: %.2f%n",balance);
    
    }
    public static void withdraw() {
        if(verifyPin()) {
            System.out.println("Enter amount to withdraw:  ");
            double amount = sc.nextDouble();
            
            if(amount<0) {
                System.out.println("Negative amount not allowed.");
            }else if(amount > balance){
                System.out.println("Insufficient balance");
            }
            else {
                balance -= amount;
                System.out.println("New balance: "+ balance);
            }
        }
    }
    
    public static void changePin() {
        if(verifyPin()) {
            System.out.print("Enter new PIN: ");
            int newPin = sc.nextInt();
            pin = newPin;
            System.out.print("PIN changed successfully ");
            
        }
        
    }
    
    public static void printBalance() {
        System.out.printf("Current Balance: %.2f%n", balance);
    }
    
    public static boolean verifyPin() {
        System.out.println("Enter pin: ");
        int yourPin = sc.nextInt();
        
        if(yourPin == pin) {
            return true;
        }
        else {
            System.out.println("Incorrect PIN.");
            return false;
        }        
        
    }
}

// 5. Calendar: Day of Week (Zeller’s Congruence)
class CalendarDay {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter date (dd mm yyyy):");
        int day = scanner.nextInt();
        int month = scanner.nextInt();
        int year = scanner.nextInt();
        
        String result = dayOfWeek(day, month, year);
        System.out.println(result);
        
        
    }
    
    public static String dayOfWeek(int d, int m, int y) {
        if (m < 1 || m > 12 || d < 1 || d > 31 || y < 1) {
            return "Invalid Input ";
        }
        
        int daysInMonth[] = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        boolean isLeap = (y % 4 == 0 && y % 100 != 0) || (y % 400 == 0);
        
        if(isLeap) {
            daysInMonth[2] = 29;
        }
        
        if (d > daysInMonth[m]) {
            return "Invalid Date: Day " + d + " does not exist in month " + m;
        }
        
        if (m == 1 || m == 2) {
            m += 12;
            y -= 1;
        }
        
        int q = d;               
        int K = y % 100;        
        int J = y / 100;
        
        int h = (q + (13 * (m + 1) / 5) + K + (K / 4) + (J / 4) - (2 * J)) % 7;
        
        if (h < 0) {
            h += 7;
        }
        
        String days[] = {
                "Saturday", 
                "Sunday", 
                "Monday", 
                "Tuesday", 
                "Wednesday", 
                "Thursday", 
                "Friday"
            };

            return days[h];
    }
}

// 6. Compressed Run-Length Encoding (RLE) with Options
class RunLengthEncoding {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1) Compress\n2) Decompress\n3) Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 3) break;

            System.out.print("Enter string: ");
            String input = scanner.nextLine();

            if (choice == 1) {
                System.out.println("Result: " + compress(input));
            } else if (choice == 2) {
                if (isValidEncoded(input)) {
                    System.out.println("Result: " + decompress(input));
                } else {
                    System.out.println("Invalid format for decompression.");
                }
            }
        }
        scanner.close();
    }

    public static String compress(String s) {
        if (s == null || s.isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        
        int count = 1; // Correct initialization

        for (int i = 0; i < s.length(); i++) {
            if (i == s.length() - 1 || s.charAt(i) != s.charAt(i + 1)) {
                sb.append(s.charAt(i));
                sb.append(count);
                count = 1;
            } else {
                count++;
            }
        }
        return sb.toString();
    }

    public static String decompress(String s) {
        if (isValidEncoded(s)) {
            StringBuilder sb = new StringBuilder();
            
            // i increments by 2 because we consume pairs
            for (int i = 0; i < s.length(); i += 2) {
                char c = s.charAt(i);
                int count = Character.getNumericValue(s.charAt(i + 1));

                for (int k = 0; k < count; k++) {
                    sb.append(c);
                }
            }
            return sb.toString();
        }
        return ""; // Return empty or error message if invalid
    }

    public static boolean isValidEncoded(String s) {
        if (s.length() % 2 != 0) {
            return false;
        }

        for (int i = 0; i < s.length(); i += 2) {
            if (!Character.isLetter(s.charAt(i))) return false;
            if (!Character.isDigit(s.charAt(i + 1))) return false;
        }
        return true;
    }
}

// 7. Matrix Operations Toolkit (2D Arrays)
class MatrixToolkit {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter number of rows: ");
        int r = scanner.nextInt();
        System.out.println("Enter number of columns: ");
        int c = scanner.nextInt();

        if (r < 2 || r > 10 || c < 2 || c > 10) {
            System.out.println("Invalid dimensions! Please restart and enter values between 2-10.");
            return; 
        }

        int[][] A = new int[r][c];
        int[][] B = new int[r][c];

        System.out.println("Enter Matrix A:");
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                A[i][j] = scanner.nextInt();
            }
        }

        System.out.println("Enter Matrix B:");
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                B[i][j] = scanner.nextInt();
            }
        }

        while (true) {
            System.out.println("\n--- MENU ---");
            System.out.println("1) Add");
            System.out.println("2) Subtract");
            System.out.println("3) Multiply");
            System.out.println("4) Transpose A");
            System.out.println("5) Row/Col Sums A");
            System.out.println("6) Exit");
            System.out.print("Choose operation: ");
            
            int choice = scanner.nextInt();

            if (choice == 6) {
                System.out.println("Exiting...");
                break;
            }

            switch (choice) {
                case 1:
                    System.out.println("Result: Add");
                    printMatrix(add(A, B));
                    break;
                case 2:
                    System.out.println("Result: Subtract");
                    printMatrix(sub(A, B));
                    break;
                case 3:
                    if (A[0].length != B.length) {
                        System.out.println("Error: Dimensions mismatch. For A(rxc) * B(rxc), matrix must be square (r=c).");
                    } else {
                        System.out.println("Result: Multiply");
                        printMatrix(multiply(A, B));
                    }
                    break;
                case 4:
                    System.out.println("Result: Transpose A");
                    printMatrix(transpose(A));
                    break;
                case 5:
                    printSum(A);
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
        scanner.close();
    } 

    public static void printMatrix(int[][] m) {
        for (int[] row : m) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }

    public static int[][] add(int[][] A, int[][] B) {
        int r = A.length, c = A[0].length;
        int[][] res = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                res[i][j] = A[i][j] + B[i][j];
            }
        }
        return res;
    }

    public static int[][] sub(int[][] A, int[][] B) {
        int r = A.length, c = A[0].length;
        int[][] res = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                res[i][j] = A[i][j] - B[i][j];
            }
        }
        return res;
    }

    public static int[][] multiply(int[][] A, int[][] B) {
        int r1 = A.length;        
        int c1 = A[0].length;     
        int c2 = B[0].length;     
        int[][] res = new int[r1][c2];

        for (int i = 0; i < r1; i++) {
            for (int j = 0; j < c2; j++) {
                for (int k = 0; k < c1; k++) {
                    res[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return res;
    }

    public static int[][] transpose(int[][] A) {
        int r = A.length, c = A[0].length;
        int[][] res = new int[c][r]; 
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                res[j][i] = A[i][j];
            }
        }
        return res;
    }

    public static void printSum(int[][] A) {
        int r = A.length;
        int c = A[0].length;

        System.out.println("Row Sums:");
        for (int i = 0; i < r; i++) {
            int rowTotal = 0;
            for (int j = 0; j < c; j++) {
                rowTotal += A[i][j];
            }
            System.out.println("Row " + (i + 1) + ": " + rowTotal);
        }

        System.out.println("Column Sums:");
        for (int j = 0; j < c; j++) {
            int colTotal = 0;
            for (int i = 0; i < r; i++) {
                colTotal += A[i][j];
            }
            System.out.println("Col " + (j + 1) + ": " + colTotal);
        }
    }

}

// 8. Robust Password Validator
class PasswordValidator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("username: ");
        String username = scanner.nextLine();

        System.out.print("password: ");
        String password = scanner.nextLine();
        if (isValid(password, username)) {
            System.out.println("Password is valid.");
        } else {
            
            System.out.println(feedback(password, username));
        }
    }
    
    public static boolean isValid(String pwd, String user) {
        if (pwd.length() < 8 || pwd.length() > 20) return false;
        
        if (pwd.contains(" ")) return false;
        
        String lowerUser = user.toLowerCase();
        String lowerPwd = pwd.toLowerCase();
        
        
        int sequenceLimit = 4; 

        for (int i = 0; i <= lowerUser.length() - sequenceLimit; i++) {

            String sequence = lowerUser.substring(i, i + sequenceLimit);

            if (lowerPwd.contains(sequence)) {
                System.out.println("Invalid: contains username sequence \"" + sequence + "\"");
                return false; 
            }
        }
        
        
        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;
        String specialChars = "!@#$%^&*";

        for (int i = 0; i < pwd.length(); i++) {
            char c = pwd.charAt(i);

            if (Character.isUpperCase(c)) {
                hasUpper = true;
                continue; 
            }
            if (Character.isLowerCase(c)) {
                hasLower = true;
                continue;
            }
            if (Character.isDigit(c)) {
                hasDigit = true;
                continue;
            }
            if (specialChars.indexOf(c) != -1) {
                hasSpecial = true;
                continue;
            }
        }

        return hasUpper && hasLower && hasDigit && hasSpecial;  
    
    }
    
    public static String feedback(String pwd, String user) {
        StringBuilder sb = new StringBuilder();
        boolean validLength = pwd.length() >= 8 && pwd.length() <= 20;
        boolean hasSpace = pwd.contains(" ");
        
        boolean containsUserSequence = false;
        String lowerUser = user.toLowerCase();
        String lowerPwd = pwd.toLowerCase();
        int sequenceLimit = 4;

        if (lowerUser.length() >= sequenceLimit) {
            for (int i = 0; i <= lowerUser.length() - sequenceLimit; i++) {
                String sequence = lowerUser.substring(i, i + sequenceLimit);
                if (lowerPwd.contains(sequence)) {
                    containsUserSequence = true;
                    break;
                }
            }
        } else {
            containsUserSequence = lowerPwd.contains(lowerUser);
        }

        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;
        String specialChars = "!@#$%^&*";

        for (int i = 0; i < pwd.length(); i++) {
            char c = pwd.charAt(i);
            if (Character.isUpperCase(c)) { hasUpper = true; continue; }
            if (Character.isLowerCase(c)) { hasLower = true; continue; }
            if (Character.isDigit(c))      { hasDigit = true; continue; }
            if (specialChars.indexOf(c) != -1) { hasSpecial = true; continue; }
        }

        if (containsUserSequence) {
            sb.append("Invalid: contains a sequence from your username.\n");
        } else if (!validLength || hasSpace || !hasUpper || !hasLower || !hasDigit || !hasSpecial) {
             sb.append("Invalid Input.\n");
        }

        sb.append("Feedback:\n");

        if (containsUserSequence) {
            sb.append("- Avoid using parts of your name (sequences of 4+ characters).\n");
        }
        if (!validLength) sb.append("- Password must be between 8 and 20 characters.\n");
        if (hasSpace) sb.append("- Password must not contain spaces.\n");
        if (!hasUpper) sb.append("- Add at least one uppercase letter.\n");
        if (!hasLower) sb.append("- Add at least one lowercase letter.\n");
        if (!hasDigit) sb.append("- Add at least one number.\n");
        if (!hasSpecial) sb.append("- Add more special characters (!@#$%^&*).\n");

        return sb.toString();
    }
}

// 9. Number Stream Aggregator (Sentinel & Stats)
class NumberStream {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        ArrayList<Integer> input = new ArrayList<>();
        System.out.println("Enter numbers (enter -999 to stop):");
        
        while(true) {
            if(scanner.hasNextInt()) {
                int n = scanner.nextInt();
                
                if (n == -999) {
                    break; 
                }
                input.add(n);
                
            }
        }
        System.out.println("Finished reading. Input: " + input);
        System.out.println("Min: " + min(input)); 
        System.out.println("Max: " + max(input)); 
        System.out.printf("Avg: %.2f%n", average(input));
        System.out.printf("Median: %.2f%n", median(input));
        System.out.println("Mode: " + mode(input));
    }
    
    public static int min(ArrayList<Integer> a) { 
        int min = a.get(0);
        for (int i = 0; i < a.size(); i++) {
            if(a.get(i) < min) {
                min = a.get(i);
            }
        }
        return min;
    }
    
    public static int max(ArrayList<Integer> a) {
        int max = a.get(0);
        for (int i = 0; i < a.size(); i++) {
            if(a.get(i) > max) {
                max = a.get(i);
            }
        }
        return max;
    }
    public static double average(ArrayList<Integer> a) {
        int sum =0;
        for (int i = 0; i < a.size(); i++) {
            sum += a.get(i);
        }
        int avg = sum/a.size();
        return avg;
    }
    public static int mode(ArrayList<Integer> a) {
        int mode = a.get(0);
        int max = 0;
        boolean[] visited = new boolean[a.size()];
        
        for (int i = 0; i < a.size(); i++) {
            int count =0;
            if (visited[i] == true) {
                continue; 
            }
            
            for (int j = 0; j < a.size(); j++) {
                if (a.get(j) == a.get(i)) {
                    count++;
                    visited[j] = true; 
                }
            }
            if (count > max) {
                max = count;
                mode = a.get(i);
            }
            
        }
        
        return mode;
    }
    public static double median(ArrayList<Integer> a) {
        Collections.sort(a);
        double median = a.get(0);
        
            if (a.size() % 2 != 0) {
                return a.get(a.size() / 2);
            } else {
                return (a.get((a.size() / 2) - 1) + a.get(a.size() / 2)) / 2.0;
            }
    
    }
}

// 10. CLI Phonebook with Partial Match & Commands
class Phonebook {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] names = new String[100];
        String[] phones = new String[100];
        int count = 0;

        System.out.println("Phonebook Ready (Commands: ADD, DEL, FIND, LIST, EXIT)");
        
        while(true) {
            String input = scanner.nextLine().trim();
            
            String[] parts = input.split(" "); 
            String name = "";
            String phone = "";
            
            String command = parts[0].toUpperCase();
            if(parts.length > 1) name = parts[1];
            if(parts.length > 2) phone = parts[2];

            if (command.equals("EXIT")) {
                System.out.println("EXITED");
                break;
            }
            
            switch(command) {
            case "ADD":
                if(count > 100) {
                    System.out.println("Phonebook is full.");
                }
                
                count = add(name, phone,names, phones, count);
                break;
            case "DELETE":
                if(count == 0) {
                    System.out.println("Phonebook is empty.");
                    
                }
                count = delete(name,names, phones, count);
                break;
            case "FIND":
                find(name, names, phones, count);
                break;
            case "LIST":
                list(names, phones, count);
                break;
            default:
                System.out.println("Unknown command.");
                
            }
        }
    }
    
    public static int add(String name, String phone,String[] names, String[] phones, int count) {
        if (!isValid(phone)) {
            System.out.println("Error: Phone must be exactly 10 digits.");
        }
        
        names[count] = name;
        phones[count] = phone;
        System.out.println("Added: " + name);
        return count + 1;
    }
    public static int delete(String name,String[] names, String[] phones, int count){
        for(int i = 0; i<count;i++) {
            if (names[i].equalsIgnoreCase(name)) {
                
                for (int j = i; j < count - 1; j++) {
                    names[j] = names[j+1];
                    phones[j] = phones[j+1];
                }
                
                names[count - 1] = null;
                phones[count - 1] = null;
                
                System.out.println("Deleted: " + name);
                
        }}
        return count - 1;
        
    }
    public static void find(String name, String[] names, String[] phones, int count) {
        name = name.toLowerCase();
        System.out.print("Found: ");
        boolean foundAny = false;

        for (int i = 0; i < count; i++) {
            if(names[i].toLowerCase().contains(name)) {
                System.out.print(names[i] + "(" + phones[i] + ")");
                foundAny = true;
            }
        }
        if (!foundAny) System.out.print("None");
        System.out.println();
    }
    public static void list(String[] names, String[] phones, int count) {
        if (count == 0) {
            System.out.println("Phonebook is empty.");
        }
        else {
            for(int i=0; i<count; i++) {
                System.out.println(names[i] + "("+phones[i]+")");
            }
        }
    
    }
    
    public static boolean isValid(String p) {
        if (p.length() != 10) return false;
        for (char c : p.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    
    }
}