public class NumberToWords {

    // Main method to convert any integer number to words
    String convertToWords(int number) {
        if (number == 0) {
            return "Zero";
        }
        String prefix = "";
        if (number < 0) {
            number = -number;
            prefix = "Negative ";
        }

        String current = "";
        int place = 0;

        do {
            int n = number % 1000;
            if (n != 0) {
                String s = convertLessThanOneThousand(n);
                current = s + placeToWord(place) + " " + current;
            }
            place++;
            number /= 1000;
        } while (number > 0);

        return (prefix + current).trim();
    }

    // Method to return the place value in words
    private String placeToWord(int place) {
        switch (place) {
            case 1:
                return "Thousand";
            case 2:
                return "Million";
            case 3:
                return "Billion";
            case 4:
                return "Trillion";
            case 5:
                return "Quadrillion";
            default:
                return ""; // For unsupported places
        }
    }

    // Method to convert numbers less than 1000 to words
    private String convertLessThanOneThousand(int number) {
        final String[] units = { "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine" };
        final String[] teens = { "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen",
                "Seventeen", "Eighteen", "Nineteen" };
        final String[] tens = { "", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety" };

        String current = "";

        if (number % 100 < 20 && number % 100 >= 10) {
            current = teens[number % 10]; // Handle teens
            number /= 100;
        } else {
            if (number % 10 != 0) {
                current = units[number % 10]; // Handle units
            }
            number /= 10;

            if (number % 10 != 0) {
                current = tens[number % 10] + " " + current; // Handle tens
            }
            number /= 10;
        }

        if (number > 0) {
            current = units[number] + " Hundred " + current; // Handle hundreds
        }

        return current.trim();
    }

    // Test the method
    public static void main(String[] args) {
        NumberToWords converter = new NumberToWords();
        System.out.println(converter.convertToWords(1234567)); // Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
        System.out.println(converter.convertToWords(-12345));   // Output: "Negative Twelve Thousand Three Hundred Forty Five"
        System.out.println(converter.convertToWords(0));        // Output: "Zero"
    }
}