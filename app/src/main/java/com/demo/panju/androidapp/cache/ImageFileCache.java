package com.demo.panju.androidapp.cache;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Author : PZY
 * Date : 2016.8.19
 */
public class ImageFileCache {
    /**
     * source : http://blog.csdn.net/singwhatiwanna/article/details/17588159
     */
    private final static String TAG = ImageFileCache.class.getSimpleName();

    private final static String CACHE_DIR = "ImageCache";

    //Cache扩展名
    private final static String CACHE_TAIL = ".cache";

    private final static int MB = 1024 * 1024;

    private final static int CACHE_SIZE = 1;

    //当SD卡剩余空间小于10M的时候会清理缓存
    private static final int FREE_SD_SPACE_NEEDED_TO_CACHE = 10;

    private Context mContext;

    public ImageFileCache(Context context) {
        this.mContext = context;

        removeCache(getCacheDir());
    }



    /**
     * 计算存储目录下的文件大小，
     * 当文件总大小大于规定的CACHE_SIZE或者sdcard剩余空间小于FREE_SD_SPACE_NEEDED_TO_CACHE的规定
     * 那么删除40%最近没有被使用的文件
     * @param dirPath
     * @return
     */
    private boolean removeCache(String dirPath) {
        if (!isExternalStorageWritable()) {
            return false;
        }

        File dir = new File(dirPath);
        File [] files = dir.listFiles();
        if (null == files) {
            return true;
        }

        int dirSize = 0;

        for (int i = 0; i < files.length; i++) {
            if (files[i].getName().contains(CACHE_TAIL)) {
                dirSize += files[i].length();
            }
        }

        if (dirSize > CACHE_SIZE * MB || getSdCardFreeSpace() < FREE_SD_SPACE_NEEDED_TO_CACHE) {
            int removeFactor = (int) (0.4 * files.length);
            Arrays.sort(files, new FileLastModifiedSort());
            for (int i = 0; i < removeFactor; i++) {
                if (files[i].getName().contains(CACHE_TAIL)) {
                    files[i].delete();
                }
            }
        }

        if (getSdCardFreeSpace() <= CACHE_SIZE) {
            return false;
        }

        return true;
    }

    private String getInternalStorageDir() {
        return mContext.getCacheDir().getPath();
    }

    /**
     * 检查外部存储是否可读写
     * @return
     */
    private boolean isExternalStorageWritable() {
        String status = Environment.getExternalStorageState();

        if (Environment.MEDIA_MOUNTED.equals(status)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 检查外部存储是否可读
     * @return
     */
    private boolean isExternalStorageReadable() {
        String status = Environment.getExternalStorageState();

        if (Environment.MEDIA_MOUNTED.equals(status) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(status)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取外部存储路径
     * @return
     */
    private String getExternalStoragePath() {
        return Environment.getExternalStorageDirectory().getPath();
    }

    /**
     * 获取SD卡剩余空间
     * @return
     */
    private int getSdCardFreeSpace() {
        StatFs statFs = new StatFs(getExternalStoragePath());
        double freeSpace;

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2) {
            freeSpace = ((double) statFs.getAvailableBlocks() * (double) statFs.getBlockSize()) / MB;
        } else {
            freeSpace = ((double) statFs.getAvailableBlocksLong() * (double) statFs.getBlockSizeLong()) / MB;
        }

        return (int) freeSpace;
    }

    /**
     * 文件更新时间
     * @param path
     */
    private void updateFileTime(String path) {
        File file = new File(path);

        long time = System.currentTimeMillis();
        file.setLastModified(time);
    }

    /**
     * 获取外部存储缓存目录
     * @return
     */
    private String getCacheDir() {
        return getExternalStoragePath() + "/" + CACHE_DIR;
    }

    private class FileLastModifiedSort implements Comparator<File> {

        @Override
        public int compare(File file0, File file1) {
            if (file0.lastModified() > file1.lastModified()) {
                return 1;
            } else if (file0.lastModified() < file1.lastModified()) {
                return -1;
            } else {
                return 0;
            }
        }
    }

}
