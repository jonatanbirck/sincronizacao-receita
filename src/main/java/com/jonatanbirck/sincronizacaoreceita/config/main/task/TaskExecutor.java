package com.jonatanbirck.sincronizacaoreceita.config.main.task;

/**
 * Represents a task that can run standalone by the application
 */
public interface TaskExecutor<T> {
    void execute(T arg);
}
