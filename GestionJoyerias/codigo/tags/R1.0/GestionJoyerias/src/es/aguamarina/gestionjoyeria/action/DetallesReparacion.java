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
 * Action encargado de obtener los detalles de una reparacion
 * @author Daniel Gaspar Jiménez
 * 
 */
public class DetallesReparacion extends GestionJoyeriasAction{
	
	/**
	 * Método execute() de Action
	 * @return <b>ok:</b> Reparacion recuperada correctamente.</br>
	 * 		   <b>ko:</b> Error al obtener los datos de la reparacion.	
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		
		Logger log = Logger.getLogger(Constants.LOGGER_GESTION_JOYERIAS);
		String ACTION_NAME = this.getClass().getSimpleName();
		
		HandlerReparaciones handlerReparaciones;
		
		try {
			log.info("Entrada execute del Action " + ACTION_NAME);
			
			//Obtenemos el codigo de la reparacion de la request
			String sCodReparacion = request.getParameter(Constants.PARAMETER_COD_REPARACION);
			
			if(sCodReparacion != null && !sCodReparacion.equals(""))
			{
				Integer codReparacion = Integer.valueOf(sCodReparacion);
				
				//Obtenemos el dao de reparaciones
				handlerReparaciones  = (HandlerReparaciones) cxt.getBean("handlerReparaciones");
				
				//Obtenemos los datos de la reparacion
				Reparacion reparacion = handlerReparaciones.getReparacion(codReparacion);			
				
				if (reparacion != null)
				{
					request.setAttribute(Constants.PARAMETER_NOW, new Date());
					request.setAttribute(Constants.PARAMETER_REPARACION, reparacion);
					request.setAttribute(Constants.PARAMETER_METODO, Constants.PARAMETER_MODIFICAR);
					return mapping.findForward(Constants.MAPPING_OK);
				}else{
					return mapping.findForward(Constants.MAPPING_KO);
				}
			}else{
				request.setAttribute(Constants.PARAMETER_ERROR, "Parametro requerido: " 
						+ Constants.PARAMETER_COD_REPARACION);
				return mapping.findForward(Constants.MAPPING_KO);
			}
		
		} catch (DataBaseException e) {
			
			log.error("Error al obtener el detalle de la reparacion", e);
			request.setAttribute(Constants.PARAMETER_ERROR, e.toString());
			return mapping.findForward(Constants.MAPPING_KO); 
			
		}finally{
			log.info("Salida execute del Action " + ACTION_NAME);			
		}
	}
}
