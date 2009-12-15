package es.aguamarina.gestionjoyeria.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import es.aguamarina.gestionjoyeria.config.Constants;
import es.aguamarina.gestionjoyeria.dto.Joyeria;
import es.aguamarina.gestionjoyeria.exception.DataBaseException;
import es.aguamarina.gestionjoyeria.persistence.dao.HandlerJoyerias;



/**
 * Action encargado de crear una nueva Joyería.
 * Muestra el formulario de creacion de la joyeria.
 * @author Daniel Gaspar Jiménez
 *  
 */
public class NuevaJoyeria extends GestionJoyeriasAction{
	
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		
		Logger log = Logger.getLogger(Constants.LOGGER_GESTION_JOYERIAS);
		
		try {
			
			log.debug("Entrada execute del Action NuevaJoyeria");
			
			HandlerJoyerias handler = (HandlerJoyerias) cxt.getBean("handlerJoyeria");
			
			//Obtenemos el primer codigo de joyeria displonible.
			Integer codigoMax = handler.ultimoCodigo();
			
			//Creamos una nueva joyeria con el codigo recuperado
			Joyeria joyeria =  new Joyeria();
			joyeria.setCodJoyeria(codigoMax);				
			
			request.setAttribute(Constants.PARAMETER_METODO, Constants.PARAMETER_NUEVA);
			request.setAttribute(Constants.PARAMETER_JOYERIA, joyeria);
			
			return mapping.findForward(Constants.MAPPING_OK);
				
		} catch (DataBaseException e) {			
			log.error(e);
			request.setAttribute(Constants.PARAMETER_ERROR, e.toString());
			return mapping.findForward(Constants.MAPPING_KO);
			
		}finally{
			log.debug("Salida execute del Action NuevaJoyeria");
		}
	}
}
