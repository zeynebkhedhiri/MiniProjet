package com.example.miniprojet;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class StressCheckActivity extends AppCompatActivity {

    private EditText stressLevelInput;
    private Button analyzeButton;
    private TextView adviceTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stress_check);

        stressLevelInput = findViewById(R.id.stressLevelInput);
        analyzeButton = findViewById(R.id.analyzeButton);
        adviceTextView = findViewById(R.id.adviceTextView);

        analyzeButton.setOnClickListener(v -> {
            String input = stressLevelInput.getText().toString().trim();
            if (input.isEmpty()) {
                Toast.makeText(this, "Please enter a stress level (1-10)", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                int level = Integer.parseInt(input);
                if (level < 1 || level > 10) {
                    Toast.makeText(this, "Stress level must be between 1 and 10", Toast.LENGTH_SHORT).show();
                } else {
                    String advice = getAdviceForLevel(level);
                    adviceTextView.setText(advice);
                }
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Invalid number", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getAdviceForLevel(int level) {
        if (level <= 3) {
            return "You seem relaxed. Keep maintaining a balanced routine!";
        } else if (level <= 6) {
            return "You're a bit stressed. Try some deep breathing or a short walk.";
        } else if (level <= 8) {
            return "High stress. Consider taking a break, meditating, or talking to someone.";
        } else {
            return "Very high stress! Please rest, and consider speaking to a mental health professional.";
        }
    }
}
