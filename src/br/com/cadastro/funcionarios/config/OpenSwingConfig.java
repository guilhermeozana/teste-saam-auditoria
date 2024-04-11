/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.cadastro.funcionarios.config;

import br.com.cadastro.funcionarios.util.Alerta;
import br.com.cadastro.funcionarios.util.Util;
import java.awt.event.KeyEvent;
import java.util.Hashtable;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.openswing.swing.internationalization.java.XMLResourcesFactory;
import org.openswing.swing.permissions.java.ButtonsAuthorizations;
import org.openswing.swing.util.client.ClientSettings;

/**
 *
 * @author Guilherme
 */
public abstract class OpenSwingConfig {
    
    public static void configurarOpenSwing() {

        Hashtable domains = new Hashtable();

        Hashtable xmlFiles = new Hashtable();
        xmlFiles.put("PT_BR", "br/com/cadastro/funcionarios/arquivos/Resources_pt_br.xml");
        ClientSettings clientSettings = new ClientSettings(
                new XMLResourcesFactory(xmlFiles, false),
                domains,
                new ButtonsAuthorizations()
        );

        try {
            Properties props = new Properties();

            props.put("logoString", "Cadastro");
            props.setProperty("brightMode", "on");
            props.setProperty("menuOpaque", "on");
            props.setProperty("backgroundPattern", "off");
            props.setProperty("drawSquareButtons", "on");
            props.setProperty("backgroundColor", "255 255 255");

            props.setProperty("windowDecoration", "false");

            com.jtattoo.plaf.mcwin.McWinLookAndFeel.setTheme(props);
            UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {

            Util.getAlert().alertErro("Ocorreu um erro durante a configuração do visual do aplicativo. Por favor, entre em contato com o suporte técnico para obter assistência.");
        }
        
        ClientSettings.AS_TAB = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
        ClientSettings.LOOK_AND_FEEL_CLASS_NAME = "com.jtattoo.plaf.mcwin.McWinLookAndFeel";

        ClientSettings.VIEW_BACKGROUND_SEL_COLOR = true;
        ClientSettings.BUTTON_INSERT_IMAGE_NAME = "inserir.png";
        ClientSettings.BUTTON_RELOAD_IMAGE_NAME = "reload.png";
        ClientSettings.BUTTON_SAVE_IMAGE_NAME = "salvar.png";
        ClientSettings.BUTTON_FILTER_IMAGE_NAME = "pesquisa.png";
        ClientSettings.BUTTON_DELETE_IMAGE_NAME = "excluir.png";
        ClientSettings.BUTTON_COPY_IMAGE_NAME = "copiar.png";
        ClientSettings.BUTTON_EDIT_IMAGE_NAME = "editar.png";
        ClientSettings.BUTTON_EXPORT_IMAGE_NAME = "exportar.png";
        ClientSettings.BUTTON_IMPORT_IMAGE_NAME = "importar.png";

        ClientSettings.VIEW_MANDATORY_SYMBOL = true;
        ClientSettings.ALLOW_OR_OPERATOR = false;
        ClientSettings.LIKE = "ilike";

        ClientSettings.TREE_BACK = null;
        ClientSettings.FILTER_PANEL_ON_GRID = false;
        ClientSettings.SHOW_FILTER_SYMBOL = true;
        ClientSettings.ASK_BEFORE_CLOSE = true;
        ClientSettings.RELOAD_LAST_VO_ON_FORM = true;
        ClientSettings.MAX_EXPORTABLE_ROWS = 2000000000;
        ClientSettings.SHOW_EVENT_QUEUE_EXCEPTIONS = true;
        ClientSettings.SHOW_FOCUS_BORDER_ON_FORM = false;

        ClientSettings.getInstance().setLanguage("PT_BR");
    }
    
}
