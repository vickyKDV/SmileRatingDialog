package com.vickykdv.myapplication;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.vickykdv.ratingdialog.BottomSheetRating;
import com.vickykdv.ratingdialog.CenterRating;

@SuppressLint("SetTextI18n")
public class MainActivity extends AppCompatActivity {

    private CenterRating centerRating;
    private  BottomSheetRating bottomSheetRating;
    TextView txt_rating;
    TextView txt_komentar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt_komentar = findViewById(R.id.txt_komentar);
        txt_rating = findViewById(R.id.txt_rating);

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



//              Center Rating
                centerRating = new CenterRating.Builder(MainActivity.this)
                        .setTitle("Beri Rating")
                        .setMessage("Beri nilai pada aplikasi kami...")
                        .setCancelable(false)
                        .setPositiveButton("Kirim",  new BottomSheetRating.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Log.d("MainActivity", "Rating: "+centerRating.getKomentar());
                                Log.d("MainActivity", "Komentar: "+centerRating.getRating());
                                txt_komentar.setText("Komentar: "+centerRating.getKomentar());
                                txt_rating.setText("Rating: "+centerRating.getRating());
                                dialogInterface.dismiss();
                            }
                        })
                        .setNegativeButton("Batal",  new BottomSheetRating.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                Toast.makeText(getApplicationContext(), "batal!", Toast.LENGTH_SHORT).show();
                                dialogInterface.dismiss();
                            }
                        })
                        .build();
                centerRating.show();
            }
        });

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                // Bottom Rating
                 bottomSheetRating = new BottomSheetRating.Builder(MainActivity.this)
                        .setTitle("Beri Rating")
                        .setMessage("Beri nilai pada aplikasi kami...")
                        .setCancelable(false)
                        .setPositiveButton("Kirim",  new BottomSheetRating.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Log.d("MainActivity", "Rating: "+bottomSheetRating.getKomentar());
                                Log.d("MainActivity", "Komentar: "+bottomSheetRating.getRating());
                                txt_komentar.setText("Komentar: "+bottomSheetRating.getKomentar());
                                txt_rating.setText("Rating: "+bottomSheetRating.getRating());
                                dialogInterface.dismiss();
                            }
                        })
                        .setNegativeButton("Batal",  new BottomSheetRating.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                Toast.makeText(getApplicationContext(), "batal!", Toast.LENGTH_SHORT).show();
                                dialogInterface.dismiss();
                            }
                        })
                        .build();
                bottomSheetRating.show();
            }
        });

    }
}
