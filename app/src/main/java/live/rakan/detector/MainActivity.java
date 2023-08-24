package live.rakan.detector;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import live.rakan.detector.security.JavaDetector;


public class MainActivity extends AppCompatActivity {
    public native void checkFrida();
    static {
        System.loadLibrary("ndktest");
    }


    JavaDetector detector;
    TextView textView1;
    Button checkBTN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        detector = new JavaDetector();
        textView1 = findViewById(R.id.TextView1);
        checkBTN = findViewById(R.id.CheckBTN);

        checkBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Boolean isSuExists = detector.checkSuExists();
                 if (isSuExists){
                     textView1.setText("Root Detected");
                 }else {
                     textView1.setText("Root not Detected");
                 }
//                 readSystemFile("/proc/self/maps");
                checkFrida();
            }
        });

    }

    private void readSystemFile(String path){
        String content="";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;
            while ((line = reader.readLine()) != null) {
                content += line+'\n';
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(content.contains("frida-agent"))
            throw new RuntimeException("");
    }
}