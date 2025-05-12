package chx.img;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileNameBuilder {
    public static void main(String[] args) {
        // 1. 配置参数
        String targetDirectory = "C:\\mycode\\code\\tt-learn-master\\tt-play\\src\\main\\java\\chx\\img";      // 要扫描的目录（默认当前目录）
        String outputFile = "C:\\mycode\\code\\tt-learn-master\\tt-play\\src\\main\\java\\chx\\img\\image-list.js"; // 输出文件名
        boolean sortNames = true;          // 是否按文件名排序

        // 2. 获取图片列表
        List<String> imageNames = getImageNames(targetDirectory, sortNames);

        // 3. 生成并保存JS文件
        if (!imageNames.isEmpty()) {
            generateJSFile(imageNames, outputFile);
            System.out.println("生成成功！找到 " + imageNames.size() + " 张图片");
        } else {
            System.out.println("未找到任何JPG/JPEG图片");
        }
    }

    private static List<String> getImageNames(String directoryPath, boolean sort) {
        List<String> imageNames = new ArrayList<>();
        File directory = new File(directoryPath);

        if (!directory.exists() || !directory.isDirectory()) {
            System.out.println("错误：目录不存在 - " + directoryPath);
            return imageNames;
        }

        for (File file : directory.listFiles()) {
            if (file.isFile() && isJpegFile(file)) {
                imageNames.add(file.getName());
            }
        }

        if (sort) {
            imageNames.sort(String::compareToIgnoreCase);
        }
        return imageNames;
    }

    private static boolean isJpegFile(File file) {
        String name = file.getName().toLowerCase();
        return name.endsWith(".jpg") || name.endsWith(".jpeg") || name.endsWith(".png");
    }

    private static void generateJSFile(List<String> names, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("// 自动生成的图片列表\n");
            writer.write("const imageList = [\n");

            for (int i = 0; i < names.size(); i++) {
                String line = "  \"" + names.get(i) + "\"";
                if (i < names.size() - 1) line += ",";
                writer.write(line + "\n");
            }

            writer.write("];\n");
        } catch (IOException e) {
            System.out.println("写入文件失败: " + e.getMessage());
        }
    }
}
