package br.com.alura.forumhub.controller;

import br.com.alura.forumhub.dto.DadosLogin;
import br.com.alura.forumhub.model.Usuario;
import br.com.alura.forumhub.repository.UsuarioRepository;
import br.com.alura.forumhub.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public String login(@RequestBody DadosLogin dados){

        Usuario usuario = usuarioRepository.findByLogin(dados.login());

        if(usuario == null){
            throw new RuntimeException("Usuário não encontrado");
        }

        if(!usuario.getSenha().equals(dados.senha())){
            throw new RuntimeException("Senha inválida");
        }

        return tokenService.gerarToken(usuario.getLogin());
    }

}