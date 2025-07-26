package $Expertise.Language;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/*
* 4 ways to read file- BufferReader, Scanner Class,FileReader,read whole file in a list,Read as a complete String
* */

class Readers{

    void BufferedReader( File file) throws IOException {

        BufferedReader br= new BufferedReader(new java.io.FileReader(file));
        String str="";
        while ((str=br.readLine())!=null){
            System.out.println(str);
        }
    }

    void ScannerReader(File file) throws  FileNotFoundException{
        Scanner sc= new Scanner(file);
        System.out.println(sc.nextInt());
        sc.nextLine();
        System.out.println(sc.nextInt());
        sc.nextLine();

        int b=Integer.parseInt(sc.nextLine().split(" ")[1]);
        System.out.println(sc.nextLine());
    }
}
public class FileReader {
    public static void main(String[] args) throws Exception {
            File file= new File("A:\\Documents\\GitHub\\JavaPrograms\\src\\input.txt");
           Readers readers = new Readers();
         //   readers.BufferedReader(file);

        readers.ScannerReader(file);

    }

}
