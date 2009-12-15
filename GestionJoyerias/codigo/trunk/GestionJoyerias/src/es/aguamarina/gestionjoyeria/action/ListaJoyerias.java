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
import es.aguamarina.gestionjoyeria.persistence.dao.HandlerJoyerias;

public class ListaJoyerias extends GestionJoyeriasAction{
	
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
	
		Logger log = Logger.getLogger(Constants.LOGGER_GESTION_JOYERIAS);
		
		try {
			log.info("Ejecutando accion ListaJoyerias");
			HandlerJoyerias handler = (HandlerJoyerias) cxt.getBean("handlerJoyeria");
			List listado = handler.listadoJoyerias(); 
			if (listado != null)
			{
				request.setAttribute("listadoJoyerias", listado);
				return mapping.findForward(Constants.MAPPING_OK);
			}else
			{
				request.getSession().setAttribute("error", "No se ha encontrado ninguna joyeria");
				return mapping.findForward(Constants.MAPPING_KO);
			}
		
		} catch (DataBaseException e) {
			request.getSession().setAttribute("error", e.toString());
			return mapping.findForward(Constants.MAPPING_KO); 
			
		}
		
		
		
		
		
	}
}
