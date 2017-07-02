package com.video.startup.editthevideodio.constantes;

/**
 * Classe que armazena constantes da aplicação
 */
public class ConstantesApp {

    private static final String SERVER = "https://silent-card-167518.appspot.com/request";
    private static final String SERVER_LOCALHOST = "127.0.0.1:8080";
    //recebe server que for utilizado
    private static final String SERVER_CURRENT = SERVER;

    //contexto para transações no banco
    private static final String TRANSACT = "/TS";

    //Entidades
    private static final String USUARIO = "/Usuario";
    private static final String EMPRESA = "/Empresa";
    private static final String ENDERECO = "/Endereco";

    public static final String APP_TESTE = SERVER_CURRENT + "/teste";

    //Transacoes
    private static final String SELECT_SINGLE = "/Recuperar/";
    private static final String SELECT_ALL = "/Pesquisar";

    public static final String APP_CRIAR_EMPRESA = SERVER_CURRENT + TRANSACT + "/CriarEmpresa";
    public static final String APP_CRIAR_FREE = SERVER_CURRENT + TRANSACT + "/CriarFreela";
    public static final String APP_CRIAR_USUARIO = SERVER_CURRENT + TRANSACT + "/CriarUsuario";
    public static final String APP_LISTAR_PROFISSIONAL = SERVER_CURRENT + TRANSACT + "/CriarUsuario";
    public static final String APP_LISTAR_ = SERVER_CURRENT + TRANSACT + "/CriarUsuario";

    public static final String APP_RECUPERAR_USUARIO = SERVER_CURRENT + TRANSACT + USUARIO + SELECT_SINGLE;
    public static final String APP_RECUPERAR_EMPRESA = SERVER_CURRENT + TRANSACT + EMPRESA + SELECT_SINGLE;


    public static final String APP_RECUPERAR_ALL_USUARIO = SERVER_CURRENT + TRANSACT + USUARIO + SELECT_ALL;
    public static final String APP_RECUPERAR_ALL_EMPRESA = SERVER_CURRENT + TRANSACT + EMPRESA + SELECT_ALL;


    //nome de objetos recebidos como json
    public static final String USUARIO_JSON = "usuario";
    public static final String ENDERECO_JSON = "endereco";
    public static final String EMPRESA_JSON = "empresa";
    public static final String LISTA_JSON = "lista";

}
