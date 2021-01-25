package br.jtech.veiculos.relatorio;

import java.io.InputStream;
import java.util.List;

import br.jtech.veiculos.entidade.Carro;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class Relatorio {
	
	public void gerarRelatorio(List<Carro> lista) throws JRException {
		
		InputStream arq = Relatorio.class.getResourceAsStream("/Relatorio/relatorioveiculos.jrxml");
		
		JasperReport report = JasperCompileManager.compileReport(arq);
		
		JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(lista));
		
		JasperViewer.viewReport(print, false);
	}

}
