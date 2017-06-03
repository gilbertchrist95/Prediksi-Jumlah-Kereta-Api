/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControlPackage;

import EntitasPackage.DataInisialisasi;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.rmi.runtime.Log;

/**
 *
 * @author Gilbert
 */
public class CInisialisasi extends DataInisialisasi{

    DataInisialisasi DInisialisasi;
    
    public CInisialisasi(int layerMasukan, int layerKeluaran, double learningRate, double minError, int hiddenLayer) {
        setLearningrate(learningRate);
        setMinError(minError);
        setHiddenLayer(hiddenLayer);
        setN(layerMasukan);
        setY(layerKeluaran);
    }
    
    public void simpanInisialisasi (int layerMasukan, int layerKeluaran,double learningRate, double minError, int hiddenLayer){
        String dataSave="";
        dataSave = dataSave + layerMasukan + "\n" + layerKeluaran + "\n" +learningRate + "\n" + minError + "\n" + hiddenLayer;

        File FSave = new File("Inisialisasi.txt");
        FileWriter fw;
        try {
            fw = new FileWriter(FSave);
            fw.write(dataSave);
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(CInisialisasi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
    public DataInisialisasi getInisialisasi(){
        return DInisialisasi;
    }
        
        
    
    
}
