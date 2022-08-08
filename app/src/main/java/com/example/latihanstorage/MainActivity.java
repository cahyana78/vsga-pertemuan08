package com.example.latihanstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String FILENAME = "namafile.txt";
    Button btnBuat, btnBaca, btnUbah, btnHapus;
    TextView tvBaca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBaca = findViewById(R.id.btnBaca);
        btnBuat = findViewById(R.id.btnBuat);
        btnUbah = findViewById(R.id.btnUbah);
        btnHapus = findViewById(R.id.btnHapus);

        tvBaca = findViewById(R.id.tv_baca);

        btnBaca.setOnClickListener(this);
        btnBuat.setOnClickListener(this);
        btnUbah.setOnClickListener(this);
        btnHapus.setOnClickListener(this);
    }

    void buatFile(){
        String isiFile = "Coba buat isi file";
        File file = new File(getFilesDir(), FILENAME);
        FileOutputStream outputStream = null;

        try{
            file.createNewFile();
            outputStream = new FileOutputStream(file, true);
            outputStream.write(isiFile.getBytes());
            outputStream.flush();
            outputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    void bacaFile(){
        File file = new File(getFilesDir(), FILENAME);

        if(file.exists()){
            StringBuilder text = new StringBuilder();

            try{
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line = br.readLine();
                while(line!=null){
                    text.append(line);
                    line = br.readLine();
                }
                br.close();
            }catch (IOException e){
                System.out.println("Error " + e.getMessage());
            }
            tvBaca.setText(text.toString());
        }

    }

    @Override
    public void onClick(View view) {
        jalankanPerintah(view.getId());

    }

    public void jalankanPerintah(int id){
        switch (id){
            case R.id.btnBuat:
                buatFile();
                break;
            case R.id.btnBaca:
                bacaFile();
                break;
        }
    }
}