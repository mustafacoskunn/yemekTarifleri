package tarifleri.yemekler.yemektarifleri.com.yemektarifleri;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class tariflerDAO {
    public ArrayList<tarifler> tariflerArrayList(Veritabani vt){
        ArrayList<tarifler> tariflerArrayList=new ArrayList<>(); //her seferinde boşalt

        SQLiteDatabase db=vt.getWritableDatabase();
        Cursor c=db.rawQuery("select * from Yemektarifleri ORDER BY RANDOM() LIMIT 3540",null);
        while (c.moveToNext()){
            tarifler t=new tarifler(c.getInt(c.getColumnIndex("id")),
                    c.getString(c.getColumnIndex("yemekad")),
                            c.getString(c.getColumnIndex("hazirlamasuresi")),
                            c.getInt(c.getColumnIndex("kisisayisi")),
                            c.getString(c.getColumnIndex("resim")),
                            c.getString(c.getColumnIndex("malzeme")),
                             c.getString(c.getColumnIndex("yemektarifi")));
            tariflerArrayList.add(t);
        }
        return tariflerArrayList;

    }
    public ArrayList<tarifler> tarifAra(Veritabani vt,String arananTarif){
        ArrayList<tarifler> tariflerArrayList=new ArrayList<>(); //her seferinde boşalt

        SQLiteDatabase db=vt.getWritableDatabase();
        Cursor c=db.rawQuery("select * from Yemektarifleri where yemekad like '%"+arananTarif+"%'",null);
        while (c.moveToNext()){
            tarifler t=new tarifler(c.getInt(c.getColumnIndex("id")),
                    c.getString(c.getColumnIndex("yemekad")),
                    c.getString(c.getColumnIndex("hazirlamasuresi")),
                    c.getInt(c.getColumnIndex("kisisayisi")),
                    c.getString(c.getColumnIndex("resim")),
                    c.getString(c.getColumnIndex("malzeme")),
                    c.getString(c.getColumnIndex("yemektarifi")));
            tariflerArrayList.add(t);
        }
        return tariflerArrayList;

    }
}
