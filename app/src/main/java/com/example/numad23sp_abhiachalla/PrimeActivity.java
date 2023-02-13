package com.example.numad23sp_abhiachalla;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class PrimeActivity extends AppCompatActivity {
    WorkerClass wc;
    TextView displayCurr;
    TextView displayLatest;
    CheckBox pacify_search;
    boolean isStopped = false;


    public class WorkerClass extends Thread{

        boolean threadStopped;
        int currNum;
        int lastPrime;

        public WorkerClass() {
            threadStopped = false;
            currNum = 3;
            lastPrime = -1;
        }


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
    public void onBackPressed() {
        if (!isStopped) {
            new AlertDialog.Builder(this)
                    .setTitle("Exiting")
                    .setMessage("Are you sure you want to terminate the current search?")
                    .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();
        } else {
            super.onBackPressed();
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

        pacify_search = findViewById(R.id.pacify_search);





        findPrimes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                if(!isStopped) {

                    findPrimes.setEnabled(false);
                    terminateSearch.setEnabled(true);
                    wc = new WorkerClass();
                    wc.start();
//                }
            }
        });


        terminateSearch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                findPrimes.setEnabled(true);
                terminateSearch.setEnabled(false);
                wc.stopThread();
            }
        });

        //To preserve the value of pacify search on screen rotation.
        if(savedInstanceState != null) {
            pacify_search.setChecked(savedInstanceState.getBoolean("pacify_search"));
            displayCurr.setText(savedInstanceState.getString("currNum"));
            displayLatest.setText(savedInstanceState.getString("lastPrime"));
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        //When screen is rotated we are preserving the pacify search value.
        outState.putBoolean("pacify_search", pacify_search.isChecked());
        outState.putString("currNum", displayCurr.getText().toString());
        outState.putString("lastPrime", displayLatest.getText().toString());
        super.onSaveInstanceState(outState);
    }
}
