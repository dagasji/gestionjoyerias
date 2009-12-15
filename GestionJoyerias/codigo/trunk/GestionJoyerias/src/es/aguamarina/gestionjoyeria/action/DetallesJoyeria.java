package es.aguamarina.gestionjoyeria.action;

import java.util.List;

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
import es.aguamarina.gestionjoyeria.persistence.dao.HandlerReparaciones;

/**
 * Action encargado de obtener el listado de reparaciones
 * @author Daniel Gaspar Jiménez
 * 
 */
public class DetallesJoyeria extends GestionJoyeriasAction{
	
	
	/**
	 * Método execute() de Action
	 * @return <b>ok:</b> Listado recuperado correctamente.</br>
	 * 		   <b>ko:</b> Error al obtener los datos de la joyeria.	
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		
		Logger log = Logger.getLogger(Constants.LOGGER_GESTION_JOYERIAS);
		String ACTION_NAME = this.getClass().getSimpleName();
		
		HandlerJoyerias handlerJoyerias;
		HandlerReparaciones handlerReparaciones;
		Integer codJoyeria;
		Joyeria joyeria;
		List listadoReparaciones;
		
		try {
			log.info("Entrada execute del Action " + ACTION_NAME);
			
			//Instanciamos el HandlerJoyerias
			handlerJoyerias = (HandlerJoyerias) cxt.getBean("handlerJoyeria");
			//Instanciamos el HandlerReparaciones
			handlerReparaciones  = (HandlerReparaciones) cxt.getBean("handlerReparaciones");
			
			//Obtenemos el codigo de joyeria
			String sCodJoyeria = request.getParameter(Constants.PARAMETER_CODIGO_JOYERIA);
			
			if(sCodJoyeria == null)
				codJoyeria = (Integer)request.getAttribute(Constants.PARAMETER_CODIGO_JOYERIA);
			else
				codJoyeria = Integer.valueOf(sCodJoyeria);
			
			//Obtenemos la joyeria
			joyeria = handlerJoyerias.detallesJoyeria(codJoyeria);
			
			//Obtenemos el estado
			String estado = request.getParameter(Constants.PARAMETER_ESTADO);
			if(estado == null)
				estado = Constants.ESTADO_SINREPARAR;
			
			//Recuperamos de bbdd el listado de reparaciones
			listadoReparaciones = handlerReparaciones.listadoReparaciones(joyeria.getCodJoyeria(), estado);
			
			request.getSession().setAttribute(Constants.PARAMETER_JOYERIA, joyeria);
			
			if (listadoReparaciones != null)
			{
				request.setAttribute(Constants.PARAMETER_LISTADO_REPARACIONES, listadoReparaciones);
				
				if (estado.equals(Constants.ESTADO_SINFACTURAR))
					return mapping.findForward(Constants.MAPPING_SIN_FACTURAR);
				else
					return mapping.findForward(Constants.MAPPING_OK);
			}else
			{				
				return mapping.findForward(Constants.MAPPING_OK);
			}
		
		} catch (DataBaseException e) {
			
			log.error("Error al obtener el listado de la joyeria", e);
			request.getSession().setAttribute(Constants.PARAMETER_ERROR, e.toString());
			return mapping.findForward(Constants.MAPPING_KO); 
			
		}finally{
			log.info("Salida execute del Action " + ACTION_NAME);			
		}
	}
}
