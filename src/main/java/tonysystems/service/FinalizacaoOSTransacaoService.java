package tonysystems.service;

import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import tonysystems.model.FormaPagamento;
import tonysystems.model.Manutencao;
import tonysystems.model.Material;
import tonysystems.model.Pagamento;
import tonysystems.model.StatusOS;
import tonysystems.model.UsoMaterial;
import tonysystems.util.JPAUtil;

public class FinalizacaoOSTransacaoService {

    public void finalizarOS(
            Manutencao manutencao,
            List<UsoMaterial> itens,
            FormaPagamento formaPagamento) {

        if (manutencao == null || manutencao.getId() == null) {
            throw new IllegalArgumentException(
                    "Manutenção inválida!");
        }

        if (itens == null || itens.isEmpty()) {
            throw new IllegalArgumentException(
                    "Adicione pelo menos um material!");
        }

        if (formaPagamento == null) {
            throw new IllegalArgumentException(
                    "Selecione uma forma de pagamento!");
        }

        EntityManager em = JPAUtil.getEntityManager();

        try {
            em.getTransaction().begin();

            Manutencao manutencaoBanco = em.find(
                    Manutencao.class,
                    manutencao.getId()
            );

            if (manutencaoBanco == null) {
                throw new IllegalArgumentException(
                        "Ordem de serviço não encontrada!");
            }

            if (manutencaoBanco.getStatusOS() == StatusOS.CONCLUIDO) {
                throw new IllegalStateException(
                        "Esta ordem de serviço já foi concluída!");
            }

            FinalizacaoOSService service = new FinalizacaoOSService();

            Map<Integer, Integer> consumo = service.agruparMateriais(itens);

            for (Map.Entry<Integer, Integer> entrada : consumo.entrySet()) {

                Integer idMaterial = entrada.getKey();
                int quantidadeTotal = entrada.getValue();

                Material materialBanco = em.find(
                        Material.class,
                        idMaterial
                );

                if (materialBanco == null) {
                    throw new IllegalArgumentException(
                            "Material não encontrado! ID: " + idMaterial);
                }

                if (!service.temEstoqueSuficiente(
                        materialBanco, quantidadeTotal)) {

                    throw new IllegalArgumentException(
                            "Estoque insuficiente ou quantidade inválida para: "
                            + materialBanco.getNome());
                }
            }

            for (Map.Entry<Integer, Integer> entrada : consumo.entrySet()) {

                Integer idMaterial = entrada.getKey();
                int quantidadeTotal = entrada.getValue();

                Material materialBanco = em.find(
                        Material.class,
                        idMaterial
                );

                int estoqueRestante = service.calcularEstoqueRestante(
                        materialBanco,
                        quantidadeTotal
                );

                materialBanco.setQtdEstoque(estoqueRestante);
            }

            for (Map.Entry<Integer, Integer> entrada : consumo.entrySet()) {

                Integer idMaterial = entrada.getKey();
                int quantidadeTotal = entrada.getValue();

                Material materialBanco = em.find(
                        Material.class,
                        idMaterial
                );

                UsoMaterial uso = new UsoMaterial(
                        manutencaoBanco,
                        materialBanco,
                        quantidadeTotal
                );

                em.persist(uso);
            }

            double total = service.calcularTotal(itens);

            Pagamento pagamento = new Pagamento();

            pagamento.setValor(total);
            pagamento.setData(LocalDate.now());
            pagamento.setFormaPagamento(formaPagamento);
            pagamento.setManutencao(manutencaoBanco);

            em.persist(pagamento);
            
            manutencaoBanco.setStatusOS(StatusOS.CONCLUIDO);

            em.getTransaction().commit();

        } catch (Exception e) {

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            throw new RuntimeException(
                    "Erro ao finalizar a ordem de serviço.", e);

        } finally {
            em.close();
        }
    }
}
