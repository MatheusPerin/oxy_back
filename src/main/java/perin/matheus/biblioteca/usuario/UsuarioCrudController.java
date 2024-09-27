package perin.matheus.biblioteca.usuario;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import perin.matheus.biblioteca.base.controller.CrudController;

/**
 * Controlador para operações relacionadas a usuários
 */
@RestController
@RequestMapping("/usuario")
public class UsuarioCrudController extends CrudController<UsuarioEntity, Long> {

}
