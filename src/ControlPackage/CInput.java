/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControlPackage;


import EntitasPackage.DataInput;
import java.text.DecimalFormat;


/**
 *
 * @author Gilbert
 */
public class CInput extends DataInput{
    
    DataInput dataInput;
    
    static double tempMax, max;
    public CInput() {
     //super();  
     dataInput = new DataInput();
    }
    
    public void setDataInput1(String fileNameMasukanPembelajaran)
    {
        setLocationPembelajaran(fileNameMasukanPembelajaran);
        setKolomBarisPembelajaran();
        alokasiDataPembelajaran();
    }
    
    public void setDataInput2(String fileNameMasukanPengujian){
        setLocationPengujian(fileNameMasukanPengujian);
        setKolomBarisPengujian();
        alokasiDataPengujian();
    }
    
    public static void normalisasiDataInputLatih(){
        int baris, kolom;
        
        double tempMin, min;
        DataInput.alokasiDataInputMax(DataInput.getJumlahKolomPembelajaran());
//        System.out.println(DataInput.getJumlahKolomPembelajaran());
        for (kolom = 0; kolom < DataInput.getJumlahKolomPembelajaran(); kolom++) {
            tempMax = DataInput.getDataInputPembelajaran(0, kolom);
//            System.out.println("tempMax: "+ tempMax);
            for (baris = 1; baris<DataInput.getJumlahBarisPembelajaran(); baris++) {
               max  = searchmax(tempMax, DataInput.getDataInputPembelajaran(baris, kolom));
               tempMax = max;
                //System.out.println(max);
            }
            DataInput.setDataInputMax(kolom, tempMax);
            for (baris = 0; baris < DataInput.getJumlahBarisPembelajaran(); baris++) {
                double a  = DataInput.getDataInputPembelajaran(baris, kolom)/tempMax;
                DecimalFormat decimalFormat = new DecimalFormat("#.####");
                String b = decimalFormat.format(a);
                double dataInputan = Double.parseDouble(b);
                DataInput.setDataInputPembelajaran(baris,kolom,dataInputan);
                //System.out.println("a:" + a + " b: " +dataInputan );                
            }
        }
    }
    
    static double searchmax(double max, double temp){
        if (max > temp) return max;
        else return temp;
    }

    
}
