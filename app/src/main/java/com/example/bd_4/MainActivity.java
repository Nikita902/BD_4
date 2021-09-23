package com.example.bd_4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {
    Button result;
    EditText SecondName;
    EditText Name;
    String fname = "Base_Lab.txt";
    Button Rdbtn;
    TextView ReadFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = (Button) findViewById(R.id.result);
        ReadFile = (TextView) findViewById(R.id.ReadFile);
        Rdbtn = (Button) findViewById(R.id.Rdbtn);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Создание файла" + fname)
                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        Log.d("Log_02", "Создание файла" + fname);
                        dialogInterface.cancel();
                    }
                });
        AlertDialog ad = builder.create();
        ad.show();

        File f = new File(MainActivity.super.getFilesDir(), fname);
        try {
            f.createNewFile();
            Log.d("Log_02", "Файл" + fname + "создан");
        } catch (IOException e) {
            Log.d("Log_02", "Файл" + fname + " не создан");
        }

        View.OnClickListener btn = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FileOutputStream fos = null;
                try {
                    SecondName = (EditText) findViewById(R.id.SecondName);
                    String text = SecondName.getText().toString() + ";" + "\r\n";
                    Name = (EditText) findViewById(R.id.Name);
                    String text_2 = Name.getText().toString() + ";" + "\r\n";

                    fos = openFileOutput(fname, MODE_PRIVATE);
                    fos.write(text.getBytes());
                    fos.write(text_2.getBytes());
                    Log.d("Log_02", "Данные записаны");
                } catch (IOException ex) {

                    Log.d("Log_02", "Данные не записаны");
                }
            }
        };
        result.setOnClickListener(btn);
    }

    public void OpenText (View view){
        FileInputStream fin = null;
        try {
            fin = openFileInput(fname);
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            String text = new String (bytes);
            ReadFile.setText(text);
        }
        catch(IOException ex) {
            Log.d("Log_02", "Файл" + fname + " невозможно прочитать");
        }
    }
}
