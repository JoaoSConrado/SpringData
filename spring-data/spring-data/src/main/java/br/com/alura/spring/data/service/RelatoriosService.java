package br.com.alura.spring.data.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.repository.FuncionarioRepository;

@Service
public class RelatoriosService {

	private Boolean system = true;

	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	private final FuncionarioRepository funcionarioRepository;

	public RelatoriosService(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}

	public void inicial(Scanner scanner) {

		while (system) {
			System.out.println("QUAL AÇÃO DE CARGO DESEJA EXECUTAR?");
			System.out.println("0 - SAIR");
			System.out.println("1 - BUSCAR FUNCIONARIO PELO NOME");
			System.out.println("2 - BUSCAR FUNCIONARIO PELO NOME, DATA DE CONTRATAÇÃO E SALARIO MAIOR");
			System.out.println("3 - BUSCAR FUNCIONARIO DATA DE CONTRATAÇÃO");

			int action = scanner.nextInt();

			switch (action) {
			case 1:
				buscaFuncionarioNome(scanner);
				break;
			case 2:
				buscaFuncionarioNomeSalarioMaiorData(scanner);
				break;
			case 3:
				buscaFuncionarioDataContratacao(scanner);
				break;
			default:
				system = false;
				break;
			}

		}

	}

	private void buscaFuncionarioNome(Scanner scanner) {
		System.out.println("QUAL O NOME DO FUNCIONARIO? ");
		String nome = scanner.next();
		List<Funcionario> list = funcionarioRepository.findByNome(nome);
		list.forEach(System.out::println);
	}

	private void buscaFuncionarioNomeSalarioMaiorData(Scanner scanner) {
		System.out.println("QUAL O NOME DO FUNCIONARIO? ");
		String nome = scanner.next();

		System.out.println("QUAL A DATA DE CONTRATAÇÃO DO FUNCIONARIO? ");
		String data = scanner.next();
		LocalDate localDate = LocalDate.parse(data, formatter);

		System.out.println("QUAL O SALÁRIO DO FUNCIONARIO? ");
		Double salario = scanner.nextDouble();
		
		List<Funcionario> list = funcionarioRepository.findNomeSalarioMaiorDataContratacao(nome, salario, localDate);
		list.forEach(System.out::println);
	}
	
	private void buscaFuncionarioDataContratacao(Scanner scanner) {
		System.out.println("QUAL A DATA DE CONTRATAÇÃO DO FUNCIONARIO? ");
		String data = scanner.next();
		LocalDate localDate = LocalDate.parse(data, formatter);
		
		List<Funcionario> list = funcionarioRepository.findDataContratacaoMaior(localDate);
		list.forEach(System.out::println);
		
	}

}
