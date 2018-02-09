package com.example.android.restful.utils;

/**
 * Created by TheDancerCodes on 09/02/2018.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.android.restful.model.DataItem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageCacheManager {

    public static Bitmap getBitmap(Context context, DataItem dataItem) {

        // Check whether file is in persistent storage
        String fileName = context.getCacheDir() + "/" + dataItem.getImage();
        File file = new File(fileName);
        if (file.exists()) {
            try {
                return BitmapFactory.decodeStream(new FileInputStream(file));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;

    }

    // Save file into persistent storage
    public static void putBitmap(Context context, DataItem dataItem, Bitmap bitmap) {
        String fileName = context.getCacheDir() + "/" + dataItem.getImage();
        File file = new File(fileName);
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);

            // Reduce size of file as much as possible
            bitmap.compress(Bitmap.CompressFormat.JPEG, 85, outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
