package com.edw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * <pre>
 *     com.edw.Main
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 28 Mei 2024 19:04
 */
public class Main {

    public static void main(String[] args) throws Exception {
        new Main().callRestAPI();
    }

    public Main () {

    }

    private void callRestAPI () throws Exception {
        URL url = new URL("https://some-insecure-url");

        //make connection
        URLConnection urlc = url.openConnection();

        //get result
        BufferedReader br = new BufferedReader(new InputStreamReader(urlc.getInputStream()));
        String l = null;
        while ((l=br.readLine())!=null) {
            System.out.println(l);
        }
        br.close();
    }

}
