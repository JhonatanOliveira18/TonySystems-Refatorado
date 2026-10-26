package tonysystems.service;

import tonysystems.dao.TelevisaoDAO;
import tonysystems.model.Cliente;
import tonysystems.model.Televisao;

public class TelevisaoService {

    public void cadastrar(Cliente cliente, String marca, String modelo) {

        if (cliente == null || cliente.getId() == null) {
            throw new IllegalArgumentException(
                    "Selecione um cliente válido!"
            );
        }

        if (marca == null || marca.isBlank()
                || modelo == null || modelo.isBlank()) {
            throw new IllegalArgumentException(
                    "Preencha a marca e o modelo da televisão!"
            );
        }

        Televisao tv = new Televisao();
        tv.setCliente(cliente);
        tv.setMarca(marca.trim());
        tv.setModelo(modelo.trim());

        TelevisaoDAO dao = new TelevisaoDAO();
        dao.cadastrar(tv);
    }
}