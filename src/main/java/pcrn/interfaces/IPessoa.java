package pcrn.interfaces;

import java.util.List;

import pcrn.model.Pessoa;

/** Esta � uma Interface que possui as assinaturas dos metodos da classe Pessoa,
*   
* @author silas
* @since 15-08-2016
*/
public interface IPessoa {
	
	/** Este metodo faz o login do usuario no sistema.
	 * 	Este metodo ainda n�o est� funcionando por alguns bugs.
	*   por enquanto a classe que faz login � a LoginBean, Mas futuramente Esta classe ser� a classe de login definitiva.
	*   
	*   @param pessoa, Esta pessoa � o objeto Pessoa que ir� fazer o login.
	* 	@return boolean, retorna verdadeiro se a pessoa fez o login correto caso contrario retorna false.
	* 	
	*   	
	*/
	public boolean login(Pessoa pessoa);
	
	/** Este metodo faz o logout do usuario no sistema.
	* 	Este metodo ainda n�o est� funcionando por alguns bugs.
	*   	
	*/
	public void logout();
	
	/** Este metodo lista todas as pessoas cadastradas
	*   
	* @return retorna a lista das pessoas cadastradas.
	*   	
	*/
	public List<Pessoa> listar();
	
	/** Este metodo pesquisa uma pessoa por seu id.
	*  	
	*  @param codigo, Este codigo � o id da pessoa que voc� est� procurando.
	*  @return retorna a Pessoa daquele id que voc� est� pesquisando.
	*   	
	*/
	public Pessoa porCodigo(Integer codigo);
	
	
	
	public Pessoa porLogin(String login);
	
	/** Este metodo cria ou altera uma pessoa.
	*  	
	*  @param pessoa, Esta pessoa � o objeto Pessoa que voc� ir� criar ou modificar.
	*   	
	*/
	public void salvar(Pessoa pessoa);
	
	/** Este metodo Remove uma Pessoa.
	*  	
	*  @param pessoa, Esta pessoa � o objeto Pessoa que voc� ir� remover.
	*   	
	*/
	public void remover(Pessoa pessoa);

}