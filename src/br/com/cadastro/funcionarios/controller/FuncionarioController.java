/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.cadastro.funcionarios.controller;

import br.com.cadastro.funcionarios.dao.FuncionarioDAO;
import br.com.cadastro.funcionarios.util.Util;
import br.com.cadastro.funcionarios.view.FuncionarioView;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.send.java.GridParams;
import org.openswing.swing.table.client.GridController;
import org.openswing.swing.table.java.GridDataLocator;



/**
 *
 * @author Guilherme
 */
public class FuncionarioController extends GridController implements GridDataLocator {

    public FuncionarioView funcionarioView;
    
    public FuncionarioDAO funcionarioDao = new FuncionarioDAO();

    public FuncionarioController() {

        funcionarioView = new FuncionarioView();
        
        this.funcionarioView.getGridControlFuncionario().setController(FuncionarioController.this);
        this.funcionarioView.getGridControlFuncionario().setGridDataLocator(FuncionarioController.this);

        this.funcionarioView.setVisible(true);
        
        this.funcionarioView.getGridControlFuncionario().getTable().setDimensionFilterDialog(new Dimension(500, 400));
    }
    
    public FuncionarioView getFuncionarioView() {

        return funcionarioView;
    }

    @Override
    public Response loadData(int action,
            int startIndex,
            Map filteredColumns,
            ArrayList currentSortedColumns,
            ArrayList currentSortedVersusColumns,
            Class valueObjectType,
            Map otherGridParams) {

        filteredColumns = Util.ajustaFiltro(filteredColumns);

        GridParams gridParams = new GridParams(
                action,
                startIndex,
                filteredColumns,
                currentSortedColumns,
                currentSortedVersusColumns,
                new HashMap()
        );

        return this.funcionarioDao.loadData(gridParams);
    }


    @Override
    public Response insertRecords(int[] rowNumbers, ArrayList newValueObjects) throws Exception {

        Response insertRecords = this.funcionarioDao.insertRecords(newValueObjects);
        
        if(!insertRecords.isError()) {
            Util.getAlert().alertSucesso("Registro(s) salvo(s) com sucesso!");
        }
        
        return insertRecords;
    }

    @Override
    public Response updateRecords(int[] rowNumbers, ArrayList oldPersistentObjects, ArrayList persistentObjects) throws Exception {
        Response updateRecords = this.funcionarioDao.updateRecords(persistentObjects);
        
        if(!updateRecords.isError()) {
            Util.getAlert().alertSucesso("Registro(s) atualizado(s) com sucesso!");
        }
        
        return updateRecords;
    }

    @Override
    public Response deleteRecords(ArrayList persistentObjects) throws Exception {
    
        Response deleteRecords = this.funcionarioDao.deleteRecords(persistentObjects);
        
        if(!deleteRecords.isError()) {
            Util.getAlert().alertSucesso("Registro(s) excluído(s) com sucesso!");
        }
        
        return deleteRecords;
    }

    @Override
    public void afterInsertGrid(GridControl grid) {

        grid.reloadData();
    }

    

    
}
