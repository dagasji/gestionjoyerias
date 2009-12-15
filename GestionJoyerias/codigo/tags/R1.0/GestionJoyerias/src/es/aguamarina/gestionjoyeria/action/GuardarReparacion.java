package es.aguamarina.gestionjoyeria.action;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import es.aguamarina.gestionjoyeria.config.Constants;
import es.aguamarina.gestionjoyeria.dto.Factura;
import es.aguamarina.gestionjoyeria.dto.Joyeria;
import es.aguamarina.gestionjoyeria.dto.Reparacion;
import es.aguamarina.gestionjoyeria.exception.DataBaseException;
import es.aguamarina.gestionjoyeria.persistence.dao.HandlerReparaciones;



/**
 * Action encargado de guardar una reparacion
 * @author Daniel Gaspar Jiménez
 * 
 */
public class GuardarReparacion extends GestionJoyeriasAction{
	
	/**
	 * Método execute() de Action
	 * @return <b>ok:</b> Reparacion guardada correctamente.</br>
	 * 		   <b>ko:</b> Error al guardar la reparacion.	
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		HttpSession session = request.getSession();
		
		try {
				HandlerReparaciones handler = (HandlerReparaciones) cxt.getBean("handlerReparaciones");
				String aux =null;
				
				Integer codJoyeria = Integer.valueOf(request.getParameter(Constants.PARAMETER_CODIGO_JOYERIA));
				
				Integer codReparacion = Integer.valueOf(request.getParameter("codReparacion"));
				
				String codSobre = request.getParameter("codSobre");
				
				String articulo = request.getParameter("articulo");
				
				String descripcion = request.getParameter("descripcion");
				
				Float precio = null;
				aux =request.getParameter("precio");
				if(aux != null && !aux.equals(""))
					precio = Float.valueOf(aux);
				
				Float costo = null;
				aux =request.getParameter("costo");
				if(aux != null && !aux.equals(""))
					costo = Float.valueOf(aux);
				
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
				
				aux = request.getParameter("tipoReparacion");
				Integer tipoReparacion = null;
				if (aux != null && !aux.equals(""))
					tipoReparacion = Integer.valueOf(aux);
				
				/*
				aux = request.getParameter("fechaFactura");
				Date fechaFactura =null;
				if (aux != null && !aux.equals(""))
					fechaFactura = sdf.parse(aux);
				*/				
				String nombreCliente = request.getParameter("nombreCliente");
				
				aux = request.getParameter("codFactura");
				Integer codFactura = null;
				if(aux != null && !aux.equals("") && !aux.equals("0"))
					codFactura = Integer.valueOf(aux);					
								
				Reparacion reparacion = new Reparacion();
				Factura factura = new Factura();
				
				if(codJoyeria != null && codReparacion != null && codSobre !=null)
				{
					reparacion.setJoyeria(new Joyeria(codJoyeria));
					reparacion.setCodReparacion(codReparacion);
					reparacion.setCodSobre(codSobre);
					reparacion.setArticulo(articulo);
					reparacion.setReparacion(descripcion);					
					reparacion.setPrecio(precio);
					reparacion.setCosto(costo);
					reparacion.setReparado(reparado);
					reparacion.setEntregado(entregado);
					reparacion.setFacturado(facturado);
					reparacion.setPresupuesto(presupuesto);					
					reparacion.setFechaEntrada(fechaEntrada);
					reparacion.setFechaSalida(fechaSalida);
					//reparacion.setFechaFactura(fechaFactura);
					reparacion.setNombreCliente(nombreCliente);
					reparacion.setTipoReparacion(tipoReparacion);
					if(facturado){
						factura.setCodFactura(codFactura);
						//factura.setFechaFactura(fechaFactura);
						factura.setFechaFactura(new Date());
						factura.setNombreCliente(reparacion.getNombreCliente());
						reparacion.setFactura(factura);	
					}
						
					
					
					if(request.getParameter(Constants.PARAMETER_METODO).equals("nueva"))
						handler.insertarReparacion(reparacion);
					else
						handler.updateReparacion(reparacion);
					request.setAttribute(Constants.PARAMETER_NOW, new Date());
					session.setAttribute(Constants.PARAMETER_REPARACION, reparacion);
					request.setAttribute(Constants.PARAMETER_METODO, Constants.PARAMETER_MODIFICADO_OK);					
					return mapping.findForward(Constants.MAPPING_OK);
					
				}else{
					return mapping.findForward(Constants.MAPPING_KO);
				}
				
		} catch (DataBaseException e) {
				request.setAttribute(Constants.PARAMETER_ERROR, e.toString());
				return mapping.findForward(Constants.MAPPING_KO);
		} catch (ParseException e) {
			request.getSession().setAttribute(Constants.PARAMETER_ERROR, "El formato de la fecha introducida no es correcto");
			return mapping.findForward(Constants.MAPPING_KO);
		}		
		
	}
}
