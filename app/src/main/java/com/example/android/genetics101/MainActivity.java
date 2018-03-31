// Modified from Udacity's Just Java app

        package com.example.android.genetics101;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form for taking genetics test.
 */
public class MainActivity extends AppCompatActivity {

    int score = 0;

    int correct_questions = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the answer button is clicked.
     */
    public void submitAnswer(View view) {
        // Get user's name
        EditText nameField = (EditText) findViewById(R.id.name_field);
        Editable nameEditable = nameField.getText();
        String name = nameEditable.toString();

        // Checkboxes
        RadioButton question1RadioButton = (RadioButton) findViewById(R.id.question1_radioButton);
        boolean question1Correct = question1RadioButton.isChecked();

        RadioButton question2RadioButton = (RadioButton) findViewById(R.id.question2_radioButton);
        boolean question2Correct = question2RadioButton.isChecked();

        RadioButton question3RadioButton = (RadioButton) findViewById(R.id.question3_radioButton);
        boolean question3Correct = question3RadioButton.isChecked();

        RadioButton question4RadioButton = (RadioButton) findViewById(R.id.question4_radioButton);
        boolean question4Correct = question4RadioButton.isChecked();

        RadioButton question5RadioButton = (RadioButton) findViewById(R.id.question5_radioButton);
        boolean question5Correct = question5RadioButton.isChecked();

        CheckBox question6CheckBox1 = (CheckBox) findViewById(R.id.question6_checkbox1);
        CheckBox question6CheckBox2 = (CheckBox) findViewById(R.id.question6_checkbox2);
        CheckBox question6CheckBox3 = (CheckBox) findViewById(R.id.question6_checkbox3);
        CheckBox question6CheckBox4 = (CheckBox) findViewById(R.id.question6_checkbox4);
        boolean question6Correct1 = question6CheckBox1.isChecked();
        boolean question6Correct2 = question6CheckBox2.isChecked();
        boolean question6Correct3 = question6CheckBox3.isChecked();
        boolean question6Correct4 = question6CheckBox4.isChecked();

        // Calculate the points
        int point = calculatePrice(question1Correct, question2Correct, question3Correct, question4Correct, question5Correct, question6Correct1,
                question6Correct2,question6Correct3,question6Correct4);

        // Display the summary on the screen
        String message = createTestSummary(name, point, question1Correct, question2Correct, question3Correct, question4Correct, question5Correct,
                question6Correct1,question6Correct2,question6Correct3,question6Correct4);

       displayMessage(message);

       // Essential to make sure points don't go up just for clicking the questions.
       score = 0;
       correct_questions = 0;
        }

    /**
     * Calculates the points.
     *
     * @param addQuestion1    is whether or not the first question is answered correct
     * @param addQuestion2    is whether or not the second question is answered correct
     * @param addQuestion3    is whether or not the third question is answered correct
     * @param addQuestion4    is whether or not the fourth question is answered correct
     * @param addQuestion5    is whether or not the fifth question is answered correct
     * @param addQuestion6_1, addQuestion6_2, addQuestion6_3, addQuestion6_4    is whether or not the fifth question is answered correct
     * @return total point [score is the initial; point is what's finally returned]
     */
    private int calculatePrice(boolean addQuestion1, boolean addQuestion2, boolean addQuestion3, boolean addQuestion4, boolean addQuestion5,
                               boolean addQuestion6_1, boolean addQuestion6_2, boolean addQuestion6_3, boolean addQuestion6_4) {

        // Increments by one point
        if (addQuestion1) {
            correct_questions = correct_questions + 1;
        }

        if (addQuestion2) {
            correct_questions = correct_questions + 1;
        }

        if (!addQuestion3) {
            correct_questions = correct_questions + 1;
        }

        if (addQuestion4) {
            correct_questions = correct_questions + 1;
        }

        if (!addQuestion5) {
            correct_questions = correct_questions + 1;
        }

        if (addQuestion6_1 && addQuestion6_2 && !addQuestion6_3 && addQuestion6_4) {
            correct_questions = correct_questions + 1;
        }


        // Calculate the total points; each question is worth 2 points
        return score + correct_questions*2;
    }

