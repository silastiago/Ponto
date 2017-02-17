package pcrn.conversores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import pcrn.model.Pessoa;
import pcrn.repository.Pessoas;
import pcrn.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Pessoa.class)
public class PessoaConversor implements Converter{

	private Pessoas pessoas;

	public PessoaConversor() {
		pessoas = CDIServiceLocator.getBean(Pessoas.class);
	}
	
	
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Pessoa retorno = null;
			
		if (value != null) {
			retorno = pessoas.porCodigo(new Integer(value));
		}

		return retorno;
	}

	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if (value != null) {
			Pessoa pessoa = (Pessoa) value;
			return pessoa.getCodigo() == null ? null : pessoa.getCodigo().toString();

		}

		return null;
	}

}