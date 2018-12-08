package br.ufal.aracomp.teste;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.ufal.aracomp.teste.aluno.Aluno;
import br.ufal.aracomp.teste.aluno.AlunoRepository;
import br.ufal.aracomp.teste.turma.Turma;
import br.ufal.aracomp.teste.turma.TurmaRepository;

@Component
public class DataLoader implements CommandLineRunner {

	private final Logger logger = LoggerFactory.getLogger(DataLoader.class);

	@Autowired
	private AlunoRepository alunoRepository;

	@Autowired
	private TurmaRepository turmaRepository;
	
	@Override
	public void run(String... args) throws Exception {

		logger.info("Carregando dados de teste na inicialização...");

		this.dadosTurmas();
		this.dadosAlunos();

		logger.info("Alunos carregadas: {}", alunoRepository.count());
		logger.info("Turmas carregadas: {}", turmaRepository.count());

		logger.info("Finalizando teste na inicialização...");
	}
	
	private void dadosTurmas() {
		Turma t1 = new Turma("511-A");
		Turma t2 = new Turma("411-A");
		Turma t3 = new Turma("211-A");
		Turma t4 = new Turma("514-A");
		
		turmaRepository.save(t1);
		turmaRepository.save(t2);
		turmaRepository.save(t3);
		turmaRepository.save(t4);
		
	}

	private void dadosAlunos() {
		
		Turma turm1 = turmaRepository.getOne(1L);
		Turma turma2 = turmaRepository.getOne(4L);
		Turma turma3 = turmaRepository.getOne(3L);
		
		Aluno a1 = new Aluno("111", "Biu", "Ciência da Computação");
		a1.setTurma(turm1);
		Aluno a2 = new Aluno("222", "Zé", "Arquitetura e Urbanismo");
		a2.setTurma(turm1);
		Aluno a3 = new Aluno("333", "Mark", "Engenharia Elétrica");
		a3.setTurma(turma2);
		Aluno a4 = new Aluno("444", "Renata", "Letras");
		a4.setTurma(turma3);
		Aluno a5 = new Aluno("555", "Jão", "CC");
		
		alunoRepository.save(a1);
		alunoRepository.save(a2);
		alunoRepository.save(a3);
		alunoRepository.save(a4);
		alunoRepository.save(a5);
		
	}

}
