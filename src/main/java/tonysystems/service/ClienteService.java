package tonysystems.service;

import tonysystems.dao.ClienteDAO;
import tonysystems.model.Cliente;

public class ClienteService {

    private final ClienteDAO dao = new ClienteDAO();

    public void cadastrar(String nome, String cpf,
            String telefone, String endereco) {

        validarDados(nome, cpf, telefone, endereco);

        if (dao.buscarPorCpf(cpf.trim()) != null) {
            throw new IllegalArgumentException(
                    "Já existe um cliente cadastrado com este CPF!"
            );
        }

        Cliente cliente = new Cliente();
        cliente.setNome(nome.trim());
        cliente.setCpf(cpf.trim());
        cliente.setTelefone(telefone.trim());
        cliente.setEndereco(endereco.trim());

        dao.cadastrar(cliente);
    }

    public void atualizar(Cliente cliente, String nome,
            String telefone, String endereco) {

        if (cliente == null || cliente.getId() == null) {
            throw new IllegalArgumentException(
                    "Nenhum cliente selecionado para edição!"
            );
        }

        validarDados(nome, cliente.getCpf(), telefone, endereco);

        cliente.setNome(nome.trim());
        cliente.setTelefone(telefone.trim());
        cliente.setEndereco(endereco.trim());

        dao.atualizar(cliente);
    }

    private void validarDados(String nome, String cpf,
            String telefone, String endereco) {

        if (nome == null || nome.isBlank()
                || endereco == null || endereco.isBlank()) {
            throw new IllegalArgumentException(
                    "Preencha todos os campos obrigatórios!"
            );
        }

        if (cpf == null || cpf.replaceAll("\\D", "").length() != 11) {
            throw new IllegalArgumentException(
                    "Informe um CPF com 11 dígitos!"
            );
        }

        if (telefone == null
                || telefone.replaceAll("\\D", "").length() != 11) {
            throw new IllegalArgumentException(
                    "Informe um celular com DDD e 11 dígitos!"
            );
        }
    }

    public void excluir(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException(
                    "Selecione um cliente válido para excluir!"
            );
        }

        dao.excluir(id);
    }
}
