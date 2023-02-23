package com.employee_modelandview_dao;

import java.util.Scanner;

public class tet {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(fibo(15));
        System.out.println(sumArr());

    }
    private static int fibo(int n){
        int a1=1, a2 =1;
        if(n==1|| n==2) return 1;
        int i =3, sum =0;
        while (i<=n){
            sum = a1 +a2;
            a1= a2;
            a2 = sum;
            i++;

        }
        return sum;
    }

    private static int sumArr(){
        int[] arr = {1,2,3,5};
        return 10;
    }
}
