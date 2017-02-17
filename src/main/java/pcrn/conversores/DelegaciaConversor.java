package pcrn.conversores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import pcrn.model.Delegacia;
import pcrn.repository.Delegacias;
import pcrn.util.cdi.CDIServiceLocator;




@FacesConverter(forClass = Delegacia.class)
public class DelegaciaConversor implements Converter{

	private Delegacias delegacias;


	public DelegaciaConversor() {
		delegacias = CDIServiceLocator.getBean(Delegacias.class);
	}

	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Delegacia retorno = null;


		if (value != null) {
			retorno = delegacias.porCodigo(new Integer(value));

		}

		return retorno;
	}


	public String getAsString(FacesContext context, UIComponent component, Object value) {

		if (value != null) {
			Delegacia delegacia = (Delegacia) value;
			
			return delegacia.getCodigo() == null ? null : delegacia.getCodigo().toString();

		}

		return null;
	}

}
