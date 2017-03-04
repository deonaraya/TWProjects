package com.deo.tw.api;

import org.testng.annotations.Test;

import java.util.HashMap;

/**
 * Created by chandrad on 8/1/16.
 */
public class CiscoTests {


    @Test
    public void practice(){

        int i = 0;
        int sum = 0;

        while (i < 6)
        {
            sum += i*i ;
            i+= 1;
        }
        System.out.println(sum);
    }

    @Test
    public void pattern(){

        int i = 0;
        for (int row = 9; row > 0; row--){

            for ( int k = i ; k >0;k--){
                System.out.print(" ");
            }
            for ( int j = 9 - i ; j >0 ; j--){
                System.out.print(j);
            }

            i++;
            System.out.println(" ");
        }

        String s1 = new String("geeksforgeeks");
        String s2 = new String("geeksforgeeks");
        if (s1.equals(s2))
            System.out.println("Equal");
        else
            System.out.println("Not equal");
    }

    @Test
    public void fibonaachi(int n ){

        // sample : 0 1 1 2 3 5 8 13 21
            int i = 0;
            int j = 1;
            int k = 0;
        System.out.print(i);
        int ser = 0;


        while (n>1){

            ser+= ser ;

        }
    }

    public Character recString(String str) {
        HashMap<Character, Integer> mp = new HashMap<Character, Integer>();

        for (int i = 0; i < str.length() - 1; i++) {
            if (mp.containsKey(str.charAt(i))) {
                mp.put(str.charAt(i), mp.get(str.charAt(i)) + 1);
            } else
                mp.put(str.charAt(i), 1);
        }
        System.out.println(mp);

        for (int j = 0; j < str.length(); j++) {
            if (mp.get(str.charAt(j)) == 1) {
                return str.charAt(j);
            }
        }
        return null;

    }

    @Test
    public void test(){
     //   recString("india");
        System.out.println(recString("abrabrab"));
    }
    



}
