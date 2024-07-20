import java.util.Scanner;

public class Main {

    private static String XOR(String a, String b) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < a.length(); i++) {
            result.append(a.charAt(i) == b.charAt(i) ? '0' : '1');
        }
        return result.toString();
    }

    public static String generateCodeword(String data, String gen_poly) {
        int N = gen_poly.length();
        int data_length = data.length();

        StringBuilder paddedData = new StringBuilder(data);
        for (int i = 0; i < N - 1; i++) {
            paddedData.append('0');
        }

        String check_value = paddedData.substring(0, N);

        for (int i = N; i <= data_length + N - 1; i++) {
            if (check_value.charAt(0) == '1') {
                check_value = XOR(check_value, gen_poly);
            }
            if (i < paddedData.length()) {
                check_value = check_value.substring(1) + paddedData.charAt(i);
            }
        }

        String remainder = check_value.substring(1); // Exclude the first bit of check_value
        
        String codeword = data + remainder;
        return codeword;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter dataword: ");
        String input = scanner.nextLine().trim();

        if (input.length() == 4 && input.matches("[01]+")) {
            String dataword = input;
            String gen_poly = "1011";
            String codeword = generateCodeword(dataword, gen_poly);
            String remainder = codeword.substring(dataword.length());
            System.out.println();
            System.out.println("SENDER");
            System.out.println("Dataword: " + dataword);
            System.out.println("Remainder: " + remainder);
            System.out.println("Codeword: " + codeword);
        } else {
            throw new IllegalArgumentException("Invalid input");
        }
    }
}
