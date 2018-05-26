package com.me.bui.hydrationreminder.sync;


import android.content.Context;
import android.support.annotation.NonNull;

import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.Driver;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.Trigger;

import java.util.concurrent.TimeUnit;

/**
 * Created by mao.bui on 5/24/2018.
 */
public class ReminderUtilities {
    private static final int REMINDER_INTERVAL_MINUTES = 1;
    private static final int REMINDER_INTERVAL_SECONDS = (int) TimeUnit.MINUTES.toSeconds(REMINDER_INTERVAL_MINUTES);
    private static final int SYNC_FLEXTIME_SECONDS = REMINDER_INTERVAL_SECONDS;

    private static final String REMIDER_JOB_TAG = "hydration_reminder_tag";

    private static boolean sInitialized;

    synchronized public static void scheduleChargingRemider(@NonNull final Context context) {
        if (sInitialized) return;
        Driver driver = new GooglePlayDriver(context);
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(driver);
        Job job = dispatcher.newJobBuilder()
                // the JobService that will be called
                .setService(WaterReminderFirebaseJobService.class)
                // uniquely identifies the job
                .setTag(REMIDER_JOB_TAG)
                // constraints that need to be satisfied for the job to run
                .setConstraints(Constraint.DEVICE_CHARGING)
                //
                .setLifetime(Lifetime.FOREVER)
                // one-off job
                .setRecurring(true)
                // start between REMINDER_INTERVAL_SECONDS and REMINDER_INTERVAL_SECONDS + SYNC_FLEXTIME_SECONDS seconds
                .setTrigger(Trigger.executionWindow(REMINDER_INTERVAL_SECONDS, REMINDER_INTERVAL_SECONDS + SYNC_FLEXTIME_SECONDS))
                // overwrite an existing job with the same tag
                .setReplaceCurrent(true)
                .build();
        dispatcher.schedule(job);
        sInitialized = true;
    }
}
