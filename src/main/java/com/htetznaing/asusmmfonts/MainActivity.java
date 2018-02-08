package com.htetznaing.asusmmfonts;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<String> images = new ArrayList<>();
    MgAdapter adapter;
    AdRequest adRequest;
    InterstitialAd interstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        images = getImages();
        listView = findViewById(R.id.listView);
        adapter = new MgAdapter(this,images);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showAD();
                Intent intent = new Intent(MainActivity.this,InstallActivity.class);
                intent.putExtra("font",getNames().get(i)+"(iFont).apk");
                intent.putExtra("name",getNames().get(i));
                startActivity(intent);
            }
        });
        adRequest = new AdRequest.Builder().build();

        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-2780984156359274/4391067541");
        interstitialAd.loadAd(adRequest);
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                loadAD();
            }

            @Override
            public void onAdFailedToLoad(int i) {
                loadAD();
            }

            @Override
            public void onAdOpened() {
                loadAD();
            }
        });
    }

    public void loadAD(){
        if (!interstitialAd.isLoaded()){
            interstitialAd.loadAd(adRequest);
        }
    }

    public void showAD(){
        if (interstitialAd.isLoaded()){
            interstitialAd.show();
        }else{
            interstitialAd.loadAd(adRequest);
        }
    }

    public ArrayList<String> getImages(){
        ArrayList<String> arrayList = new ArrayList<>();
        String s1 = "dancing.png\n" +
                "darcy.png\n" +
                "eainmat.png\n" +
                "ghost.png\n" +
                "heart.png\n" +
                "htinshu.png\n" +
                "izawgyi.png\n" +
                "jojar.png\n" +
                "kason.png\n" +
                "khninsi.png\n" +
                "love.png\n" +
                "matrix.png\n" +
                "nattaw.png\n" +
                "nayon.png\n" +
                "ngaye.png\n" +
                "notosans.png\n" +
                "notosansmix.png\n" +
                "ooredoo.png\n" +
                "padauk.png\n" +
                "paoh.png\n" +
                "pyatho.png\n" +
                "sagar.png\n" +
                "szg.png\n" +
                "tabaung.png\n" +
                "tabodwe.png\n" +
                "tabodwemix.png\n" +
                "tagu.png\n" +
                "tdg.png\n" +
                "tran.png\n" +
                "tsm.png\n" +
                "ttl.png\n" +
                "ubuntu.png\n" +
                "warso.png\n" +
                "wg.png\n" +
                "yoeyar.png\n" +
                "yuzana.png\n" +
                "zg2.png\n" +
                "zo.png\n" +
                "zy.png";
        String s2[] = s1.split("\n");
        for (int i=0;i<s2.length;i++){
            arrayList.add(s2[i]);
        }

        return arrayList;
    }

    public ArrayList<String> getNames(){
        ArrayList<String> goo = new ArrayList<>();
        String s1 = "Dancing\n" +
                "Darcy\n" +
                "EainMat\n" +
                "Ghost\n" +
                "Heart\n" +
                "Htinshu\n" +
                "iPhoneZawgyi\n" +
                "Jojar\n" +
                "Kason\n" +
                "KhinHninsi\n" +
                "Love\n" +
                "Matrix\n" +
                "Nattaw\n" +
                "Nayon\n" +
                "NgaYe\n" +
                "NotoSansZawgyi\n" +
                "NotoSansZawgyiMix\n" +
                "Ooredoo\n" +
                "Padauk\n" +
                "PaOh\n" +
                "Pyatho\n" +
                "Sagar\n"+
                "SmartZawgyi\n" +
                "Tabaung\n" +
                "Tabodwe\n" +
                "TabodweMix\n" +
                "Tagu\n" +
                "Tawthalin\n" +
                "Transformer\n" +
                "Tansaungmone\n" +
                "Tawthalin\n" +
                "Ubuntu\n" +
                "Warso\n" +
                "Wargaung\n" +
                "YoeYar\n" +
                "Yuzana\n" +
                "Zawgyi2\n" +
                "ZawgyiOne\n" +
                "ZawgyiYang";
        String s2 []= s1.split("\n");
        for (int i=0;i<s2.length;i++){
            goo.add(s2[i]);
        }
        return goo;
    }

    public void share(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,"Beautiful Myanmar Font Style For ASUS Zen Phones.\n Download Free Here : play.google.com/store/apps/details?id="+getPackageName());
        startActivity(Intent.createChooser(intent,"ASUS Myanmar Font Style"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.feedback:
                AlertDialog.Builder builder = new AlertDialog.Builder(this)
                        .setPositiveButton("Facebook", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                try {
                                    Uri uri = Uri.parse("fb-messenger://user/627699334104477");
                                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                    startActivity(intent);
                                }catch (Exception e){
                                    Intent intent = new Intent(Intent.ACTION_VIEW);
                                    intent.setData(Uri.parse("https://m.facebook.com/messages/read/?tid=627699334104477"));
                                    startActivity(intent);
                                }
                            }
                        })
                        .setNeutralButton("Email", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                                        "mailto", "mmapps2018.com@gmail.com", null));
                                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "ASUS Myanmar Font Style");
                                emailIntent.putExtra(Intent.EXTRA_TEXT, "Enter your feedback here");
                                startActivity(Intent.createChooser(emailIntent, "Send email..."));
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("Attention!")
                .setMessage("Do you want to exit ?")
                .setIcon(R.drawable.icon)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        showAD();
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        showAD();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
