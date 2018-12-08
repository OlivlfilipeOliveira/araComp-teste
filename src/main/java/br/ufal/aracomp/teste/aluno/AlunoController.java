package br.ufal.aracomp.teste.aluno;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufal.aracomp.teste.turma.Turma;
import br.ufal.aracomp.teste.turma.TurmaDTO;
import br.ufal.aracomp.teste.turma.TurmaService;
import br.ufal.aracomp.teste.util.ResourceNotFoundException;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

	@Autowired
	private AlunoRepository repository;

	@Autowired
	private AlunoService alunoService;

	@Autowired
	private TurmaService turmaService;

	public Class<AlunoDTO> getResourceClass() {
		return AlunoDTO.class;
	}

	@GetMapping
	public List<AlunoDTO> findAll() {
		Iterable<Aluno> alunos = repository.findAll();

		List<AlunoDTO> alunosdto = alunoService.alunosToAlunosDTO(alunos, this.getResourceClass());

		return alunosdto;
	}

	@GetMapping("/{matricula}")
	public AlunoDTO finOne(@PathVariable("matricula") String matricula) throws Exception {

		Aluno aluno = repository.getOne(matricula);

		if (aluno != null) {
			AlunoDTO alunodto = alunoService.alunoToAlunoDTO(aluno, this.getResourceClass());

			return alunodto;
		} else {
			throw new ResourceNotFoundException("resource not found for id " + matricula);
		}
	}

	@DeleteMapping("/{matricula}")
	public AlunoDTO delete(@PathVariable("matricula") String matricula) throws Exception {
		Aluno aluno = repository.getOne(matricula);

		if (aluno != null) {
			repository.deleteById(matricula);
			AlunoDTO alundto = alunoService.alunoToAlunoDTO(aluno, this.getResourceClass());
			
			return alundto;
		} else {
			throw new ResourceNotFoundException("resource not found for id " + matricula);
		}
	}

	@PostMapping
	public AlunoDTO create(@RequestBody AlunoDTO alunodto) {

		Aluno aluno = alunoService.alunoDTOToAluno(alunodto, this.getResourceClass());

		if (aluno != null) {
			Aluno retorno = repository.save(aluno);
			AlunoDTO retornodto = alunoService.alunoToAlunoDTO(retorno, this.getResourceClass());

			return retornodto;
		} else {
			return null;
		}
	}

	@PutMapping()
	public AlunoDTO update(@RequestBody AlunoDTO alunodto) throws Exception {

		Aluno aluno = repository.getOne(alunodto.getMatricula());

		if (aluno != null) {

			aluno.setNome(alunodto.getNome());
			aluno.setCurso(alunodto.getCurso());

			TurmaDTO turmadto = alunodto.getTurma();
			Turma turma = turmaService.turmaDTOToTurma(turmadto, this.getResourceClass());
			aluno.setTurma(turma);

			Aluno retorno = repository.save(aluno);

			AlunoDTO alunoRetorno = alunoService.alunoToAlunoDTO(retorno, this.getResourceClass());

			return alunoRetorno;
		} else {
			throw new ResourceNotFoundException("resource not found for id " + alunodto.getMatricula());
		}
	}
}
