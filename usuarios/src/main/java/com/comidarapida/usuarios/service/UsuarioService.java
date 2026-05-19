package com.comidarapida.usuarios.service;


import com.comidarapida.usuarios.dto.UsuarioDTO;
import com.comidarapida.usuarios.model.Usuario;
import com.comidarapida.usuarios.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioDTO crearUsuario(Usuario usuario){
        Usuario guardado = usuarioRepository.save(usuario);
        return convertirADto(guardado);
    }

    public List<UsuarioDTO> obtenerTodos(){
        return usuarioRepository.findAll()
                .stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

    public Optional<UsuarioDTO>buscarPorId(Long id){
        return usuarioRepository.findById(id)
                .map(this::convertirADto);
    }

    public Optional<Usuario>buscarPorEmail(String email){
        return usuarioRepository.findByEmail(email);
    }

    private UsuarioDTO convertirADto(Usuario usuario){
        return new UsuarioDTO(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getRol()
        );
    }





}
