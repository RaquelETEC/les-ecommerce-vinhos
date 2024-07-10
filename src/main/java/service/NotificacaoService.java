package service;

import java.util.ArrayList;

import dao.DaoCupons;
import model.entity.Cliente;
import model.entity.Cupons;
import model.entity.Notificacoes;

public class NotificacaoService {

	static DaoCupons daoCupom = new DaoCupons();

	public NotificacaoService() {
		new Notificacoes();
	}

	public ArrayList<Cupons> listarNotificações(Cliente cliente) {
		return daoCupom.ListarCupons(cliente);
	}

	public static String gerarNotificacao(Notificacoes notificacao) {
		return daoCupom.gerarNotificacao(notificacao);
	}
}
