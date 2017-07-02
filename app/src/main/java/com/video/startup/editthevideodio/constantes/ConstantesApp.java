package com.video.startup.editthevideodio.constantes;

/**
 * Classe que armazena constantes da aplicação
 */
public class ConstantesApp {

    private static final String SERVER = "https://silent-card-167518.appspot.com/request";
    private static final String SERVER_LOCALHOST = "http://10.0.3.2:8080/request";
    //recebe server que for utilizado
    private static final String SERVER_CURRENT = SERVER_LOCALHOST;

    //contexto para transações no banco
    private static final String TRANSACT = "/TS";

    //Entidades
    private static final String USUARIO = "/Usuario";
    private static final String EMPRESA = "/Empresa";
    private static final String ENDERECO = "/Endereco";
    private static final String PROFISSIONAL = "/Profissional";

    public static final String APP_TESTE = SERVER_CURRENT + "/teste";

    //Transacoes
    private static final String SELECT_SINGLE = "/Recuperar/";
    private static final String SELECT_ALL = "/Pesquisar";
    private static final String INCLUIR = "/Cadastrar";

    //SELECT WHERE ID
    public static final String APP_RECUPERAR_USUARIO = SERVER_CURRENT + TRANSACT + USUARIO + SELECT_SINGLE;
    public static final String APP_RECUPERAR_EMPRESA = SERVER_CURRENT + TRANSACT + EMPRESA + SELECT_SINGLE;
    public static final String APP_RECUPERAR_PROFISSIONAL = SERVER_CURRENT + TRANSACT + PROFISSIONAL + SELECT_SINGLE;

    //SELECT *
    public static final String APP_RECUPERAR_ALL_USUARIO = SERVER_CURRENT + TRANSACT + USUARIO + SELECT_ALL;
    public static final String APP_RECUPERAR_ALL_EMPRESA = SERVER_CURRENT + TRANSACT + EMPRESA + SELECT_ALL;
    public static final String APP_RECUPERAR_ALL_PROFISSIONAL = SERVER_CURRENT + TRANSACT + PROFISSIONAL + SELECT_ALL;

    //INCLUIR
    public static final String APP_INCLUIR_ENDERECO = SERVER_CURRENT + TRANSACT + ENDERECO + INCLUIR;
    public static final String APP_INCLUIR_USUARIO = SERVER_CURRENT + TRANSACT + USUARIO + INCLUIR;
    public static final String APP_INCLUIR_EMPRESA = SERVER_CURRENT + TRANSACT + EMPRESA + INCLUIR;
    public static final String APP_INCLUIR_PROFISSIONAL = SERVER_CURRENT + TRANSACT + PROFISSIONAL + INCLUIR;

    //nome de objetos recebidos como json
    public static final String USUARIO_JSON = "usuario";
    public static final String ENDERECO_JSON = "endereco";
    public static final String EMPRESA_JSON = "empresa";
    public static final String PROFISSIONAL_JSON = "profissional";
    public static final String LISTA_JSON = "lista";

}
