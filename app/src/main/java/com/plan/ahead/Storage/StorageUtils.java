package com.plan.ahead.Storage;

import java.io.File;

/**
 * Created by mihai on 27.02.2018.
 *  Utility methods for working with files on /data partition
 */

public class StorageUtils {

    // checks if the storage dir exists and if not it creates it
    // call this when the app is opened
    public static void createAppStorageDir()
    {
        File storageDir = new File("/data/data/com.plan.ahead/files/PlanAheadStorage/");
        if(!storageDir.exists())
            storageDir.mkdirs();
    }

    // returns the path for the writable storage dir of the application
    public static String getAppStoragePath()
    {
        return "/data/data/com.plan.ahead/files/PlanAheadStorage/";
    }
}
