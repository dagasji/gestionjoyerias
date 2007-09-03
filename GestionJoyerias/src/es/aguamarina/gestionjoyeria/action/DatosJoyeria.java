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

public class DatosJoyeria extends Action{
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		
		try {
			HandlerJoyerias handler = new HandlerJoyerias();
			Joyeria joyeria = handler.detallesJoyeria(Integer.valueOf(request.getParameter("codigo"))); 
			if (joyeria != null)
			{
				request.getSession().setAttribute("joyeria", joyeria);
				request.getSession().setAttribute("metodo", "modificar");
				return mapping.findForward("ok");
			}else
			{
				request.getSession().setAttribute("error", "No se han encontrado datos.");
				return mapping.findForward("ko");
			}
		
		} catch (DataBaseException e) {
			request.getSession().setAttribute("error", e.toString());
			return mapping.findForward("ko"); 
			
		}
		
		
		
		
		
	}
}