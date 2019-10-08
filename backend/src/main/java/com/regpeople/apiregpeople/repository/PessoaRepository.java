package com.regpeople.apiregpeople.repository;

import com.regpeople.apiregpeople.dto.PessoaDTO;
import com.regpeople.apiregpeople.entity.Pessoa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PessoaRepository extends CrudRepository<Pessoa, Long> {

    boolean existsByCpf(String cpf);
    List<PessoaDTO> findByCpf(String cpf);
    List<PessoaDTO> findByNome(String nome);
    List<PessoaDTO> findByNaturalidade(String naturalidade);
    List<PessoaDTO> findByNacionalidade(String nacionalidade);
    List<PessoaDTO> findByEmail(String email);
    List<PessoaDTO> findByDtNascimento(LocalDate dtNascimento);
}
