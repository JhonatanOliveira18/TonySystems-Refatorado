package tonysystems.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import tonysystems.model.Material;
import tonysystems.model.UsoMaterial;

public class TesteFinalizacaoOS {

    public static void main(String[] args) {

        Material capacitor = new Material();
        capacitor.setPreco(12.00);
        capacitor.setQtdEstoque(10);

        Material fusivel = new Material();
        fusivel.setPreco(5.00);

        UsoMaterial uso1 = new UsoMaterial();
        uso1.setMaterial(capacitor);
        uso1.setQtdUsada(2);

        UsoMaterial uso2 = new UsoMaterial();
        uso2.setMaterial(fusivel);
        uso2.setQtdUsada(3);

        List<UsoMaterial> itens = new ArrayList<>();
        itens.add(uso1);
        itens.add(uso2);

        FinalizacaoOSService service
                = new FinalizacaoOSService();

        double total = service.calcularTotal(itens);

        System.out.println("Total calculado: R$ " + total);

        if (Math.abs(total - 39.00) > 0.001) {
            throw new AssertionError(
                    "Falha no teste: total esperado R$ 39,00, mas foi R$ " + total
            );
        }

        System.out.println("TESTE DO TOTAL: APROVADO!");

        System.out.println("Teste de estoque:");

        System.out.println("Quantidade disponível: "
                + service.temEstoqueSuficiente(capacitor, 2));

        System.out.println("Quantidade insuficiente: "
                + service.temEstoqueSuficiente(capacitor, 20));

        System.out.println("Material inexistente: "
                + service.temEstoqueSuficiente(null, 1));

        System.out.println("Quantidade inválida: "
                + service.temEstoqueSuficiente(capacitor, 0));

        if (!service.temEstoqueSuficiente(capacitor, 2)
                || service.temEstoqueSuficiente(capacitor, 20)
                || service.temEstoqueSuficiente(null, 1)
                || service.temEstoqueSuficiente(capacitor, 0)) {

            throw new AssertionError(
                    "Falha no teste de validação de estoque!"
            );
        }

        System.out.println("TESTE DE ESTOQUE: APROVADO!");

        capacitor.setId(5);
        fusivel.setId(10);

        UsoMaterial uso3 = new UsoMaterial();
        uso3.setMaterial(capacitor);
        uso3.setQtdUsada(4);

        itens.add(uso3);

        Map<Integer, Integer> consumo
                = service.agruparMateriais(itens);

        System.out.println("Agrupamento de materiais:");

        System.out.println("Capacitores: "
                + consumo.get(5));

        System.out.println("Fusíveis: "
                + consumo.get(10));

        if (!Integer.valueOf(6).equals(consumo.get(5))
                || !Integer.valueOf(3).equals(consumo.get(10))) {

            throw new AssertionError(
                    "Falha no teste de agrupamento de materiais!"
            );
        }

        System.out.println("TESTE DE AGRUPAMENTO: APROVADO!");

        // Teste do cálculo do estoque restante
        Material materialTeste = new Material();

        materialTeste.setQtdEstoque(10);

        FinalizacaoOSService serviceEstoque = new FinalizacaoOSService();

        int restante = serviceEstoque.calcularEstoqueRestante(materialTeste, 3);

        System.out.println("Estoque restante: " + restante);
        System.out.println("Estoque original: " + materialTeste.getQtdEstoque());

        if (restante != 7) {
            throw new AssertionError(
                    "Falha no teste: estoque restante deveria ser 7!"
            );
        }

        if (materialTeste.getQtdEstoque() != 10) {
            throw new AssertionError(
                    "Falha no teste: o estoque original foi alterado!"
            );
        }

        System.out.println("TESTE DE ESTOQUE RESTANTE: APROVADO!");
    }
}
