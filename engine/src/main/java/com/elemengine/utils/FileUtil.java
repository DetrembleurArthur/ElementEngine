package com.elemengine.utils;

import com.elemengine.conf.Configuration;

import java.io.File;

public class FileUtil
{
    public static File getEngineFile(String resourcePath)
    {
        return new File(Configuration.getEngineAssetsPath() + resourcePath);
    }

    public static File getFile(String resourcePath)
    {
        return new File(Configuration.getAssetsPath() + resourcePath);
    }
}
