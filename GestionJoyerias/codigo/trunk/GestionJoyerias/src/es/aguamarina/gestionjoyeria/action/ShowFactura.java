package es.aguamarina.gestionjoyeria.action;

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

public class ShowFactura extends GestionJoyeriasAction{
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		
		Logger log = Logger.getLogger(Constants.LOGGER_GESTION_JOYERIAS);
		
		try {
			
			
			HandlerReparaciones hr = (HandlerReparaciones) cxt.getBean("handlerReparaciones");
			FacturasDao hf = (FacturasDao) cxt.getBean("facturasDao");
			
			String aux = request.getParameter(Constants.PARAMETER_COD_FACTURA);
			Integer codFactura = Integer.valueOf(aux);
			
			List listadoReparaciones = hr.listadoReparacionesByFactura(codFactura);			
			Factura factura = hf.getFactura(codFactura);
			
						
			request.setAttribute("factura", factura);
			request.setAttribute(Constants.PARAMETER_LISTADO_REPARACIONES, listadoReparaciones);
			return mapping.findForward(Constants.MAPPING_OK);
			
		
		} catch (DataBaseException e) {
			log.error("Error al obtener los datos de las reparaciones.");
			request.getSession().setAttribute(Constants.PARAMETER_ERROR, e.toString());
			return mapping.findForward(Constants.MAPPING_KO); 
			
		}
	}
}
