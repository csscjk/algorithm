package wanzhuansuanfamianshi.chapter2;

import java.text.DecimalFormat;

public class Tools {
    /**
     * 求变量x的n次方:
     *      分析： x的6次方 等于 x的3次方 乘以 x的3次方。 x的7次方 等于 x的3次方 乘以 x的3次方  乘以 x
     * @param x 变量
     * @param n 次方
     * @return  x的n次方
     */
    public static double pow(double x,int n){
        //DecimalFormat decimalFormat = new DecimalFormat("#.00");
        // 或者 使用 Double.parseDouble(String.format("%.2f", result));
        if(x==0){
            return 0;
        }
        if(n==0){
            return 1.0;
        }
        boolean flag=false;
        if(n<0){
            n=-n;
            flag=true;
        }
        double t = pow(x,n/2);
        if(flag){
            t=1/t;
            x=1/x;
        }

        if(n%2==0) {
            return Double.parseDouble(String.format( "%.2f",t * t));
        }else {
            return Double.parseDouble(String.format("%.2f",x * t * t));
        }
    }
}
