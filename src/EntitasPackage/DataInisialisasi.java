/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntitasPackage;

/**
 *
 * @author Gilbert
 */
public class DataInisialisasi {
    
    private static int  n;
    private static int y;
    private static double learningRate;
    private static double minError;
    private static int hiddenLayer;
    
    public void setN(int lMasukan){
        this.n=lMasukan;
    }
    
    public void setY (int lKeluaran){
        this.y=lKeluaran;
    }
    
    public void setLearningrate (double lRate){
        this.learningRate = lRate;
    };
     
    public void setMinError (double mError){
        this.minError = mError;
    };
    
    public void setHiddenLayer (int hLayer){
        this.hiddenLayer  =hLayer;  
    };
    
    public static double getLearningRate(){
        return learningRate;
    };
    
    public static double getMinError()
    {
        return minError;
    }
    
    public static int getHiddenLayer(){
        return hiddenLayer;
    }
    
    public static int getN(){
        return n;
    }
    
    public static int getY(){
        return y;
    }
}
