package com.video.startup.editthevideodio.util;

import com.google.gson.Gson;
import com.video.startup.editthevideodio.connection.dto.GenericDTO;

/**
 * Created by Diogo on 18/06/2017.
 */

public class JsonUtil {

    public static GenericDTO jsonToGeneric(String json){
        return new Gson().fromJson(json, GenericDTO.class);
    }

}
