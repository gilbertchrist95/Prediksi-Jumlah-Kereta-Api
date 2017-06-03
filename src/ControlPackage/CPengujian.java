/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControlPackage;

import EntitasPackage.BackPropogation;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gilbert
 */
public class CPengujian {

    BackPropogation backPropogation;
    CInput kInput = new CInput();
    double data[] = new double[35];
    double keluaran[][] = new double[50][2];
    double tingkatAkurasi[] = new double[35];
    
    private String namaFileHasilPengujian;
    private String namaFileTingkatAkurasi;
    private String lokasiSimpan;
    double[] dataV = new double[100];
    double[] dataW = new double[100];
    
    public CPengujian() {
        backPropogation = new BackPropogation(
                6,13,1
        ); 
    }
    
    public void setKeluaran(int baris, int kolom, double keluaran){
        this.keluaran[baris][kolom] = keluaran;
    }
    
    public double getKeluaran(int baris, int kolom){
        return this.keluaran[baris][kolom];
    }
    
    public void setDataTargetPengujian (int i, double data){
        this.data[i] = data;
    }
    
    public double getDataTargetPengujian(int i){
        return this.data[i];
    }
    
    public void setTingkatAkurasi (int i, double akurasi){
        this.tingkatAkurasi[i] = akurasi;
    }
    
    public double getTingkatAkurasi (int i){
        return this.tingkatAkurasi[i];
    }
    
    public void setNamaFileHasilPengujian(String nama){
        this.namaFileHasilPengujian = nama;
    }
    
    public void setNamaFileTingkatAkurasi(String nama){
        this.namaFileTingkatAkurasi = nama;
    }
    
    public String getNamaFileHasilPengujian(){
        return this.namaFileHasilPengujian;
    }
    
    public String getNamaFileTingkatAkurasi(){
        return this.namaFileTingkatAkurasi;
    }
    
    public void ambilBobotVW(){
        ambilV();
        ambilW();
    }
    
