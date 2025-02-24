package io.github.gabrielmatosprogramador.projetoJao.controllers;

import io.github.gabrielmatosprogramador.projetoJao.entities.Cliente;
import io.github.gabrielmatosprogramador.projetoJao.entities.updateEmailSup;
import io.github.gabrielmatosprogramador.projetoJao.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    ClienteRepository repository;

    @GetMapping
    public List<Cliente> findAll(){
        List<Cliente> result = repository.findAll();
        return result;
    }

    @GetMapping(value = "/{cpf}")
    public Cliente findByCpf(@PathVariable String cpf) {
        return repository.findById(cpf)
                .orElseThrow(() -> new IllegalArgumentException("Cliente Não encontrado com o CPF: " + cpf));
    }

    @PostMapping
    public void saveClient(@RequestBody Cliente cliente){
        if(verifyCpf(cliente.getCpf())){
            throw new IllegalArgumentException("CPF Já consta na base de dados");
        } else {
            repository.save(cliente);
        }
    }

    @DeleteMapping(value = "/{cpf}")
    public void deleteClient(@PathVariable String cpf){
        repository.deleteById((cpf));
    }

    @PutMapping(value = "/{cpf}/email")
    public ResponseEntity<?> updateEmail(@PathVariable String cpf, @RequestBody updateEmailSup updateEmailDTO) {
        // Busca o cliente pelo CPF
        Cliente cliente = repository.findById(cpf)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado com o CPF: " + cpf));

        // Atualiza o e-mail usando o valor do DTO
        cliente.setEmail(updateEmailSup.getEmail());

        // Salva as alterações
        repository.save(cliente);

        return ResponseEntity.ok("E-mail atualizado com sucesso para o CPF: " + cpf);
    }

    public boolean verifyCpf (String cpf){
        return repository.findById((cpf)).isPresent();
    }
}


