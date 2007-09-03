package es.aguamarina.gestionjoyeria.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;



import es.aguamarina.gestionjoyeria.exception.DataBaseException;
import es.aguamarina.gestionjoyeria.handler.HandlerJoyerias;
import es.aguamarina.gestionjoyeria.structura.Joyeria;




public class NuevaJoyeria extends Action{
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		
		try {
				HandlerJoyerias handler = new HandlerJoyerias();
				Integer codigoMax = handler.ultimoCodigo();
				
				Joyeria joyeria =  new Joyeria();
				joyeria.setCodJoyeria(codigoMax);
								
				request.getSession().setAttribute("joyeria", joyeria);
				request.getSession().setAttribute("metodo", "nueva");
				return mapping.findForward("ok");
				
		} catch (DataBaseException e) {
				request.getSession().setAttribute("error", e.toString());
				return mapping.findForward("ko");
		}
		
		
		
		
		
		
	}
}
