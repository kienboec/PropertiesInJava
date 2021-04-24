import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        try {
            loadKeyValuePair();
            loadXMLSettings();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadKeyValuePair() throws IOException {
        // key value pair extending Hashtable
        // https://en.wikipedia.org/wiki/.properties
        Properties appProps = new Properties();
        appProps.load(Thread.currentThread()
                            .getContextClassLoader()
                            .getResourceAsStream("app.properties"));

        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        appProps.setProperty("lastUpdate", dateFormat.format(date));

        var out = new FileOutputStream("current.app.properties");
        appProps.store(out, "my comment");
        out.close();

        printSettings(appProps);
    }

    private static void loadXMLSettings() throws IOException {
        Properties appProps = new Properties();
        appProps.loadFromXML(Thread.currentThread()
                                   .getContextClassLoader()
                                   .getResourceAsStream("app.xml"));
        printSettings(appProps);
    }

    private static void printSettings(Properties props) {
        System.out.println(props.getProperty("name") + " - " + props.getProperty("version"));
        System.out.println("-----------------");
        System.out.println(props.getProperty("message"));
        System.out.println();
    }
}
