package es.aguamarina.gestionjoyeria.action;


import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


import es.aguamarina.gestionjoyeria.config.Constants;
import es.aguamarina.gestionjoyeria.dto.Reparacion;
import es.aguamarina.gestionjoyeria.exception.DataBaseException;
import es.aguamarina.gestionjoyeria.persistence.dao.HandlerReparaciones;



/**
 * Acción que realiza el proceso de iniciar la creación de una nueva reparacion.
 * @author Dani
 *
 */
public class NuevaReparacion extends GestionJoyeriasAction{
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{		
		Logger log = Logger.getLogger(Constants.LOGGER_GESTION_JOYERIAS);
		
		try {
				log.debug("Entrada execute del Action NuevaReparacion");
			
				HandlerReparaciones handler = (HandlerReparaciones) cxt.getBean("handlerReparaciones");
				
				//Obtenemos el codigo de la joyeria
				Integer codJoyeria = Integer.valueOf(request.getParameter(Constants.PARAMETER_CODIGO_JOYERIA));
				
				//Obtenemos el primer codigo de reparacion disponible
				Integer codigoMax = handler.ultimoCodigo();				
				
				//Instanciamos un nuevo objeto Reparacion
				Reparacion reparacion =  new Reparacion();
				reparacion.setCodReparacion(codigoMax);
				reparacion.setFechaEntrada(new Date());
				
				//Guardamos como atributo de la request el codigo de la joyeria
				request.setAttribute(Constants.PARAMETER_CODIGO_JOYERIA, codJoyeria);
				
				//Guardamos como atributo de la request la nueva reparacion
				request.setAttribute("reparacion", reparacion);
				
				//Guardamos como atributo de la request el metodo que se esta ejecutando
				request.setAttribute("metodo", "nueva");
				
				log.debug("Salida execute del Action NuevaReparacion");
				
				return mapping.findForward(Constants.MAPPING_OK);
				
		} catch (DataBaseException e) {
				log.error(e);
				request.setAttribute("error", e.toString());
				return mapping.findForward(Constants.MAPPING_KO);
		} 
	}
}

