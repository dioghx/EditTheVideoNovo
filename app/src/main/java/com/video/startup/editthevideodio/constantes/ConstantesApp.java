package com.video.startup.editthevideodio.constantes;

/**
 * Classe que armazena constantes da aplicação
 */
public class ConstantesApp {

    private static final String SERVER = "http://silent-card-167518.appspot.com/request";
    private static final String SERVER_LOCALHOST = "127.0.0.1:8080";
    //recebe server que for utilizado
    private static final String SERVER_CURRENT = SERVER;

    //contexto para transações no banco
    private static final String TRANSACT = "/TS";
    private static final String USUARIO = "/Usuario";


    public static final String APP_TESTE = SERVER_CURRENT + "/test";

    public static final String APP_CRIAR_EMPRESA = SERVER_CURRENT + TRANSACT + "/CriarEmpresa";
    public static final String APP_CRIAR_FREE = SERVER_CURRENT + TRANSACT + "/CriarFreela";
    public static final String APP_CRIAR_USUARIO = SERVER_CURRENT + TRANSACT +USUARIO+ "/Cadastrar";
}
