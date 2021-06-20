package com.elemengine.utils;

import com.elemengine.conf.Configuration;

import java.io.File;

public class FileUtil
{
    public static File getFile(String resourcePath)
    {
        return new File(Configuration.getResourcesPath() + resourcePath);
    }
}
