package com.jonatanbirck.sincronizacaoreceita.job.item;

public interface ItemWriter<T> {
    void write(T t);
}
