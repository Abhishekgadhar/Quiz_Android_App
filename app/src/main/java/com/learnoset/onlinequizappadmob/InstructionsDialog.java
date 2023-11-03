package com.learnoset.onlinequizappadmob;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;

public class InstructionsDialog extends Dialog {

    private int instructionsCount = 0;

    public InstructionsDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instrutions_dialog_layout);

        // getting widgets from instrutions_dialog_layout.xml
        final AppCompatButton continueBtn = findViewById(R.id.continueBtn);
        final TextView instructionsTV = findViewById(R.id.instructionsTV);

        // Setting instructions
        setInstruction(instructionsTV, "1. You will get maximum 2 minutes to complete the Entire quiz.");
        setInstruction(instructionsTV, "2. You will 1 point on every correct answer.");
        setInstruction(instructionsTV, "3. This Quiz will we consisting of 10 Questions of 1 marks each.");
        setInstruction(instructionsTV, "4. Do read All the questions completely ");

        // TODO add your own instructions
        // Ex. setInstruction(instructionsTV, "6. Your instruction.");

        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void setInstruction(TextView instructionsTV, String instructionTxt) {

        if (instructionsCount == 0) {
            instructionsTV.setText(instructionTxt);
        } else {
            instructionsTV.setText(instructionsTV.getText() + "\n\n" + instructionTxt);
        }

        instructionsCount++;
    }
}
