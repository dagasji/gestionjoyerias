package es.aguamarina.gestionjoyeria.action;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import es.aguamarina.gestionjoyeria.config.Config;
import es.aguamarina.gestionjoyeria.config.Constants;
import es.aguamarina.gestionjoyeria.dto.Factura;
import es.aguamarina.gestionjoyeria.persistence.dao.FacturasDao;
import es.aguamarina.gestionjoyeria.persistence.dao.HandlerReparaciones;



public class PrintFactura extends GestionJoyeriasAction{

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
			HttpServletResponse response){
		
		Logger log = Logger.getLogger(Constants.LOGGER_GESTION_JOYERIAS);
		
		try{		
			log.debug("Entrada execute del Action PrintFactura");
		
			String aux = null;
			
			//Indicamos que el contenido va a ser un pdf
			response.setContentType("application/pdf;");
		
			//Obtenemos el codigo de la factura
			aux = request.getParameter(Constants.PARAMETER_COD_FACTURA);
			Integer codFactura = Integer.valueOf(aux);
		
			//Plantilla jrxml para el reporte
			String reportName = Config.getPropiedad(Constants.CONFIG_RUTA_PLANTILLA_JR);

			JRBeanCollectionDataSource dataSource;
			JasperReport jasperReport;
			JasperPrint jasperPrint;
			
			//Obtenemos los datos de la factura
			FacturasDao hf = (FacturasDao) cxt.getBean("facturasDao");
			Factura factura = hf.getFactura(codFactura);
						
			//1- Incluimos los parametros en el HashMap
			Map parametros = new HashMap();
			parametros.put(Constants.PARAMETER_FECHA_FACTURA, factura.getFechaFactura());
			parametros.put(Constants.PARAMETER_NOMBRE_CLIENTE, factura.getNombreCliente());
			parametros.put(Constants.CONFIG_RUTA_LOGO_FACTURA, Config.getPropiedad(Constants.CONFIG_RUTA_LOGO_FACTURA));
			
		
			//2- Llenar el datasource con los datos de las reparaciones
			HandlerReparaciones hr = (HandlerReparaciones) cxt.getBean("handlerReparaciones");
			Collection lista = hr.listadoReparacionesByFactura(codFactura);
			dataSource = new JRBeanCollectionDataSource(lista);
			
			//3- Compilamos el archivo XML y lo cargamos en memoria
			jasperReport = JasperCompileManager.compileReport(reportName);
			
			//4- Llenamos el reporte con la información de la coleccion y parametros necesarios para la consulta
			jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, dataSource);
			
			//5- Exportamos el reporte a pdf y lo escribimos en el Output de response
			JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
			
			return null;

		}catch (Exception e){
			log.error(e);
			request.setAttribute(Constants.PARAMETER_ERROR, "Error al generar el la factura en pdf");			
			return mapping.findForward(Constants.MAPPING_KO);
			
		}finally{		
			log.debug("Salida execute del Action PrintFactura");
		}
		
		
  }

}