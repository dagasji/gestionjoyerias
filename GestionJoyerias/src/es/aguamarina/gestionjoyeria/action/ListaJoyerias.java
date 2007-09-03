package es.aguamarina.gestionjoyeria.action;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import es.aguamarina.gestionjoyeria.exception.DataBaseException;
import es.aguamarina.gestionjoyeria.handler.HandlerJoyerias;

public class ListaJoyerias extends Action{
	Logger log = Logger.getLogger(this.getClass());
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		
		try {
			log.info("Ejecutando accion ListaJoyerias");
			HandlerJoyerias handler = new HandlerJoyerias();
			List listado = handler.listadoJoyerias(); 
			if (listado != null)
			{
				request.setAttribute("listadoJoyerias", listado);
				return mapping.findForward("ok");
			}else
			{
				request.getSession().setAttribute("error", "No se ha encontrado ninguna joyeria");
				return mapping.findForward("ko");
			}
		
		} catch (DataBaseException e) {
			request.getSession().setAttribute("error", e.toString());
			return mapping.findForward("ko"); 
			
		}
		
		
		
		
		
	}
}
