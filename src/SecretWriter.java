import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SecretWriter {
    public static void encodeToFile(File file, Integer[] messageCode){
        if(!file.exists()){
            System.out.println("File does not exist");
            return;
        }
        try{
            FileWriter messageWriter= new FileWriter(file, false);
            messageWriter.flush();
            messageWriter.write(messageCode.length+"\n");
            for (Integer Charcode:messageCode){
                messageWriter.write(Charcode+" ");
            }
            messageWriter.close();

        } catch (IOException e) {
            System.out.println("Error writing to File");
            e.printStackTrace();


        }
    }

    public static String decodeFromFile(File file){
        String message= "";
        if(!file.exists()){
            System.out.println("File does not exist");
            return message;
        }

        try{
            Scanner scanner= new Scanner(file);
            StringBuilder messageBuilder= new StringBuilder();
            int i=0;
            while(scanner.hasNext())
            {
                String intLetterKeyStr= scanner.next();
                i++;
                if(i==1){
                    continue;

                }
                try{
                    int intCharKey= Integer.parseInt(intLetterKeyStr);
                    Character c=SecretCode.INTEGER_DECODE_MAP.get(intCharKey);
                    if (c !=null && SecretCode.INTEGER_DECODE_MAP.containsKey(intCharKey)){
                        messageBuilder.append(c);

                    }else{
                        messageBuilder.append(" ");
                    }

                }catch(NumberFormatException e){
                    System.out.println("Error reading to File");
                    e.printStackTrace();
                }
            }
            scanner.close();
            message=messageBuilder.toString();

        }catch(IOException e){
            System.out.println("Error writing to File");
            e.printStackTrace();
        }
        return message;
    }
}
