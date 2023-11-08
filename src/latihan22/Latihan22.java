/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package latihan22;
import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Latihan22 {
    
    public static Scanner input = new Scanner(System.in);
    
    public static void main(String[] args) {
        
    int noInput;

        menuLayanan();
        System.out.print("Masukkan nomer menu: ");
        noInput = input.nextInt();

        if(noInput == 1) {
            try {
                displayData();
            } catch (Exception e) {
                System.out.println("Data Tidak Ditemukan");
                System.err.print(e);
            }
        } else if(noInput == 2) {
            try {
                tambahData();
            } catch (Exception e) {
                System.out.println("Data Tidak Ditemukan");
                System.err.print(e);
            }
        } else if(noInput == 3) {
            try {
                ubahData();
            } catch (Exception e) {
                System.out.println("Data Tidak Ditemukan");
                System.err.print(e);
            }
        }

        
        
            
        System.out.println("--------------------------------------");
        
        System.out.println("Terima Kasih");
        
    }
        
    private static void displayData() throws IOException{
        
        FileReader file = new FileReader("src/latihan22/database.txt");
        BufferedReader bufferFile = new BufferedReader(file);
        
        String data = bufferFile.readLine();
 
        System.out.printf("%-8s | %-8s | Gender\n","NAMA","NIM");
        System.out.println("--------------------------------------");
        
        while(data != null) {
            StringTokenizer st = new StringTokenizer(data,",");
            
            String nama = st.nextToken();
            String NIM = st.nextToken();
            String gender = st.nextToken();
            
            System.out.printf("%-8s | %-8s | %s\n",nama,NIM,gender);
            data = bufferFile.readLine();
        } 
    }
    
    private static void menuLayanan(){
        System.out.println("Daftar Menu: ");
        System.out.println("1. Tampilkan Seluruh Data");
        System.out.println("2. Tambahkan Data");
        System.out.println("3. Ubah Data");
    }
    
    private static void tambahData() throws Exception {
        
        System.out.println("--------------------------------------");
        
        FileWriter file = new FileWriter("src/latihan22/database.txt",true);
        BufferedWriter bufferFile = new BufferedWriter(file);        
        
        System.out.println("Masukkan Data: ");
        System.out.print("Nama Mahasiswa   : ");
        String nama = input.next();
        System.out.print("NIM Mahasiswa    : ");
        String NIM = input.next();
        System.out.print("Gender Mahasiswa : ");
        String gender = input.next();
        
        bufferFile.write(nama + "," + NIM + "," + gender);
        bufferFile.newLine();
        bufferFile.flush();
        bufferFile.close();
        
    }
    
    private static void ubahData() throws Exception {
         
        System.out.println("--------------------------------------");
        
        File database = new File("src/latihan22/database.txt");
        FileReader read = new FileReader(database);
        BufferedReader bufferRead = new BufferedReader(read);
        
        File databaseCopy = new File("src/latihan22/databasecopy.txt");
        FileWriter write = new FileWriter(databaseCopy);
        BufferedWriter bufferWrite = new BufferedWriter(write);
        
        displayData();
        System.out.print("Pilih Baris Data yang Ingin Diubah: ");
        int barisData = input.nextInt();
        
        String data = bufferRead.readLine();
        
        int i = 0;
        String[] x = {"Nama","NIM","Gender"};
        String[] newData = new String[3];
        
        while(data != null) {
            i++;
            if(i == barisData) {
                StringTokenizer st = new StringTokenizer(data,",");

                String nama = st.nextToken();
                String NIM = st.nextToken();
                String gender = st.nextToken();
               
                System.out.println("--------------------------------------");
                System.out.println("Ini Baris Data yang ingin Anda Ubah: ");
                System.out.println("--------------------------------------");
                System.out.printf("%-8s | %-8s | %s\n",nama,NIM,gender);
                System.out.println("--------------------------------------");
                
                //RESET TOKEN
                st = new StringTokenizer(data,",");
                
                for(int j = 0; j < newData.length; j++) {
                    System.out.print("Apakah anda ingin mengubah data " + x[j] + "(y/t)? ");
                    String valid = input.next();
                    
                    if(valid.equals("y")) {
                        System.out.print("Masukkan data baru: ");
                        newData[j] = input.next();
                        st.nextToken();
                    } else {
                        newData[j] = st.nextToken();
                    }
                }
                bufferWrite.write(newData[0] + "," + newData[1] + "," + newData[2]);
                bufferWrite.newLine();
            } else {
                bufferWrite.write(data);
                bufferWrite.newLine();
            }
            data = bufferRead.readLine();
        }
        
        bufferWrite.flush();
        bufferRead.close();
        bufferWrite.close();
        
        // Duplikat Database
        database.delete();
        databaseCopy.renameTo(database);
    }
    
}
