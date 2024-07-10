package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import model.entity.CarrinhoItens;
import model.entity.CartaoDeCredito;
import model.entity.Cliente;
import model.entity.Cupons;
import model.entity.PedidoItens;
import model.entity.PedidoVenda;
import model.entity.Produtos;
import model.entity.TiposStatusItensPedido;
import model.entity.Troca;

public class DAOPedidoVenda {

	public ArrayList<PedidoVenda> ListarPedidos(Cliente cliente, String statusPedido, int statusItem) {
		ArrayList<PedidoVenda> listaDePedidos = new ArrayList<>();

		// Consulta SQL inicial sem a cláusula AND opcional
		String sql = "SELECT " + "pv.ven_id, " + "pv.vend_id_cliente, " + "pv.ven_status, " + "pv.ven_data, "
				+ "pv.ven_total_pedido, " + "pi.ped_item_id, " + "pi.ped_item_prod_id, " + "pi.ped_item_prod_desc, "
				+ "pi.ped_item_prod_quantidade, " + "pi.ped_item_prod_valor, " + "pi.ped_item_prod_valor_total, "
				+ "pi.ped_item_status_troca, " + "pi.ped_item_quant_troca, " + "p.pro_img, p.pro_codigo_barra "
				+ "FROM pedido_venda pv " + "LEFT JOIN pedido_itens pi ON pv.ven_id = pi.ped_item_ven_id "
				+ "LEFT JOIN produto p ON pi.ped_item_prod_id = p.pro_id " + "WHERE 1=1";

		if (statusPedido != null && !statusPedido.isEmpty()) {
			sql += " AND pv.ven_status = ? ";
		}
		if (statusItem > 0) {
			sql += " AND pi.ped_item_status_troca <> 0 ";
		}
		if (cliente != null && cliente.getId() != 0) {
			sql += " AND pv.vend_id_cliente = ? ";
		}
		try {
			Connection con = Conexao.conectar();
			PreparedStatement pstmt = con.prepareStatement(sql);

			int paramIndex = 1;
			if (statusPedido != null && !statusPedido.isEmpty()) {
				pstmt.setString(paramIndex++, statusPedido);
			}
			if (cliente != null && cliente.getId() > 0) {
				pstmt.setInt(paramIndex++, cliente.getId());
			}
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int idPedido = rs.getInt("ven_id");
				PedidoVenda pedido = new PedidoVenda();
				pedido.setId(idPedido);
				pedido.setCliente(new DaoCliente().buscarClientePorId(rs.getInt("vend_id_cliente")));
				pedido.setStatus(rs.getString("ven_status"));
				pedido.setData(rs.getDate("ven_data"));
				pedido.setTotalPedido(rs.getDouble("ven_total_pedido"));

				// Verificar se o pedido não está na lista
				PedidoVenda pedidoExistente = listaDePedidos.stream().filter(p -> p.getId() == idPedido).findFirst()
						.orElse(null);

				if (pedidoExistente == null) {
					// Se o pedido ainda não foi adicionado à lista, adicione-o
					listaDePedidos.add(pedido);
				}

				// Adicionar o item do pedido, se houver, à lista de itens do pedido
				if (rs.getInt("ped_item_prod_id") != 0) {
					// Consulta SQL para obter a troca mais recente para este item
					String sqlTrocas = "SELECT troca_id, item_id, quantidade_solicitada, status "
							+ "FROM trocas WHERE item_id = ? ORDER BY troca_id DESC LIMIT 1";
					PreparedStatement pstmtTrocas = con.prepareStatement(sqlTrocas);
					pstmtTrocas.setInt(1, rs.getInt("pi.ped_item_id"));
					ResultSet rsTrocas = pstmtTrocas.executeQuery();

					Troca troca = null;
					if (rsTrocas.next()) {
						troca = new Troca();
						troca.setId(rsTrocas.getInt("troca_id"));
						troca.setItemId(rsTrocas.getInt("item_id"));
						troca.setQuantidadeSolicitada(rsTrocas.getInt("quantidade_solicitada"));
						troca.setStatus(TiposStatusItensPedido.values()[rsTrocas.getInt("status")]);
					}

					rsTrocas.close();
					pstmtTrocas.close();

					Produtos produto = new Produtos();
					PedidoItens item = new PedidoItens();
					item.setId(rs.getInt("pi.ped_item_id"));
					item.setDescricao(rs.getString("ped_item_prod_desc"));
					item.setQuantidade(rs.getInt("ped_item_prod_quantidade"));
					item.setPreco(rs.getDouble("ped_item_prod_valor"));
					item.setTotalProduto(rs.getDouble("ped_item_prod_valor_total"));
					item.setTipos(TiposStatusItensPedido.values()[rs.getInt("ped_item_status_troca")]);
					item.setQuantidadeTrocada(rs.getInt("ped_item_quant_troca"));

					produto.setId(rs.getInt("ped_item_prod_id"));
					produto.setImg(rs.getString("pro_img"));
					produto.setCodigo_barra(rs.getString("p.pro_codigo_barra"));

					item.setProduto(produto);
					item.setTroca(troca);

					if (pedidoExistente == null) {
						// Se o pedido não existir na lista, associe o item ao novo pedido
						pedido.getPedidoItens().add(item);
					} else {
						// Se o pedido já existir na lista, associe o item ao pedido existente
						pedidoExistente.getPedidoItens().add(item);
					}
				}
			}

			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Erro ao listar pedidos: " + e);
		}

