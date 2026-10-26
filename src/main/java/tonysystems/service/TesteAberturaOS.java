package tonysystems.service;

import java.time.LocalDate;
import tonysystems.model.Televisao;

public class TesteAberturaOS {

    public static void main(String[] args) {

        AberturaOSService service = new AberturaOSService();

        verificarErro(
                () -> service.abrirOS(null, LocalDate.now(), "TV não liga"),
                "TV obrigatória"
        );

        verificarErro(
                () -> service.abrirOS(new Televisao(), null, "TV não liga"),
                "Data obrigatória"
        );

        verificarErro(
                () -> service.abrirOS(new Televisao(), LocalDate.now(), null),
                "Problema obrigatório (null)"
        );

        verificarErro(
                () -> service.abrirOS(new Televisao(), LocalDate.now(), "   "),
                "Problema obrigatório (vazio)"
        );

        System.out.println("TODOS OS TESTES DE ABERTURA FORAM APROVADOS!");
    }

    private static void verificarErro(Runnable operacao, String nomeTeste) {

        try {
            operacao.run();
        } catch (IllegalArgumentException e) {
            System.out.println("APROVADO: " + nomeTeste);
            return;
        }

        throw new AssertionError("FALHOU: " + nomeTeste);
    }
}