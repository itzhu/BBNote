package me.itzhu.common.util;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Administrator on 2016/10/28.
 */
public class AssetsUtil {

    /**
     * 将assets文件copy到sdcard
     *
     * @param context
     * @param assetsFileName
     * @param sdFileAbsPath  sd文件绝对路径，需要自己检测文件可用性
     * @throws IOException
     */
    public static void copyAssetsToSD(Context context, String assetsFileName, String sdFileAbsPath) throws IOException {
        AssetManager assetManager = context.getAssets();
        InputStream in = assetManager.open(assetsFileName);
        OutputStream out = new FileOutputStream(sdFileAbsPath);
        byte[] buffer = new byte[1024];
        int length = in.read(buffer);
        while (length > 0) {
            out.write(buffer, 0, length);
            length = in.read(buffer);
        }
        out.flush();
        in.close();
        out.close();
    }

}
