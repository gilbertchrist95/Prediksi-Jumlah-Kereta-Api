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
public class BackPropogation {

    int layerMasukan = 7;
    int layerTersembunyi = 14;
    int layerKeluaran = 2;
     public int maxIterasi = 1000;
    
    long n=6;
    long p=13; //p:hidden layer
    long y=1;
    double kecepatanPembelajaran;
    double minimumError;
    double X[] = new double[layerMasukan];
    double[] Zj = new double[layerTersembunyi];
    public double Yk[] = new double[2];
    double[] Yink = new double[layerKeluaran];
    double[] Dj = new double[layerTersembunyi];
    double[] Dk = new double[layerKeluaran];
    double[][] Wjk = new double[layerTersembunyi][layerKeluaran];
    double[][] Update_Wjk = new double[layerTersembunyi][layerKeluaran];
    double t[] = new double[layerKeluaran];
    double[][] Vij = new double[layerMasukan][layerTersembunyi];
    double[][] Update_Vij = new double[100][100];
    double w[][] = new double[100][100];
    double Maks[]= new double[100];
    public static double ye;
    
    double MSE;
    
    public BackPropogation(){
        
    }
    
    public BackPropogation(int n, int hiddeLayer, int y) {
        setN(n);
        setP(hiddeLayer);
        setY(y);
    }
    
    public void setN(int n){
        this.n = n;
    }
    
    public void setP(int p){
        this.p = p;
    }
    
    public void setY(int y){
        this.y = y;
    }
    
    public void setPembelajaran(double pembelajaran){
        this.kecepatanPembelajaran = pembelajaran;
    }
    
    public void setMinError (double minError){
        this.minimumError = minError;
    }
    
    public void setX(int i, double X){
        this.X[i] = X;
    }
    
    public void setZj(int i, double Z){
        this.Zj[i] = Z;
    }
    
    public void setYk(int i, double Y){
        this.Yk[i] = Y;
    }
    
    public void setDj(int i, double erh){
        this.Dj[i] = erh;
    }
    
    public void setDk(int i, double err){ //hitung δk
        this.Dk[i] = err;
    }
    
    public void setT(int i, double t){
        this.t[i] = t;
    }
    
    public  void setVij(int i, int j, double value){
        this.Vij[i][j] = value;
    }
    
    public double getVij(int i, int j){
        return this.Vij[i][j];
    }
    
     public  void setUpdate_Vij(int i, int j, double value){
        this.Update_Vij[i][j] = value;
    }
     
    public double getUpdate_Vij(int i, int j){
        return this.Vij[i][j];
    }

    
//    public void setW(int i, int j, double value){
//        this.w[i][j] = value;
//    }
    
    public void setMSE(double MSE){
        this.MSE = MSE;
    }
    
     public long getN(){
        return this.n;
    }
    
    public long getP(){
        return this.p;
    }
    
    public long getY(){
        return this.y;
    }
    
    public double getLearningRate(){
        return this.kecepatanPembelajaran;
    }
    
    public double getMinimumError(){
        return this.minimumError;
    }
    
    public double getX (int i){
        return this.X[i];
    }
    
    public double getZj(int i){
        return this.Zj[i];
    }

    public double getYk(int i){
        return this.Yk[i];
    }
    
    public double getDk(int i){
       return this.Dk[i];
    }
    
    public double getDj(int i){
        return this.Dj[i];
    }
    public double getT(int i){
        return this.t[i];
    }
    
    public double getMSE(){
        return MSE;
    }
    
    public void setMaks(int kolom, double value){
        this.Maks[kolom] = value;
    }
    
    public double getMaks(int kolom){
        return this.Maks[kolom];
    }
    
    
//    public  double getW(int i, int j){
//        return this.w[i][j];
//    }
//    
    public void setWjk (int j, int k, double value ){
        this.Wjk[j][k] = value;
    }
    
    public void setUpdate_Wjk (int j, int k, double value ){
        this.Update_Wjk[j][k] = value;
    }
    
    public double getWjk (int j, int k){
        return this.Wjk[j][k];
    }
    
    public double getUpdate_Wjk (int j, int k){
        return this.Update_Wjk[j][k];
    }
    
    double fAktivasi (double x){
        return (1/(1+(Math.exp(-x))));
    }
    
    double fAktivasiAksen (double x){
        return fAktivasi(x)*(1-(fAktivasi(x)));
    }
    
    public void hitungUnitTersembunyi(){
        //hitung nilai Zin[j] = Zin[j] + (X[i]+Vij[i][j];
        //abis itu Zj[j] = sigmoid(Zj[in][j]);
        int p = (int) this.getP();
        int n = (int) this.getN();
//        System.out.println(p);
//        System.out.println(n);
//        System.out.println("");
        for (int j = 1; j <= p; j++) {
//          System.out.println(j);
            double sum=0;
//            this.setZj(j, 0);
           //Z[j]  = 0;
            for (int i = 1; i <= n; i++) {
                double x = this.getX(i);
                double vij = this.getVij(i, j);
                sum =sum+(x*vij);
            }
            this.setZj(j, sum);
//            System.out.print(this.getZj(j)+" ");
            
        }
        
        for (int j = 1; j <= p; j++) {
            double x = fAktivasi(this.getZj(j));
//            Zj[j] = fAktivasi(x);
            this.setZj(j, x);
//             System.out.print(this.getZj(j)+" ");
        }
    }

