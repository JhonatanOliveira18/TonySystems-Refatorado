package tonysystems.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import tonysystems.model.Material;
import tonysystems.model.UsoMaterial;

public class FinalizacaoOSService {

    public double calcularTotal(List<UsoMaterial> itens) {

        double total = 0;

        for (UsoMaterial uso : itens) {
            total += uso.getMaterial().getPreco()
                    * uso.getQtdUsada();
        }

        return total;
    }

    public boolean temEstoqueSuficiente(
            Material material, int quantidadeSolicitada) {

        if (material == null) {
            return false;
        }

        return quantidadeSolicitada > 0
                && material.getQtdEstoque() >= quantidadeSolicitada;
    }

    public Map<Integer, Integer> agruparMateriais(
            List<UsoMaterial> itens) {

        Map<Integer, Integer> consumo = new HashMap<>();

        for (UsoMaterial uso : itens) {

            int id = uso.getMaterial().getId();

            consumo.put(id,
                    consumo.getOrDefault(id, 0)
                    + uso.getQtdUsada());
        }

        return consumo;
    }

    public int calcularEstoqueRestante(Material material, int quantidadeUtilizada) {

        if (!temEstoqueSuficiente(material, quantidadeUtilizada)) {
            throw new IllegalArgumentException(
                    "Estoque insuficiente ou quantidade inválida!");
        }

        return material.getQtdEstoque() - quantidadeUtilizada;
    }

}
