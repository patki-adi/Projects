package MarvellousPackerUnpacker;

import java.util.*;
import java.io.*;

class MarvellousUnpacker
{
    private String PackName;
    public MarvellousUnpacker(String A)
    {
        this.PackName = A;
    }

    public void UnpackingAcitvity()
    {
        try
        {
            System.out.println("------------------------------------------------------------------");
            System.out.println("-------------------Marvellous Packer Unpacker---------------------");
            System.out.println("------------------------------------------------------------------");
            System.out.println("-----------------------Unpacking Activity-------------------------");
            System.out.println("------------------------------------------------------------------");

            String Header = null;
            File fobjnew = null;
            int FileSize = 0, iRet = 0, iCountFile = 0;

            File fobj = new File(PackName);

            // If packed file is not present
            if(!fobj.exists())
            {
                System.out.println("Unable to access packed file");
                return;
            }

            System.out.println("Packed file get succesfully opened");

            FileInputStream fiobj = new FileInputStream(fobj);

            //Buffer to read the header
            byte HeaderBuffer[] = new byte[100];

            // scan the packed file to extract the files from it
            while((iRet = fiobj.read(HeaderBuffer,0,100)) != -1)
            {
                // Convert byte array to string
                Header = new String(HeaderBuffer);

                Header = Header.trim();

                // tokenize the header into two parts
                String Tokens[] = Header.split(" ");

                fobjnew = new File(Tokens[0]);

                //create new file to extract
                fobjnew.createNewFile();

                FileSize = Integer.parseInt(Tokens[1]);

                // create new buffer to store file data
                byte Buffer[] = new byte[100];

                FileOutputStream foobj = new FileOutputStream(fobjnew);

                // read the data from packed file
                fiobj.read(Buffer,0,FileSize);

                // write the data into extracted file
                foobj.write(Buffer,0,FileSize);

                System.out.println("File Unpacked with name" + Tokens[0] + "having size" + Tokens[1]);

                iCountFile++;

                foobj.close();
            } //End of while

            System.out.println("------------------------------------------------------------------");
            System.out.println("-------------------Statistical Report-----------------------------");
            System.out.println("------------------------------------------------------------------");

            System.out.println("total number o files unpacked"+iCountFile);

            System.out.println("------------------------------------------------------------------");
            System.out.println("-----------------Thank you for using our application--------------");
            System.out.println("------------------------------------------------------------------");

            fiobj.close();
            
        }
        catch(Exception eobj)
        {}
    }
}