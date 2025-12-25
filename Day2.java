import java.util.Scanner;

// 1. TEMP CONVERSION
class TempConversion {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Convert to (Celsius or Fahrenheit): ");
        
        String input = scanner.nextLine();
        
        System.out.print("Enter temperature: ");
        
        double value = scanner.nextDouble();
        
        boolean isValid = true;
        
        if(input.equalsIgnoreCase("Celsius")) {
            value = (value - 32) * (5.0/9.0);
        }
        else if(input.equalsIgnoreCase("Fahrenheit")) {
            value = (value * (9.0/5.0)) + 32;
        }
        else {
            System.out.print("Invalid input");
            isValid = false;
        }
        
        if(isValid) {
            System.out.println("Converted value: " + value);
        }
    }
}

// 2. STUDENT GRADE CALCULATOR
class StudentGradeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter 5 subjects marks separated by space (out of 100) :");
        double a = scanner.nextDouble();
        double b = scanner.nextDouble();
        double c = scanner.nextDouble();
        double d = scanner.nextDouble();
        double e = scanner.nextDouble();
        
        double percent = ((a+b+c+d+e)/500)*100;
        
        char grade = 'A';
        
        if(percent >= 90) {
            grade = 'A';
        }
        else if(percent >= 75) {
            grade = 'B';
        }
        else if(percent >= 60) {
            grade = 'C';
        }
        else if(percent >= 40) {
            grade = 'D';
        }
        else{
            grade = 'F';
        }
        
        System.out.println("Percentage: "+percent);
        System.out.print("Grade: "+grade);
    }
}

// 3. PRINT PATTERN
class PrintPattern {
    public static void main(String[] args) {
        for(int i = 1; i<5; i++) {
            for(int j = 1; j<=i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}

// 4. REVERSE THE WORDS FROM THE SENTENCE
class ReverseWords {
    public static void main(String[] args) {
        String input = "Java is fun";
        
        if (input == null || input.isEmpty()) {
            System.out.print(input);
        }
        
        String words[] = input.split(" ");
        
        StringBuilder output = new StringBuilder();
        
        for(String word : words) {
            StringBuilder reverseWord = new StringBuilder(word);
            
            output.append(reverseWord.reverse());
            
            output.append(" ");
        }
        
        System.out.print("Output: " +output.toString().trim());
    }
}

// 5. SECOND LARGEST
class SecondLargest {
    public static void main(String[] args) {
        int arr[] = {10, 10, 5, 8};
        
        int largest = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;
        
        for(int i = 0; i<arr.length; i++) {
            if(arr[i]>largest) {
                second = largest;
                largest = arr[i];
            }
            else {
                if(arr[i]>second && arr[i] != largest) {
                    second = arr[i];
                }
            }
        }
        System.out.print(second);
    }
}

// 6. PALINDROME CHECKER
class PalindromeChecker {
    public static boolean Checker(String s) {
        char[] charArray = s.toCharArray();
        
        int left = 0;
        int right = charArray.length - 1;
        
        while (left < right) {
            char temp = charArray[left];
            charArray[left] = charArray[right];
            charArray[right] = temp;
            
            left++;
            right--;
        }
        
        String reversedS = new String(charArray);
        
        if(s.equalsIgnoreCase(reversedS)) {
            return true;
        }
        else {
            return false;
        }
    }
    
    public static void main(String[] args) {
         String s = "hem";
         
         if(Checker(s)) {
             System.out.print("Palindrome");
         }
         else {
             System.out.print("Not a Palindrome");
         }
    }
}

// 7. SUM OF EVEN & ODD
class SumEvenOdd {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter n: ");
        int n = scanner.nextInt();
        
        int even = 0;
        int odd = 0;
        
        System.out.println("Enter " + n + " numbers:");
        
        for(int i =0; i<n; i++) {
            int num = scanner.nextInt();
            
            if(num%2 == 0) {
                even += num;
            }
            else {
                odd+= num;
            }
        }
        System.out.println("Even sum: "+ even);
        System.out.println("Odd sum: "+ odd);
    }
}

// 8. COUNT FREQUENCY OF WORDS IN A SENTENCE
class WordFrequency {
    public static void main(String[] args) {
        String input = "Java is fun and Java is powerful";
        
        String words[] = input.split(" ");
        boolean[] visited = new boolean[words.length];
        
        for(int i=0; i<words.length; i++) {
            if(visited[i]) {
                continue;
            }
            
            int count = 1;
            for(int j=i+1; j<words.length; j++) {
                if(words[i].equalsIgnoreCase(words[j])) {
                    count ++;
                    visited[j] = true;
                }
            }
            System.out.println(words[i]+ ": "+ count);
        }
    }
}

// 9. FACTORIAL
class Factorial {
    public static int factorial(int n) {
         int fact = 1;
         
         for(int i=1; i<=n; i++) {
             fact *= i;
         }
         
         return fact;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    
        System.out.print("Enter n: ");
        int n = scanner.nextInt();
        
        int ans = factorial(n);
        
        System.out.println("Factorial: "+ ans);
    }
}

// 10. ATM SIMULATION
class AtmSimulation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("1. Check Balance\r\n"
                + "2. Deposit\r\n"
                + "3. Withdraw\r\n"
                + "4. Exit\n");
        
        
        int balance = 1000;
        
        boolean flag = true;
        
        while(flag) {
            System.out.print("Enter your choice: ");
            int n = scanner.nextInt();
            switch(n) {
            
            case 1:
                System.out.print("------1. Check Balance------\n");
                System.out.print("Your balance: "+ balance);
                break;
                
            case 2:
                System.out.print("------2. Deposit------\n");
                System.out.println("Enter amount: ");
                int amount = scanner.nextInt();
                balance = balance+amount;
                System.out.println("New Balance:"+(balance));
                break;
                
            case 3:
                System.out.print("------3. Withdraw------\n");
                System.out.println("Enter amount to withdraw: ");
                int withdraw = scanner.nextInt();
                
                if(withdraw > balance) {
                    System.out.println("Insufficient Balance");
                }
                else {
                    balance -= withdraw;
                    System.out.println("New Balance:"+(balance));
                }
                break;
            case 4:
                System.out.print("------4. EXIT------ ");
                flag = false;
                break;
            }
        }
    }
}