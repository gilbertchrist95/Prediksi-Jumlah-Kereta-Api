/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControlPackage;

import Apackage.FormMenuUtama;
import EntitasPackage.BackPropogation;
import EntitasPackage.DataBobot;
import EntitasPackage.DataInisialisasi;
import EntitasPackage.DataInput;
import EntitasPackage.DataTarget;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gilbert
 */
public class CPembelajaran {

    private int tempIterasi[];
    int iterasi;
    int totalIteration;
    double error;
    BackPropogation backPropogation;
    CInisialisasi kInisialisasi;
    CInput kInput;
    CTarget kTarget;
    CBobot kBobot;
    DataBobot dataBobot;
    static String lokasiV;
    static String lokasiW;
    
    public CPembelajaran() {
        
        backPropogation = new BackPropogation(
                kInisialisasi.getN(),
                kInisialisasi.getHiddenLayer(),
                kInisialisasi.getY()
        );
        backPropogation.setPembelajaran(kInisialisasi.getLearningRate());
        backPropogation.setMinError(kInisialisasi.getMinError());
        dataBobot = new DataBobot(){};
        kBobot = new CBobot();
//        System.out.println("Layer masukan: "+ backPropogation.getN());
//        System.out.println("Hidden Layer : "+ backPropogation.getP());
//        System.out.println("Layer keluaran: "+ backPropogation.getY());
//        System.out.println("Learning rate: "+ backPropogation.getLearningRate());
//        System.out.println("Minimum error: "+ backPropogation.getMinimumError());
    }
    
    public void setIterasi(int iterasi){
        this.iterasi = iterasi;
    }
    
    public int getIterasi(){
        return this.iterasi;
    }
    
    public void setError(double value){
        this.error = value;
    }
    
    public double getError(){
        return this.error;
    }
    
    public void setTotalIterasi(int totalIterasi){
        this.totalIteration = totalIterasi;
    }
    
    public int getTotalIterasi(){
        return this.totalIteration;
    }
    
    public void prosesPelatihan(){
        int baris, i = 0,j,iterasi, totalIterasi;

        
        int n =  (int) this.backPropogation.getN();
//        System.out.println("nilai N: "+n);
        int hL = (int) this.backPropogation.getP();
//        System.out.println("nilai hL: "+hL);
        int y = (int) this.backPropogation.getY();
//        System.out.println("nilai y: "+y);
        
        inisialBobotV(n,hL);
//        for (int f = 0; f < n; f++) {
//            for (int g = 0; g < hL ; g++) {
//                System.out.println(this.backPropogation.getVij(f, g));
//            }
//        }
        inisialBobotW(hL,y);
//        for (int f = 0; f <hL; f++) {
//            for (int g = 0; g < y ; g++) {
//                System.out.println(this.backPropogation.getWjk(f, g));
//            }
//        }    
        alokasiIterasi();
        kInput.normalisasiDataInputLatih();
        kTarget.normalisasiDataTargetLatih();
        
        totalIterasi = 0;
        for (baris = 0; baris < DataInput.getJumlahBarisPembelajaran(); baris++) {
//            System.out.println(baris);
            iterasi =1;
            setJaringan(baris);
            pelatihan(iterasi);
//            System.out.println(this.getIterasi());
            
            totalIterasi = totalIterasi +  this.getIterasi();
        }
       
        this.setTotalIterasi(totalIterasi);
//        System.out.println("Total Iterasi:"+this.getTotalIterasi());
//        System.out.println("Nilai error  :"+this.getError());
        
        
        simpanBobotV();
        simpanBobotW();
    }
    
    
    void alokasiIterasi(){
        tempIterasi = new int[kInput.getJumlahBarisPembelajaran()];
        //System.out.println("getJumlahBarisPembelajaran: "+kInput.getJumlahBarisPembelajaran());
    }
    
    void setJaringan(int baris){
        int i;
       
        for (i = 1 ; i <= this.backPropogation.getN(); i++) {
            //System.out.println(this.backPropogation.getN());
            //System.out.println(DataInput.getDataInputPembelajaran(baris, i-1));
           this.backPropogation.setX(i, DataInput.getDataInputPembelajaran(baris, i-1));
           // System.out.println(this.backPropogation.getX(i));
        }
        
        this.backPropogation.setX(0, 1);
        
        for (i = 1; i <= this.backPropogation.getY(); i++) {
//            System.out.println(this.backPropogation.getY());
            this.backPropogation.setT(i, DataTarget.getDataTargetPembelajaran(baris, i-1));
//            System.out.println(this.backPropogation.getT(i));
        }
        this.backPropogation.setMSE(1);
    }
    
