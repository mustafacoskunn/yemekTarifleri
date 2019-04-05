package tarifleri.yemekler.yemektarifleri.com.yemektarifleri;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.squareup.picasso.Picasso;

import java.util.List;

import maes.tech.intentanim.CustomIntent;

public class tariflerAdapter extends RecyclerView.Adapter<tariflerAdapter.cardViewTutucum>{
    private Context mContext;
    private List<tarifler> tariflerList;

    public tariflerAdapter(Context mContext, List<tarifler> tariflerList) {
        this.mContext = mContext;
        this.tariflerList = tariflerList;
    }


    @Override
    public cardViewTutucum onCreateViewHolder( ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.cardiviewtasarimi,parent,false);
        //cardTasarimTutucuya gönderelim bunu bunun için nesne oluşturucam
        return new cardViewTutucum(view);
    }

    @Override
    public void onBindViewHolder(final cardViewTutucum holder, final int position) {
      final tarifler tarifler=tariflerList.get(position);
        Typeface face=Typeface.createFromAsset(mContext.getAssets(),"fonts/deneme1.otf");
        Typeface face1=Typeface.createFromAsset(mContext.getAssets(),"fonts/deneme2.otf");
        holder.yemekAdText.setTypeface(face);
        holder.kisiSayisiText.setTypeface(face1);
        holder.hazirlamasuresiText.setTypeface(face1);
        YoYo.with(Techniques.FadeInDown).playOn(holder.cardView); //aşagı dogru animasyon
      holder.yemekAdText.setText(tarifler.getYemekad());
      holder.hazirlamasuresiText.setText("Hazırlama süresi:"+tarifler.getHazirlamasuresi()+" dk");
      String sure=String.valueOf(tarifler.getSure());
      if(tarifler.getSure()==0){
          sure="-";
      }
       holder.hazirlamasuretext.setText(":"+sure+" dk");
      holder.kisiSayisiText.setText(String.valueOf(tarifler.getKisisayisi()+" kisilik"));
        //holder.resim.setImageResource(mContext.getResources().getIdentifier(tariflerList.get(position).getResim()
          //      ,"drawable", mContext.getPackageName())); //bu kod resimin ismine göre drawablenden çekmeye yarıyor sıra sıra card view ekliyor

        Picasso.with(mContext)
                .load("http://indirkaydet.com/yemekresimleri/"+tariflerList.get(position).getResim()+".jpg")
                .into(holder.resim);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               YoYo.with(Techniques.FadeOutLeft).playOn(holder.cardView);

                new CountDownTimer(750, 750) {

                    public void onTick(long millisUntilFinished) {

                    }

                    public void onFinish() {
                        Intent intent=new Intent(mContext,Detay.class);
                        intent.putExtra("tarifbilgi",tarifler);
                        intent.putExtra("asd",mContext.getResources().getIdentifier(tariflerList.get(position).getResim()
                                ,"drawable", mContext.getPackageName()));
                        mContext.startActivity(intent);
                        CustomIntent.customType(mContext, "fadein-to-fadeout"); //animasyonlu geçiş



                    }

                }.start();

            }
        });


    }

    @Override
    public int getItemCount() {
        return tariflerList.size();
    }

    public class cardViewTutucum extends RecyclerView.ViewHolder { // biz bu sınıfı yukardaki sınıfa bağlamamız gerekli

        public CardView cardView;
        public TextView kisiSayisiText,hazirlamasuresiText,yemekAdText,hazirlamasuretext;

        private ImageView resim;





        public cardViewTutucum(View itemView) { //textview vs şeyleri bağlamak için
            super(itemView);
            cardView=itemView.findViewById(R.id.cardview);
            kisiSayisiText=itemView.findViewById(R.id.kisiSayisiText);
            hazirlamasuresiText=itemView.findViewById(R.id.hazirlamasuresiText);
            yemekAdText=itemView.findViewById(R.id.yemekAdText);
            resim=itemView.findViewById(R.id.resim);
            hazirlamasuretext=itemView.findViewById(R.id.hazirlamasuretext);

        }
    }
}