		return listaDePedidos;
	}

	public PedidoVenda selecionarPedidos(PedidoVenda pedidovenda) {
		String read2 = "select ven_total_pedido,ven_status from pedido_venda where ven_id = ?";
		try {
			Connection con = Conexao.conectar();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setInt(1, pedidovenda.getId());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				pedidovenda.setStatus(rs.getString(1));
			}
			// Fechar os recursos
			rs.close();
			pst.close();
			con.close();
		} catch (Exception e) {
			System.out.println("ERRO AO SELECIONAR" + e);
		}

		return pedidovenda;
	}

	public String EditarPedido(PedidoVenda pedidovenda) {
		String update = "update pedido_venda set " + "ven_status = ? " + "WHERE ven_id = ?;";
		try {
			Connection con = Conexao.conectar();

			PreparedStatement pst = con.prepareStatement(update);
			pst.setString(1, pedidovenda.getStatus());
			pst.setInt(2, pedidovenda.getId());
			pst.executeUpdate();
			pst.close();
			con.close();

			System.err.println("Atualizado o pedido no dao!!" + pedidovenda.getId());
			return "SUCESS";

		} catch (Exception e) {

			System.out.println("error ao atualizar o pedido: " + e);
			return e + "";

		}
	}

	public String CadastrarPedidoDao(PedidoVenda pedido, ArrayList<CarrinhoItens> itens, ArrayList<Cupons> listaCupons,
			ArrayList<CartaoDeCredito> listaCartoes) {
		System.out.println("Dao cadastrar pedido");
		String sqlPedido = "INSERT INTO pedido_venda (vend_id_cliente, ven_end_id , ven_status, ven_data,ven_total_pedido,ven_total_desconto,ven_total_frete,ven_total_pagamento, ven_saldo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		String sqlItem = "INSERT INTO pedido_itens (ped_item_ven_id, ped_item_prod_id, ped_item_prod_desc, ped_item_prod_quantidade, ped_item_prod_valor, ped_item_prod_valor_total, ped_item_status_troca) VALUES (?, ?, ?, ?, ?, ?, ?)";
		String sqlCuponsPedido = "INSERT INTO pedido_cupons (pedido_id, cupom_id) VALUES (?, ?)";
		String sqlCartoesPedido = "INSERT INTO pedido_cartoes (pedido_id, cartao_id, valor) VALUES (?, ?, ?)";

		try (Connection con = Conexao.conectar();
				PreparedStatement pstmtPedido = con.prepareStatement(sqlPedido,
						PreparedStatement.RETURN_GENERATED_KEYS);
				PreparedStatement pstmtItem = con.prepareStatement(sqlItem);
				PreparedStatement pstmtCuponsPedido = con.prepareStatement(sqlCuponsPedido);
				PreparedStatement pstmtCartoesPedido = con.prepareStatement(sqlCartoesPedido)) {

			con.setAutoCommit(false);

			// Inserir o pedido
			pstmtPedido.setInt(1, pedido.getCliente().getId());
			pstmtPedido.setInt(2, pedido.getEndereco().getId());
			pstmtPedido.setString(3, pedido.getStatus());
			pstmtPedido.setDate(4, new java.sql.Date(pedido.getData().getTime()));
			pstmtPedido.setDouble(5, pedido.getTotalPedido());
			pstmtPedido.setDouble(6, pedido.getTotalDesconto());
			pstmtPedido.setDouble(7, pedido.getTotalFrete());
			pstmtPedido.setDouble(8, pedido.getTotalPagamento());
			pstmtPedido.setDouble(9, pedido.getTotalSaldo());

			pstmtPedido.executeUpdate();

			// Obter o ID do pedido gerado
			ResultSet generatedKeys = pstmtPedido.getGeneratedKeys();
			int idPedidoGerado = -1;
			if (generatedKeys.next()) {
				idPedidoGerado = generatedKeys.getInt(1);
				pedido.setId(idPedidoGerado);
			}

			// Inserir os itens do pedido
			if (idPedidoGerado != -1) {
				for (CarrinhoItens item : itens) {
					setParametrosItemPedido(pstmtItem, idPedidoGerado, item);
					pstmtItem.addBatch(); // Adicionar a instrução ao batch
				}
				pstmtItem.executeBatch();

				// Inserir os cupons do pedido
				for (Cupons cupom : listaCupons) {
					setParametrosCuponsPedido(pstmtCuponsPedido, idPedidoGerado, cupom);
					pstmtCuponsPedido.addBatch();
				}
				pstmtCuponsPedido.executeBatch();

				// Inserir os cartões do pedido
				for (CartaoDeCredito cartao : listaCartoes) {
					setParametrosCartoesPedido(pstmtCartoesPedido, idPedidoGerado, cartao);
					pstmtCartoesPedido.addBatch();
				}
				pstmtCartoesPedido.executeBatch();
			}

			con.commit();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return "Erro ao cadastrar pedido: " + e.getMessage();
		}

		return "?id=" + pedido.getCliente().getId() + "&idPedido=" + pedido.getId();
	}

	private void setParametrosItemPedido(PreparedStatement pstmtItem, int idPedido, CarrinhoItens item)
			throws SQLException {
		System.out.println("Configurando parâmetros do item do pedido");

		double valorTotal = item.getProduto().getPro_preco_venda() * item.getQuantProd();

		pstmtItem.setInt(1, idPedido);
		pstmtItem.setInt(2, item.getProduto().getId());
		pstmtItem.setString(3, item.getProduto().getDesc());
		pstmtItem.setDouble(4, item.getQuantProd());
		pstmtItem.setDouble(5, item.getProduto().getPro_preco_venda());
		pstmtItem.setDouble(6, valorTotal);
		pstmtItem.setInt(7, TiposStatusItensPedido.TROCA_NAO_SOLICITADA.ordinal());
	}

	private void setParametrosCuponsPedido(PreparedStatement pstm, int idPedido, Cupons cupom) throws SQLException {
		System.out.println("Configurando parametros do cupom do pedido");
		pstm.setInt(1, idPedido);
		pstm.setInt(2, cupom.getId());
	}

	private void setParametrosCartoesPedido(PreparedStatement pstm, int idPedido, CartaoDeCredito cartao)
			throws SQLException {
		System.out.println("Configurando parametros do cartao do pedido");
		pstm.setInt(1, idPedido);
		pstm.setInt(2, cartao.getId());
		pstm.setDouble(3, cartao.getValor());
	}

}