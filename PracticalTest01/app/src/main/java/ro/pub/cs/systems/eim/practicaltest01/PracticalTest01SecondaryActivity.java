package ro.pub.cs.systems.eim.practicaltest01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PracticalTest01SecondaryActivity extends AppCompatActivity {
    Button okButton, cancelButton;
    SecondaryListener secondaryListener = new SecondaryListener();
    TextView view;

    private class SecondaryListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button_ok:
                    setResult(Constants.RESULT_OKAY);
                    break;
                case R.id.button_cancel:
                    setResult(Constants.RESULT_CANCEL);
                    break;
            }
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_secondary);
        okButton = (Button)findViewById(R.id.button_ok);
        cancelButton = (Button)findViewById(R.id.button_cancel);

        view = (TextView)findViewById(R.id.textView3);
        okButton.setOnClickListener(secondaryListener);
        cancelButton.setOnClickListener(secondaryListener);

        Intent intent = getIntent();
        if (intent != null && intent.getExtras().containsKey(Constants.NO_CLICKS)) {
            view.setText(String.valueOf(intent.getExtras().get(Constants.NO_CLICKS)));
        }
    }
}
