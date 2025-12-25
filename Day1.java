import java.util.Scanner;


// 1. REVERSE A STRING

class ReverseString {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
     
        System.out.print("Enter text: ");
        String text = scanner.nextLine();
        char[] charArray = text.toCharArray();
        
        int left = 0;
        int right = charArray.length - 1;
        
        while (left < right) {
            char temp = charArray[left];
            charArray[left] = charArray[right];
            charArray[right] = temp;
            
            left++;
            right--;
        }
        System.out.println("Reversed: " + new String(charArray));
    }
}


// 2. SMALLEST NUMBER IN AN ARRAY

class SmallestNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        
        int arr[] = new int[5];
        
        for (int i = 0; i < 5; i++) {
            System.out.print("Element " + i + ": ");
            arr[i] = scanner.nextInt();
        }
        
        int smallest = arr[0];
        
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < smallest) {
                smallest = arr[i];
            }
        }
        
        System.out.println("Smallest: " + smallest);
    }
}


// 3. PRIME NUMBERS

class CheckPrime {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        

        System.out.print("Enter a no.: ");
        int num = scanner.nextInt();
        
        if (num == 0 || num == 1) {
            System.out.println(num + " is not a prime number");
            return; 
        }
        
        for (int i = 2; i < num; i++) {
            if (num % i == 0) {
                System.out.println(num + " is not a prime number");
                return;
            }
        }
        System.out.println(num + " is a prime number");
    }
}


// 4. FREQUENCY OF CHARACTERS

class CharFrequency {
    public static void main(String[] args) {
        
        String input = "abba"; 
        

        System.out.println("Input: " + input);
        
        int arr[] = new int[26];
        
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            
            if (ch >= 'a' && ch <= 'z') {
                arr[ch - 'a']++;
            }
        }
        
        for (int i = 0; i < 26; i++) {
            if (arr[i] > 0) {
                System.out.println((char)(i + 'a') + ":" + arr[i]);
            }
        }
    }
}


// 5. REVERSE STRING SKIPPING SYMBOLS

class ReverseSkippingSymbols {
    public static void main(String[] args) {
        String input = "a,bc$";
        
        System.out.println("--- Q5: Reverse Skipping Symbols ---");
        System.out.println("Input: " + input);
        
        char[] charArray = input.toCharArray();
        
        int left = 0;
        int right = charArray.length - 1;
        
        while (left < right) {
            
            if (!Character.isLetter(charArray[left])) {
                left++;
            }
            else if (!Character.isLetter(charArray[right])) {
                right--;
            }
            else {
                char temp = charArray[left];
                charArray[left] = charArray[right];
                charArray[right] = temp;
                
                left++;
                right--;
            }
        }
        System.out.println("Result: " + new String(charArray));
    }
}