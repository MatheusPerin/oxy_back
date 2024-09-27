package perin.matheus.biblioteca.emprestimo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import perin.matheus.biblioteca.base.controller.CrudController;

/**
 * Controlador para operações relacionadas a empréstimos
 */
@RestController
@RequestMapping("/emprestimo")
public class EmprestimoCrudController extends CrudController<EmprestimoEntity, Long> {

}
