package tarifleri.yemekler.yemektarifleri.com.yemektarifleri;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;



import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;

import maes.tech.intentanim.CustomIntent;

public class Detay extends AppCompatActivity {

    private tarifler tarif;
    private tarifler gelen;
    TextView malzemeGetir,yemekDetay,yemekadi,malzemeler,tariftext;
    ImageView resim,favoriekle;
    SQLiteDatabase db;
    DatabaseCopyHelper databaseCopyHelper;
    Toolbar toolbar3;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    int tarifidsi;
    int tiklama;
    int tikiliMi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detay);
        malzemeGetir=findViewById(R.id.malzemeGetir);
        Typeface kalin=Typeface.createFromAsset(getAssets(),"fonts/deneme1.otf");
        Typeface ince=Typeface.createFromAsset(getAssets(),"fonts/deneme2.otf");
        resim=findViewById(R.id.resim);
        yemekDetay=findViewById(R.id.yemekDetay);
        yemekadi=findViewById(R.id.yemekadi);
        malzemeler=findViewById(R.id.malzemeler);
        tariftext=findViewById(R.id.tariftext);
        yemekDetay.setTypeface(ince);
        yemekadi.setTypeface(kalin);
        malzemeGetir.setTypeface(ince);
        malzemeler.setTypeface(kalin);
        tariftext.setTypeface(kalin);
        favoriekle=findViewById(R.id.favoriekle);
        favoriekle.setBackgroundResource(R.drawable.ic_favorite_border_black_24dp);
        favoriekle.setTag((R.drawable.ic_favorite_border_black_24dp));
        toolbar3=findViewById(R.id.toolBar3);
        toolbar3.setTitle("");
        setSupportActionBar(toolbar3);
        tarif=(tarifler) getIntent().getSerializableExtra("tarifbilgi");

        int a= (int) getIntent().getSerializableExtra("asd");


        System.out.println("geldi1");

         databaseCopyHelper=new DatabaseCopyHelper(this);
        try {
            databaseCopyHelper.createDataBase();
            System.out.println("geldi2");
            db=databaseCopyHelper.getWritableDatabase();
            Cursor c=db.rawQuery("select * from Yemektarifleri where id="+tarif.getId()+" order by id desc",null);
            while (c.moveToNext()){

                gelen=new tarifler(c.getInt(c.getColumnIndex("id")),
                        c.getString(c.getColumnIndex("yemekad")),
                        c.getString(c.getColumnIndex("hazirlamasuresi")),
                        c.getInt(c.getColumnIndex("kisisayisi")),
                        c.getString(c.getColumnIndex("resim")),
                        c.getString(c.getColumnIndex("malzeme")),
                        c.getString(c.getColumnIndex("yemektarifi")),
                        c.getInt(c.getColumnIndex("sure")));
                malzemeGetir.setText(c.getString(c.getColumnIndex("malzeme")));
                yemekDetay.setText(c.getString(c.getColumnIndex("yemektarifi")));
                yemekadi.setText(c.getString(c.getColumnIndex("yemekad")));
                Picasso.with(Detay.this)
                        .load("http://indirkaydet.com/yemekresimleri/"+c.getString(c.getColumnIndex("resim"))+".jpg")
                        .into(resim);
                tarifidsi=c.getInt(c.getColumnIndex("id"));

            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("hatalı"+e);

        }



        databaseCopyHelper=new DatabaseCopyHelper(getApplicationContext());
        try {
            databaseCopyHelper.createDataBase();
            System.out.println("geldi2");
            db=databaseCopyHelper.getWritableDatabase();
            Cursor c=db.rawQuery("select * from favoriler where favoriid="+tarif.getId(),null);
            while (c.moveToNext()) {
                favoriekle.setBackgroundResource(R.drawable.ic_favorite_black_24dp);
                favoriekle.setTag((R.drawable.ic_favorite_black_24dp));

            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("hatalı"+e);

        }


        System.out.println("taglar"+favoriekle.getTag());

        favoriekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if((Integer)favoriekle.getTag()==2131165286){
                    favoriekle.setBackgroundResource(R.drawable.ic_favorite_border_black_24dp);
                    favoriekle.setTag((R.drawable.ic_favorite_border_black_24dp));
                    System.out.println("açıksimge;"+favoriekle.getTag());
                    db.execSQL("delete from favoriler where favoriid="+tarif.getId());
                   Snackbar.make(v,"Favorilerden kaldırıldı",Snackbar.LENGTH_LONG).setAction("Favorilere Git", new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           Intent intent=new Intent(getBaseContext(),favoriler.class);
                           startActivity(intent);
                       }
                   }).show();
                }
                else{
                    favoriekle.setBackgroundResource(R.drawable.ic_favorite_black_24dp);
                    favoriekle.setTag((R.drawable.ic_favorite_black_24dp));
                    System.out.println("kapalisimge;"+favoriekle.getTag());
                    ContentValues cv = new ContentValues();
                    cv.put("favoriid", tarifidsi);

                    db.insert("favoriler",null,cv);
                    Snackbar.make(v,"Favorilere eklendi",Snackbar.LENGTH_LONG).setAction("Favorilere Git", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(getBaseContext(),favoriler.class);
                            startActivity(intent);
                        }
                    }).show();


                }




            }
        });




        System.out.println("sdsdsd;"+favoriekle.getTag());

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
        CustomIntent.customType(this, "fadein-to-fadeout"); //animasyonlu geçiş


        return super.onKeyDown(keyCode, event);
    }
}
