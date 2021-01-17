package service;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.util.JRLoader;

public class RelatorioService implements Serializable{

	private static final long serialVersionUID = 1L;

	private static final String FOLDER_RELATORIOS = "/relatorios";
	private static final String SUBREPORT_DIR = "SUBREPORT_DIR";
	private String SEPARATOR = File.separator;
	private String caminhoArquivoRelatorio = null;
	private JRExporter exporter = null;
	private String caminhoSubReport_Dir = "";
	private File arquivoGerado = null;
	
	
	public String gerarRelatorio(List<?> listaDataBeanCollection, HashMap parametrosRelatorio, 
			String nomeRelatorioJasper, String nomeRelatorioSaida, ServletContext servletContext, String tipoExportar) throws Exception{
		
		/*Cria a lista de collectionDataSource de beans qie carregam os dados para o relat�rio*/
		JRBeanCollectionDataSource jrbcds = new JRBeanCollectionDataSource(listaDataBeanCollection);
		
		/*Fornece o caminho fisico  at�  a pasta que contem os relat�rios .jasper*/
		String caminhoRelatorio = servletContext.getRealPath(FOLDER_RELATORIOS);
		
		File file = new File(caminhoRelatorio + SEPARATOR + nomeRelatorioJasper + ".jasper");
		
		if(caminhoRelatorio == null || (caminhoRelatorio != null && caminhoRelatorio.isEmpty()) || !file.exists()) {
			
			caminhoRelatorio = this.getClass().getResource(FOLDER_RELATORIOS).getPath();
			SEPARATOR = "";
		}
		
		/*caminho para imagens*/
		parametrosRelatorio.put("REPORT_PARAMETERS_IMG", caminhoRelatorio);
		
		/*caminho completo at� o relat�roio compilado indicado*/
		String caminhoArquivosJasper = caminhoRelatorio + SEPARATOR + nomeRelatorioJasper + ".jasper";
		
		/*Faz o carregamento do relat�rio*/
		JasperReport relatorioJasper = (JasperReport) JRLoader.loadObjectFromFile(caminhoArquivosJasper);
		
		/*sera parametros SUBREPORT_DIR com o caminho fisico para subreport*/
		caminhoSubReport_Dir = caminhoRelatorio + SEPARATOR;
		parametrosRelatorio.put(SUBREPORT_DIR, caminhoSubReport_Dir);
		
		/*Carrega o arquivo*/
		JasperPrint impressoraJasper = JasperFillManager.fillReport(relatorioJasper, parametrosRelatorio, jrbcds);
		
		if(tipoExportar.equalsIgnoreCase("pdf")) {
		exporter = new JRPdfExporter();
		
		}else if(tipoExportar.equalsIgnoreCase("xls")) {
			exporter = new JRXlsExporter();
			
		}
		/*Caminho relatorio exportado*/
		caminhoArquivoRelatorio = caminhoRelatorio + SEPARATOR + nomeRelatorioSaida + "."+tipoExportar;
		
		/*Criar novo arquivo exportado*/
		arquivoGerado = new File(caminhoArquivoRelatorio);
		
		/*PREPARA A IMPRESS�O*/
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, impressoraJasper);
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE, arquivoGerado);
		
		/*Executa a exporta��o*/
		exporter.exportReport();
		
		/*Remove o arquivo do servidor ap�s ser feito o download*/
		arquivoGerado.deleteOnExit();
		
		return caminhoArquivoRelatorio;
	}
	
}
