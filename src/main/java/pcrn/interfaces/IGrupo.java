package pcrn.interfaces;

import java.util.List;

import pcrn.model.Grupo;


/** Esta � uma Interface que possui as assinaturas dos metodos da classe Grupo,
*   
* @author silas
* @since 15-08-2016
*/

public interface IGrupo {

	/** Este metodo lista todos os grupos cadastrados.
	*   
	* @return retorna a lista dos grupos cadastrados.
	*   	
	*/
	public List<Grupo> listar();
	
	/** Este metodo pesquisa um grupo por seu id.
	*  	
	*  @param codigo, Este codigo � o id do Grupo que voc� est� procurando.
	*  @return retorna o Grupo daquele id que voc� est� pesquisando.
	*   	
	*/
	public Grupo porCodigo(Integer codigo);
	
	/** Este metodo cria ou altera um grupo.
	*  	
	*  @param grupo, Este grupo � o objeto Grupo que voc� ir� criar ou modificar.
	*   	
	*/
	public void salvar(Grupo grupo);
	
	/** Este metodo Remove um grupo.
	*  	
	*  @param grupo, Esta grupo � o objeto Grupo que voc� ir� remover.
	*   	
	*/
	public void remover(Grupo grupo);
}