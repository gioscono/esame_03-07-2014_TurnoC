package it.polito.tdp.meteo;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.meteo.bean.Model;
import it.polito.tdp.meteo.bean.Situazione;
import it.polito.tdp.meteo.bean.Statistiche;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class MeteoController {

	private Model model;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> boxCitta;

    @FXML
    private Button btnElencoDate;

    @FXML
    private Button btnSimula;

    @FXML
    private TextField txtProbabilita;

    @FXML
    private TextArea txtResult;

    @FXML
    private TextField txtTecnici;
    
    @FXML
    void doElencoDate(ActionEvent event) {

    	String s = boxCitta.getValue();
    	if(s==null){
    		txtResult.appendText("Errore: selezionare una citta.\n");
    		return;
    	}
    	List<Situazione> ris = model.listaDate(s);
    	for(Situazione ss : ris){
    		txtResult.appendText(ss.getData()+" "+ss.getTMax()+"\n");
    	}
    }

    @FXML
    void doSimula(ActionEvent event) {

    	String prob = txtProbabilita.getText();
    	String tecnici = txtTecnici.getText();
    	
    	try{
    		double probabilita = Double.parseDouble(prob);
    		int numeroTecnici = Integer.parseInt(tecnici);
    		Statistiche s = model.avviaSimulazione(probabilita, numeroTecnici);
    		txtResult.appendText("E' stato necessario chiamare "+s.getAssunzioni()+" colte tecnici\nesterni. Costo totale: "+s.getCosto());
    	}catch(NumberFormatException e){
    	txtResult.appendText("Errore: inserire valori validi.\n");
    	return;
    	}
    }

    @FXML
    void initialize() {
        assert boxCitta != null : "fx:id=\"boxCitta\" was not injected: check your FXML file 'Meteo.fxml'.";
        assert btnElencoDate != null : "fx:id=\"btnElencoDate\" was not injected: check your FXML file 'Meteo.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Meteo.fxml'.";
        assert txtProbabilita != null : "fx:id=\"txtProbabilita\" was not injected: check your FXML file 'Meteo.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Meteo.fxml'.";
        assert txtTecnici != null : "fx:id=\"txtTecnici\" was not injected: check your FXML file 'Meteo.fxml'.";


    }


	public void setModel(Model model) {
		this.model = model;
		boxCitta.getItems().clear();
		boxCitta.getItems().addAll(model.getAllCitta());
	}

}
