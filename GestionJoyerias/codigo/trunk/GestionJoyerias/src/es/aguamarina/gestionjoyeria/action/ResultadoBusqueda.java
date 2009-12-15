package es.aguamarina.gestionjoyeria.action;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import es.aguamarina.gestionjoyeria.config.Constants;
import es.aguamarina.gestionjoyeria.persistence.criteria.ReparacionCriteria;
import es.aguamarina.gestionjoyeria.exception.DataBaseException;
import es.aguamarina.gestionjoyeria.persistence.dao.HandlerReparaciones;


/**
 * 
 * @author Daniel Gaspar Jimenez
 *
 */
public class ResultadoBusqueda extends GestionJoyeriasAction{
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
				
		try {
				HandlerReparaciones handler = (HandlerReparaciones) cxt.getBean("handlerReparaciones");
				String aux =null;
				
				Integer codJoyeria = Integer.valueOf(request.getParameter("codJoyeria"));
				String codSobre = request.getParameter("codSobre");
				String articulo = request.getParameter("articulo");
				String descripcion = request.getParameter("descripcion");
				String nombreCliente = request.getParameter("nombreCliente");
				
				/*Float precio = null;
				aux =request.getParameter("precio");
				if(aux != null && !aux.equals(""))
					precio = Float.valueOf(aux);
				Boolean reparado= false;
				if (request.getParameter("reparado") != null)
					reparado = true;
				Boolean entregado= false;
				if (request.getParameter("entregado") != null)
					entregado = true;
				Boolean facturado = false;
				if (request.getParameter("facturado") != null)
					facturado = true;
				
				Boolean presupuesto = false;
				if (request.getParameter("presupuesto") != null)
					presupuesto = true;
				
				DateFormat sdf = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY);
				aux = request.getParameter("fechaEntrada");
				Date fechaEntrada = null;
				if (aux != null && !aux.equals(""))
					fechaEntrada = sdf.parse(aux);
				
								
				
				aux = request.getParameter("fechaSalida");
				Date fechaSalida =null;
				if (aux != null && !aux.equals(""))
					fechaSalida = sdf.parse(aux);
				
				aux = request.getParameter("fechaFactura");
				Date fechaFactura =null;
				if (aux != null && !aux.equals(""))
					fechaFactura = sdf.parse(aux);
				
				
				
				*/
					
				ReparacionCriteria reparacion = new ReparacionCriteria();
							
				if(codJoyeria != null)
				{
					reparacion.setCodJoyeria(codJoyeria);				
					if (codSobre != null && !codSobre.equals(""))
						reparacion.setCodSobr(codSobre);
					if (articulo != null && !articulo.equals(""))
						reparacion.setArticulo(articulo);
					if (descripcion != null && !descripcion.equals(""))
						reparacion.setReparacion(descripcion);
					if (nombreCliente != null && !nombreCliente.equals(""))
						reparacion.setNombreCliente(nombreCliente);
					
					/*reparacion.setPrecio(precio);
					reparacion.setReparado(reparado);
					reparacion.setEntregado(entregado);
					reparacion.setFacturado(facturado);
					reparacion.setPresupuesto(presupuesto);
					
					reparacion.setFechaEntrada(fechaEntrada);
					reparacion.setFechaSalida(fechaSalida);
					reparacion.setFechaFactura(fechaFactura);
					
					
					reparacion.setFactura(null);
					*/
					
					ArrayList lista = handler.searchReparacionByCriteria(reparacion);
					
					request.setAttribute("listadoReparaciones", lista);
					return mapping.findForward(Constants.MAPPING_OK);
					
				}else{
					return mapping.findForward(Constants.MAPPING_KO);
				}
				
		} catch (DataBaseException e) {
				request.getSession().setAttribute("error", e.toString());
				return mapping.findForward(Constants.MAPPING_KO);
		} 
		
		
		
		
		
		
	}
}
