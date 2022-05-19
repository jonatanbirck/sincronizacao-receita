package com.jonatanbirck.sincronizacaoreceita.config.main;

import com.jonatanbirck.sincronizacaoreceita.config.main.task.CreateFakeCsvTask;
import com.jonatanbirck.sincronizacaoreceita.config.main.task.TaskExecutor;
import com.jonatanbirck.sincronizacaoreceita.config.main.task.UpdateAccountTask;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Map;

/**
 * Manages input arguments on run standalone by the application
 */
public class ArgsProperties {

    private ArgsProperties() {}

    private static final Map<String, Class<?>> mapper = Map.of(
        "createFakeCsv", CreateFakeCsvTask.class,
        "updateAccounts", UpdateAccountTask.class
    );

    public static void executeArgs(String[] args, ConfigurableApplicationContext context) {
        if (args == null) return;

        String[] argSplited;

        for (String arg : args) {
            argSplited = arg.split("=");

            if (argSplited.length != 2) {
                continue;
            }

            Class<?> clazz = mapper.get(argSplited[0]);

            if (clazz != null) {
                TaskExecutor<String> task = (TaskExecutor<String>) context.getBean(clazz);
                task.execute(argSplited[1]);
            }
        }
    }

}
