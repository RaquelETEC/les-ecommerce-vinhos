package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.entity.Cliente;
import model.entity.Endereco;
import model.entity.TiposEndereco;

public class DaoEndereco {
	
	
    public String inserirEndereco(Cliente cliente, Endereco endereco) {
        System.out.println("DAO : id do cliente para o endereço:"+ cliente.getId());

		String create =   "INSERT INTO endereco" + //
                        "(end_cli_id,end_tipo_residencia,end_tipo_logradouro,end_logradouro,end_numero,"+
                        "end_bairro,end_cep,end_cidade, end_estado, end_pais,end_padrao,end_observacoes,end_nome,end_tipo )" + //
                        "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			Connection con = Conexao.conectar();
            System.out.println("chegou no try no inserirEndereco" +cliente.getId() );
			PreparedStatement pst = con.prepareStatement(create);
			pst.setInt(1, cliente.getId());
			pst.setString(2, endereco.getTipoResidencia());
			pst.setString(3, endereco.getTipoLogradouro());
			pst.setString(4, endereco.getLogradouro());
			pst.setString(5, endereco.getNumero());
			pst.setString(6, endereco.getBairro());
			pst.setString(7, endereco.getCep());
			pst.setString(8, endereco.getCidade());
			pst.setString(9, endereco.getEstado());
			pst.setString(10, endereco.getPais());
			pst.setString(11, endereco.getPadrao());
			pst.setString(12, endereco.getObservacao());
			pst.setString(13, endereco.getNome());
			pst.setString(14, endereco.getTipos().toString());

			pst.executeUpdate(); 
			System.err.println("inserido endere�o no dao!!");
			con.close();
			return "Sucesso";

		} catch (Exception e) {
			System.out.println("erro ao inserir Endere�o: "+e);
			return "Erro";
		}
	}

    public ArrayList<Endereco> ListarEnderecos(Cliente cliente) {
        ArrayList<Endereco> listaDeEnderecos = new ArrayList<>();
        String read = "SELECT end_id,"
        		+ "end_cli_id,"
        		+ "end_tipo_residencia,"
        		+ "end_tipo_logradouro,"
        		+ "end_logradouro,"
        		+ "end_numero,"
        		+ "end_bairro,"
        		+ "end_cep,"
        		+ "end_cidade,"
        		+ "end_estado,"
        		+ "end_pais,"
        		+ "end_padrao,"
        		+ "end_observacoes,"
        		+ "end_nome,"
        		+ "end_tipo "
        		+ "FROM ecommerce.endereco "
        		+ "WHERE end_cli_id = ?";
        try {
            Connection con = Conexao.conectar();
            PreparedStatement pst = con.prepareStatement(read);
            
            pst.setInt(1, cliente.getId());
            
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
               Endereco endereco = new Endereco();
            	
        	   endereco.setId(rs.getInt("end_id"));
               endereco.setCliente(cliente);
               endereco.setTipoResidencia(rs.getString("end_tipo_residencia"));
               endereco.setTipoLogradouro(rs.getString("end_tipo_logradouro"));
               endereco.setLogradouro(rs.getString("end_logradouro"));
               endereco.setNumero(rs.getString("end_numero"));
               endereco.setBairro(rs.getString("end_bairro"));
               endereco.setCep(rs.getString("end_cep"));
               endereco.setCidade(rs.getString("end_cidade"));
               endereco.setEstado(rs.getString("end_estado"));
               endereco.setPais(rs.getString("end_pais"));
               endereco.setPadrao(rs.getString("end_padrao"));
               endereco.setObservacao(rs.getString("end_observacoes"));
               endereco.setNome(rs.getString("end_nome"));
               
               // Converter a string do tipo de endereço para o enum correspondente
               String tipoEnderecoString = rs.getString("end_tipo");
               TiposEndereco tipoEndereco = TiposEndereco.valueOf(tipoEnderecoString);
               endereco.setTipos(tipoEndereco);
            	
               listaDeEnderecos.add(endereco);
            }
            con.close();
        } catch (Exception e) {
            System.out.println("Erro ao listar endereços: " + e);
        }
        return listaDeEnderecos;
    }
}
