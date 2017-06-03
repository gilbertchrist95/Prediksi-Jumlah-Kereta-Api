/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControlPackage;

import EntitasPackage.DataTarget;
import java.text.DecimalFormat;

/**
 *
 * @author Gilbert
 */
public class CTarget extends DataTarget{

    DataTarget dataTarget = new DataTarget();           
    static double tempMax, max;
    public CTarget() {
        super();
        
    }
    
    public void setDataTarget1(String fileNameMasukanTarget)
    {
        setLocationTargetPembelajaran(fileNameMasukanTarget);
        setKolomBarisTargetPembelajaran();
        alokasiDataTargetPembelajaran();
    }
    
    public void setDataTarget2(String fileNameMasukanTargetPengujian){
        getLocationTargetPengujian(fileNameMasukanTargetPengujian);
        setKolomBarisTargetPengujian();
        alokasiDataTargetPengujian();
    }
    
    public static double searchMax(double max, double temp){
        if (temp>max){
            max = temp;
        }
        return max;
    }
    
   public static void normalisasiDataTargetLatih(){
       int baris, kolom;
       double tempMin, min;
       DataTarget.alokasiDataTargetMax(DataTarget.getJumlahKolomTargetPembelajaran());
       //System.out.println(DataTarget.getJumlahKolomTargetPembelajaran());
       for (kolom = 0; kolom < DataTarget.getJumlahKolomTargetPembelajaran(); kolom++){
           tempMax = DataTarget.getDataTargetPembelajaran(0,kolom);
          // System.out.println(tempMax);
           for (baris = 1; baris<DataTarget.getJumlahBarisTargetPembelajaran(); baris++) {
               max= searchMax(tempMax, DataTarget.getDataTargetPembelajaran(baris, kolom));
               tempMax = max;
               //System.out.println(tempMax);
           }
           DataTarget.setDataTargetMax(kolom, tempMax);
           for (baris = 0; baris<DataTarget.getJumlahBarisTargetPembelajaran(); baris++) {
               double a  = DataTarget.getDataTargetPembelajaran(baris, kolom)/tempMax;
               DecimalFormat decimalFormat = new DecimalFormat("#.####");
                String b = decimalFormat.format(a);
                double dataTargetan = Double.parseDouble(b);
                DataTarget.setDataTargetPembelajaran(baris, kolom, dataTargetan);
                //System.out.println(dataTargetan);
           }
       }
   }
    
}
