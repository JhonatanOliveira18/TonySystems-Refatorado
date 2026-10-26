package tonysystems.service;

import java.time.LocalDate;
import tonysystems.dao.ManutencaoDAO;
import tonysystems.model.Manutencao;
import tonysystems.model.StatusOS;
import tonysystems.model.Televisao;

public class AberturaOSService {

    public void abrirOS(Televisao televisao,
                        LocalDate dataInicio,
                        String problema) {

        if (televisao == null) {
            throw new IllegalArgumentException(
                    "Selecione uma TV!"
            );
        }

        if (dataInicio == null) {
            throw new IllegalArgumentException(
                    "Informe a data de início!"
            );
        }

        if (problema == null || problema.isBlank()) {
            throw new IllegalArgumentException(
                    "Informe o problema relatado!"
            );
        }

        Manutencao manutencao = new Manutencao();

        manutencao.setTelevisao(televisao);
        manutencao.setDataInicio(dataInicio);
        manutencao.setProblema(problema.trim());
        manutencao.setStatusOS(StatusOS.PENDENTE);

        ManutencaoDAO dao = new ManutencaoDAO();
        dao.cadastrar(manutencao);
    }
}