    /**
     * Create summary of the test.
     *
     * @param name            on the test
     * @param addQuestion1    is whether or not to add chocolate to the coffee
     * @return text summary
     */
    private String createTestSummary(String name, int point,
                                      boolean addQuestion1, boolean addQuestion2, boolean addQuestion3, boolean addQuestion4, boolean addQuestion5,
                                     boolean addQuestion6_1, boolean addQuestion6_2, boolean addQuestion6_3, boolean addQuestion6_4) {
        String testMessage = getString(R.string.test_summary_name, name);
        testMessage += "\n" + getString(R.string.test_summary_question1, addQuestion1);
        testMessage += "\n" + getString(R.string.test_summary_question2, addQuestion2);
        testMessage += "\n" + getString(R.string.test_summary_question3, !addQuestion3);
        testMessage += "\n" + getString(R.string.test_summary_question4, addQuestion4);
        testMessage += "\n" + getString(R.string.test_summary_question5, !addQuestion5);

        boolean addQuestion6;

        if (addQuestion6_1 && addQuestion6_2 && !addQuestion6_3 && addQuestion6_4) {
            addQuestion6 = true;
        }

        else {
            addQuestion6 = false;
        }

        testMessage += "\n" + getString(R.string.test_summary_question6, addQuestion6);
        testMessage += "\n" + getString(R.string.test_summary_correct, correct_questions);
        testMessage += "\n" + getString(R.string.test_summary_score, point);
        testMessage += "\n" + getString(R.string.thank_you);
        return testMessage;
    }


    private void displayMessage(String message) {
        TextView testSummaryTextView = (TextView) findViewById(R.id.test_summary_text_view);
        testSummaryTextView.setText(message);
    }

    // used when reset button is clicked
    public void resetQuiz(View view) {

        score = 0;
        correct_questions = 0;


        EditText nameField = (EditText) findViewById(R.id.name_field);
        nameField.setText(null);
        RadioButton question1RadioButton = (RadioButton) findViewById(R.id.question1_radioButton);
        RadioButton question2RadioButton = (RadioButton) findViewById(R.id.question2_radioButton);
        RadioButton question3RadioButton = (RadioButton) findViewById(R.id.question3_radioButton);
        RadioButton question4RadioButton = (RadioButton) findViewById(R.id.question4_radioButton);
        RadioButton question5RadioButton = (RadioButton) findViewById(R.id.question5_radioButton);

        CheckBox question6CheckBox1 = (CheckBox) findViewById(R.id.question6_checkbox1);
        CheckBox question6CheckBox2 = (CheckBox) findViewById(R.id.question6_checkbox2);
        CheckBox question6CheckBox3 = (CheckBox) findViewById(R.id.question6_checkbox3);
        CheckBox question6CheckBox4 = (CheckBox) findViewById(R.id.question6_checkbox4);

        if (question1RadioButton.isChecked()) {
            question1RadioButton.setChecked(false);
        }
        if (question2RadioButton.isChecked()) {
            question2RadioButton.setChecked(false);
        }
        if (question3RadioButton.isChecked()) {
            question3RadioButton.setChecked(false);
        }
        if (question4RadioButton.isChecked()) {
            question4RadioButton.setChecked(false);
        }
        if (question5RadioButton.isChecked()) {
            question5RadioButton.setChecked(false);
        }

        if (question6CheckBox1.isChecked()) {
            question6CheckBox1.setChecked(false);
        }

        if (question6CheckBox2.isChecked()) {
            question6CheckBox2.setChecked(false);
        }

        if (question6CheckBox3.isChecked()) {
            question6CheckBox3.setChecked(false);
        }

        if (question6CheckBox4.isChecked()) {
            question6CheckBox4.setChecked(false);
        }

            String message = "Your answers are reset. Try again. All checkboxes are unchecked now";
        displayMessage(message);
    }
}
