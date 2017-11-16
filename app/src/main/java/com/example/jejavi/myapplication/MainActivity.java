package com.example.jejavi.myapplication;

import android.app.ProgressDialog;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.app.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Emisora> radios = new ArrayList<>();
    private RecyclerView recyclerView;
    private Emisora_adapter mAdapter;

    /// Reproductor de medios
    private Button btnplay,btnStop;
    private boolean playPause;
    private MediaPlayer mediaPlayer;
    private ProgressDialog progressDialog;
    private boolean initialStage = true;

    String urlRadio="http://radio.yaservers.com:8197/;stream.mp3";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView =  findViewById(R.id.viewRecycler);

        mAdapter = new Emisora_adapter(radios);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        //elementos de reproduccion
        btnplay = findViewById(R.id.btnplay);
        btnStop = findViewById(R.id.btnstop);

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        //mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        progressDialog = new ProgressDialog(this);
        //Eventos de Click en el recycler

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {

            @Override
            public void onClick(View view, int position) {
                Toast.makeText(MainActivity.this, radios.get(position).getNombre() +" "+radios.get(position).getCiudad() , Toast.LENGTH_SHORT).show();
                 urlRadio=radios.get(position).getUrl();

            }
            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initialStage = true;
                playPause = false;
                btnplay.setText("play");
                mediaPlayer.stop();
                mediaPlayer.reset();

            }
        });


        //Evento de click en el boton play
        btnplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!playPause) {
                    btnplay.setText("Pause ");

                    if (initialStage) {
                        //new Player().execute("https://www.ssaurel.com/tmp/mymusic.mp3");
                        //new Player().execute("http://i50.letio.com/9152.aac");
                        new Player().execute(urlRadio);
                    } else {
                        if (!mediaPlayer.isPlaying())
                            mediaPlayer.start();
                    }

                    playPause = true;

                } else {
                    btnplay.setText("Play");

                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.pause();
                    }

                    playPause = false;
                }
            }
        });

        iniciarRadios();


    }

    private void iniciarRadios() {

        Emisora radio1=new Emisora("Olimpica","Barranquilla","http://i50.letio.com/9102.aac", getResources().getDrawable(R.drawable.olimpica));
        radios.add(radio1);

        Emisora radio2=new Emisora("Olimpica","Santa Marta","http://radio.yaservers.com:8197/;stream.mp3", getResources().getDrawable(R.drawable.olimpica));
        radios.add(radio2);

        Emisora radio3=new Emisora("Olimpica","Cartagena","http://radio.yaservers.com:8197/;stream.mp3", getResources().getDrawable(R.drawable.olimpica));
        radios.add(radio3);

        Emisora radio4=new Emisora("Olimpica","Monteria","http://i60.letio.com/9112.aac", getResources().getDrawable(R.drawable.olimpica));
        radios.add(radio4);

        Emisora radio7=new Emisora("Olimpica","Sincelejo","http://i60.letio.com/9116.aac", getResources().getDrawable(R.drawable.olimpica));
        radios.add(radio7);

        Emisora radio5=new Emisora("La Reina","Barranquilla","http://i60.letio.com/9106.aac", getResources().getDrawable(R.drawable.lareina));
        radios.add(radio5);

        Emisora radio6=new Emisora("La Reina","Cartagena","http://i50.letio.com/9152.aac", getResources().getDrawable(R.drawable.lareina));
        radios.add(radio6);

        mAdapter.notifyDataSetChanged();
    }


/*    @Override
    protected void onPause() {
        super.onPause();

        if (mediaPlayer != null) {
            mediaPlayer.reset();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }*/

    class Player extends AsyncTask<String, Void, Boolean>   {

        @Override
        protected Boolean doInBackground(String... strings) {
            Boolean prepared = false;

            try {
                mediaPlayer.setDataSource(strings[0]);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        initialStage = true;
                        playPause = false;
                        btnplay.setText("Lanzar Audio");
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                    }
                });

                mediaPlayer.prepare();
                prepared = true;

            } catch (Exception e) {
               // Log.e("MyAudioStreamingApp", e.getMessage());
                prepared = false;
            }

            return prepared;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);

            if (progressDialog.isShowing()) {
                progressDialog.cancel();
            }



            mediaPlayer.start();


            initialStage = false;
        }

        @Override
        protected void onPreExecute(){
            super.onPreExecute();

            progressDialog.setMessage("Buffering...");
            progressDialog.show();
        }
    }
}



