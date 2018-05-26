package com.me.bui.hydrationreminder.sync;


import android.content.Context;
import android.os.AsyncTask;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

/**
 * Created by mao.bui on 5/23/2018.
 */
public class WaterReminderFirebaseJobService extends JobService {
    private AsyncTask mBackgroundTask;
    @Override
    public boolean onStartJob(final JobParameters jobParameters) {
        mBackgroundTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                Context context = WaterReminderFirebaseJobService.this;
                ReminderTasks.executeTask(context, ReminderTasks.ACTION_CHARGING_REMINDER);
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                jobFinished(jobParameters, false);
            }
        };
        mBackgroundTask.execute();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        if (mBackgroundTask != null) mBackgroundTask.cancel(true);
        return true;
    }
}
