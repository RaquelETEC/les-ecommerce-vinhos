package Dao;

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
import model.entity.Cliente;
import model.entity.Cupons;
import model.entity.PedidoItens;
import model.entity.PedidoVenda;
import model.entity.Produtos;
import model.entity.TiposStatusItensPedido;


public class DAOPedidoVenda {
	
	public ArrayList<PedidoVenda> ListarPedidos(Cliente cliente, String statusPedido, int statusItem) {
	    ArrayList<PedidoVenda> listaDePedidos = new ArrayList<>();

	    // Consulta SQL inicial sem a cláusula AND opcional
	    String sql = "SELECT pv.ven_id, pv.vend_id_cliente, pv.ven_status, pv.ven_data, pv.ven_valor, " +
	                 "pi.ped_item_id, pi.ped_item_prod_id, pi.ped_item_prod_desc, pi.ped_item_prod_quantidade, pi.ped_item_prod_valor, pi.ped_item_prod_valor_total, pi.ped_item_status_troca, " +
	                 "p.pro_img, p.pro_codigo_barra " +
	                 "FROM pedido_venda pv " +
	                 "LEFT JOIN pedido_itens pi ON pv.ven_id = pi.ped_item_ven_id " +
	                 "LEFT JOIN produto p ON pi.ped_item_prod_id = p.pro_id " +
	                 "WHERE 1=1";

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
	            pedido.setValor(rs.getDouble("ven_valor"));

	            // Verificar se o pedido jÃ¡ estÃ¡ na lista
	            PedidoVenda pedidoExistente = listaDePedidos.stream()
	                    .filter(p -> p.getId() == idPedido)
	                    .findFirst()
	                    .orElse(null);

	            if (pedidoExistente == null) {
	                // Se o pedido ainda nÃ£o foi adicionado Ã&nbsp; lista, adicione-o
	                listaDePedidos.add(pedido);
	            }

	            // Adicionar o item do pedido, se houver, Ã&nbsp; lista de itens do pedido
	            if (rs.getInt("ped_item_prod_id") != 0) {
	            	Produtos produto = new Produtos(); 
	            	
	                PedidoItens item = new PedidoItens();
	                item.setId(rs.getInt("pi.ped_item_id"));
	                item.setDescricao(rs.getString("ped_item_prod_desc"));
	                item.setQuantidade(rs.getInt("ped_item_prod_quantidade"));
	                item.setPreco((rs.getDouble("ped_item_prod_valor")));  
	                item.setTotalProduto(rs.getDouble("ped_item_prod_valor_total"));
	                item.setTipos(TiposStatusItensPedido.values()[rs.getInt("ped_item_status_troca")]);
	                
	                produto.setId(rs.getInt("ped_item_prod_id"));
	                produto.setImg(rs.getString("pro_img"));
	                produto.setCodigo_barra(rs.getString("p.pro_codigo_barra"));
	                item.setProduto(produto);
	                
	                
	                if (pedidoExistente == null) {
	                    // Se o pedido nÃ£o existir na lista, associe o item ao novo pedido
	                    pedido.getPedidoItens().add(item);
	                } else {
	                    // Se o pedido jÃ¡ existir na lista, associe o item ao pedido existente
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
		String read2 = "select ven_valor,ven_status from pedido_venda where ven_id = ?";
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
	
	
	public PedidoVenda EditarPedido(PedidoVenda pedidovenda) {
		String update = "update pedido_venda set " + "ven_status = ? " 
				+ "WHERE ven_id = ?;";
		try {
			Connection con = Conexao.conectar();

			PreparedStatement pst = con.prepareStatement(update);
			pst.setString(1, pedidovenda.getStatus());
			pst.setInt(2, pedidovenda.getId());
			pst.executeUpdate();
			con.close();
			
			System.err.println("Atualizado o pedido no dao!!" + pedidovenda.getId());
			return pedidovenda;

		} catch (Exception e) {
			System.out.println("error ao atualizar o pedido: " + e);
			return pedidovenda;
		}
	}


	public String CadastrarPedidoDao(PedidoVenda pedido, ArrayList<CarrinhoItens> itens) {
	    System.out.println("Dao cadastrar pedido");
	    String sqlPedido = "INSERT INTO pedido_venda (vend_id_cliente, ven_status, ven_data, ven_valor) VALUES (?, ?, ?, ?)";
	    String sqlItem = "INSERT INTO pedido_itens (ped_item_ven_id, ped_item_prod_id, ped_item_prod_desc, ped_item_prod_quantidade, ped_item_prod_valor, ped_item_prod_valor_total, ped_item_status_troca) VALUES (?, ?, ?, ?, ?, ?, ?)";

	    try (Connection con = Conexao.conectar();
	         PreparedStatement pstmtPedido = con.prepareStatement(sqlPedido, PreparedStatement.RETURN_GENERATED_KEYS);
	         PreparedStatement pstmtItem = con.prepareStatement(sqlItem)) {

	        con.setAutoCommit(false);

	        // Inserir o pedido
	        pstmtPedido.setInt(1, pedido.getCliente().getId());
	        pstmtPedido.setString(2, pedido.getStatus());
	        pstmtPedido.setDate(3, new java.sql.Date(pedido.getData().getTime()));
	        pstmtPedido.setDouble(4, pedido.getValor());
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
	                pstmtItem.addBatch(); // Adicionar a instruÃ§Ã£o ao batch
	            }

	            // Executar o batch de inserÃ§Ã£o
	            pstmtItem.executeBatch();
	        }

	        con.commit();

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return "Erro ao cadastrar pedido: " + e.getMessage();
	    }

	    return "?id=" + pedido.getCliente().getId() + "&idPedido=" + pedido.getId();
	}

	private void setParametrosItemPedido(PreparedStatement pstmtItem, int idPedido, CarrinhoItens item) throws SQLException {
	    System.out.println("Configurando parÃ¢metros do item do pedido");

	    double valorTotal = item.getProduto().getPro_preco_venda() * item.getQuantProd();

	    pstmtItem.setInt(1, idPedido);
	    pstmtItem.setInt(2, item.getProduto().getId());
	    pstmtItem.setString(3, item.getProduto().getDesc());
	    pstmtItem.setDouble(4, item.getQuantProd());
	    pstmtItem.setDouble(5, item.getProduto().getPro_preco_venda());
	    pstmtItem.setDouble(6, valorTotal);
	    pstmtItem.setInt(7, TiposStatusItensPedido.TROCA_NAO_SOLICITADA.ordinal());
	}


	/*public ArrayList<PedidoVenda> listarPedidosTodosClientes() {
		System.out.println("cheguei no dao ");
	    ArrayList<PedidoVenda> listaDePedidos = new ArrayList<>();
	    String read = "SELECT " +
	        "ven_id, " +
	        "vend_id_cliente, " +
	        "ven_status, " +
	        "ven_data, " +
	        "ven_valor " +
	        "FROM pedido_venda";
	    try {
	    	Connection con = Conexao.conectar();
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rs = pst.executeQuery();
	        while (rs.next()) {
	            PedidoVenda pedido = new PedidoVenda();
	            pedido.setId(rs.getInt("ven_id"));

	            int clienteId = rs.getInt("vend_id_cliente");
	            Cliente cliente = new DaoCliente().buscarClientePorId(clienteId); // Chamada para buscar o cliente pelo ID
	            
	            cliente.setId(clienteId);
	            pedido.setCliente(cliente);

	            pedido.setStatus(rs.getString("ven_status"));
	            pedido.setData(rs.getDate("ven_data"));
	            pedido.setValor(rs.getDouble("ven_valor"));

	            listaDePedidos.add(pedido);
	        }
	        con.close();
	    } catch (Exception e) {
	        System.out.println("Erro ao listar pedidos: " + e);
	    }
	    return listaDePedidos;
	}
	*/
	
	 public String editarStatusItem(PedidoItens item, TiposStatusItensPedido novoStatus) {
        String update = "UPDATE pedido_itens SET ped_item_status_troca = ? WHERE ped_item_id = ?";
        String retorno = "";
        try (Connection con = Conexao.conectar();
             PreparedStatement pst = con.prepareStatement(update)) {
            
        	pst.setInt(1, novoStatus.ordinal());
            pst.setInt(2, item.getId());

            pst.executeUpdate();
            
            con.close();
            retorno = "sucess";
            

        } catch (Exception e) {
            retorno = "error" + e;
        }

		return retorno;
	 	}

	    public String gerarCupom(String codigo, String descricao, String img, String tipoCupom, Double valorCupom,
                Date validade, int usado) {
				String insert = "INSERT INTO cupons (cup_codigo, cup_desc, cup_img, cup_tipo, cup_valor, cup_validade, cup_usado) " +
				      "VALUES (?, ?, ?, ?, ?, ?, ?)";
				
				int idCupomGerado = -1; // Valor padrão para indicar que nenhum cupom foi gerado
				
				try (Connection con = Conexao.conectar();
					  PreparedStatement pst = con.prepareStatement(insert, PreparedStatement.RETURN_GENERATED_KEYS)) {
					
					  pst.setString(1, codigo);
					  pst.setString(2, descricao);
					  pst.setString(3, img);
					  pst.setString(4, tipoCupom);
					  pst.setDouble(5, valorCupom);
					  pst.setDate(6, new java.sql.Date(validade.getTime())); // Convertendo a data para o tipo SQL
					  pst.setInt(7, usado);
					
					  int linhasAfetadas = pst.executeUpdate();
					
					  if (linhasAfetadas > 0) {
					      ResultSet rs = pst.getGeneratedKeys();
					      if (rs.next()) {
					          idCupomGerado = rs.getInt(1); // Obtém o ID gerado
					      }
					  }
					  con.close();
					  
				} catch (SQLException e) {
				  e.printStackTrace();
				  return "error: " + e;
				}
				
				return "" + idCupomGerado;
			}


		public String vincularCupomAoCliente(Cupons cupom, Cliente cliente) {
			String insert = "INSERT INTO rel_cup_cli (rcc_cup_id, rcc_cli_id) " +
			      "VALUES (?, ?)";
			String resposta = "";
			
			try (Connection con = Conexao.conectar();
				 PreparedStatement pst = con.prepareStatement(insert)) {	
				
				  pst.setInt(1, cupom.getId());
				  pst.setInt(2, cliente.getId());
				 
				  con.close();		
				  resposta = "sucess";
				
			} catch (SQLException e) {
			  e.printStackTrace();
			  resposta= "error: " + e;
			}
			
			return resposta;
		}


}