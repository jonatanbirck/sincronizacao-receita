package com.jonatanbirck.sincronizacaoreceita;

import com.jonatanbirck.sincronizacaoreceita.config.main.ArgsProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SincronizacaoReceitaApplication {

	public static void main(String[] args) {
		ArgsProperties.executeArgs(args, SpringApplication.run(SincronizacaoReceitaApplication.class, args));
	}

}
