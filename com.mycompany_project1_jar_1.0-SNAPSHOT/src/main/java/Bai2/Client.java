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
import java.util.Scanner;
public class Client {
     public static void main(String[] args) throws IOException {

        Socket client = new Socket("localhost", 9999);
        Scanner sc = new Scanner(System.in);

        DataInputStream din = new DataInputStream(client.getInputStream());
        DataOutputStream dout = new DataOutputStream(client.getOutputStream());

        String studentId;
        boolean isValid = false;

        while (!isValid) {
            System.out.print("Nhập mã sinh viên: ");
            studentId = sc.nextLine().toUpperCase();
            if (studentId.matches("[A-Z0-9]+")) { 
                isValid = true;
                dout.writeUTF(studentId); 
            } else {
                System.out.println("Mã sinh viên không hợp lệ. Vui lòng nhập lại.");
            }
        }

        int sumEven = din.readInt(); 
        String primeNumbers = din.readUTF();

        System.out.println("Tổng các số chẵn trong mã sinh viên là: " + sumEven);
        System.out.println("Các số nguyên tố trong mã sinh viên là: " + (primeNumbers.isEmpty() ? "Không có" : primeNumbers));

        din.close();
        dout.close();
        client.close();
    }
}
