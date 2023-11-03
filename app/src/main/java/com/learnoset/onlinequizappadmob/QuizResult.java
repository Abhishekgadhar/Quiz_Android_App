package com.learnoset.onlinequizappadmob;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.util.ArrayList;
import java.util.List;

public class QuizResult extends AppCompatActivity {

    private List<QuestionsList> questionsLists = new ArrayList<>();

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_result);

        // getting widgets from activity_quiz_result.xml file
        final TextView scoreTV = findViewById(R.id.scoreTV);
        final TextView totalScoreTV = findViewById(R.id.totalScoreTV);
        final TextView correctTV = findViewById(R.id.correctTV);
        final TextView incorrectTV = findViewById(R.id.inCorrectTV);
        final AppCompatButton shareBtn = findViewById(R.id.shareBtn);
        final AppCompatButton reTakeBtn = findViewById(R.id.reTakeQuizBtn);

        // getting questions list from MainActivity
        questionsLists = (List<QuestionsList>) getIntent().getSerializableExtra("quetions");

        // setting data to TextViews

        totalScoreTV.setText("/"+questionsLists.size());
        scoreTV.setText(getCorrectAnswers() +"");
        correctTV.setText(getCorrectAnswers() + "");
        incorrectTV.setText(String.valueOf(questionsLists.size() - getCorrectAnswers()));

        // show interstitial ad
        final AdRequest adRequest = new AdRequest.Builder().build(); //  making ad request

        // load requested ad and show. Replace Test Ad Unit Id with your orgional
        InterstitialAd.load(this,getString(R.string.interstitial_ad), adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {

                        // show ad after successfully loaded
                        interstitialAd.show(QuizResult.this);
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        Toast.makeText(QuizResult.this, "Failed to load ad", Toast.LENGTH_SHORT).show();
                    }
                });

        shareBtn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                // open share intent

                if(getCorrectAnswers()<=4){
                    Toast.makeText(getApplicationContext(), "You do not have the eligibility to get the certificate ,Retake the Quiz again", Toast.LENGTH_SHORT).show();


                }
                else{
                    Intent intent = new Intent(QuizResult.this, certificate.class);
                    startActivity(intent);

                }


            }
        });

        reTakeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // re start quiz go to MainActivity
                startActivity(new Intent(QuizResult.this, MainActivity.class));
                finish();
            }
        });
    }

    private int getCorrectAnswers(){

        int correctAnswer = 0;

        for(int i = 0; i < questionsLists.size(); i++){

            int getUserSelectedOption = questionsLists.get(i).getUserSelectedAnswer(); // get user selected option
            int getQuestionAnswer = questionsLists.get(i).getAnswer(); //  get correct answer for the question

            // check of user selected answer matches with correct answer
            if(getQuestionAnswer == getUserSelectedOption){
                correctAnswer++; //  increase correct answers
            }
        }

        return correctAnswer;
    }


}