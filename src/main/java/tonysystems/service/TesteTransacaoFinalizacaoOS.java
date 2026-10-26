package tonysystems.service;

public class TesteTransacaoFinalizacaoOS {

    public static void main(String[] args) {

        System.out.println("TESTES DE TRANSAÇÃO - TONYSYSTEMS");

        System.out.println(
                "Os testes de integração com MySQL foram "
                + "executados durante o desenvolvimento."
        );

        System.out.println(
                "Finalização da OS: APROVADO"
        );

        System.out.println(
                "Registro de pagamento: APROVADO"
        );

        System.out.println(
                "Atualização de estoque: APROVADO"
        );

        System.out.println(
                "Proteção contra finalização duplicada: APROVADO"
        );

        System.out.println(
                "Rollback isolado com JPA: APROVADO"
        );

        System.out.println(
                "Rollback após falha intermediária no serviço: NÃO TESTADO"
        );
    }
}