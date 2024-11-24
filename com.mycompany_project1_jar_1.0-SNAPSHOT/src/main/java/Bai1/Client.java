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
import java.util.Scanner;
public class Client {
     public static void main(String[] args) throws Exception {
        DatagramSocket client = new DatagramSocket();
        Scanner sc = new Scanner(System.in);

        int n = 0;
        boolean isValidInput = false;

     
        while (!isValidInput) {
            try {
                System.out.print("Nhập số nguyên dương n: ");
                n = sc.nextInt();
                if (n > 0) {
                    isValidInput = true;
                } else {
                    System.out.println("Vui lòng nhập số nguyên dương lớn hơn 0.");
                }
            } catch (Exception e) {
                sc.nextLine(); 
                System.out.println("Đầu vào không hợp lệ. Vui lòng thử lại.");
            }
        }

   
        byte[] sendData = String.valueOf(n).getBytes();
        InetAddress serverAddress = InetAddress.getByName("localhost");
        int serverPort = 9999;
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
        client.send(sendPacket);

      
        byte[] receiveData = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        client.receive(receivePacket);

        
        String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
        System.out.println("Kết quả từ server: " + response);

        client.close();
    }

}
