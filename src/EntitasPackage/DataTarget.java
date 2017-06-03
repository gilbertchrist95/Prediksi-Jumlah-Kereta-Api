/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntitasPackage;

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
public class DataTarget{

    String lokasiTargetPembelajaran;
    String lokasiTargetPengujian;
    static int jumlahKolomTargetPembelajaran;
    static int jumlahBarisTargetPembelajaran;
    static int jumlahKolomTargetPengujian;
    static int jumlahBarisTargetPengujian;
    int barisCC = 0;
    int barisC=0;
    static double[][] DtTargetPembelajaran;
    static double[][] DtTargetPengujian;
    Scanner scanIn;
    String inputLine;
    static  double[] dataTargetMax;
    
    public DataTarget() {
    }
   
    
    public void setLocationTargetPembelajaran (String fileNameMasukanTarget){
        this.lokasiTargetPembelajaran= fileNameMasukanTarget;
    }
    
    public void getLocationTargetPengujian (String fileNameMasukanTargetPengujian){
        this.lokasiTargetPengujian= fileNameMasukanTargetPengujian;
    }
    
    public void setKolomBarisTargetPembelajaran(){
        try {
            scanIn = new Scanner(new BufferedReader(new FileReader(lokasiTargetPembelajaran)));
            while (scanIn.hasNextLine()){
                inputLine = scanIn.nextLine();
                jumlahKolomTargetPembelajaran = 1;
                jumlahBarisTargetPembelajaran++;
            }
            DtTargetPembelajaran = new double[jumlahBarisTargetPembelajaran][jumlahKolomTargetPembelajaran];
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DataTarget.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void setKolomBarisTargetPengujian(){
        try {
            jumlahKolomTargetPengujian =1;
            scanIn = new Scanner(new BufferedReader(new FileReader(lokasiTargetPengujian)));
            while (scanIn.hasNextLine()){
                inputLine = scanIn.nextLine();
                jumlahBarisTargetPengujian++;
            }
            DtTargetPengujian = new double[jumlahBarisTargetPengujian][jumlahKolomTargetPengujian];
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DataTarget.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void setDataTargetPembelajaran(int i, int j, double dataT){
        DtTargetPembelajaran[i][j] = dataT;
    }
    
    public static void setDataTargetPengujian(int i, int j, double dataT){
        DtTargetPengujian[i][j] = dataT;
    }
    
    public static void setDataTargetMax(int kolom, double valueTempMax){
        dataTargetMax[kolom] = valueTempMax;
    }
    
    public void alokasiDataTargetPembelajaran(){
        try {
            scanIn = new Scanner(new BufferedReader(new FileReader(lokasiTargetPembelajaran)));
            while (scanIn.hasNext()){
                //read line in from file
                inputLine = scanIn.nextLine();
                //String[] inArray = inputLine.split(",");
                for (int i = 0; i < 1; i++) {
                    DtTargetPembelajaran[barisC][i] = Double.parseDouble(inputLine);
                }
                barisC++;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DataTarget.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void alokasiDataTargetPengujian(){
        try {
            scanIn =  new Scanner(new BufferedReader(new FileReader(lokasiTargetPengujian)));
            while (scanIn.hasNext()){
                inputLine = scanIn.nextLine();
                for (int i = 0; i < 1; i++) {
                    DtTargetPengujian[barisCC][i] = Double.parseDouble(inputLine);
                }
                barisCC++;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DataTarget.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static void alokasiDataTargetMax(int kolom){
        dataTargetMax = new double[kolom];
    }
    
    public static int getJumlahKolomTargetPembelajaran(){
        return jumlahKolomTargetPembelajaran;
    }
    
    public static  int getJumlahBarisTargetPembelajaran(){
        return jumlahBarisTargetPembelajaran;
    }
    
    public static int getJumlahKolomTargetPengujian(){
        return jumlahKolomTargetPengujian;
    }
    
    public static int getJumlahBarisTargetPengujian(){
        return jumlahBarisTargetPengujian;
    }
    
    public static double getDataTargetPembelajaran(int i , int j){
        return DtTargetPembelajaran[i][j];
    }
    
    public static double getDataTargetPengujian(int i, int j){
        return DtTargetPengujian[i][j];
    }
            
}
