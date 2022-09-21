package br.com.alura.spring.data.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.UnidadeTrabalho;
import br.com.alura.spring.data.repository.CargoRepository;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import br.com.alura.spring.data.repository.UnidadeTrabalhoRepository;

@Service
public class CrudFuncionarioService {

	private Boolean system = true;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	private final CargoRepository cargoRepository;
	private final FuncionarioRepository FuncionarioRepository;
	private final UnidadeTrabalhoRepository unidadeTrabalhoRepository;

	public CrudFuncionarioService(FuncionarioRepository funcionarioRepository, CargoRepository cargoRepository,
			UnidadeTrabalhoRepository unidadeTrabalhoRepository) {
		this.FuncionarioRepository = funcionarioRepository;
		this.cargoRepository = cargoRepository;
		this.unidadeTrabalhoRepository = unidadeTrabalhoRepository;
	}

	public void inicial(Scanner scanner) {
		while (system) {
			System.out.println("QUAL AÇÃO DE FUNCIONARIO DESEJA EXECUTAR?");
			System.out.println("0 - SAIR");
			System.out.println("1 - SALVAR");
			System.out.println("2 - ATUALIZAR");
			System.out.println("3 - VISUALIZAR");
			System.out.println("4 - DELETAR");

			int action = scanner.nextInt();

			switch (action) {
			case 1:
				salvar(scanner);
				break;
			case 2:
				atualizar(scanner);
				break;
			case 3:
				visualizar();
				break;
			case 4:
				deletar(scanner);
				break;
			default:
				system = false;
				break;
			}

		}
	}

	private void salvar(Scanner scanner) {

		System.out.println("NOME DO FUNCIONARIO: ");
		String nome = scanner.next();

		System.out.println("CPF DO FUNCIONARIO: ");
		String cpf = scanner.next();

		System.out.println("SALARIO DO FUNCIONARIO: ");
		Double salario = scanner.nextDouble();

		System.out.println("DATA DE CONTRATAÇÃO DO FUNCIONARIO: ");
		String dataContratacao = scanner.next();

		System.out.println("DIGITE O CARGO ID: ");
		Integer cargoId = scanner.nextInt();

		List<UnidadeTrabalho> unidades = unidade(scanner);

		Funcionario funcionario = new Funcionario();

		funcionario.setNome(nome);
		funcionario.setCpf(cpf);
		funcionario.setSalario(salario);
		funcionario.setDataContratacao(LocalDate.parse(dataContratacao, formatter));

		Optional<Cargo> cargo = cargoRepository.findById(cargoId);
		funcionario.setCargo(cargo.get());
		funcionario.setUnidadeTrabalhos(unidades);

		FuncionarioRepository.save(funcionario);

		System.out.println("SALVO");
	}

	private List<UnidadeTrabalho> unidade(Scanner scanner) {
		Boolean isTrue = true;
		List<UnidadeTrabalho> unidades = new ArrayList<>();

		while (isTrue) {
			System.out.println("Digite o unidadeId (Para sair digite 0)");
			Integer unidadeId = scanner.nextInt();

			if (unidadeId != 0) {
				Optional<UnidadeTrabalho> unidade = unidadeTrabalhoRepository.findById(unidadeId);
				unidades.add(unidade.get());
			} else {
				isTrue = false;
			}
		}

		return unidades;
	}

	private void atualizar(Scanner scanner) {
		System.out.println("DIGITE O ID");
		Integer id = scanner.nextInt();

		System.out.println("NOME DO FUNCIONARIO: ");
		String nome = scanner.next();

		System.out.println("CPF DO FUNCIONARIO: ");
		String cpf = scanner.next();

		System.out.println("SALARIO DO FUNCIONARIO: ");
		Double salario = scanner.nextDouble();

		System.out.println("DATA DE CONTRATAÇÃO DO FUNCIONARIO: ");
		String dataContratacao = scanner.next();

		Funcionario funcionario = new Funcionario();

		funcionario.setId(id);
		funcionario.setNome(nome);
		funcionario.setCpf(cpf);
		funcionario.setSalario(salario);
		funcionario.setDataContratacao(LocalDate.parse(dataContratacao, formatter));

		FuncionarioRepository.save(funcionario);

		System.out.println("ATUALIZADO");
	}

	private void visualizar() {
		FuncionarioRepository.findAll().forEach(System.out::println);
	}

	private void deletar(Scanner scanner) {
		System.out.println("Id");
		int id = scanner.nextInt();
		FuncionarioRepository.deleteById(id);
		System.out.println("DELETADO");
	}

}
