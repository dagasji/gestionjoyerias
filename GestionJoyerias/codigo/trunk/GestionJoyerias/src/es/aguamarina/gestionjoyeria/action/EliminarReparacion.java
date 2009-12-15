package es.aguamarina.gestionjoyeria.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import es.aguamarina.gestionjoyeria.config.Constants;
import es.aguamarina.gestionjoyeria.dto.Joyeria;
import es.aguamarina.gestionjoyeria.exception.DataBaseException;
import es.aguamarina.gestionjoyeria.persistence.dao.HandlerReparaciones;

/**
 * Action encargado de eliminar una reparacion
 * @author Daniel Gaspar Jiménez
 * 
 */
public class EliminarReparacion extends GestionJoyeriasAction{
	
	/**
	 * Método execute() de Action
	 * @return <b>ok:</b> Reparacion eliminada correctamente.</br>
	 * 		   <b>ko:</b> Error al eliminar la reparacion.	
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		
		Logger log = Logger.getLogger(Constants.LOGGER_GESTION_JOYERIAS);
		String ACTION_NAME = this.getClass().getSimpleName();
		
		HandlerReparaciones hr;
		
		try {
			
			log.info("Entrada execute del Action " + ACTION_NAME);
			
			String sCodReparacion = request.getParameter(Constants.PARAMETER_COD_REPARACION);
			if (sCodReparacion != null && !sCodReparacion.equals(""))
			{
				Integer codReparacion = Integer.parseInt(sCodReparacion);
				
				//Obtenemos el HandlerReparaciones
				hr  = (HandlerReparaciones) cxt.getBean("handlerReparaciones");
				
				//Obtenemos de la session la joyeria
				HttpSession session = request.getSession();
				Joyeria joyeria = (Joyeria) session.getAttribute(Constants.PARAMETER_JOYERIA);
				
				hr.eliminarReparacion(codReparacion);
				request.setAttribute(Constants.PARAMETER_CODIGO_JOYERIA, joyeria.getCodJoyeria());
	
				return mapping.findForward(Constants.MAPPING_OK);
				
			}else{
				request.setAttribute(Constants.PARAMETER_ERROR, "Parametro requerido: " 
						+ Constants.PARAMETER_COD_REPARACION);
				return mapping.findForward(Constants.MAPPING_KO);
			}
			
		} catch (DataBaseException e) {			
			
			log.error("Error al eliminar la reparacion", e);
			request.getSession().setAttribute(Constants.PARAMETER_ERROR, e.toString());
			return mapping.findForward(Constants.MAPPING_KO); 

		}finally{
			
			log.info("Salida execute del Action " + ACTION_NAME);
			
		}
	}
}
