package com.iafasep.Utiles;

import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;
import org.apache.commons.compress.archivers.sevenz.SevenZOutputFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class SevenZ {

    private SevenZ() {
    }

    public static int compress(String name, List<File> files) throws IOException {
        int count = 0;
        try ( SevenZOutputFile out = new SevenZOutputFile(new File(name))) {
            for (File file : files) {
                boolean isOk = addToArchiveCompression(out, file, ".");
                count++;
            }
            out.close();
        }
        return count;
    }

    public static boolean decompress(String in, File destination) throws IOException {
        SevenZFile sevenZFile = new SevenZFile(new File(in));
        SevenZArchiveEntry entry;
        boolean isOk = false;
        while ((entry = sevenZFile.getNextEntry()) != null) {
            if (entry.isDirectory()) {
                continue;
            }
            String filename = entry.getName();
            filename = filename.replace("[R]", "");
            File curfile = new File(destination, filename);
            File parent = curfile.getParentFile();
            if (!parent.exists()) {
                parent.mkdirs();
            }
            FileOutputStream out = new FileOutputStream(curfile);
            byte[] content = new byte[(int) entry.getSize()];
            sevenZFile.read(content, 0, content.length);
            out.write(content);
            out.close();
        }
        isOk = true;
        return isOk;
    }

    private static boolean addToArchiveCompression(SevenZOutputFile out, File file, String dir) throws IOException {
        String name = /*dir + File.separator +*/ file.getName();
        boolean isOk = false;
        if (file.isFile()) {
            SevenZArchiveEntry entry = out.createArchiveEntry(file, name);
            out.putArchiveEntry(entry);
            FileInputStream in = new FileInputStream(file);
            byte[] b = new byte[1024];
            int count = 0;
            while ((count = in.read(b)) > 0) {
                out.write(b, 0, count);
            }
            out.closeArchiveEntry();
            in.close();
            isOk = true;
        } else if (file.isDirectory()) {
            File[] children = file.listFiles();
            if (children != null) {
                for (File child : children) {
                    isOk = addToArchiveCompression(out, child, name);
                }
            }
        } else {
            System.out.println(file.getName() + " is not supported");
            isOk = false;
        }
        return isOk;
    }
}
