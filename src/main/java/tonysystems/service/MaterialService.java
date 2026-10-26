package tonysystems.service;

import tonysystems.dao.MaterialDAO;
import tonysystems.model.Material;

public class MaterialService {

    public void cadastrar(String nome, String marca,
            String categoria, String precoTexto,
            String quantidadeTexto) {

        if (nome == null || nome.isBlank()
                || marca == null || marca.isBlank()
                || categoria == null || categoria.isBlank()
                || precoTexto == null || precoTexto.isBlank()
                || quantidadeTexto == null || quantidadeTexto.isBlank()) {

            throw new IllegalArgumentException(
                    "Preencha todos os campos!"
            );
        }

        double preco;
        int quantidade;

        try {
            preco = Double.parseDouble(
                    precoTexto.trim().replace(",", ".")
            );

            quantidade = Integer.parseInt(
                    quantidadeTexto.trim()
            );

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    "Informe um preço e uma quantidade válidos!"
            );
        }

        if (!Double.isFinite(preco) || preco < 0) {
            throw new IllegalArgumentException(
                    "O preço deve ser um número válido e não negativo!"
            );
        }

        if (quantidade < 0) {
            throw new IllegalArgumentException(
                    "A quantidade não pode ser negativa!"
            );
        }

        Material material = new Material();

        material.setNome(nome.trim());
        material.setMarca(marca.trim());
        material.setCategoria(categoria.trim());
        material.setPreco(preco);
        material.setQtdEstoque(quantidade);

        MaterialDAO dao = new MaterialDAO();
        dao.cadastrar(material);
    }

    public void excluir(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException(
                    "Selecione um material válido para excluir!"
            );
        }

        MaterialDAO dao = new MaterialDAO();
        dao.excluir(id);
    }
}
