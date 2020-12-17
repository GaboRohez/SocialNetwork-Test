package xyz.gaborohez.socialnetwork.ui.utils;

import android.content.Context;
import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import id.zelory.compressor.Compressor;

/**
 * Comprime imagenes y transforma a bitmap
 */
public class CompressorBitmapImage {

    public static byte[] getImage(Context ctx, String path, int width, int height) {
        final File file_thumb_path = new File(path);
        Bitmap thumb_bitmap = null;

        try {
            thumb_bitmap = new Compressor(ctx)
                    .setMaxWidth(width)
                    .setMaxHeight(height)
                    .setQuality(50)
                    .compressToBitmap(file_thumb_path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        thumb_bitmap.compress(Bitmap.CompressFormat.JPEG,80,baos);
        byte[] thumb_byte = baos.toByteArray();
        return  thumb_byte;
    }
}
