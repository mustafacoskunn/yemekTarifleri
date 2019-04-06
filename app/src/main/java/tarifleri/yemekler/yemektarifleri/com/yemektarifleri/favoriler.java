package tarifleri.yemekler.yemektarifleri.com.yemektarifleri;

import android.database.sqlite.SQLiteDatabase;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;

import java.io.IOException;
import java.util.ArrayList;


public class favoriler extends AppCompatActivity  implements SearchView.OnQueryTextListener{
    Toolbar toolbar1;
    RecyclerView rv1;
    ArrayList<tarifler> tariflerListt;
    private  tariflerAdapter adapterr;
    private Veritabani vtt;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoriler);
        veritabaniKopyala();
        toolbar1=findViewById(R.id.toolBar1);
        rv1=findViewById(R.id.rv1);
        rv1.setHasFixedSize(true);
        rv1.setLayoutManager(new LinearLayoutManager(this));

        vtt=new Veritabani(this);


        tariflerListt=new tariflerDAO().tariflerArrayListFavori(vtt);


        adapterr=new tariflerAdapter(this,tariflerListt);
        rv1.setAdapter(adapterr);

        toolbar1.setTitle("Favorilerim");
        setSupportActionBar(toolbar1); //toolbarı ekle
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { //menu arama yapmak için


        getMenuInflater().inflate(R.menu.menu,menu); //menuyu aktif et diyoruz
        MenuItem menuItem=menu.findItem(R.id.yemekAra); //yemekAra ya bastıgında olacaklar
        SearchView searchView= (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(this);// bu searchView nesnesi burdaki listeneri dinlemektedir
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onQueryTextSubmit(String query) { //arama tuşuna bastıgında veri gönderiyor(aradıgımız veri)

        System.out.println("onQueryTextSubmit:"+query);
        aramaYap(query);


        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) { //aradıgımız zaman sürekli bize veri döndürüyor (giridigmiz harfi dinliyor)
        System.out.println("onQueryTextChange:"+newText);

        aramaYap(newText);
        return false;
    }

    public void veritabaniKopyala(){
        DatabaseCopyHelper databaseCopyHelper=new DatabaseCopyHelper(this);


        try {
            databaseCopyHelper.createDataBase();
        } catch (IOException e) {

        }


    }
    public void aramaYap(String searchKelime){
        tariflerListt=new tariflerDAO().favıritarifAra(vtt,searchKelime);


        adapterr=new tariflerAdapter(this,tariflerListt);
        rv1.setAdapter(adapterr);

    }
}
