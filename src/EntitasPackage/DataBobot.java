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
public class DataBobot {

    static int layerMasukan1 = 7;
    static int layerTersembunyi1 = 14;
    static int layerKeluaran1 = 2;
    
    private static int layerTersembunyi;
    private double V[][] = new double[layerMasukan1][layerTersembunyi1];
    private double W[][] = new double[layerTersembunyi1][layerKeluaran1];
    
    public DataBobot() {
    }
    
    public static void setLayerTersembunyi(int value){
        layerTersembunyi = value;
    }
    
    public static int getLayerTersembunyi(){
        return layerTersembunyi;
    }
    
    public void setV(int i, int j, double v){
        this.V[i][j] = v;
    }
    
    public double getV(int i, int j){
        return this.V[i][j];
    }
    
    public void setW(int j, int k, double v){
        this.W[j][k] = v;
    }
    
    public double getW(int j, int k){
        return this.W[j][k];
    }
    
}
