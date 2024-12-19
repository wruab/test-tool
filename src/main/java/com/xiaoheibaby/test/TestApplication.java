package com.xiaoheibaby.test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class TestApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        // 创建 JavaFX 界面
        Label label = new Label("Hello, JavaFX!");
        Scene scene = new Scene(label, 300, 200);

        primaryStage.setTitle("JavaFX Application");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        // 启动 Spring Boot 应用
        new SpringApplicationBuilder(TestApplication.class)
            .web(WebApplicationType.NONE) // 禁用 Web 环境
            .run(args);

        launch(args);
    }
}
