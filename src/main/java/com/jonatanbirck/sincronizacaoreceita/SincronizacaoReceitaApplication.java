package com.jonatanbirck.sincronizacaoreceita;

import com.jonatanbirck.sincronizacaoreceita.util.FileUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.util.Properties;

@SpringBootApplication
public class SincronizacaoReceitaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SincronizacaoReceitaApplication.class, args);

		//TODO: transfer responsibility for service
		if (args.length > 0) {
			final File inputFile = new File(args[0]);

			if (!FileUtils.isCsvFile(inputFile)) {
				System.out.println("ERRO: Input file is not valid");
			}
		}
	}

}
