package com.tuccro.notes.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by tuccro on 2/7/16.
 */
public class FileUtils {

    private static final String TAG = "FileUtils";

    private static final String IMAGE_PATH = "images";


    public static void moveFile(String inputPath, String inputFile, String outputPath) {

        InputStream in = null;
        OutputStream out = null;
        try {

            //create output directory if it doesn't exist
            File dir = new File(outputPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            in = new FileInputStream(inputPath + inputFile);
            out = new FileOutputStream(outputPath + inputFile);

            byte[] buffer = new byte[1024];
            int read;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
            in.close();
            in = null;

            // write the output file
            out.flush();
            out.close();
            out = null;

            // delete the original file
            deleteFile(inputPath, inputFile);

        } catch (FileNotFoundException e) {
            Log.e(TAG, e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    public static void deleteFile(String inputPath, String inputFile) {
        try {
            // delete the original file
            new File(inputPath + inputFile).delete();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    public static void copyFile(String inputPath, String inputFile, String outputPath, String outputFile) {

        InputStream in;
        OutputStream out;
        try {

            //create output directory if it doesn't exist
            File dir = new File(outputPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            in = new FileInputStream(inputPath + inputFile);
            out = new FileOutputStream(outputPath + outputFile);

            byte[] buffer = new byte[1024];
            int read;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
            in.close();
            in = null;

            // write the output file
            out.flush();
            out.close();
            out = null;

        } catch (FileNotFoundException e) {
            Log.e(TAG, e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    @Nullable
    public static String addNewImageToAppDir(Context context, Bitmap bitmap) {

        File appFiles = context.getExternalFilesDir(null);

        if (appFiles == null) {
            appFiles = context.getFilesDir();
        }

        if (appFiles == null) return null;

        File destination = new File(appFiles, IMAGE_PATH);
        if (!destination.exists()) destination.mkdirs();

        return writeBitmapToDestination(destination, bitmap);
    }

    /**
     * Write bitmap to JPG file with unique name (current time in millis)
     *
     * @param destination dir for image
     * @param bitmap      image bitmap
     * @return path of created image file
     */
    @Nullable
    public static String writeBitmapToDestination(File destination, Bitmap bitmap) {

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bytes);

        File photoFile = new File(destination, System.currentTimeMillis() + ".jpg");

        FileOutputStream fo;
        try {
            photoFile.createNewFile();
            fo = new FileOutputStream(photoFile);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            Log.e(TAG, "Can not write photo!", e);
        } catch (IOException e) {
            Log.e(TAG, "Can not write photo!", e);
        }

        if (photoFile.exists()) return photoFile.getAbsolutePath();
        else return null;
    }
}
