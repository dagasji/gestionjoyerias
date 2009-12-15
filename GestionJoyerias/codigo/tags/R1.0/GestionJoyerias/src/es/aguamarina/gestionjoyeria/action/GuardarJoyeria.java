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
import es.aguamarina.gestionjoyeria.exception.ParameterException;
import es.aguamarina.gestionjoyeria.persistence.dao.HandlerJoyerias;


/**
 * Action encargado de guardar una joyeria
 * @author Daniel Gaspar Jiménez
 * 
 */
public class GuardarJoyeria extends GestionJoyeriasAction{
	
	

	/**
	 * Método execute() de Action
	 * @return <b>ok:</b> Joyeria guardada correctamente.</br>
	 * 		   <b>ko:</b> Error al eliminar la joyeria.	
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		Logger log = Logger.getLogger(Constants.LOGGER_GESTION_JOYERIAS);
		String ACTION_NAME = this.getClass().getSimpleName();
		
		HandlerJoyerias handler;
		
		try {
				
			log.info("Entrada execute del Action " + ACTION_NAME);
		
			handler = (HandlerJoyerias) cxt.getBean("handlerJoyeria");
			
			Integer codigo = Integer.valueOf(request.getParameter(Constants.PARAMETER_CODIGO_JOYERIA));
			String nombre = request.getParameter(Constants.PARAMETER_NOMBRE_JOYERIA);
			String telefono = request.getParameter(Constants.PARAMETER_TELEFONO_JOYERIA);
			
			if(codigo != null && nombre != null && telefono != null)
			{
				Joyeria joyeria =  new Joyeria();
				joyeria.setCodJoyeria(codigo);
				joyeria.setNombreJoyeria(nombre);
				joyeria.setTelefono(telefono);
					
				if(request.getParameter(Constants.PARAMETER_METODO).equals(Constants.PARAMETER_NUEVA))
					handler.guardarJoyeria(joyeria);
				else
					handler.modificarJoyeria(joyeria);
			
				request.setAttribute(Constants.PARAMETER_JOYERIA, joyeria);
				request.setAttribute(Constants.PARAMETER_METODO, Constants.PARAMETER_MODIFICADO_OK);
				
				return mapping.findForward(Constants.MAPPING_OK);
				
			}else{
				throw new ParameterException();					
			}			
				
				
		} catch (DataBaseException e) {
			
			log.error("Error al guardar la joyeria", e);
			request.setAttribute(Constants.PARAMETER_ERROR, e.toString());
			return mapping.findForward(Constants.MAPPING_KO);
			
		} catch (ParameterException e) {
			
			log.error("Error al guardar la joyeria", e);
			request.setAttribute(Constants.PARAMETER_ERROR, e.toString());
			return mapping.findForward(Constants.MAPPING_KO);
			
		} catch(Exception e){			
			
			log.error("Error al guardar la joyeria", e);
			request.setAttribute(Constants.PARAMETER_ERROR, e.toString());
			return mapping.findForward(Constants.MAPPING_KO);
			
		}finally{
			log.info("Salida execute del Action " + ACTION_NAME);
		}
		
	}
}
