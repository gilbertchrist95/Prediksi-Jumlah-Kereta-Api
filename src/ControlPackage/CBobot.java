/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControlPackage;

import EntitasPackage.BackPropogation;
import EntitasPackage.DataBobot;
import java.util.Random;

/**
 *
 * @author Gilbert
 */
public class CBobot extends DataBobot{

    DataBobot dataBobot;
    private static String namaFileBobotV;
    private static String namaFileBobotW;
    private static String namaFileBobotMaks;
   
    
    BackPropogation backPropogation;
    
    public CBobot() {
        dataBobot = new DataBobot();
        backPropogation =  new BackPropogation();
    }
    
    public static void setNamaFileBobotV(String nama){
        namaFileBobotV = nama;
    }
    
    public static void setNamaFileBobotW(String nama){
        namaFileBobotW = nama;
    }
    
    public static  void setNamaFileBobotMaks(String nama){
        namaFileBobotMaks = nama;
    }
    
    public static String getNamaFileBobotV(){
        return namaFileBobotV;
    }
    
    public static String getNamaFileBobotW(){
        return namaFileBobotW;
    }
    
    public static String getNamaFileBobotMaks(){
        return namaFileBobotMaks;
    }
    
    
    
    public static double acakBobot(){
//        Vij[i][j] = ((rand()%32767)/32767.0 - 0.5)*2.0;
        Random rand = new Random();
        double range = 1 - (-1);
            double scaled = rand.nextDouble()*range;
            double shifted = scaled + (-1);
//            double random = ((rand.nextInt()%32767)/32767.0 - 0.5)*2.0;
            return shifted;
//            return random;
        
    }   
}


