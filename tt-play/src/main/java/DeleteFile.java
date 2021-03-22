import java.io.File;

public class DeleteFile {

    public static void delete(File file){
        if (!file.exists()) {
            return;
        }
        if (file.isDirectory()){
            File[] files = file.listFiles();
            for (File fileItem : files) {
                if (file.isDirectory()){
                    delete(fileItem);
                }
            }
        }
        file.delete();
    }

    public static void main(String[] args) {
        delete(new File("C:\\Users\\guoyitao\\Desktop\\abvc"));
    }
}
