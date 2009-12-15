package es.aguamarina.gestionjoyeria.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import es.aguamarina.gestionjoyeria.config.Constants;
import es.aguamarina.gestionjoyeria.exception.DataBaseException;
import es.aguamarina.gestionjoyeria.persistence.dao.FacturasDao;

/**
 * Action encargado de recuperar el listado de facturas
 * @author Daniel Gaspar Jiménez
 * 
 */
public class ListaFacturas extends GestionJoyeriasAction{
	
	/**
	 * Método execute() de Action
	 * @return <b>ok:</b> Listado recuperado correctamente.</br>
	 * 		   <b>ko:</b> Error al recuperar el listado de facturas.	
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		
		Logger log = Logger.getLogger(Constants.LOGGER_GESTION_JOYERIAS);
		
		try {
			log.debug("Entrada execute del Action ListaFacturas");
			
			FacturasDao hf = (FacturasDao) cxt.getBean("facturasDao");
			
			String aux = request.getParameter(Constants.PARAMETER_CODIGO_JOYERIA);
			Integer codJoyeria = Integer.valueOf(aux);
			
			//Llamada handler para recuperar todas las facturas
			List lista = hf.getListFacturasByJoyeria(codJoyeria);					
			
			//falta devolver el listado con las facturas
			request.setAttribute("listado", lista);
			
			//request.setAttribute(Constants.PARAMETER_LISTADO_REPARACIONES, listadoReparaciones);
			return mapping.findForward(Constants.MAPPING_OK);
			
		
		} catch (DataBaseException e) {
			log.error("Error al obtener los datos de las reparaciones.");
			request.getSession().setAttribute(Constants.PARAMETER_ERROR, e.toString());
			return mapping.findForward(Constants.MAPPING_KO); 
			
		}finally{		
			log.debug("Salida execute del Action ListaFacturas");
		}
	}
}