    public void hitungUnitKeluaran(){
            //hitung nilai Yink[k] = Yink[k] + (Zj[j]*Wjk[j][k];
            //abis itu Yk[k] = sigmoid (Yink[k]);
            int y = (int) this.getY();
            int p = (int) this.getP();
//            System.out.println(y);
            for (int k = 1; k <= y; k++) {
                double sum = 0;
//                this.setYk(k,0);
                for (int j = 1; j <= p; j++) {
                    double zj = this.getZj(j);
                    double wjk = this.getWjk(j, k);
                    sum = sum + (zj*wjk);
                }
                this.setYk(k, sum);
            }
            for (int k = 1; k <= y; k++) {
                double x = fAktivasi(this.getYk(k));
                this.setYk(k, x);                
            }
            
	}
  
	public void hitungErrorKeluaran(){
//            δk=(tk-yk) f’(y_netk)= (tk-yk) yk(1-yk) 
            int y = (int) this.getY();
//            System.out.println("Nilai Y:"+y);
//            System.out.println(this.getT(0));
//            System.out.println(this.getT(1));
//            System.out.println(this.getYk(0));
//            System.out.println(this.getYk(1));
            for (int k=1; k <= y; k++) {
                double tk = this.getT(k);
                double yk = this.getYk(k);
                double dk = (tk-yk)*(yk*(1-yk));
                this.setDk(k, dk);
//                System.out.println(this.getDk(k));;
            }
            
	}

	public void hitungSukuPerubahanBobotKeluaran(){
//            ∆wkj= α δkzj
            
            int p = (int) this.getP();
            int y = (int) this.getY();
            double alfa =  this.getLearningRate();
            for (int j = 1; j <= p ; j++) {
                double zj = this.getZj(j);
                for (int k = 1; k <= y; k++) {
                    double dk = this.getDk(k);
                    double update_wjk = alfa*dk*zj;
                    this.setUpdate_Wjk(j, k, update_wjk);
//                    System.out.println(this.getWjk(j, k));
                }
                
            }
	}

	public void hitungErrorTersembunyi(){
//          hitung delta d_inJ=δ_netjf’(z_netj);
            int p = (int) this.getP();
            int y = (int) this.getY();
            for (int j = 1; j <= p; j++) {
                double sum =0;
                for (int k = 1; k <= y; k++) {
                    double dk = this.getDk(k);
                    double wjk =  this.getWjk(j, k);
                    sum = sum + (dk*wjk);
                }
                double zj = this.getZj(j);
                double dj = sum*zj*(1-zj);
                this.setDj(j, dj);
//                System.out.println(this.getDj(j));
            }
	}

	public void hitungSukuPerubahanBobotTersembunyi(){
//          ∆vji=α δjx 
            int x = (int)this.getN();
            int p = (int) this.getP();
            double alfa =  this.getLearningRate();
            for (int i = 1; i <= x; i++) {
                double xi = this.getX(i);
                for (int j = 1; j <= p; j++) {
                    double dj = this.getDj(j);
                    double updateVij = alfa* dj * xi;
//                    update_Vij[i][j] = a * deltaJ[j] * xi[i];
                    this.setUpdate_Vij(i, j, updateVij);
//                    System.out.println(this.getUpdate_Vij(i, j));
                }
            }
	}

    public void hitungPerubahanBobotKeluaran(int iterasi){
//            update Wjk =wkj(lama) + ∆wkj 
            int p = (int) this.getP();
            int y = (int) this.getY();
            for (int j = 1; j <= p ; j++) {
                for (int k = 1; k <= y; k++) {
                    this.Wjk[j][k] = this.Wjk[j][k] + this.Update_Wjk[j][k];
//                    System.out.println(this.getUpdate_Wjk(j, k));
                }
            }
    }

    public void hitungPerubahanBobotTersembunyi(int iterasi){
//              update Vij= vji(lama) + ∆vji
            int x = (int)this.getN();
            int p = (int) this.getP();
            for(int i=1; i<=x; i++){
                for(int j=1; j<=p; j++){
//                Vij[i][j] = Vij[i][j] + update_Vij[i][j];
                    this.Vij[i][j] = this.Vij[i][j] + this.Update_Vij[i][j];
//                    System.out.println(this.getVij(i, j));
                }   
            }
    }

    public void hitungMSE(){
            //ini ni hitung ERH
            // for k=0-y -> temp + temp+(0.5*(pow((t[k]-yk[k]),2)));
            // return temp;
            double temp = 0;
            int y = (int) this.getY();
            for (int k = 1; k <= y; k++) {
//                System.out.println("Nilai t[k]: "+ this.getT(k));
                temp = temp + (0.5*Math.pow(this.getT(k)-this.getYk(k),2));
                this.setMSE(temp);
//                System.out.println(this.getMSE()+" "+ this.getMinimumError());
            }
	}
}
