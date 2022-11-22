package org.trenitalia;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Biglietteria {

	public static void main(String[] args) {
		
		FileWriter fw = null;
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			
			try {
				System.out.println("Km: ");
				int km = sc.nextInt();
				
				System.out.println("Eta': ");
				int eta = sc.nextInt();
				
				System.out.println("Flessibile 0/1");
				int flessibile = sc.nextInt();
				
				Biglietto b1 = new Biglietto(km, eta, flessibile == 1);
				System.out.println(b1);
				
				fw = new FileWriter("./biglietti.txt", true);
				fw.append(b1.toString() + "\n-----------------------\n");
				
				break;
				
			} catch(Exception e) {
				
				System.err.println(e.getMessage());
			} finally {
				
				try {
					fw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		System.out.println("\n\n--------------------------\n");
		
		Scanner fileSc = null;
		
		try {
			
			File f = new File("./biglietti.txt");
			fileSc = new Scanner(f);
			
			while (fileSc.hasNextLine()) {
				
				String line = fileSc.nextLine();
				System.out.println(line);
			}
		} catch (Exception e) {
			
			System.err.println(e.getMessage());
		} finally {
			
			fileSc.close();
		}
	}
}









