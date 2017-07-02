package com.video.startup.editthevideodio.util;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.video.startup.editthevideodio.connection.dto.GenericDTO;
import com.video.startup.editthevideodio.constantes.ConstantesApp;
import com.video.startup.editthevideodio.model.Empresa;
import com.video.startup.editthevideodio.model.Endereco;
import com.video.startup.editthevideodio.model.Profissional;
import com.video.startup.editthevideodio.model.Usuario;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vitor on 18/06/2017.
 */

public class JsonUtil {

    private static Gson gson = new Gson();

    /**
     * Recebe json para converter em dto
     * @param json
     * @return
     */
    public static GenericDTO jsonToGeneric(String json){
        GenericDTO dto = null;
        try {
            JSONObject jsonDto = new JSONObject(json);
            dto = gson.fromJson(json, GenericDTO.class);
            if(isLista(jsonDto))
                dto.setLista((List<Object>)getResult(jsonDto));
            else
                dto.setObjeto(getResult(jsonDto));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return dto;
    }


    /**
     * Metodo usada para conversao de objetos de resultados dentro de GenericDTO
     * @param json
     * @return
     * @throws JSONException
     */
    private static Object getResult(JSONObject json) throws JSONException {

        if(json.has(ConstantesApp.LISTA_JSON) && !json.isNull(ConstantesApp.LISTA_JSON)){
            Type tipo = null;
            if(json.has(ConstantesApp.USUARIO_JSON)){
                tipo = new TypeToken<ArrayList<Usuario>>(){}.getType();
            }
            if(json.has(ConstantesApp.EMPRESA_JSON)){
                tipo = new TypeToken<ArrayList<Empresa>>(){}.getType();
            }
            if(json.has(ConstantesApp.ENDERECO_JSON)){
                tipo = new TypeToken<ArrayList<Endereco>>(){}.getType();
            }
            return (List<Object>)gson.fromJson(json.getJSONArray(ConstantesApp.LISTA_JSON).toString(), tipo);
        }
        if(json.has(ConstantesApp.USUARIO_JSON)){
            return jsonToUsuario(json);
        }
        if(json.has(ConstantesApp.EMPRESA_JSON)){
            return jsonToEmpresa(json);
        }
        if(json.has(ConstantesApp.ENDERECO_JSON)){
            return jsonToEndereco(json);
        }
        if(json.has(ConstantesApp.PROFISSIONAL_JSON)){
            return jsonToProfissional(json);
        }
        return null;
    }

    private static Usuario jsonToUsuario(JSONObject json) throws JSONException {
        JSONObject usuarioJson = json.getJSONObject(ConstantesApp.USUARIO_JSON);
        Usuario usuario = gson.fromJson(usuarioJson.toString(), Usuario.class);
        usuario.setEndereco(gson.fromJson(usuarioJson.getJSONObject(ConstantesApp.ENDERECO_JSON).toString(), Endereco.class));
        return usuario;
    }

    private static Empresa jsonToEmpresa(JSONObject json) throws JSONException {
        JSONObject empresaJson = json.getJSONObject(ConstantesApp.EMPRESA_JSON);
        Empresa empresa = gson.fromJson(empresaJson.toString(), Empresa.class);

        JSONObject usuarioJson = empresaJson.getJSONObject(ConstantesApp.USUARIO_JSON);
        Usuario usuario = gson.fromJson(usuarioJson.toString(), Usuario.class);

        usuario.setEndereco(gson.fromJson(usuarioJson.getJSONObject(ConstantesApp.ENDERECO_JSON).toString(), Endereco.class));
        empresa.setUsuario(usuario);
        return empresa;
    }

    private static Profissional jsonToProfissional(JSONObject json) throws JSONException{
         JSONObject empresaJson = json.getJSONObject(ConstantesApp.PROFISSIONAL_JSON);
        Profissional profissional = gson.fromJson(empresaJson.toString(), Profissional.class);

        JSONObject usuarioJson = empresaJson.getJSONObject(ConstantesApp.USUARIO_JSON);
        Usuario usuario = gson.fromJson(usuarioJson.toString(), Usuario.class);

        usuario.setEndereco(gson.fromJson(usuarioJson.getJSONObject(ConstantesApp.ENDERECO_JSON).toString(), Endereco.class));
        profissional.setUsuario(usuario);
        return profissional;
    }

    private static Endereco jsonToEndereco(JSONObject json) throws JSONException{
        return gson.fromJson(json.getJSONObject(ConstantesApp.ENDERECO_JSON).toString(), Endereco.class);
    }

    private static boolean isLista(JSONObject json){
        if(json.has(ConstantesApp.LISTA_JSON) && !json.isNull(ConstantesApp.LISTA_JSON)){
            return true;
        }
        return false;
    }

    public static String toJSON(Object p){
        if(p instanceof  Endereco){
            return gson.toJson(p, Endereco.class);
        }
        if(p instanceof  Usuario){
            return gson.toJson(p, Usuario.class);
        }
        if(p instanceof  Empresa){
            return gson.toJson(p, Empresa.class);
        }
        if(p instanceof  Profissional){
            return gson.toJson(p, Profissional.class);
        }
        return null;
    }

}