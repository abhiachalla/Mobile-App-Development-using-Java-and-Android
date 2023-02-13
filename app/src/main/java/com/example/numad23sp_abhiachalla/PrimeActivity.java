package com.example.numad23sp_abhiachalla;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class PrimeActivity extends AppCompatActivity {
    WorkerClass wc;
    TextView displayCurr;
    TextView displayLatest;
    public class WorkerClass extends Thread{

        boolean threadStopped = false;
        int currNum = 3;
        int lastPrime = -1;


        @Override
        public void run() {
            while(!threadStopped) {
                if(isPrime(currNum)) {
                    lastPrime = currNum;
                    this.setLatestPrime(lastPrime);
                }
                this.setCurrNum(currNum);
                currNum += 2;

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        public void stopThread() {
            //set boolean var false;
            threadStopped = true;
            currNum = 3;
        }

        public int getCurrNum() {
            return this.currNum;
        }

        public int getLastPrime() {
            return this.lastPrime;
        }

        public boolean isPrime(int n) {
            for (int i = 3; i <= Math.sqrt(n); i += 2) {
                if (n % i == 0)
                    return false;
            }
            return true;
        }

        public void setCurrNum(int currNum) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    displayCurr.setText(String.valueOf(currNum));
                }
            });
        }

        public void setLatestPrime(int lastPrime) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    displayLatest.setText(String.valueOf(lastPrime));
                }
            });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.primelayout);



        Button findPrimes = findViewById(R.id.find_primes);
        Button terminateSearch = findViewById(R.id.terminate_search);
        displayCurr = findViewById(R.id.display_current);
        displayLatest = findViewById(R.id.display_latest);





        findPrimes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                findPrimes.setEnabled(false);
                terminateSearch.setEnabled(true);
                wc = new WorkerClass();
                wc.start();
            }
        });


        terminateSearch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                findPrimes.setEnabled(true);
                terminateSearch.setEnabled(false);
                wc.stopThread();
            }
        });


    }
}
