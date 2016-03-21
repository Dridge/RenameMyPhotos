package app;
/*
* File handler
*
*/

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.String;import java.lang.System;import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileHandler {
    private File folder;
    private File[] listOfFiles;
    private List<File> dscFiles = new ArrayList<>();
    private List<File> vidFiles = new ArrayList<>();
    private File logFile;
    private FileWriter out;

    FileHandler(String s) throws IOException {
        folder = new File(s);
        listOfFiles = folder.listFiles();
        logFile = new File(folder.getAbsolutePath()+"\\log.txt");
        out = new FileWriter(logFile, true /*append=yes*/);

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        out.write("RenameMyCameraPhotos program - " + dateFormat.format(new Date()));

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                System.out.println("File " + listOfFiles[i].getName());
            } else if (listOfFiles[i].isDirectory()) {
                System.out.println("Directory " + listOfFiles[i].getName());
            }
        }
        setDscFiles();
        setVidFiles();
    }

    // check file name is valid
    public boolean isValidFileName(String fileName) {
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].getName().matches(fileName)) {
                if (listOfFiles[i].isFile()) {
                    return true;
                }
            }
        }
        return false;
    }

    // get file modified date
    public String getModifiedDate(File file) {
        //System.out.println("Before Format : " + file.lastModified());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        //System.out.println("After Format : " + sdf.format(file.lastModified()));
        return sdf.format(file.lastModified());
    }

    private void setDscFiles() {
        for (int i = 0; i < listOfFiles.length; i++) {
            if(listOfFiles[i].getName().startsWith("DSC")) {
                dscFiles.add(listOfFiles[i]);
            }
        }
    }
    private void setVidFiles() {
        for (int i = 0; i < listOfFiles.length; i++) {
            if(listOfFiles[i].getName().startsWith("VID")) {
                vidFiles.add(listOfFiles[i]);
            }
        }
    }

    public void renameDscFiles() throws IOException {
        // determine new file name
        // File (or directory) with old name
        for (File f : dscFiles) {
            if (!f.exists()) {
                throw new java.io.IOException("Original dsc file no longer exists");
            }

            String newNameUsingDate = this.getNameUsingModifiedDate(f);

            File newFile = new File(folder.getAbsolutePath() + "\\" + newNameUsingDate);

            if (newFile.exists()) {
                newFile = new File(folder.getAbsolutePath() + "\\" + newNameUsingDate + "_1");
            }

            // Rename file (or directory)
            boolean success = f.renameTo(newFile);

            if (!success) {
                out.append("Failed when renaming this: " + f.getName() + ", into this file name: " + newFile.getName());
                // File was not successfully renamed
                throw new java.io.IOException("A file was not successfully renamed");
            } else {
                out.append("Renamed this: " + f.getName() + ", into this file name: " + newFile.getName());
            }
        }
    }

    private String getNameUsingModifiedDate(File f) {
        String name = f.getName();
        //get file type
        String fileType = name.substring(name.indexOf('.'), name.length());

        return this.getModifiedDate(f) + fileType;
    }

    public void renameVidFiles() throws IOException {
        // determine new file name
        for (File f : vidFiles) {
            if (!f.exists()) {
                throw new java.io.IOException("Original dsc file no longer exists");
            }

            String s = getSubstring(f.getName());

            File newFile = new File(folder.getAbsolutePath() + "\\" + s);

            if (newFile.exists()) {
                newFile = new File(folder.getAbsolutePath() + "\\" + s + "_1");
            }

            // Rename file (or directory)
            boolean success = f.renameTo(newFile);

            if (!success) {
                out.append("Failed when renaming this: " + f.getName() + ", into this file name: " + newFile.getName());
                // File was not successfully renamed
                throw new java.io.IOException("A file was not successfully renamed");
            } else {
                out.append("Renamed this: " + f.getName() + ", into this file name: " + newFile.getName());
            }
        }
    }

    public void renameImgFiles() throws IOException {
        // determine new file name
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].getName().startsWith("IMG")) {
                if (!listOfFiles[i].exists()) {
                    throw new java.io.IOException("Original dsc file no longer exists");
                }

                String s = getSubstring(listOfFiles[i].getName());

                File newFile = new File(folder.getAbsolutePath() + "\\" + s);

                if (newFile.exists()) {
                    newFile = new File(folder.getAbsolutePath() + "\\" + s + "_1");
                }

                // Rename file (or directory)
                boolean success = listOfFiles[i].renameTo(newFile);

                if (!success) {
                    out.append("Failed when renaming this: " + listOfFiles[i].getName() + ", into this file name: " + newFile.getName());
                    // File was not successfully renamed
                    throw new java.io.IOException("A file was not successfully renamed");
                } else {
                    out.append("Renamed this: " + listOfFiles[i].getName() + ", into this file name: " + newFile.getName());
                }
            }
        }

        }
    private String getSubstring(String string) {
        return string.substring(string.indexOf('_', 0)+1,string.length());
    }
}