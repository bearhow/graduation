package com.example.zhang.filepersistencetest;

import android.app.Activity;
import java.util.ArrayList;
import java.util.List;

public class ActivityCollector {
        public static List<Activity> activities =new ArrayList<>();
        public static void addActivity(Activity activity){
            activities.add(activity);
        }
        public static void removerActivity(Activity activity)
        {
            activities.remove(activity);
        }
        public static void finishaAll(){
            for(Activity activity:activities){
                if(!activity.isFinishing()){
                    activity.finish();
                }
            }
            activities.clear();
        }

    public static void removeActivity(BaseActivity baseActivity) {
    }

    public static void finishAll() {
    }
}


