package ro.pub.cs.systems.eim.practicaltest01;

import android.content.Context;
import android.content.Intent;

import java.util.Date;
import java.util.Random;

public class ProcessingThread extends Thread {
    private Context context = null;
    private boolean isRunning = true;

    private Random random = new Random();

    private double ar_mean, geo_mean;

    public ProcessingThread(Context context, int left_counter, int right_counter) {
        this.context = context;

        ar_mean = (left_counter + right_counter) / 2;
        geo_mean = Math.sqrt(left_counter * right_counter);
    }

    private void sleep() {
        try {
            sleep(Constants.SLEEP_TIME);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendMessage() {
        Intent intent = new Intent();
        intent.setAction(Constants.actions[random.nextInt(Constants.actions.length)]);
        intent.putExtra(Constants.BROADCAST_CODE, new Date(System.currentTimeMillis()) +
                    " ari mean is: " + ar_mean + ", and geo mean is: " + geo_mean);
        context.sendBroadcast(intent);
    }

    @Override
    public void run() {
        while (isRunning) {
            sendMessage();
            sleep();
        }
    }
}
