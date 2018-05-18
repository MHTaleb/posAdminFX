package easypos.easyposserveradministrator;

import com.mashape.unirest.http.Unirest;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClients;


public class MainApp extends Application {
    
    static{
        System.setProperty("javax.net.ssl.trustStore", "c:/ssl/keystore.jks");
        System.setProperty("javax.net.ssl.trustStorePassword", "notfound");
        System.setProperty("javax.net.ssl.keyStore", "c:/ssl/keystore.jks");
        System.setProperty("javax.net.ssl.keyStorePassword", "notfound");
    }

    @Override
    public void start(Stage stage) throws Exception {
        try {
            
        HttpClient httpClient = HttpClients.custom()
                .setDefaultCookieStore(new BasicCookieStore())
                .build();
        Unirest.setHttpClient(httpClient);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        
        stage.setTitle("JavaFX and Maven");
        
        stage.setScene(scene);
        stage.show();
        
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
