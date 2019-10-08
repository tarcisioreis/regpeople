package com.regpeople.apiregpeople.controller;

import com.regpeople.apiregpeople.dto.PessoaDTO;
import com.regpeople.apiregpeople.exceptions.BusinessException;
import com.regpeople.apiregpeople.service.PessoaService;
import com.regpeople.apiregpeople.utils.Utilitarios;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/regpeople")
@CrossOrigin(origins="*")
@Api(value="API REST Cadastro de Pessoa Controller")
public class PessoaController {

    private final PessoaService pessoaService;

    private List<PessoaDTO> lista;
    private PessoaDTO dto;
    private HttpStatus httpStatus;
    private boolean found;
    private String message;
    private String cpf;
    private String maskCpf;

    @Autowired
    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
        this.cpf = null;
        this.setMaskCpf("###.###.###-##");
    }

    private String getMaskCpf() {
        return maskCpf;
    }
    private void setMaskCpf(String maskCpf) {
        this.maskCpf = maskCpf;
    }

    @PostMapping("/findByCpf/{cpf}")
    @ApiOperation(value="Busca de Cadastro por Cpf.")
    ResponseEntity<List<PessoaDTO>> findByCpf(@Valid @PathVariable String cpf) {

        try {
            httpStatus = null;
            lista = pessoaService.findByCpf(Utilitarios.formatarString(Utilitarios.somenteDigitos(cpf), getMaskCpf()));

            if (lista.size() == 0 || lista.isEmpty()) {
                httpStatus = HttpStatus.NOT_FOUND;
                throw new BusinessException("Não foi encontrado cadastro de pessoa pelo Cpf.");
            }

            httpStatus = HttpStatus.FOUND;

            return new ResponseEntity<>(lista, httpStatus);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }

    }

    @PostMapping("/findByName/{nome}")
    @ApiOperation(value="Busca de Cadastro por Nome.")
    ResponseEntity<List<PessoaDTO>> findByName(@Valid @PathVariable String nome) {

        try {
            httpStatus = null;
            lista = pessoaService.findByName(nome);

            if (lista.size() == 0 || lista.isEmpty()) {
                httpStatus = HttpStatus.NOT_FOUND;
                throw new BusinessException("Não foi encontrado cadastro de pessoa pelo Nome.");
            }

            httpStatus = HttpStatus.FOUND;

            return new ResponseEntity<>(lista, httpStatus);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }

    }

    @PostMapping("/findByNatural/{naturalidade}")
    @ApiOperation(value="Busca de Cadastro por Naturalidade.")
    ResponseEntity<List<PessoaDTO>> findByNatural(@Valid @PathVariable String naturalidade) {

        try {
            httpStatus = null;
            lista = pessoaService.findByNatural(naturalidade);

            if (lista.size() == 0 || lista.isEmpty()) {
                httpStatus = HttpStatus.NOT_FOUND;
                throw new BusinessException("Não foi encontrado cadastro de pessoa pela Naturalidade.");
            }

            httpStatus = HttpStatus.FOUND;

            return new ResponseEntity<>(lista, httpStatus);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }

    }

    @PostMapping("/findByNationality/{nacionalidade}")
    @ApiOperation(value="Busca de Cadastro por Nacionalidade.")
    ResponseEntity<List<PessoaDTO>> findByNationality(@Valid @PathVariable String nacionalidade) {

        try {
            httpStatus = null;
            lista = pessoaService.findByNationality(nacionalidade);

            if (lista.size() == 0 || lista.isEmpty()) {
                httpStatus = HttpStatus.NOT_FOUND;
                throw new BusinessException("Não foi encontrado cadastro de pessoa pela Nacionalidade.");
            }

            httpStatus = HttpStatus.FOUND;

            return new ResponseEntity<>(lista, httpStatus);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }

    }

    @PostMapping("/findByEmail/{email}")
    @ApiOperation(value="Busca de Cadastro por Email.")
    ResponseEntity<List<PessoaDTO>> findByEmail(@Valid @PathVariable String email) {

        try {
            httpStatus = null;
            lista = pessoaService.findByEmail(email);

            if (lista.size() == 0 || lista.isEmpty()) {
                httpStatus = HttpStatus.NOT_FOUND;
                throw new BusinessException("Não foi encontrado cadastro de pessoa pelo E-mail.");
            }

            httpStatus = HttpStatus.FOUND;

            return new ResponseEntity<>(lista, httpStatus);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }

    }

    @PostMapping("/findByBirthDate")
    @ApiOperation(value="Busca de Cadastro por Data de Nascimento.")
    ResponseEntity<List<PessoaDTO>> findByBirthDate(@Valid @RequestParam(name="dtNascimento") String dtNascimento) {

        try {
            httpStatus = null;
            lista = pessoaService.findByBirthDate(dtNascimento);

            if (lista.size() == 0 || lista.isEmpty()) {
                httpStatus = HttpStatus.NOT_FOUND;
                throw new BusinessException("Não foi encontrado cadastro de pessoa pela Data de Nascimento.");
            }

            httpStatus = HttpStatus.FOUND;

            return new ResponseEntity<>(lista, httpStatus);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }

    }

    @GetMapping("/list")
    @ApiOperation(value="Listagem de Todas as pessoas cadastradas.")
    ResponseEntity<List<PessoaDTO>> list() {

        try {
            httpStatus = null;
            lista = pessoaService.findAll();

            if (lista.size() == 0 || lista.isEmpty()) {
                httpStatus = HttpStatus.NOT_FOUND;
                throw new BusinessException("Não foram encontrados cadastros.");
            }

            if (httpStatus == null) {
                httpStatus = HttpStatus.OK;
            }
        } catch (Exception e) {
            if (httpStatus == null) {
                httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            }
            throw new BusinessException(e.getMessage());
        }

        return new ResponseEntity<>(lista, httpStatus);
    }

    @PostMapping("/create")
    @ApiOperation(value = "Criação do cadastro da pessoa.")
    ResponseEntity<PessoaDTO> create(@Valid @RequestBody PessoaDTO pessoaDTO) {

        try {
            httpStatus = null;

            dto = new PessoaDTO();
            BeanUtils.copyProperties(pessoaDTO, dto);

            cpf = Utilitarios.formatarString(Utilitarios.somenteDigitos(dto.getCpf()), getMaskCpf());
            dto.setCpf(cpf);

            if ((message = pessoaService.validarAtributos(dto, "CREATE")) != null) {
                httpStatus = HttpStatus.BAD_REQUEST;
                throw new BusinessException(message);
            }

            found = pessoaService.existsByCpf(dto.getCpf());

            if (found) {
                httpStatus = HttpStatus.FOUND;
                throw new BusinessException("CPF já cadastrado.");
            }

            if (httpStatus == null) {
                httpStatus = HttpStatus.CREATED;
            }
        } catch (Exception e) {
            if (httpStatus == null) {
                httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            }
            throw new BusinessException(e.getMessage());
        }

        return new ResponseEntity<>(dto.valueOf(pessoaService.save(dto)), httpStatus);
    }

    @PutMapping("/update")
    @ApiOperation(value = "Alteração de dados de cadastro da pessoa.")
    ResponseEntity<PessoaDTO> update(@Valid @RequestBody PessoaDTO pessoaDTO) {

        try {
            boolean error = false;
            httpStatus = null;

            dto = new PessoaDTO();
            BeanUtils.copyProperties(pessoaDTO, dto);

            cpf = Utilitarios.formatarString(Utilitarios.somenteDigitos(dto.getCpf()), getMaskCpf());
            dto.setCpf(cpf);

            if ((message = pessoaService.validarAtributos(dto, "UPDATE")) != null) {
                httpStatus = HttpStatus.BAD_REQUEST;
                throw new BusinessException(message);
            }

            if (!pessoaService.findById(dto.getId()).isPresent()) {
                httpStatus = HttpStatus.NOT_FOUND;
                throw new BusinessException("Não foi encontrado cadastro da pessoa informada.");
            }

            lista = pessoaService.findByCpf(dto.getCpf());
            if (lista != null) {
                for(PessoaDTO pessoas : lista) {
                    if (pessoas.getCpf().equals(dto.getCpf()) && !pessoas.getId().equals(dto.getId())) {
                        error = true;
                        break;
                    }
                }

                if (error) {
                    httpStatus = HttpStatus.FOUND;
                    throw new BusinessException("CPF já cadastrado.");
                }
            }

            if (httpStatus == null) {
                httpStatus = HttpStatus.OK;
            }

        } catch (Exception e) {
            if (httpStatus == null) {
                httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            }
            throw new BusinessException(e.getMessage());
        }

        return new ResponseEntity<>(dto.valueOf(pessoaService.save(dto)), httpStatus);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value="Exclusão de cadastro de pessoa.")
    ResponseEntity<String> delete(@Valid @PathVariable Long id) {

        try {
            httpStatus = null;
            message = "Cadastro de pessoa excluido com sucesso.";

            if (!pessoaService.findById(id).isPresent()) {
                httpStatus = HttpStatus.NOT_FOUND;
                message = "Não foi encontrado cadastro da pessoa informada.";
                throw new BusinessException(message);
            } else {
                httpStatus = HttpStatus.OK;
                pessoaService.delete(id);
            }
        } catch (Exception e) {
            if (httpStatus == null) {
                httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            }
            message = e.getMessage();
            throw new BusinessException(message);
        }

        return new ResponseEntity<>(message, httpStatus);
    }

}