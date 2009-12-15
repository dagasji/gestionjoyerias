package es.aguamarina.gestionjoyeria.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import es.aguamarina.gestionjoyeria.config.Constants;
import es.aguamarina.gestionjoyeria.dto.Factura;
import es.aguamarina.gestionjoyeria.exception.DataBaseException;
import es.aguamarina.gestionjoyeria.persistence.dao.FacturasDao;
import es.aguamarina.gestionjoyeria.persistence.dao.HandlerReparaciones;

/**
 * Action encargado de facturar
 * @author Daniel Gaspar Jiménez
 * 
 */
public class Facturar extends GestionJoyeriasAction{
	
	/**
	 * Método execute() de Action
	 * @return <b>ok:</b> Factura creada correctamente.</br>
	 * 		   <b>ko:</b> Error al crear la factura.	
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		
		Logger log = Logger.getLogger(Constants.LOGGER_GESTION_JOYERIAS);
		String ACTION_NAME = this.getClass().getSimpleName();
		
		Factura factura = null;
		HandlerReparaciones hr;
		FacturasDao hf;
		
		try {
			
			log.info("Entrada execute del Action " + ACTION_NAME);
			
			String[] arrayAux = request.getParameterValues("listado");
			
			if(arrayAux != null && arrayAux.length != 0){
			
				//Obtenemos el HandlerReparaciones
				hr = (HandlerReparaciones) cxt.getBean("handlerReparaciones");
				//Obtenemos el HandlerFacturas
				hf = (FacturasDao) cxt.getBean("facturasDao");
				
				//Obtenemos el listado de reparaciones a facturar			
				Integer[] codReparaciones = new Integer[arrayAux.length];
				for (int i=0; i<arrayAux.length; i++)
					codReparaciones[i] = Integer.valueOf(arrayAux[i]);
				
				String confirmar = request.getParameter("confirmar");
				if(confirmar != null && confirmar.equals("ok")){
					String nombreCliente = request.getParameter(Constants.PARAMETER_NOMBRE_CLIENTE);
					factura = new Factura();
					factura.setFechaFactura(new Date());
					factura.setNombreCliente(nombreCliente);
					factura = hf.facturar(codReparaciones, factura);
				}
				
				//Obtenemos de base de datos los datos de las reparaciones
				List listadoReparaciones = hr.listadoReparaciones(codReparaciones);			
				
				log.info("listado reparaciones ok");
				
				request.setAttribute("factura", factura);
				request.setAttribute("listadoReparaciones", listadoReparaciones);
				
				return mapping.findForward(Constants.MAPPING_OK);
			
			}else{
				
				request.setAttribute(Constants.PARAMETER_ERROR, "No se ha indicado ninguna reparación para facturar.");
				return mapping.findForward(Constants.MAPPING_KO);
				
			}
				
		
		} catch (DataBaseException e) {
			
			log.error("Error al obtener los datos de las reparaciones.", e);
			request.setAttribute(Constants.PARAMETER_ERROR, e.toString());
			return mapping.findForward(Constants.MAPPING_KO); 
			
		}finally{
			log.info("Salida execute del Action " + ACTION_NAME);
		}
	}
}
