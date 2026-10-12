package genericUtility;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.openqa.selenium.*;

public class ScreenshotUtility {
    public String capture(WebDriver driver, String testName) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        File folder = new File("Screenshots");
        
        if (!folder.exists()) folder.mkdirs();
        String safeName = testName.replaceAll("[^a-zA-Z0-9._-]", "_");
        String path = new File(folder, safeName + "_" + timestamp + ".png").getAbsolutePath();
        File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        source.renameTo(new File(path));
        return path;
    }
}