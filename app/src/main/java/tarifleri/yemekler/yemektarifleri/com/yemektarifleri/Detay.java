package tarifleri.yemekler.yemektarifleri.com.yemektarifleri;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

import maes.tech.intentanim.CustomIntent;

public class Detay extends AppCompatActivity {

    private tarifler tarif;
    private tarifler gelen;
    TextView malzemeGetir,yemekDetay;
    ImageView resim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detay);
        malzemeGetir=findViewById(R.id.malzemeGetir);

        resim=findViewById(R.id.resim);
        yemekDetay=findViewById(R.id.yemekDetay);
        tarif=(tarifler) getIntent().getSerializableExtra("tarifbilgi");

        int a= (int) getIntent().getSerializableExtra("asd");


        DatabaseCopyHelper databaseCopyHelper=new DatabaseCopyHelper(this);
        try {
            databaseCopyHelper.createDataBase();

            SQLiteDatabase db=databaseCopyHelper.getWritableDatabase();
            Cursor c=db.rawQuery("select * from Yemektarifleri where id="+tarif.getId()+" order by id desc",null);
            while (c.moveToNext()){
                gelen=new tarifler(c.getInt(c.getColumnIndex("id")),
                        c.getString(c.getColumnIndex("yemekad")),
                        c.getString(c.getColumnIndex("hazirlamasuresi")),
                        c.getInt(c.getColumnIndex("kisisayisi")),
                        c.getString(c.getColumnIndex("resim")),
                        c.getString(c.getColumnIndex("malzeme")),
                        c.getString(c.getColumnIndex("yemektarifi")));
                malzemeGetir.setText(c.getString(c.getColumnIndex("malzeme")));
                yemekDetay.setText(c.getString(c.getColumnIndex("yemektarifi")));
                resim.setImageResource(a);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }






    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
        CustomIntent.customType(this, "fadein-to-fadeout"); //animasyonlu geçiş


        return super.onKeyDown(keyCode, event);
    }
}
