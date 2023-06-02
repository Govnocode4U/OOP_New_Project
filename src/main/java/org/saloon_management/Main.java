package org.saloon_management;

import javafx.application.Application;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.spring.SpringFxWeaver;
import org.saloon_management.ui.application.SpringBootJavaFxApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

//#TODO:22. Разработать ПК для администратора салона красоты.
//      В ПК должна храниться информация о клиентах салона, мастерах с указанием их специализации, услугах и их ценах.
//      Администратор может добавлять, изменять или удалять эту информацию. Ему могут понадобиться следующие сведения и возможности:
//      1 - Просмотр расписания работы салона на выбранный день
//      2 - Просмотр загруженности определённого мастера на день/неделю/месяц
//      3 - Запись клиента на процедуру к определённому мастеру
//      4 - Отчет о работе салона за неделю/месяц: сколько клиентов было обслужено, сколько денег пришло, какие мастера самые востребованные и какие – самые доходные

@SpringBootApplication
@EnableAsync
public class Main {

    public static void main(String[] args) {
//        SpringApplication.run(Main.class, args);
        Application.launch(SpringBootJavaFxApplication.class, args);
    }

    @Bean
    public FxWeaver fxWeaver(ConfigurableApplicationContext applicationContext) {
        return new SpringFxWeaver(applicationContext);
    }
}



