package br.com.alura.spring.data.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.UnidadeTrabalho;
import br.com.alura.spring.data.repository.UnidadeTrabalhoRepository;

@Service
public class CrudUnidadeTrabalhoService {

	private Boolean system = true;
	private final UnidadeTrabalhoRepository UnidadeTrabalhoRepository;

	public CrudUnidadeTrabalhoService(UnidadeTrabalhoRepository unidadeTrabalhoRepository) {
		this.UnidadeTrabalhoRepository = unidadeTrabalhoRepository;
	}

	public void inicial(Scanner scanner) {
		while (system) {
			System.out.println("QUAL AÇÃO DE UNIDADE DE TRABALHO DESEJA EXECUTAR?");
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
		System.out.println("DESCRIÇÃO DA UNIDADE: ");
		String descricao = scanner.next();

		System.out.println("ENDEREÇO DA UNIDADE: ");
		String endereco = scanner.next();

		UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();

		unidadeTrabalho.setDescricao(descricao);
		unidadeTrabalho.setEndereco(endereco);

		UnidadeTrabalhoRepository.save(unidadeTrabalho);
		System.out.println("SALVO");

	}

	private void atualizar(Scanner scanner) {

		System.out.println("DIGITE O ID");
		Integer id = scanner.nextInt();

		System.out.println("DESCRIÇÃO DA UNIDADE: ");
		String descricao = scanner.next();

		System.out.println("ENDEREÇO DA UNIDADE: ");
		String endereco = scanner.next();

		UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();

		unidadeTrabalho.setId(id);
		unidadeTrabalho.setDescricao(descricao);
		unidadeTrabalho.setEndereco(endereco);

		UnidadeTrabalhoRepository.save(unidadeTrabalho);
		System.out.println("ATUALIZADO");

	}

	private void visualizar() {
		UnidadeTrabalhoRepository.findAll().forEach(System.out::println);
	}

	private void deletar(Scanner scanner) {
		System.out.println("Id");
		int id = scanner.nextInt();
		UnidadeTrabalhoRepository.deleteById(id);
		System.out.println("DELETADO");
	}
}
