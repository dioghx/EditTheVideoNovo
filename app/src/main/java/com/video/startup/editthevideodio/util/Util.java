package com.video.startup.editthevideodio.util;

import android.app.Activity;
import android.widget.Toast;

import com.google.gson.Gson;
import com.video.startup.editthevideodio.model.Empresa;
import com.video.startup.editthevideodio.model.Endereco;
import com.video.startup.editthevideodio.model.Genero;
import com.video.startup.editthevideodio.model.Profissional;
import com.video.startup.editthevideodio.model.Usuario;
import com.video.startup.editthevideodio.model.Video;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;
import java.util.Set;


/**
 * Created by Diogo on 24/04/2017.
 */

public class Util {


    public static String unmask(String s, Set<String> replaceSymbols) {

        for (String symbol : replaceSymbols)
            s = s.replaceAll("["+symbol+"]","");

        return s;
    }

    public static String mask(String format, String text){
        String maskedText="";
        int i =0;
        for (char m : format.toCharArray()) {
            if (m != '#') {
                maskedText += m;
                continue;
            }
            try {
                maskedText += text.charAt(i);
            } catch (Exception e) {
                break;
            }
            i++;
        }
        return maskedText;
    }
    public static String webToString(InputStream inputStream) {
        InputStream localStream = inputStream;
        String localString = "";
        Writer writer = new StringWriter();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(localStream, "UTF-8"));
            String line = reader.readLine();
            while (line != null) {
                writer.write(line);
                line = reader.readLine();
            }
            localString = writer.toString();
            writer.close();
            reader.close();
            localStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return localString;
    }

    public static String streamToString(InputStream is){
        BufferedReader br;
        StringBuilder sb = new StringBuilder();

        br = new BufferedReader(new InputStreamReader(is));
        String line;
        try {
            while((line = br.readLine()) != null){
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static String convertObjectJSON(Object p){
        Gson gson = new Gson();
        try {
            gson.toJson(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (String)p;
    }

    public static void toastShort(String msg, Activity a){
        Toast.makeText(a.getApplicationContext(),msg, Toast.LENGTH_SHORT).show();
    }
}
