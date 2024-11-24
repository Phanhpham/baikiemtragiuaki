/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Bai2;

/**
 *
 * @author used
 */
import java.io.*;
import java.net.*;
public class Server {
     public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(9999);
        System.out.println("Server đang chờ kết nối...");

        while (true) {
           
            Socket client = server.accept();
            System.out.println("Kết nối từ client thành công!");

            handleClient(client);
        }
    }

    private static void handleClient(Socket client) {
        try {
            DataInputStream din = new DataInputStream(client.getInputStream());
            DataOutputStream dout = new DataOutputStream(client.getOutputStream());

            String studentId = din.readUTF();
            System.out.println("Mã sinh viên nhận được: " + studentId);

            int sumEven = Helper.calculateEvenSum(studentId);
            String primeNumbers = Helper.findPrimeNumbers(studentId);

            dout.writeInt(sumEven);
            dout.writeUTF(primeNumbers);

            din.close();
            dout.close();
            client.close();
        } catch (IOException e) {
            System.err.println("Lỗi xử lý client: " + e.getMessage());
        }
    }
}

class Helper {
    public static int calculateEvenSum(String studentId) {
        int sum = 0;
        for (char c : studentId.toCharArray()) {
            if (Character.isDigit(c)) {
                int digit = Character.getNumericValue(c);
                if (digit % 2 == 0) {
                    sum += digit;
                }
            }
        }
        return sum;
    }

    public static String findPrimeNumbers(String studentId) {
        StringBuilder primes = new StringBuilder();
        for (char c : studentId.toCharArray()) {
            if (Character.isDigit(c)) {
                int digit = Character.getNumericValue(c);
                if (isPrime(digit)) {
                    primes.append(digit).append(" ");
                }
            }
        }
        return primes.toString().trim();
    }

    private static boolean isPrime(int num) {
        if (num < 2) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}
