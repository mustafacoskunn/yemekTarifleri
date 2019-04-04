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

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener { //arama özellgini yapmak için

    Toolbar toolbar;
    RecyclerView rv;
    ArrayList<tarifler> tariflerList;
    private  tariflerAdapter adapter;
    private Veritabani vt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        vt=new Veritabani(this);
        veritabaniKopyala();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=findViewById(R.id.toolBar);
        rv=findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));



        tariflerList=new tariflerDAO().tariflerArrayList(vt);


        adapter=new tariflerAdapter(this,tariflerList);
        rv.setAdapter(adapter);

        toolbar.setTitle("");
        setSupportActionBar(toolbar); //toolbarı ekle

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
        tariflerList=new tariflerDAO().tarifAra(vt,searchKelime);


        adapter=new tariflerAdapter(this,tariflerList);
        rv.setAdapter(adapter);

    }
}
