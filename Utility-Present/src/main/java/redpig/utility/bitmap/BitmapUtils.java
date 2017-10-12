package redpig.utility.bitmap;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by admin170921 on 2017-09-26.
 */

public class BitmapUtils {

    /**
     * 비트맵 객체를 특정 폴더에 특정 파일로 변환하여 저장한다.
     * @param bitmap
     * @param folderPath
     * @param fileName
     * @param compressFormat
     * @return
     */
    public static String saveBitmapToFile(Bitmap bitmap, String folderPath, String fileName, Bitmap.CompressFormat compressFormat){
        File folderFile = new File(folderPath);
        File saveFile = new File(folderFile, fileName);

        if(!folderFile.exists()){
            folderFile.mkdir();
        }

        try{
            saveFile.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(saveFile);
            bitmap.compress(compressFormat, 100, fileOutputStream);
            fileOutputStream.close();
        }catch(Exception e){

        }
        return  saveFile.getAbsolutePath();
    }

    /**
     * Asset 리소스 폴더의 이미지를 비트맵으로 변환하여 반환한다.
     * @param context
     * @return
     */
    public static Bitmap getAssetToBitmap(Context context){
        AssetManager am = context.getAssets();
        InputStream inputStream = null;
        Bitmap bitmap = null;

        try {
            inputStream = am.open("1.jpg");
            bitmap = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(bitmap != null){
            return bitmap;
        }else{
            return null;
        }
    }
}
