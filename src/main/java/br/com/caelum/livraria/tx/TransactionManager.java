package br.com.caelum.livraria.tx;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

@Transactional
@Interceptor
public class TransactionManager implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -7786793463934136129L;
	@Inject
	private EntityManager em;

	@AroundInvoke 
	public Object executaTx(InvocationContext contexto) throws Exception {
		
		// abre transacao
		em.getTransaction().begin();

		Object resultado = contexto.proceed();

		// commita a transacao
		em.getTransaction().commit();
		
		return resultado;
	}

}
