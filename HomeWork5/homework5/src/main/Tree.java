package main;

import java.io.File;

public class Tree {

    /**
     * TODO: Доработать метод print, необходимо распечатывать директории и файлы
     * @param file
     * @param indent
     * @param isLast
     */
    public static void print(File file, String indent, boolean isLast){
        System.out.print(indent); // рисуем отступ
        if (isLast){
//            System.out.print("??");
            System.out.print("|_");
            indent += "  ";
        }
        else {
//            System.out.print("??");
            System.out.print("|-");
//            indent += "? ";
            indent += "| ";
        }
        System.out.println(file.getName());

        File[] files = file.listFiles();
        if (files == null)
            return;


        int subDirTotal = files.length; // 0;
        /*
        for (int i = 0; i < files.length; i++){
            if (files[i].isDirectory())
                subDirTotal++;
        }
        */

        int subDirCounter = 0;
        for (int i = 0; i < files.length; i++){
            if (files[i].isDirectory() || files[i].isFile()){
                print(files[i], indent, subDirCounter  == subDirTotal - 1);
                subDirCounter++;
            }
        }


    }

}
