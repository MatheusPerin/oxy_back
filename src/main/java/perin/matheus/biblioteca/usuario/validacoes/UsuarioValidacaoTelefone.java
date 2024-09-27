package perin.matheus.biblioteca.usuario.validacoes;

import org.springframework.stereotype.Component;
import perin.matheus.biblioteca.base.listener.validacao.Validacao;
import perin.matheus.biblioteca.base.listener.validacao.ValidacaoException;
import perin.matheus.biblioteca.usuario.UsuarioEntity;

import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

@Component
public class UsuarioValidacaoTelefone implements Validacao<UsuarioEntity> {

    public static String MENSAGEM_BASE = "Telefone inválido por: %s";

    public static List<String> DDD = List.of(
        //Acre (AC):
        "68",
        //Amapá (AP):
        "96",
        //Amazonas (AM):
        "92", "97",
        //Pará (PA):
        "91", "93", "94",
        //Rondônia (RO):
        "69",
        //Roraima (RR):
        "95",
        //Tocantins (TO):
        "63",
        //Alagoas (AL):
        "82",
        //Bahia (BA):
        "71", "73", "74", "75", "77",
        //Ceará (CE):
        "85", "88",
        //Maranhão (MA):
        "98", "99",
        //Paraíba (PB):
        "83",
        //Pernambuco (PE):
        "81", "87",
        //Piauí (PI):
        "86", "89",
        //Rio Grande do Norte (RN):
        "84",
        //Sergipe (SE):
        "79",
        //Distrito Federal (DF):
        "61",
        //Goiás (GO):
        "62", "64",
        //Mato Grosso (MT):
        "65", "66",
        //Mato Grosso do Sul (MS):
        "67",
        //Espírito Santo (ES):
        "27", "28",
        //Minas Gerais (MG):
        "31", "32", "33", "34", "35", "37", "38",
        //Rio de Janeiro (RJ):
        "21", "22", "24",
        //São Paulo (SP):
        "11", "12", "13", "14", "15", "16", "17", "18", "19",
        //Paraná (PR):
        "41", "42", "43", "44", "45", "46",
        //Rio Grande do Sul (RS):
        "51", "53", "54", "55",
        //Santa Catarina (SC):
        "47", "48", "49"
    );

    @Override
    public void validar(UsuarioEntity entity) throws ValidacaoException {
        if (!entity.getTelefone().matches("\\d+"))
            throw new ValidacaoException(String.format(MENSAGEM_BASE, "Deve ser composto somente por caracteres numérico"));

        if (!List.of(10, 11).contains(entity.getTelefone().length()))
            throw new ValidacaoException(String.format(MENSAGEM_BASE, "Deve conter 10 ou 11 caracteres"));

        validarComposicao(entity);
        validarDdd(entity);
    }

    private void validarDdd(UsuarioEntity entity) throws ValidacaoException {
        final String ddd = entity.getTelefone().substring(0, 2);

        if (!DDD.contains(ddd))
            throw new ValidacaoException(String.format(MENSAGEM_BASE, "O DDD (" + ddd + ") está inválido para o Basil"));
    }
    
    private void validarComposicao(UsuarioEntity entity) throws ValidacaoException {
        final char primeiroDigito = entity.getTelefone().charAt(0);
        
        for (char digito: entity.getTelefone().toCharArray()) {
            if (!Objects.equals(primeiroDigito, digito)) return;
        }

        throw new ValidacaoException(String.format(MENSAGEM_BASE, "O tenelefone não pode ser composto somente pelo digito: " + primeiroDigito));
    }

}
