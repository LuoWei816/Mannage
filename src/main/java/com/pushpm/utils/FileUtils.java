package com.pushpm.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileUtils {
    /**
     * 读取文本文件内容，以行的形式读取
	 *
             * @param String
	 *            filePathAndName 带有完整绝对路径的文件名
	 * @return String 返回文本文件的内容
	 */
    public static String readFileContent(String filePathAndName)
            throws IOException {
        return readFileContent(filePathAndName,null,null,1024);
    }

    /**
     * 读取文本文件内容，以行的形式读取
     *
     * @param String
     *            filePathAndName 带有完整绝对路径的文件名
     * @param String
     *            encoding 文本文件打开的编码方式 例如 GBK,UTF-8
     * @return String 返回文本文件的内容
     */
    public static String readFileContent(String filePathAndName, String encoding)
            throws IOException {
        return readFileContent(filePathAndName,encoding,null,1024);
    }
    /**
     * 读取文本文件内容，以行的形式读取
     *
     * @param String
     *            filePathAndName 带有完整绝对路径的文件名
     * @param int
     *            bufLen 设置缓冲区大小
     * @return String 返回文本文件的内容
     */
    public static String readFileContent(String filePathAndName,int bufLen)
            throws IOException {
        return readFileContent(filePathAndName,null,null,bufLen);
    }
    /**
     * 读取文本文件内容，以行的形式读取
     *
     * @param String
     *            filePathAndName 带有完整绝对路径的文件名
     * @param String
     *            encoding 文本文件打开的编码方式 例如 GBK,UTF-8
     * @param String
     *            sep 分隔符 例如：#，默认为空格
     * @return String 返回文本文件的内容
     */
    public static String readFileContent(String filePathAndName, String encoding, String sep)
            throws IOException {
        return readFileContent(filePathAndName,encoding,sep,1024);
    }

    /**
     * 读取文本文件内容，以行的形式读取
     *
     * @param String
     *            filePathAndName 带有完整绝对路径的文件名
     * @param String
     *            encoding 文本文件打开的编码方式 例如 GBK,UTF-8
     * @param String
     *            sep 分隔符 例如：#，默认为\n;
     * @param int
     *            bufLen 设置缓冲区大小
     * @return String 返回文本文件的内容
     */
    public static String readFileContent(String filePathAndName, String encoding, String sep,int bufLen )
            throws IOException {
        if (filePathAndName == null || filePathAndName.equals("")) {
            return "";
        }
        if(sep==null||sep.equals(""))
        {
            sep="\n";
        }
        if(!new File(filePathAndName).exists())
        {
            return "";
        }
        StringBuffer str = new StringBuffer("");
        FileInputStream fs = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            fs = new FileInputStream(filePathAndName);
            if (encoding == null||encoding.trim().equals("")) {
                isr = new InputStreamReader(fs);
            } else {
                isr = new InputStreamReader(fs, encoding.trim());
            }
            br = new BufferedReader(isr,bufLen);

            String data = "";
            while ((data = br.readLine()) != null) {
                str.append(data).append(sep);
            }

        } catch (IOException e) {
            throw e;
        } finally {
            try
            {
                if (br != null)
                    br.close();
                if (isr != null)
                    isr.close();
                if (fs != null)
                    fs.close();
            }catch(IOException e)
            {
                throw e;
            }

        }
        return str.toString();
    }

    /**
     * 新建一个文件并写入内容
     *
     * @param String
     *            path 文件全路径
     * @param String
     *            fileName 文件名
     * @param StringBuffer
     *            content 内容
     * @return boolean
     * @throws IOException
     */
    public static boolean newFile(String path, String fileName,
                                  StringBuffer content) throws IOException {

        return newFile(path, fileName, content, 1024, false);
    }

    /**
     * 新建一个文件并写入内容
     *
     * @param String
     *            path 文件全路径
     * @param String
     *            fileName 文件名
     * @param StringBuffer
     *            content 内容
     * @return boolean
     * @throws IOException
     */
    public static boolean newFile(String path, String fileName,
                                  StringBuffer content, boolean isWrite) throws IOException {

        return newFile(path, fileName, content, 1024, isWrite);
    }


    /**
     * 新建一个文件并写入内容
     *
     * @param String
     *            path 文件全路径
     * @param String
     *            fileName 文件名
     * @param StringBuffer
     *            content 内容
     * @param int
     *            bufLen 设置缓冲区大小
     * @param boolean
     *            isWrite 是否追加写入文件
     * @return boolean
     * @throws IOException
     */
    public static boolean newFile(String path, String fileName,
                                  StringBuffer content, int bufLen, boolean isWrite)
            throws IOException {
        if (path == null || path.equals("") || content == null
                || content.equals(""))
            return false;
        boolean flag = false;
        FileWriter fw = null;
        BufferedWriter bw = null;

        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();

        }
        try {
            fw = new FileWriter(path +File.separator+ fileName, isWrite);
            bw = new BufferedWriter(fw, bufLen);
            bw.write(content.toString());
            flag = true;
        } catch (IOException e) {
            flag = false;
            throw e;
        } finally {
            if (bw != null) {
                bw.flush();
                bw.close();
            }
            if (fw != null)
                fw.close();
        }

        return flag;
    }

    /**
     * 新建一个文件并写入内容
     *
     * @param String
     *            path 文件全路径
     * @param String
     *            fileName 文件名
     * @param String
     *            content 内容
     * @return boolean
     * @throws IOException
     */
    public static boolean newFile(String path, String fileName, String content)
            throws IOException {

        return newFile(path, fileName, content, 1024, false);
    }

    /**
     * 新建一个文件并写入内容
     *
     * @param String
     *            path 文件全路径
     * @param String
     *            fileName 文件名
     * @param String
     *            content 内容
     * @param boolean
     *            isWrite 是否追加写入文件
     * @return boolean
     * @throws IOException
     */
    public static boolean newFile(String path, String fileName, String content,
                                  boolean isWrite) throws IOException {

        return newFile(path, fileName, content, 1024, isWrite);
    }

    /**
     * 新建一个文件并写入内容
     *
     * @param String
     *            path 文件全路径
     * @param String
     *            fileName 文件名
     * @param String
     *            content 内容
     * @param int
     *            bufLen 设置缓冲区大小
     * @param boolean
     *            isWrite 是否追加写入文件
     * @return boolean
     * @throws IOException
     */
    public static boolean newFile(String path, String fileName, String content,
                                  int bufLen, boolean isWrite) throws IOException {

        if (path == null || path.equals("") || content == null
                || content.equals(""))
            return false;
        boolean flag = false;
        FileWriter fw = null;
        BufferedWriter bw = null;

        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();

        }
        try {

//			/fw = new FileWriter(path +File.separator+ fileName, isWrite);

            //bw = new BufferedWriter(fw, bufLen);
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path +File.separator+ fileName,true),"UTF-8"),bufLen);

            bw.write(content);
            flag = true;
        } catch (IOException e) {
            flag = false;
            throw e;
        } finally {
            if (bw != null) {
                bw.flush();
                bw.close();
            }
            if (fw != null)
                fw.close();
        }

        return flag;
    }

    /**
     * 新建一个目录
     *
     * @param String
     *            filePath 路径
     * @return boolean flag
     * @throws Exception
     */
    public static boolean newFolder(String filePath) throws Exception {
        boolean flag = false;
        if (filePath == null || filePath.equals("") || filePath.equals("null")) {
            return flag;
        }
        try {
            File myFilePath = new File(filePath);
            if (!myFilePath.exists()) {
                myFilePath.mkdirs();
            }
            flag = true;
        } catch (Exception e) {
            throw e;
        }
        return flag;
    }

    /**
     * 复制单个文件
     *
     * @param oldPath
     *            String 原文件路径 如：c:/fqf.txt
     * @param newPath
     *            String 复制后路径 如：f:
     * @return boolean
     */
    public static boolean copyFile(String oldPath, String newPath)
            throws IOException {
        return copyFile(oldPath, newPath, null);
    }

    /**
     * 复制单个文件
     *
     * @param oldPath
     *            String 原文件路径 如：c:/fqf.txt
     * @param newPath
     *            String 复制后路径 如：f:/
     * @return boolean
     */
    public static boolean copyFile(String oldPath, String newPath,
                                   String newFileName) throws IOException {

        boolean flag = false;
        if (oldPath == null || newPath == null || newPath.equals("")
                || oldPath.equals("")) {
            return flag;
        }
        InputStream inStream = null;
        FileOutputStream fs = null;
        try {
            int bytesum = 0;
            int byteread = 0;
            File file = null;
            file = new File(newPath);
            if (!file.exists()) {
                file.mkdirs();
            }
            file = new File(oldPath);
            if (file.exists()) { // 文件存在时
                inStream = new FileInputStream(oldPath); // 读入原文件
                if (newFileName == null || newFileName.equals("")) {
                    newFileName = file.getName();
                }
                fs = new FileOutputStream(newPath + File.separator
                        + newFileName);
                byte[] buffer = new byte[1024 * 8];
                while ((byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread; // 字节数 文件大小
                    //System.out.println(bytesum);
                    fs.write(buffer, 0, byteread);
                }
                flag = true;
            }
        } catch (IOException e) {
            throw e;

        } finally {
            try {
                if (fs != null) {
                    fs.close();
                }
                if (inStream != null) {
                    inStream.close();
                }
            } catch (IOException e) {
                throw e;
            }
        }
        return flag;
    }

    /**
     * 复制整个文件夹内容
     *
     * @param oldPath
     *            String 原文件路径 如：c:/fqf
     * @param newPath
     *            String 复制后路径 如：f:/fqf/ff
     */
    public static void copyFolder(String oldPath, String newPath)
            throws Exception {
        if (oldPath == null || newPath == null || newPath.equals("")
                || oldPath.equals("")) {
            return;
        }
        FileInputStream input = null;
        FileOutputStream output = null;
        try {
            File temp = null;
            temp = new File(newPath);
            if (!temp.exists()) {
                temp.mkdirs();// 如果文件夹不存在 则建立新文件夹
            }
            temp = new File(oldPath);
            String[] file = temp.list();
            for (int i = 0; i < file.length; i++) {
                if (oldPath.endsWith(File.separator)) {
                    temp = new File(oldPath + file[i]);
                } else {
                    temp = new File(oldPath + File.separator + file[i]);
                }

                if (temp.isFile()) {
                    input = new FileInputStream(temp);
                    output = new FileOutputStream(newPath + File.separator
                            + (temp.getName()).toString());
                    byte[] b = new byte[1024 * 8];
                    int len;
                    while ((len = input.read(b)) != -1) {
                        output.write(b, 0, len);
                    }
                    output.flush();

                }
                if (temp.isDirectory()) {// 如果是子文件夹
                    copyFolder(oldPath + File.separator + file[i], newPath
                            + File.separator + file[i]);
                }
            }

        } catch (Exception e) {
            throw e;

        } finally {
            try {
                if (output != null)
                    output.close();
                if (input != null)
                    input.close();
            } catch (Exception e) {
                throw e;
            }
        }

    }

    /**
     * 移动单个文件
     *
     * @param Sring
     *            srcFile 源文件 如：c:/test.txt
     * @param destPath
     *            目标目录 如：c：/test
     * @param newFileName
     *            新文件名 如：newTest.txt
     * @return boolean
     */
    public static boolean Move(String srcFile, String destPath) {
        return Move(srcFile, destPath, null);
    }

    /**
     * 移动单个文件
     *
     * @param Sring
     *            srcFile 源文件 如：c:/test.txt
     * @param destPath
     *            目标目录 如：c：/test
     * @param newFileName
     *            新文件名 如：newTest.txt
     * @return boolean
     */
    public static boolean Move(String srcFile, String destPath,
                               String newFileName) {
        boolean flag = false;
        if (srcFile == null || srcFile.equals("") || destPath == null
                || destPath.equals("")) {
            return flag;
        }
        File file = new File(srcFile);
        File dir = new File(destPath);
        if (newFileName == null || newFileName.equals("")
                || newFileName.equals("null"))
            newFileName = file.getName();
        flag = file.renameTo(new File(dir, newFileName));

        return flag;
    }

    /**
     * 移动文件到指定目录
     *
     * @param String
     *            oldPath 如：c:/fqf.txt
     * @param String
     *            newPath 如：d:/
     */
    public static void moveFile(String oldPath, String newPath)
            throws Exception {
        if (copyFile(oldPath, newPath))
            delFile(oldPath);

    }

    /**
     * 移动文件到指定目录
     *
     * @param String
     *            oldPath 如：c:/fqf.txt
     * @param String
     *            newPath 如：d:/
     * @param String
     *            newFileName 新文件名 如：newTest.txt
     */
    public static void moveFile(String oldPath, String newPath,
                                String newFileName) throws Exception {
        if (copyFile(oldPath, newPath))
            delFile(oldPath);

    }

    /**
     * 移动文件夹到指定目录
     *
     * @param String
     *            oldPath 如：c:/fqf.txt
     * @param String
     *            newPath 如：d:/
     */
    public static void moveFolder(String oldPath, String newPath)
            throws Exception {
        copyFolder(oldPath, newPath);
        delFolder(oldPath);
    }

    /**
     * 删除文件
     *
     * @param String
     *            filePathAndName 文件路径及名称 如c:/fqf.txt
     */
    public static void delFile(String filePathAndName) {
        if (filePathAndName == null || filePathAndName.equals("")
                || filePathAndName.equals("null"))
            return;
        try {
            File myDelFile = new File(filePathAndName);
            if (myDelFile.exists())
                myDelFile.delete();
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    /**
     * 删除文件夹
     *
     * @param String
     *            folderPath 文件夹路径及名称 如c:/fqf
     */
    public static void delFolder(String folderPath) {
        if (folderPath == null || folderPath.equals(""))
            return;
        try {
            File myFilePath = new File(folderPath);
            if (myFilePath.isDirectory() && myFilePath.exists()) {
                delAllFile(folderPath); // 删除完里面所有内容
                myFilePath.delete(); // 删除空文件夹
            }

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    /**
     * 删除文件夹里面的所有文件
     *
     * @param String
     *            path 文件夹路径 如 c:/fqf
     */
    public static void delAllFile(String path) {
        if (path == null || path.equals(""))
            return;
        File file = new File(path);
        if (!file.exists() || !file.isDirectory()) {
            return;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + File.separator + tempList[i]);// 先删除文件夹里面的文件
                delFolder(path + File.separator + tempList[i]);// 再删除空文件夹
            }
        }
    }

    /**
     * 删除目录以及当前目录下的文件或子目录。用递归就可以实现。
     *
     * @param String
     *            filepath 目录
     * @throws Exception
     */
    public static void del(String filepath) throws Exception {
        if (filepath == null || filepath.equals("") || filepath.equals("null"))
            return;
        try {
            File f = new File(filepath);// 定义文件路径
            if (f.exists() && f.isDirectory()) {// 判断是文件还是目录
                if (f.listFiles().length == 0) {// 若目录下没有文件则直接删除
                    f.delete();
                } else {// 若有则把文件放进数组，并判断是否有下级目录
                    File delFile[] = f.listFiles();
                    int i = f.listFiles().length;
                    for (int j = 0; j < i; j++) {
                        if (delFile[j].isDirectory()) {
                            del(delFile[j].getAbsolutePath());// 递归调用del方法并取得子目录路径
                        }
                        delFile[j].delete();// 删除文件
                    }
                }
                del(filepath);// 递归调用
            }
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 遍历文件夹下的文件
     *
     * @param  absoluteDir 文件夹路径
     * @return 文件夹下的所有文件的list
     */
    public static List<String> getFilesFromFolder(String absoluteDir) {

        // 存放所有文件绝对路径名的list
        List<String> files = new ArrayList<String>();
        if (absoluteDir == null || absoluteDir.equals("")) {
            return null;
        }
        // 当前目录的file实例
        File parentDir = new File(absoluteDir);
        if (!parentDir.exists()) {
            return null;
        }
        // 列举当前目录下的所有文件和目录的名字
        String[] list = parentDir.list();
        for (String s : list) {
            // 绝对路径名
            String name = absoluteDir + File.separator + s;
            File instance = new File(name);
            // 如果是文件则添加到list
            if (instance.isFile()) {
                files.add(name);
                // 如果是目录则使用递归
            } else {
                // files.addAll(getAllFiles(name));
            }
        }
        return files;
    }

    /** 压缩一个文件
     * @param file  文件：全路径
     * @param zipFileName  生成压缩文件的名字
     * @param basedir   压缩文件存放位置
     * */
    public static void compressFile(File file, String zipFileName,String basedir) {
        if (!file.exists()) {
            return;
        }
        try {
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(basedir+zipFileName));
            BufferedInputStream bis = new BufferedInputStream(
                    new FileInputStream(file));
            ZipEntry entry = new ZipEntry(file.getName());
            out.putNextEntry(entry);
            int count;
            byte data[] = new byte[8192];
            while ((count = bis.read(data, 0, 8192)) != -1) {
                out.write(data, 0, count);
            }
            bis.close();
            out.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
