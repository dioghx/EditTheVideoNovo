package com.video.startup.editthevideodio.util;

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


/**
 * Created by Diogo on 24/04/2017.
 */

public class Util {


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

    //Profissional
    public static Profissional JSONTProfissional(String jsonFile) {

        JSONObject mainObj;
        Profissional profissional = null;
        Gson gson = new Gson();

        try {

            mainObj = new JSONObject(jsonFile);
            JSONObject profissionalObject = mainObj.getJSONObject("profissional");
            profissional = gson.fromJson(profissionalObject.toString(), profissional.getClass());


        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            return profissional;
        }
    }

    public static List<Profissional> JSONProfissionalLista(String jsonFile)
    {
        int x ;
        JSONObject mainObject;
        Profissional profissional = null;
        List<Profissional> profissionais = null;
        Gson gson = new Gson();

        try {
            mainObject = new JSONObject(jsonFile);
            JSONArray listObj = mainObject.getJSONArray("lista");

            for (int i = 0; i <= listObj.length(); i++) {
                JSONObject profissionalObject = (JSONObject) listObj.get(i);
                profissional = gson.fromJson(profissionalObject.toString(), profissional.getClass());
                profissionais.add(profissional);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            return profissionais;
        }
    }

    //Usuario
    public static Usuario JSONUsuario(String jsonFile) {
        JSONObject mainObj;
        Usuario usuario = null;
        Gson gson = new Gson();
        try {
            mainObj = new JSONObject(jsonFile);
            JSONObject usuarioObj = mainObj.getJSONObject("usuario");
            usuario = gson.fromJson(usuarioObj.toString(), usuario.getClass());
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            return usuario;
        }
    }


    public static List<Usuario> JSONListaUsuario(String jsonFile) {
        JSONObject mainObj = null;
        Usuario usuario = null;
        List<Usuario> usuarios = null;
        Gson gson = new Gson();

        try {
            mainObj = new JSONObject(jsonFile);
            JSONArray listObj = mainObj.getJSONArray("lista");

            for (int i = 0; i <= listObj.length(); i++) {
                JSONObject usuarioObj = (JSONObject) listObj.get(i);
                usuario = gson.fromJson(usuarioObj.toString(), usuario.getClass());
                usuarios.add(usuario);
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            return usuarios;
        }
    }

    //Endereco
    public static Endereco JSONEndereco(String jsonFile) {
        JSONObject mainObj = null;
        Endereco endereco = null;
        Gson gson = new Gson();

        try {
            mainObj = new JSONObject(jsonFile);
            JSONObject enderecoObjeto = mainObj.getJSONObject("endereco");
            endereco = gson.fromJson(enderecoObjeto.toString(), endereco.getClass());

        } catch (JSONException e) {
            e.printStackTrace();
        } finally {

            return endereco;
        }

    }

    public static List<Endereco> JSONEnderecoLista(String jsonFile) {
        JSONObject mainObj = null;
        Endereco endereco = null;
        List<Endereco> enderecos = null;
        Gson gson = new Gson();
        try {
            mainObj = new JSONObject(jsonFile);
            JSONArray listaEndereco = mainObj.getJSONArray("lista");

            for (int i = 0; i <= listaEndereco.length(); i++) {
                JSONObject enderecoObjeto = (JSONObject) listaEndereco.get(i);
                endereco = gson.fromJson(enderecoObjeto.toString(), endereco.getClass());
                enderecos.add(endereco);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            return enderecos;
        }
    }

    //Genero
    public static Genero JSONGenero(String jsonFile) {
        JSONObject mainObj = null;
        Genero genero = null;
        Gson gson = new Gson();

        try {
            JSONObject generoObjeto = mainObj.getJSONObject("genero");
            genero = gson.fromJson(generoObjeto.toString(), genero.getClass());
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            return genero;
        }
    }

    public static List<Genero> JSONGeneroLista(String jsonFile) {
        JSONObject mainObj = null;
        Genero genero = null;
        List<Genero> generos = null;
        Gson gson = new Gson();

        try {
            mainObj = new JSONObject(jsonFile);
            JSONArray listaGenero = mainObj.getJSONArray("lista");
            for (int i = 0; i <= listaGenero.length(); i++) {
                JSONObject generoObjeto = (JSONObject) listaGenero.get(i);
                genero = gson.fromJson(generoObjeto.toString(), genero.getClass());
                generos.add(genero);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            return generos;
        }
    }


    //Video
    public static Video JSONVideo(String jsonFile) {

        JSONObject mainObj = null;
        Video video = null;
        Gson gson = new Gson();
        try {
            JSONObject videoObj = mainObj.getJSONObject("video");
            video = gson.fromJson(videoObj.toString(), video.getClass());
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            return video;
        }
    }

    public static List<Video> JSONVideoLista(String jsonFile) {

        JSONObject mainObj;
        List<Video> videos = null;
        Video video = null;
        Gson gson = new Gson();
        try {
            mainObj = new JSONObject(jsonFile);
            JSONArray listaVideoObjeto = mainObj.getJSONArray("lista");
            for (int i = 0; i <= listaVideoObjeto.length(); i++) {
                JSONObject videoObjeto = (JSONObject) listaVideoObjeto.get(i);
                video = gson.fromJson(videoObjeto.toString(), video.getClass());
                videos.add(video);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        finally {
            return  videos;
        }
    }

    //Empresa

    public static Empresa JSONEmpresa(String jsonFile)
    {
        JSONObject mainObj;
        Empresa empresa = null;
        Gson gson = new Gson();
        try
        {
            mainObj = new JSONObject(jsonFile);
            JSONObject empresaObjeto = mainObj.getJSONObject("empresa");
            empresa = gson.fromJson(empresaObjeto.toString(),empresa.getClass());
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        finally {
            return empresa;
        }
    }

    public static List<Empresa> JSONEmpresaLista(String jsonFile)
    {
        JSONObject mainObj;
        List<Empresa>empresas=null;
        Empresa empresa = null;
        Gson gson = new Gson();
        try
        {
            mainObj = new JSONObject(jsonFile);
            JSONArray listaEmpresaObjeto = mainObj.getJSONArray("lista");

            for (int i=0; i<=listaEmpresaObjeto.length();i++ )
            {
                JSONObject empresaObjeto = (JSONObject) listaEmpresaObjeto.get(i);
                empresa = gson.fromJson(empresaObjeto.toString(),empresa.getClass());
                empresas.add(empresa);
            }

        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        finally {

            return empresas;
        }
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

    public static String getStatusFromJSON(String json){
        try {
            return new JSONObject(json).getString("status");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Usuario permissaoLogar(String json)
    {
        Usuario usuario = null;

        try
        {
         usuario = JSONUsuario(json);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return usuario;
    }

}
