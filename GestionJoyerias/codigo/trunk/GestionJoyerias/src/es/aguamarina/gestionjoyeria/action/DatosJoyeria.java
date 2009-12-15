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
 * Action encargado de obtener los datos de una joyería
 * @author Daniel Gaspar Jiménez
 * 
 */
public class DatosJoyeria extends GestionJoyeriasAction{
	
	
	/**
	 * Método execute() de Action
	 * @return <b>ok:</b> Joyeria recuperada correctamente.</br>
	 * 		   <b>ko:</b> Error al obtener los datos de la joyeria.	
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		
		Logger log = Logger.getLogger(Constants.LOGGER_GESTION_JOYERIAS);
		String ACTION_NAME = this.getClass().getSimpleName();
		
		try {
			log.info("Entrada execute del Action " + ACTION_NAME);			
			
			HandlerJoyerias handler = (HandlerJoyerias) cxt.getBean("handlerJoyeria");
			
			//Recuperamos el codigo de la joyeria de la request
			Integer codJoyeria = Integer.valueOf(request.getParameter(Constants.PARAMETER_CODIGO_JOYERIA));
			
			//Obtenemos los datos de la joyeria
			Joyeria joyeria = handler.detallesJoyeria(codJoyeria);
			
			
			if (joyeria != null)
			{				
				request.setAttribute(Constants.PARAMETER_JOYERIA, joyeria);
				request.setAttribute(Constants.PARAMETER_METODO, Constants.PARAMETER_MODIFICAR);
				return mapping.findForward(Constants.MAPPING_OK);
			}else
			{				
				request.setAttribute(Constants.PARAMETER_ERROR, "No se han encontrado datos.");
				return mapping.findForward(Constants.MAPPING_KO);
			}
		
		} catch (DataBaseException e) {
			
			log.error("Error al obtener los datos de la joyeria", e);
			request.setAttribute(Constants.PARAMETER_ERROR, e.toString());
			return mapping.findForward(Constants.MAPPING_KO); 
			
		} catch(NumberFormatException e){
			
			log.error("Error al obtener los datos de la joyeria", e);
			request.setAttribute(Constants.PARAMETER_ERROR, "Código de joyería erroneo");
			return mapping.findForward(Constants.MAPPING_KO);
			
		}finally{
			
			log.info("Salida execute del Action " + ACTION_NAME);
			
		}
		
	}	
	
}