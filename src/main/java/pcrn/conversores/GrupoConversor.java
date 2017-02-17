package pcrn.conversores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import pcrn.model.Grupo;
import pcrn.repository.Grupos;
import pcrn.util.cdi.CDIServiceLocator;


@FacesConverter(value="grupoConverter")
public class GrupoConversor implements Converter{

	private Grupos grupos;

	public GrupoConversor() {
		grupos = CDIServiceLocator.getBean(Grupos.class);
	}

	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Grupo retorno = null;			

		if (value != null) {
			retorno = grupos.porCodigo(new Integer(value));
		}

		return retorno;
	}


	public String getAsString(FacesContext context, UIComponent component, Object value) {

		if (value != null) {
			Grupo grupo = (Grupo) value;
			return grupo.getCodigo() == null ? null : grupo.getCodigo().toString();

		}

		return null;
	}

}