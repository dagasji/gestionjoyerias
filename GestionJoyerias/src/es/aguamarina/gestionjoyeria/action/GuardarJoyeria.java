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




public class GuardarJoyeria extends Action{
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		
		try {
				HandlerJoyerias handler = new HandlerJoyerias();
				
				Integer codigo = Integer.valueOf(request.getParameter("codigo"));
				String nombre = request.getParameter("nombre");
				String telefono = request.getParameter("telefono");
				String boton = request.getParameter("boton");
				
				if(boton.equals("Volver"))
					return mapping.findForward("volver");
				
				if(codigo != null && nombre != null && telefono != null)
				{
					Joyeria joyeria =  new Joyeria();
					joyeria.setCodJoyeria(codigo);
					joyeria.setNombreJoyeria(nombre);
					joyeria.setTelefono(telefono);
					if(request.getSession().getAttribute("metodo").equals("nueva"))
						handler.guardarJoyeria(joyeria);
					else
						handler.modificarJoyeria(joyeria);
					request.getSession().setAttribute("joyeria", joyeria);
					request.getSession().setAttribute("joyeria", joyeria);
					request.getSession().setAttribute("metodo", "modificado");
					return mapping.findForward("ok");
					
				}else{
					return mapping.findForward("ko");
				}
				
				
				
				
				
				
				
		} catch (DataBaseException e) {
				request.getSession().setAttribute("error", e.toString());
				return mapping.findForward("ko");
		}
		
		
		
		
		
		
	}
}
