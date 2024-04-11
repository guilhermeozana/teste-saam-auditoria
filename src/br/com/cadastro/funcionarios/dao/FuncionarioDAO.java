/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.cadastro.funcionarios.dao;

import br.com.cadastro.funcionarios.util.Conexao;
import br.com.cadastro.funcionarios.util.Util;
import br.com.cadastro.funcionarios.vo.FuncionarioVO;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.send.java.GridParams;
import org.openswing.swing.server.QueryUtil;

/**
 *
 * @author Guilherme
 */
public class FuncionarioDAO {
    
    public Response loadData(GridParams gridParams) {

        try {
            Connection conn = Conexao.getConexao();

            String sql = "SELECT\n"
                    + "      id,\n"
                    + "      nome,\n"
                    + "      data_admissao,\n"
                    + "      valor_salario,\n"
                    + "      status\n"
                    + " from public.funcionarios";

            Map attribute2dbField = new HashMap();
            attribute2dbField.put("id", "id");
            attribute2dbField.put("nome", "nome");
            attribute2dbField.put("dataAdmissao", "data_admissao");
            attribute2dbField.put("valorSalario", "valor_salario");
            attribute2dbField.put("status", "status");

            return QueryUtil.getQuery(
                    conn,
                    sql,
                    new ArrayList(),
                    attribute2dbField,
                    FuncionarioVO.class,
                    "t",
                    "f",
                    gridParams,
                    false
            );
        } catch (Exception ex) {

            Util.getAlert().alertErro("Erro ao carregar os dados:" + ex.getMessage());
            return new ErrorResponse(ex.getMessage());
        }
    }

    public Response insertRecords(ArrayList<FuncionarioVO> newValueObjects) {

        PreparedStatement ps = null;

        String sql = "INSERT INTO \n"
                + "  public.funcionarios\n"
                + "(\n"
                + "  nome,\n"
                + "  data_admissao,\n"
                + "  valor_salario,\n"
                + "  status\n"
                + ")\n"
                + "VALUES (\n"
                + "  ?,\n"
                + "  ?,\n"
                + "  ?,\n"
                + "  ?\n"
                + ");";

        try {

            for (FuncionarioVO vo : newValueObjects) {

                ps = Conexao.getConexao().prepareStatement(sql);
                ps.setString(1, vo.getNome() == null ? "" : vo.getNome().trim());
                ps.setDate(2, new Date(vo.getDataAdmissao().getTime()));
                ps.setBigDecimal(3, vo.getValorSalario() == null ? BigDecimal.ZERO : vo.getValorSalario());
                ps.setBoolean(4, vo.isStatus());
                ps.executeUpdate();
            }
        } catch (SQLException ex) {

            Util.getAlert().alertErro("Erro ao inserir o funcionário" + ex.getMessage());
        } finally {
            try {

                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {

                Util.getAlert().alertErro("Erro ao fechar o ps:" + ex.getMessage());
            }
        }

        return new VOListResponse(newValueObjects, false, newValueObjects.size());
    }

    public Response updateRecords(ArrayList<FuncionarioVO> persistentObjects) {

        PreparedStatement ps = null;

        String sql = "UPDATE \n"
                + "  public.funcionarios \n"
                + "SET \n"
                + "  nome = ?,\n"
                + "  data_admissao = ?,\n"
                + "  valor_salario = ?,\n"
                + "  status = ?\n"
                + "WHERE \n"
                + "id = ?;";

        try {

            for (FuncionarioVO vo : persistentObjects) {

                ps = Conexao.getConexao().prepareStatement(sql);
                ps.setString(1, vo.getNome() == null ? "" : vo.getNome().trim());
                ps.setDate(2, vo.getDataAdmissao() == null ? null : new Date(vo.getDataAdmissao().getTime()));
                ps.setBigDecimal(3, vo.getValorSalario() == null ? BigDecimal.ZERO : vo.getValorSalario());
                ps.setBoolean(4, vo.isStatus());
                ps.setInt(5, vo.getId());
                ps.executeUpdate();
            }
        } catch (SQLException ex) {

            Util.getAlert().alertErro("Erro ao alterar o funcionário" + ex.getMessage());
        } finally {
            try {

                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {

                Util.getAlert().alertErro("Erro ao fechar o ps:" + ex.getMessage());
            }
        }

        return new VOListResponse(persistentObjects, false, persistentObjects.size());
    }

    public Response deleteRecords(ArrayList<FuncionarioVO> persistentObjects) {

        try {

            for (FuncionarioVO vo : persistentObjects) {

                Conexao.getConexao().prepareStatement("DELETE FROM public.funcionarios WHERE id = " + vo.getId() + ";").executeUpdate();
            }
        } catch (SQLException ex) {

            Util.getAlert().alertErro("Erro ao excluir o funcionário" + ex.getMessage());
        }

        return new VOResponse(true);
    }

}
