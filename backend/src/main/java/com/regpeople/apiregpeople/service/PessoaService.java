package com.regpeople.apiregpeople.service;

import com.regpeople.apiregpeople.dto.PessoaDTO;
import com.regpeople.apiregpeople.entity.Pessoa;
import com.regpeople.apiregpeople.repository.PessoaRepository;
import com.regpeople.apiregpeople.utils.Utilitarios;
import com.regpeople.apiregpeople.validation.DateValidatorUsingDateFormat;
import com.regpeople.apiregpeople.validation.ivalidation.DateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    @Autowired
    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public String validarAtributos(PessoaDTO pessoaDTO, String operacao) {

        String message = null;
        DateValidator validator = new DateValidatorUsingDateFormat("dd/MM/yyyy");

        try {
            if (operacao.equals("UPDATE")) {
                if (pessoaDTO.getId() == null) {
                    message = "Informe ID da pessoa.";
                } else {
                    if (pessoaDTO.getId() <= 0) {
                        message = "Informe ID da pessoa.";
                    }
                }
            }
            if (pessoaDTO.getNome() == null) {
                message = "Informe Nome da pessoa.";
            } else if ((pessoaDTO.getNome().isEmpty() || pessoaDTO.getNome().length() == 0) && message == null) {
                message = "Informe Nome da pessoa.";
            }
            if (pessoaDTO.getEmail() != null) {
                if (!Utilitarios.isValidEmail(pessoaDTO.getEmail()) && message == null) {
                    message = "Informe E-mail válido.";
                }
            }
            if (pessoaDTO.getSexo() < 0 && message == null) {
                message = "Informe 0 - Masculino e 1 - Feminino.";
            }
            if (pessoaDTO.getDtNascimento() != null) {
                if (!validator.isValid(pessoaDTO.getDtNascimento().toString()) && message == null) {
                    message = "Informe Data de Nascimento válida.";
                }
            } else {
                message = "Informe Data de Nascimento válida.";
            }
            if (pessoaDTO.getCpf() != null) {
                if ((pessoaDTO.getCpf().isEmpty() || pessoaDTO.getCpf().length() == 0) && message == null) {
                    message = "Informe CPF válido";
                } else if (!Utilitarios.isValidCPF(Utilitarios.somenteDigitos(pessoaDTO.getCpf())) && message == null) {
                    message = "CPF inválido";
                }
            } else {
                message = "Informe CPF válido";
            }
        } catch (Exception e) {
            message = e.getMessage();
        }

        return message;
    }

    public boolean existsByCpf(String cpf) {
        return pessoaRepository.existsByCpf(cpf);
    }

    public Optional<Pessoa> findById(Long id) {

        Optional<Pessoa> pessoa = null;

        try {
            pessoa = pessoaRepository.findById(id);
        } catch (Exception e) {
            return Optional.empty();
        }

        return pessoa;
    }

    public List<PessoaDTO> findByName(String nome) {
        return pessoaRepository.findByNome(nome);
    }

    public List<PessoaDTO> findByCpf(String cpf) {
        return pessoaRepository.findByCpf(cpf);
    }

    public List<PessoaDTO> findByNatural(String naturalidade) {
        return pessoaRepository.findByNaturalidade(naturalidade);
    }

    public List<PessoaDTO> findByNationality(String nacionalidade) {
        return pessoaRepository.findByNacionalidade(nacionalidade);
    }

    public List<PessoaDTO> findByEmail(String email) {
        return pessoaRepository.findByEmail(email);
    }

    public List<PessoaDTO> findByBirthDate(@Param("dtNascimento") String dtNascimento) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return pessoaRepository.findByDtNascimento(LocalDate.parse(dtNascimento, formatter));
    }

    public Pessoa save(PessoaDTO pessoaDTO) {
        return pessoaRepository.save(pessoaDTO.valueOf());
    }

    public void delete(Long id) {
        pessoaRepository.deleteById(id);
    }

    public List<PessoaDTO> findAll() {
        List<PessoaDTO> lista = new ArrayList<PessoaDTO>();
        Iterable<Pessoa> it = pessoaRepository.findAll();
        for(Pessoa pessoa : it) {
            lista.add(PessoaDTO.valueOf(pessoa));
        }

        return lista;
    }

}