    public void ambilV(){
        int barisC=1;
        String inputLine;
        try {
            Scanner scanIn = new Scanner(new BufferedReader(new FileReader(CBobot.getNamaFileBobotV())));
            while (scanIn.hasNextLine()){
                inputLine = scanIn.nextLine();
                String[] inArray = inputLine.split(" ");
                for (int i = 0; i < inArray.length; i++) {
                    dataV[barisC] = Double.parseDouble(inArray[i]);
                    barisC++;
                }
            }
            int k = 1;
            for (int i = 1; i <= 6; i++) {
                for (int j =1; j<=13;j++){
                    this.backPropogation.setVij(i, j, dataV[k]);
                    k++;
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CPengujian.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void ambilW(){
       int barisC=1;
        String inputLine;
        try {
            Scanner scanIn = new Scanner(new BufferedReader(new FileReader(CBobot.getNamaFileBobotW())));
            while (scanIn.hasNextLine()){
                inputLine = scanIn.nextLine();
                dataW[barisC] = Double.parseDouble(inputLine);
                barisC++;
            }
//            for (int i = 0; i < 20; i++) {
//                System.out.println(dataW[i]);
//            }
            int k=1;
            for (int i=1;i<=13;i++){
                this.backPropogation.setWjk(i, 1, dataV[k]);
                k++;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CPengujian.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void normalisasiDataInput(){
        int rows =  CInput.getJumlahBarisPengujian();
        int cols = CInput.getJumlahKolomPengujian();
//        System.out.println(rows + " "+ cols);
        setMaks();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                CInput.setDataInputPengujian(i, j, CInput.getDataInputPengujian(i, j)/this.backPropogation.getMaks(j));
            }
        }
//        for (int i = 0; i < rows; i++) {
//            for (int j = 0; j < cols; j++) {
//                System.out.print(CInput.getDataInputPengujian(i, j));
//            }
//            System.out.println("");
//        }
    }
    
    public void setMaks(){
        int rows =  CInput.getJumlahBarisPengujian();
        int cols = CInput.getJumlahKolomPengujian();
        double tempMax,max;
        
        for (int kolom = 0; kolom < cols; kolom++) {
            tempMax = CInput.getDataInputPengujian(0, kolom);
            for (int baris = 1; baris < rows; baris++) {
                max  = searchmax(tempMax, CInput.getDataInputPengujian(baris, kolom));
                tempMax = max;
            }
            this.backPropogation.setMaks(kolom, tempMax);
        }
        
//        for (int i = 0; i < cols; i++) {
//            System.out.println(this.backPropogation.getMaks(i));
//        }
        
    }
    
    double searchmax(double max, double temp){
        if (max > temp) return max;
        else return temp;
    }
    
    public void uji(){
        int i,j;
        int baris,kolom;
        
        ambilBobotVW();
        normalisasiDataInput();
        
        for (i = 0; i < CInput.getJumlahBarisPengujian(); i++) {
            for (j = 0;  j< CInput.getJumlahKolomPengujian(); j++) {
                double dataUji = CInput.getDataInputPengujian(i, j);
                this.backPropogation.setX(j+1, dataUji);
            }
            this.backPropogation.setX(0, 1);
            
            this.backPropogation.hitungUnitTersembunyi();
            this.backPropogation.hitungUnitKeluaran();
            
            int y = (int) this.backPropogation.getY();
//            System.out.println(y);
            
            for (j = 1; j <=y ; j++) {
//                System.out.println(this.backPropogation.getMaks(5));
//                System.out.println(this.backPropogation.Yk[j]);
                this.setKeluaran(i+1, j, this.backPropogation.getYk(j)*this.backPropogation.getMaks(j+4));
                
            }
            this.setDataTargetPengujian(i+1,CTarget.getDataTargetPengujian(i,0));
            
            double dataKeluaran = this.getKeluaran(i+1, y);
            double target = this.getDataTargetPengujian(i+1);
            double akurasi;
            
            if (dataKeluaran<target) {
                akurasi = (dataKeluaran/target) *100.00;
                this.setTingkatAkurasi(i+1, akurasi);
            }
            else if (dataKeluaran>target) {
                akurasi = (target/dataKeluaran) *100.00;
                this.setTingkatAkurasi(i+1, akurasi);
            }
            else if (dataKeluaran==target) {
                akurasi = 100.00;
                this.setTingkatAkurasi(i+1, akurasi);
            }
        }
    }
    
    public void formatHasil (){
        for (int i = 1; i <= CTarget.getJumlahBarisTargetPengujian(); i++) {
            double dataKeluaran, dataTarget, tingkatAkurasi;
            int dtKeluaran, dtTarget;
            double tAkurasi;
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            
            dataKeluaran = this.getKeluaran(i, 1);
            dtKeluaran = (int) dataKeluaran;
            
            dataTarget = this.getDataTargetPengujian(i);
            dtTarget = (int) dataTarget;
            
            tingkatAkurasi = this.getTingkatAkurasi(i);
            String a = decimalFormat.format(tingkatAkurasi);
            tAkurasi = Double.parseDouble(a);
            
            this.setKeluaran(i, 1, dtKeluaran);
            this.setDataTargetPengujian(i, dtTarget);
            this.setTingkatAkurasi(i, tAkurasi);
        }
    }
    
    public void simpanHasilUji(String lokasiSimpan){
        this.lokasiSimpan = lokasiSimpan;
        File fileSave  = new File(this.lokasiSimpan);
        
        double dataSimpan[][] = new double[50][4];
        
        for (int i = 1; i <= CTarget.getJumlahBarisTargetPengujian(); i++) {
            dataSimpan[i][1] = this.getKeluaran(i, 1);
            dataSimpan[i][2] = this.getDataTargetPengujian(i);
            dataSimpan[i][3] = this.getTingkatAkurasi(i);
        }
        
        try {
            PrintWriter printWriter = new PrintWriter(fileSave);
            printWriter.print("Data Target,Data Prediksi,Tingkat Akurasi\n");
            for (int i = 1; i <= CTarget.getJumlahBarisTargetPengujian(); i++){
                for (int j = 1; j <=3; j++) {
                    printWriter.print(dataSimpan[i][j]+",");
                }
                printWriter.print("\n");
            }
            printWriter.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CPengujian.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            
        
    }
}


