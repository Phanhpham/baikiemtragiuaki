/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Bai1;

/**
 *
 * @author used
 */
import java.net.*;
public class Server {
    public static void main(String[] args) throws Exception {
        DatagramSocket server = new DatagramSocket(9999);
        System.out.println("Server đang chạy và chờ kết nối...");

        while (true) {
            
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            server.receive(receivePacket);

           
            String receivedString = new String(receivePacket.getData(), 0, receivePacket.getLength());
            int n = Integer.parseInt(receivedString);

           
            String result = findPrimesDivisibleByFive(n);

           
            byte[] sendData = result.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, 
                                                           receivePacket.getAddress(), receivePacket.getPort());
            server.send(sendPacket);
        }
    }

    
    private static boolean isPrime(int num) {
        if (num < 2) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

   
    private static String findPrimesDivisibleByFive(int n) {
        StringBuilder result = new StringBuilder("Các số nguyên tố nhỏ hơn " + n + " chia hết cho 5 là: ");
        boolean found = false;
        for (int i = 2; i < n; i++) {
            if (isPrime(i) && i % 5 == 0) {
                result.append(i).append(" ");
                found = true;
            }
        }
        if (!found) {
            result.append("Không có số nào thỏa mãn.");
        }
        return result.toString();
    }
}
