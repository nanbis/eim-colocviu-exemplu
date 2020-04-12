package ro.pub.cs.systems.eim.practicaltest01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PracticalTest01MainActivity extends AppCompatActivity {
    Button firstButton, secondButton, changeButton;
    TextView firstView, secondView;
    PressButtonListener pressButtonListener;
    int counter_left = 0, counter_right = 0;
    protected boolean serviceStatus = false;
    private IntentFilter intentFilter = new IntentFilter();

    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(Constants.BROADCAST_TAG, intent.getStringExtra(Constants.BROADCAST_CODE));
        }
    }

    private class PressButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button2:
                    counter_left++;
                    firstView.setText(String.valueOf(counter_left));
                    break;
                case R.id.button3:
                    counter_right++;
                    secondView.setText(String.valueOf(counter_right));
                    break;
                case R.id.button:
                    Intent intent = new Intent(getApplicationContext(), PracticalTest01SecondaryActivity.class);
                    intent.putExtra(Constants.NO_CLICKS, counter_left + counter_right);
                    startActivityForResult(intent, Constants.SECONDARY_CODE);
                    break;
            }
            if (counter_left + counter_right > Constants.THRESHOLD && serviceStatus == false) {
                Intent intent = new Intent(getApplicationContext(), PracticalTest01Service.class);
                intent.putExtra(Constants.LEFT_COUNTER, counter_left);
                intent.putExtra(Constants.RIGHT_COUNTER, counter_right);
                getApplicationContext().startService(intent);
                serviceStatus = true;
            }
        }
    }
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        switch (requestCode) {
            case Constants.SECONDARY_CODE:
                Toast.makeText(this, "The activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, PracticalTest01Service.class);
        stopService(intent);
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_practical_test01_main);

        firstButton = (Button)findViewById(R.id.button2);
        secondButton = (Button)findViewById(R.id.button3);

        firstView = (TextView)findViewById(R.id.textView);
        secondView = (TextView)findViewById(R.id.textView2);

        pressButtonListener = new PressButtonListener();

        firstButton.setOnClickListener(pressButtonListener);
        secondButton.setOnClickListener(pressButtonListener);

        changeButton = (Button)findViewById(R.id.button);
        changeButton.setOnClickListener(pressButtonListener);

        if (state != null) {
            if (state.containsKey(Constants.LEFT_COUNTER)) {
                counter_left = state.getInt(Constants.LEFT_COUNTER);
            }

            if (state.containsKey(Constants.RIGHT_COUNTER)) {
                counter_right = state.getInt(Constants.RIGHT_COUNTER);
            }
        }

        for (int i = 0; i < Constants.actions.length; i ++)
            intentFilter.addAction(Constants.actions[i]);

        firstView.setText(String.valueOf(counter_left));
        secondView.setText(String.valueOf(counter_right));
    }

    @Override
    protected void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        state.putInt(Constants.LEFT_COUNTER, counter_left);
        state.putInt(Constants.RIGHT_COUNTER, counter_right);
    }

    @Override
    protected void onRestoreInstanceState(Bundle state) {
        if (state.containsKey(Constants.LEFT_COUNTER)) {
            counter_left = state.getInt(Constants.LEFT_COUNTER);
        }

        if (state.containsKey(Constants.RIGHT_COUNTER)) {
            counter_right = state.getInt(Constants.RIGHT_COUNTER);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(messageBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(messageBroadcastReceiver);
        super.onPause();
    }
}
