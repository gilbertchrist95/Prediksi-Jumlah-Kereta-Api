/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntitasPackage;

import ControlPackage.CInput;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gilbert
 */
public class DataInput {
    
    private static double[] dataInputMax;
    String locationMasukanPembelajaran;
    String locationMasukanPengujian;
    static int jumlahKolomPembelajaran;
    static int jumlahBarisPembelajaran;
    static int jumlahKolomPengujian;
    static int jumlahBarisPengujian;    
    int barisC=0;
    int barisCC=0;
    static double[][] DtInputPembelajaran;
    static double[][] DtInputPengujian;
    Scanner scanIn;
    String inputLine;
    
    public DataInput() {
    }
    
    public void setLocationPembelajaran(String lokasi)
    {
        this.locationMasukanPembelajaran = lokasi;
    }
    
    public void setLocationPengujian (String lokasi){
        this.locationMasukanPengujian = lokasi;
    }
    
    public void setKolomBarisPembelajaran()
    {
        try {
            scanIn = new Scanner(new BufferedReader(new FileReader(locationMasukanPembelajaran) ));
            
            while (scanIn.hasNextLine())
            {
                //read line in from file
                inputLine = scanIn.nextLine();
                String[] inArray = inputLine.split(",");               
                jumlahKolomPembelajaran = inArray.length;
                jumlahBarisPembelajaran++;
            }
            DtInputPembelajaran = new double[jumlahBarisPembelajaran][jumlahKolomPembelajaran];
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CInput.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setKolomBarisPengujian(){
        try {
            scanIn  = new Scanner(new BufferedReader(new FileReader(locationMasukanPengujian)));
            
            while(scanIn.hasNextLine()){
                inputLine = scanIn.nextLine();
                String[] inArray = inputLine.split(",");
                jumlahKolomPengujian = inArray.length;
                jumlahBarisPengujian++;
            }
            DtInputPengujian = new double[jumlahBarisPengujian][jumlahKolomPengujian];
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DataInput.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void alokasiDataPembelajaran()
    {
        try {
            scanIn = new Scanner(new BufferedReader(new FileReader(locationMasukanPembelajaran)));
            while (scanIn.hasNextLine())
            {
                //read line in from file
                inputLine = scanIn.nextLine();
                String[] inArray = inputLine.split(",");
                    for (int j = 0; j < inArray.length; j++) {
                        DtInputPembelajaran[barisC][j]=Double.parseDouble(inArray[j]);
                         //System.out.println(DtInputPembelajaran[barisC][j]);
                    }
                 barisC++;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DataInput.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void alokasiDataPengujian(){
        try {
            scanIn = new Scanner(new BufferedReader(new FileReader(locationMasukanPengujian)));
            while (scanIn.hasNextLine()){
                inputLine = scanIn.nextLine();
                String[] inArray = inputLine.split(",");
                for (int j = 0; j < inArray.length; j++) {
                    DtInputPengujian[barisCC][j] = Double.parseDouble(inArray[j]);
                }
                barisCC++;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DataInput.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void setDataInputMax(int kolom, double valueTempMax){
        dataInputMax[kolom] = valueTempMax;
    }
            
    
    public static int getJumlahKolomPembelajaran()
    {
        return jumlahKolomPembelajaran;
    }
    
    public static int getJumlahKolomPengujian()
    {
        return jumlahKolomPengujian;
    }
    
    public static int getJumlahBarisPembelajaran()
    {
        return jumlahBarisPembelajaran;
    }
    
    public static int getJumlahBarisPengujian()
    {
        return jumlahBarisPengujian;
    }
     
    public static double getDataInputPembelajaran(int i, int j){
        return DtInputPembelajaran[i][j];
    }
    
    public static double getDataInputPengujian(int i, int j){
        return DtInputPengujian[i][j];
    }
    
    public  static void alokasiDataInputMax(int kolom){
        dataInputMax = new double[kolom];
    }
    
    public static void setDataInputPembelajaran(int i, int j, double dataInput){
        DtInputPembelajaran[i][j] = dataInput;
    }
    
    public static void setDataInputPengujian(int i, int j, double dataInput){
        DtInputPengujian[i][j] = dataInput;
    }
    
    public double getDataInput(int i, int j){
        return DtInputPembelajaran[i][j];
    }
}
