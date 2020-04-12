package ro.pub.cs.systems.eim.practicaltest01;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class PracticalTest01Service extends Service {
    private ProcessingThread thread = null;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int left_counter = intent.getIntExtra(Constants.LEFT_COUNTER, -1);
        int right_counter = intent.getIntExtra(Constants.RIGHT_COUNTER, -1);
        thread = new ProcessingThread(this, left_counter, right_counter);
        thread.start();
        return Service.START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        thread.interrupt();
        thread = null;
    }
}
