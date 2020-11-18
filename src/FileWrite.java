import java.io.File;
import java.io.FileWriter;

public class FileWrite {

    private static final FileWrite fw = new FileWrite();

    private FileWrite() { 
	super(); 
    }

    public synchronized void write(String str) {
        try {
            File outputF = new File("log.txt");
            FileWriter writer = new FileWriter(outputF, true);
            writer.write(str+"\n");
            writer.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public static FileWrite getInstance() {
        return fw;
    }

}
