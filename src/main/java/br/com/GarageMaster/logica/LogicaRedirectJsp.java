package br.com.GarageMaster.logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Kógica usada para os daos que retornam as lista das entidades
//Retornam a lista e redireciona para páginas informadas
//Como parâmetro no método execute (String redirect)
public interface LogicaRedirectJsp {
	void executa(HttpServletRequest req, HttpServletResponse res, String redirect) throws Exception;
}