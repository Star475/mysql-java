package Week4;

public class CodingProject {

    public static void main(String[] args) {
        // 1. Create an array of int called ages
        int[] ages = {3, 9, 23, 64, 2, 8, 28, 93};

        // 1a. Subtract first element from last
        int resultA = ages[ages.length - 1] - ages[0]; // Calculate difference between last and first elements
        System.out.println("1a. Last minus first in ages: " + resultA);

        // 1b. Create new array with 9 elements
        int[] ages2 = {3, 9, 23, 64, 2, 8, 28, 93, 45};
        int lastMinusFirst2 = ages2[ages2.length - 1] - ages2[0]; // Calculate difference in larger array
        System.out.println("1b. Last element minus first element (ages2): " + lastMinusFirst2);

        // Demonstrate dynamic indexing
        System.out.println("\nDemonstrating dynamic array indexing:"); // Show array length info
        System.out.println("ages array length: " + ages.length);
        System.out.println("ages2 array length: " + ages2.length);

        // 1c. Calculate average age
        int sum = 0;
        for (int age : ages) {
            sum += age; // Sum all ages
        }
        double average = (double) sum / ages.length; // Calculate average
        System.out.println("1c. Average age: " + average);

        // 2. Array of names
        String[] names = {"Sam", "Tommy", "Tim", "Sally", "Buck", "Bob"};

        // 2a. Average letters per name
        int totalLetters = 0;
        for (String name : names) {
            totalLetters += name.length(); // Sum up name lengths
        }
        double averageLetters = (double) totalLetters / names.length; // Calculate average
        System.out.println("\n2a. Average number of letters per name: " + averageLetters);

        // 2b. Concatenate all names
        StringBuilder concatenatedNames = new StringBuilder();
        for (String name : names) {
            concatenatedNames.append(name).append(" "); // Append each name with space
        }
        System.out.println("2b. Concatenated names: " + concatenatedNames.toString().trim()); // Print combined names

        // 3. Access last element: names[names.length - 1]
        // 4. Access first element: names[0]

        // 5. Create nameLengths array
        int[] nameLengths = new int[names.length];
        for (int i = 0; i < names.length; i++) {
            nameLengths[i] = names[i].length(); // Store each name's length
        }
        System.out.print("\n5. nameLengths array: [");
        for (int i = 0; i < nameLengths.length; i++) {
            System.out.print(nameLengths[i]);
            if (i < nameLengths.length - 1) System.out.print(", "); // Format output
        }
        System.out.println("]");

        // 6. Sum of name lengths
        int totalSum = 0;
        for (int length : nameLengths) {
            totalSum += length; // Add all lengths together
        }
        System.out.println("6. Sum of all name lengths: " + totalSum);

        // 7. Call repeatWord method (added)
        System.out.println("\n7. Repeat word example (Hello 3 times): " + repeatStringNTimes("Hello", 3));

        
        // 8. Call fullName method
        System.out.println("\n8. Full name: " + getFullName("Star", "Goins"));

        // 9. Call sumGreaterThan100
        int[] numbers = {10, 20, 30, 40, 5};
        System.out.println("9. Is the sum greater than 100? " + sumGreaterThan100(numbers));

        // 10. Average of double array
        double[] scores = {88.5, 92.0, 79.5, 85.0};
        System.out.println("10. Average score: " + calculateAverage(scores));

        // 11. Compare averages of two arrays
        double[] arr1 = {90.0, 85.0, 88.0};
        double[] arr2 = {70.0, 75.0, 78.0};
        System.out.println("11. Is first array average greater? " + firstArrayAverageGreater(arr1, arr2));

        // 12. Will buy drink
        System.out.println("12. Will buy drink? " + willBuyDrink(true, 11.00));

        // 13. Custom method: calculate total with discount
        System.out.println("13. Total cost with discount: $" + calculateTotalCost(100.0, true));
    }

    // 7. Method to repeat a word n times
    public static String repeatStringNTimes(String word, int n) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < n; i++) {
            result.append(word); // Append the word n times
        }
        return result.toString(); // Return concatenated word
    }
    
    // 8. Full name method - combines first and last name with space
    public static String getFullName(String firstName, String lastName) {
        return firstName + " " + lastName;
    }

    // 9. Check if sum of array is greater than 100
    public static boolean sumGreaterThan100(int[] numbers) {
        int sum = 0;
        for (int num : numbers) {
            sum += num; // Add each number
        }
        return sum > 100; // Return true if sum > 100
    }

    // 10. Calculate average of elements in double array
    public static double calculateAverage(double[] numbers) {
        double sum = 0;
        for (double num : numbers) {
            sum += num; // Sum all values
        }
        return sum / numbers.length; // Return average
    }

    // 11. Compare averages of two arrays
    public static boolean firstArrayAverageGreater(double[] array1, double[] array2) {
        return calculateAverage(array1) > calculateAverage(array2); // Compare averages
    }

    // 12. Return true if hot outside and money is enough
    public static boolean willBuyDrink(boolean isHotOutside, double moneyInPocket) {
        return isHotOutside && moneyInPocket > 10.50; // Return based on conditions
    }

    // 13. Apply 10% discount if member
    public static double calculateTotalCost(double price, boolean isDiscountMember) {
        if (isDiscountMember) {
            return price * 0.9; // Apply discount
        }
        return price; // Return full price if not a member
    }
} 

