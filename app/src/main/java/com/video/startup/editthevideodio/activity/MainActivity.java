package com.video.startup.editthevideodio.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInstaller;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.view.MenuInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.video.startup.editthevideodio.R;
import com.video.startup.editthevideodio.util.ProfissionalAdapter;
import com.video.startup.editthevideodio.util.Util;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {



    private static final String TAG = "MainActivity";
    private TextView nomeUsuario;
    private ImageView imageUsuario;
    private Boolean informacoesFlag;
    private ProfissionalAdapter profissionalAdapter;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        //Vendo se o intent esta com informação
        informacoesFlag =  new Util().setarBoleanaIntent(pref);
        callbackManager = CallbackManager.Factory.create();
        //Criando tabs na unha
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Profissionais"));
        tabLayout.addTab(tabLayout.newTab().setText("Empresas"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //Criando viewPager para setar um adapter
        final ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager_app_bar);

        //Utilizando Adapter Criando na classe FragmentMainAdapter passando como parâmetros um manager,e  o numero de  tabs para fazer contagem
        //da quantidade de tabs.
        final FragmentMainAdapter fragmentMainAdapter = new FragmentMainAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(fragmentMainAdapter);


        //Adicionando metódos de listener para tab
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        FacebookSdk.sdkInitialize(this);
        AppEventsLogger.activateApp(this);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

            //Compara se bundle possui valor
        if(informacoesFlag || AccessToken.getCurrentAccessToken()!=null) {

            Bundle extra = getIntent().getExtras();
            // Instancia um view com referência de navigationview,pegando o primeiro index.
            //Caso faça uma referência de navigation view em um objeto antes de pegar seu header,ele retornará nulo e lançara
            //null pointer exception
            //Versão 23 utiliza listView em uma navigationMain,versões mais atuais utilizam RecyclerView,quando se referência sem
            //pegar o headerView,o objeto não estará instânciado.
            View header = navigationView.getHeaderView(0);
            nomeUsuario = (TextView) header.findViewById(R.id.mainNomeUsuario);
            imageUsuario = (ImageView) header.findViewById(R.id.imageViewUsuario);
            new Util().loadImage(this,((SharedPreferences) pref).getString("fotoUsuario",null), imageUsuario);
            nomeUsuario.setText(((SharedPreferences) pref).getString("nomeUsuario",null));
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        Menu menuNav = navigationView.getMenu();

        MenuItem cadastrarMainMenu = menuNav.findItem(R.id.cadastrarMain);
        MenuItem logarMainMenu = menuNav.findItem(R.id.logarMain);
        MenuItem configuracoesMainMenu = menuNav.findItem(R.id.configuraçoesMain);
        MenuItem deslogarMainMenu = menuNav.findItem(R.id.deslogarMain);
        if (informacoesFlag|| AccessToken.getCurrentAccessToken()!=null )
        {
            cadastrarMainMenu.setVisible(false);
            logarMainMenu.setVisible(false);
        }
        else
        {    deslogarMainMenu.setVisible(false);
            configuracoesMainMenu.setVisible(false);
        }

     return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        SharedPreferences.Editor pref = getSharedPreferences("pref", MODE_PRIVATE).edit();
        int id = item.getItemId();


        if (id == R.id.cadastrarMain) {
            Intent intent = new Intent(MainActivity.this,EscolherCadastroActivity.class);
            this.startActivity(intent);
        } else if (id == R.id.logarMain) {
            Intent intent = new Intent(MainActivity.this,TelaLoginActivity.class);
            this.startActivity(intent);

        }
        else if (id == R.id.configuraçoesMain) {
            Intent intent = new Intent(this,ConfiguracoesActivity.class);
            this.startActivity(intent);

        }
        else if (id == R.id.deslogarMain) {
            Intent intent = new Intent(MainActivity.this,TelaLoginActivity.class);
            pref.clear();
            pref.commit();
            LoginManager.getInstance().logOut();
            this.startActivity(intent);
            finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

}
