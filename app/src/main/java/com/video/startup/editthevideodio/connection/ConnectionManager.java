package com.video.startup.editthevideodio.connection;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

import com.video.startup.editthevideodio.connection.dto.GenericDTO;
import com.video.startup.editthevideodio.util.JsonUtil;
import com.video.startup.editthevideodio.util.Util;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectionManager {



    /**
     * Executa POST de forma assincrona ao servidor
     */
    public static GenericDTO executePOSTAsync(Object postParam, Context context, String url, Action action) throws Exception{

        return new GenericHTTPRequest(context, action){

            @Override
            protected String doInBackground(Object... params) {
                HttpURLConnection urlConnection = null;
                try {
                    urlConnection = (HttpURLConnection) new URL((String)params[1]).openConnection();
                    urlConnection.setRequestMethod("POST");
                    urlConnection.setRequestProperty("Content-type", "application/json");
                    urlConnection.setDoInput(true);
                    urlConnection.setDoOutput(true);

                    DataOutputStream outputStream = new DataOutputStream(urlConnection.getOutputStream());

                    outputStream.writeBytes(Util.convertObjectJSON(params[0]));

                    urlConnection.getResponseCode();
                    String response = Util.webToString(urlConnection.getInputStream());

                    outputStream.flush();
                    outputStream.close();

                    return response ;
                } catch (Exception e) {
                    Log.e("Error", "Error ", e);
                    return null;
                } finally{
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                }
            }

            GenericDTO doRequest(Object... postParamIn){
                execute(postParamIn);
                return payload;
            }
        }.doRequest(postParam,url);

    }

    /**
     * Executa POST de forma assincrona ao servidor sem ação
     */
    public static GenericDTO executePOSTAsync(Object postParam, Context context, String url) throws Exception {
        return executePOSTAsync(postParam, context, url, (GenericDTO dto) -> {});
    }


    /**
     * Classe async base para outros tipos de requests http
     */
    private static class GenericHTTPRequest extends AsyncTask<Object, Void, String> {

        public GenericHTTPRequest(Context context, Action postAction) {
            viewContext = context;
            this.postAction = postAction;
        }

        GenericDTO payload;
        Context viewContext;
        Action postAction;

        @Override
        protected String doInBackground(Object... params){return null;}

        @Override
        protected void onPreExecute() {
            if(!isConnected()){
                throw new RuntimeException("Erro de conexao com internet");
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            GenericDTO dto = JsonUtil.jsonToGeneric(s);
            if(!dto.isOk()) {
//                tratar erro
            }
            postAction.action(dto);
            payload = dto;
        }

        protected boolean isConnected(){
            ConnectivityManager cm =
                    (ConnectivityManager) viewContext.getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            return activeNetwork != null &&
                    activeNetwork.isConnectedOrConnecting();
        }
    }


    /**
     * Classes usadas para passagem e execução de ações apos async
     */
    public interface Action { void action(GenericDTO dto);}

}