    void pelatihan(int iterasi){
        int i, j, k;
        
        while ((this.backPropogation.getMSE() >= this.backPropogation.getMinimumError()) &&
                (iterasi < this.backPropogation.maxIterasi ))
        {
//            System.out.println(this.backPropogation.getMSE());
//            System.out.println(this.backPropogation.getMinimumError());
//            System.out.println("Iterasi: "+iterasi);
            this.backPropogation.hitungUnitTersembunyi();
            this.backPropogation.hitungUnitKeluaran();
            this.backPropogation.hitungMSE();
            this.backPropogation.hitungErrorKeluaran();
            this.backPropogation.hitungSukuPerubahanBobotKeluaran();
            this.backPropogation.hitungErrorTersembunyi();
            this.backPropogation.hitungSukuPerubahanBobotTersembunyi();
            this.backPropogation.hitungPerubahanBobotKeluaran(iterasi);
            this.backPropogation.hitungPerubahanBobotTersembunyi(iterasi);
            
            iterasi++;
        }
        
        int x = (int) this.backPropogation.getN();
        int p = (int) this.backPropogation.getP();
        int y = (int) this.backPropogation.getY();
        for (int a = 1; a <= x; a++) {
            for (int b = 1; b <= p ; b++) {
                double v = this.backPropogation.getVij(a, b);
//                System.out.println(v);
                this.dataBobot.setV(a, b, v);
//                System.out.println(this.dataBobot.getV(a, b));
            }
        }
        
        for (int c  = 1; c <=y ; c++) {
            for (int b = 1; b <= p; b++) {
                double w = this.backPropogation.getWjk(b, c);
//                System.out.println(w);
                this.dataBobot.setW(b, c, w);
            }
        }
        setIterasi(iterasi-1);
        setError(this.backPropogation.getMSE());
    }
    
    
    void inisialBobotV (int n, int p){
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= p; j++) {
                double acak = CBobot.acakBobot();
                DecimalFormat decimalFormat = new DecimalFormat("#.####");
                String b = decimalFormat.format(acak);
                double dataInputan = Double.parseDouble(b);
                this.backPropogation.setVij(i, j, dataInputan);
                
            }
        }
    }
    
    void inisialBobotW(int hL,int y){
        for (int j = 1; j <= hL ; j++) {
            for (int k = 1; k <=y ; k++) {
                double acak = CBobot.acakBobot();
                DecimalFormat decimalFormat = new DecimalFormat("#.####");
                String b = decimalFormat.format(acak);
                double dataInputan = Double.parseDouble(b);
//                this.backPropogation.setW(i, j, dataInputan);
                this.backPropogation.setWjk(j, k, dataInputan);
            }
        }
    }
    
    public void simpanBobotV(){
        lokasiV = CBobot.getNamaFileBobotV();
        String a = lokasiV;
//        System.out.println(a);
        File fileSave = new File(a);
        
        
        double x[][] = new double[7][14];
        
        for (int i = 1; i <= 6; i++) {
            for (int j = 1; j <= 13; j++) {
                double vv = this.backPropogation.getVij(i, j);
                DecimalFormat decimalFormat = new DecimalFormat("#.######");
                String b = decimalFormat.format(vv);
                double dataInputan = Double.parseDouble(b);
                x[i][j] = dataInputan;
            }
        }
        
        try {
            PrintWriter printWriter = new PrintWriter(fileSave);
            for (int i = 1; i <= 6; i++) {
                for (int j = 1; j <= 13; j++) {
                    printWriter.print(x[i][j] + " ");
                }
                printWriter.println();
            }
            printWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(CPembelajaran.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
    
    public void simpanBobotW(){
         lokasiW = CBobot.getNamaFileBobotW();
        String b = lokasiW;
//        System.out.println(a);
        File fileSave = new File(b);

        double x[][] = new double[14][2];
        
        for (int i = 1; i <= 13; i++) {
            for (int j = 1; j <= 1; j++) {
//                x[i][j]=this.backPropogation.getWjk(i, j);
                double ww = this.backPropogation.getWjk(i, j);
                DecimalFormat decimalFormat = new DecimalFormat("#.######");
                String bb = decimalFormat.format(ww);
                double dataInputan = Double.parseDouble(bb);
                x[i][j] = dataInputan;
            }
        }
        
        try {
            PrintWriter printWriter = new PrintWriter(fileSave);
            for (int i = 1; i <= 13; i++) {
                for (int j = 1; j <= 1; j++) {
                    printWriter.print(x[i][j] + " ");
                }
                printWriter.println();
            }
            printWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(CPembelajaran.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
